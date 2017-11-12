/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.RefList;
import java.util.ArrayList;
import auramgolddiscordbot.RefUser;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Hugs a user
 * @author Lauren Smith
 */
public class Hug extends BotCommand implements Documentable
{
	
	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Hug(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!" + what.get(0) + " (usermention)OR(text)(...)\n"
				+ "	Hugs (usermention), which is optional and can be\n"
				+ "	Either an @mention or their user ID number\n"
				+ "	If plain text is used, applies hug to plain text\n"
				+ "	If not put, defaults to using executing user"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Hugs a person.";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		if(params.isEmpty())
		{
			return "*" + command + "s " + who.getPronouns().title + " "
					+ who.getAuramName() + ".*";
		}
		else
		{
			Matcher mat = AuramgoldDiscordBot.userExtract.matcher(params.get(0));
			if(mat.matches())
			{
				String otherId = mat.group(1);
				if(!otherId.equals("342757470046781450"))
				{
					String title = RefList.getPronounRef(otherId).title;
					return "*" + command + "s " 
							+ "<@!" + otherId + ">" + ".*";
				}
				else
				{
					return "I'm sorry, "
							+ who.getHonorific() + ", "
							+ "but I can't " + command + " myself.";
				}
			}
			else
			{
				return "*" + command + "s "
						+ String.join(" ", params) + ".*";
			}
		}
	}
	
}
