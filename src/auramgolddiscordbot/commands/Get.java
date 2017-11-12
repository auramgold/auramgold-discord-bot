/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.PronounRef;
import java.util.ArrayList;
import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Gets the value of a settable.
 * @author Lauren Smith
 */
public class Get extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Get(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!get [settable] [usermention]\n"
				+ "	Where [settable] is gender/sex/pronouns/pronoun/name/form/morph/appearance\n"
				+ "	And [usermention] is either an @ mention of a user\n"
				+ "	Or their user ID that you can retrieve from developer mode\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Gets a settable value";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		if(!params.isEmpty())
		{
			String extr;
			if(params.size() == 1)
			{
				extr = "me";
			}
			else
			{
				extr = params.get(1);
			}
			Matcher mat = AuramgoldDiscordBot.userExtract.matcher(extr);
			if(mat.matches() || extr.equals("me"))
			{
				String otherId;
				RefUser otherRef;
				if(mat.matches())
				{
					otherId = mat.group(1);
					otherRef = new RefUser(Long.parseLong(otherId), (JDAImpl)AuramgoldDiscordBot.api);
				}
				else
				{
					otherId = who.getId();
					otherRef = who;
				}
				RefList.getReference(otherId);
				String otherUserName = otherRef.getName();
				PronounRef gend = RefList.getPronounRef(otherId);
				boolean isPlural = otherUserName.equals("they");
				String ret;
				switch(params.get(0))
				{
					case "gender":
					case "sex":
					case "pronoun":
					case "pronouns":
						ret = otherUserName + " use" + (isPlural ? "" : "s") 
								+ " " + gend.subject
								+ "/" + gend.object + " pronouns, "
								+ who.getHonorific() + ".";
						break;
						
					case "name":
						String setNam = RefList.getName(otherId);
						if(!setNam.equals(gend.honorific))
						{
							ret = otherUserName +" ha" + (isPlural ? "ve" : "s")
									+ " me call " + who.getPronouns().object + " "
									+ setNam + ", " + who.getHonorific() + ".";
						}
						else
						{
							ret = otherUserName + " ha" + (isPlural ? "ve" : "s")
									+ " no set name with me"
									+ ", " + who.getHonorific() + ".";
						}
						break;
					case "form":
					case "morph":
					case "appearance":
						String namer = RefList.getName(otherId);
						String finName = 
							(
								namer == null
								|| namer.length()==0
								|| namer.equals(otherRef.getHonorific())
							)
							? otherUserName : namer;
						ret = finName + " " + (finName.equals("they") ? "are" : "is")
									+ " currently in " + otherRef.getMorph()
									+ ", " + who.getHonorific() + ".";
						break;
					default:
						return "Error: " + params.get(0) + " is not a gettable value.";
				}
				return AuramgoldDiscordBot.capitalizeFirstLetter(ret);
			}
			else
			{
				return "Error: Could not find valid user id string.";
			}
		}
		else
		{
			return "Error: Not enough arguments.";
		}
	}
	
}
