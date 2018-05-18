/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.Constants;
import auramgolddiscordbot.PersonalReference;
import auramgolddiscordbot.PronounData;
import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import java.util.regex.Matcher;
import java.util.ArrayList;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Sets a settable
 * @author auramgold
 */
public class Set extends BotCommand implements Documentable
{
	String validPronouns = "";
	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Set(String... alias)
	{
		super(alias);
		for(PronounData pro: PronounData.values())
		{
			if(!validPronouns.equals(""))
			{
				validPronouns += ",";
			}
			validPronouns += pro.subject + "/" + pro.object;
		}
	}
	
	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		
		return "```maid!set [settable] ...\n"
				+ "================\n"
				+ "	maid!set gender/sex/pronoun/pronouns [value]\n"
				+ "		[value] can be:\n"
				+ "		" + validPronouns + "\n"
				+ "		(If you want a pronoun that is not in that list, dm\n"
				+ "		Lauren with that request)\n"
				+ "		Defaults to they/them when [value] is not recognized\n"
				+ "================\n"
				+ "	maid!set name [name](...)\n"
				+ "		Where [name] preserves spaces\n"
				+ "================\n"
				+ "	maid!set zapsex/defaultzap [value]\n"
				+ "		Where [value] is male or female\n"
				+ "		Affects the default override sex given from maid!zap\n"
				+ "================\n"
				+ "	maid!set zappable/zappability/canzap [value]\n"
				+ "		Turns on or off the ability to be zapped by maid!zap"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Sets a settable value";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		if(params.isEmpty() || params.get(0).equals("help"))
		{
			return getDocumentation(params);
		}
		User person = who;
		String poss = "your";
		if(params.size() >= 3 && who.getId().equals(Constants.coderId))
		{
			Matcher mat = AuramgoldDiscordBot.userExtract.matcher(params.get(params.size()-1));
			if(mat.matches())
			{
				String otherId = mat.group(1);
				RefList.getReference(otherId);
				person = AuramgoldDiscordBot.api.getUserById(otherId);
				poss = person.getName() + "'s";
			}
		}
		PersonalReference pers = RefList.getReference(person.getId());
		switch(params.get(0))
		{
			case "gender":
			case "sex":
			case "pronoun":
			case "pronouns":
				if(params.size() >=2)
				{
					int genderIndex = PersonalReference.getIndexOfString
						(params.size() > 1 ? params.get(1) : "they");
					pers.setPronouns(genderIndex);
					RefList.referenceList.put(person.getId(),pers);
					RefList.updateFile();
					return "Set " + poss + " gender pronouns to "
							+ pers.getPronouns().subject + "/"
							+ pers.getPronouns().object;
				}
				else
				{
					return "I can refer to people by these pronouns, "
							+ who.getHonorific() + ":"
							+ "```" + validPronouns.replace(",", "\r\n") + "```";
				}
			case "name":
				ArrayList<String> toJoin = (ArrayList<String>)params.clone();
				toJoin.remove(0);
				if(!person.getId().equals(who.getId()))
				{
					toJoin.remove(toJoin.size() - 1);
				}
				pers.setName(String.join(" ", toJoin));
				RefList.referenceList.put(person.getId(), pers);
				RefList.updateFile();
				return "Set " + poss + " name to \"" + pers.getName() + "\".";
			case "defaultzap":
			case "zapsex":
				MorphSex morphSex;
				switch(params.get(1))
				{
					case "m":
					case "male":
					case "boy":
					case "man":
					case "1":
						morphSex = MorphSex.MALE;
						break;
					case "f":
					case "female":
					case "girl":
					case "lady":
					case "woman":
					case "2":
						morphSex = MorphSex.FEMALE;
						break;
					default:
						morphSex = MorphSex.NONE;
						break;
				}
				pers.setOverrideSex(morphSex);
				RefList.referenceList.put(person.getId(), pers);
				RefList.updateFile();
				return "Set " + poss + " default morph zap sex to "
						+ morphSex.name().toLowerCase() + ".";
			case "zappable":
			case "canzap":
			case "zappability":
				boolean canZap;
				switch(params.get(1))
				{
					case "no":
					case "false":
					case "dont":
					case "don't":
					case "off":
					case "disable":
						canZap = false;
						break;
					default:
						canZap = true;
				}
				pers.acceptsZaps = canZap;
				RefList.referenceList.put(person.getId(), pers);
				RefList.updateFile();
				return "Set " + poss + " zappability to " + pers.zappableString() + ".";
			default:
				return "Error: " + params.get(0) + " is not a settable value.";
		}
	}
	
}
