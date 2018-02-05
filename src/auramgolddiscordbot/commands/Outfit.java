/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.Constants;
import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Dresses a user in a maid outfit.
 * @author auramgold
 */
public class Outfit extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Outfit(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what) 
	{
		return "```maid!" + what.get(0) + " (usermention)\n"
				+ "	Puts (usermention) in a maid outfit\n"
				+ " If not included, defaults to using executing user."
				+ "```";
	}

	@Override
	public String getShortDocumentation() 
	{
		return "Puts someone in a maid outfit.";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		String person;
		if(params.isEmpty() || params.get(0).equals("me"))
		{
			person = who.getId();
		}
		else
		{
			person = params.get(0);
		}

		Matcher mat = AuramgoldDiscordBot.userExtract.matcher(person);
		String otherId;
		if(mat.matches())
		{
			otherId = mat.group(1);
			if(!otherId.equals(Constants.botId))
			{
				return "*dresses " + "<@!" + otherId + ">" + " in a maid uniform*";
			}
			else
			{
				return "I'm sorry, " + who.getHonorific() + ", "
						+ "but I'm already in a maid outfit.";
			}
		}
		else
		{
			return "I can only put a user in a maid outfit, "
					+ who.getHonorific() + ".";
		}
	}
}
