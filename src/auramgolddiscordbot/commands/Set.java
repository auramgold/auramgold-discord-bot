/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.PersonalReference;
import auramgolddiscordbot.PronounRef;
import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.entities.User;

/**
 * Sets a settable
 * @author Lauren Smith
 */
public class Set extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Set(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what)
	{
		String validPronouns = "";
		for(PronounRef pro: PersonalReference.gender)
		{
			if(!validPronouns.equals(""))
			{
				validPronouns += ",";
			}
			validPronouns += pro.subject + "/" + pro.object;
		}
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
				+ "		Where [name] preserves spaces"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Sets a settable value";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		User person = who;
		String poss = "your";
		if(params.length >= 3 && who.getId().equals("242391558664486913"))
		{
			Matcher mat = AuramgoldDiscordBot.userExtract.matcher(params[params.length-1]);
			if(mat.matches())
			{
				String otherId = mat.group(1);
				RefList.getReference(otherId);
				person = AuramgoldDiscordBot.api.getUserById(otherId);
				poss = person.getName() + "'s";
			}
		}
		PersonalReference pers = RefList.getReference(person.getId());
		switch(params[0])
		{
			case "gender":
			case "sex":
			case "pronoun":
			case "pronouns":
				if(params.length >=2)
				{
					int genderIndex = PersonalReference.getIndexOfString
						(params.length > 1 ? params[1] : "they");
					pers.setPronouns(genderIndex);
					RefList.referenceList.put(person.getId(),pers);
					RefList.updateFile();
					return "Set " + poss + " gender pronouns to "
							+ PersonalReference.gender[genderIndex].subject + "/"
							+ PersonalReference.gender[genderIndex].object;
				}
				else
				{
					return "I can refer to people by these pronouns, "
							+ who.getHonorific() + ":"
							+ "```"
							+ "\nThey/them"
							+ "\nHe/him"
							+ "\nShe/her"
							+ "\nZe/zir"
							+ "\nXe/xem"
							+ "\nEy/em"
							+ "\nZe/hir```";
				}
			case "name":
				String[] toJoin = AuramgoldDiscordBot.cutOffFirst(params);
				if(!person.getId().equals(who.getId()))
				{
					toJoin = AuramgoldDiscordBot.cutOffLast(toJoin);
				}
				pers.setName(String.join(" ", toJoin));
				RefList.referenceList.put(person.getId(), pers);
				RefList.updateFile();
				return "Set " + poss + " name to \"" + pers.getName() + "\".";
			default:
				return "Error: " + params[0] + " is not a settable value.";
		}
	}
	
}
