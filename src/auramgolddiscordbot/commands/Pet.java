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
 *
 * @author Lauren Smith
 */
public class Pet extends BotCommand implements Documentable
{
	public Pet(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!" + what.get(0) + " [usermention]\n"
			+ "	Where [usermention] is the user you want to pet."
			+ "		If not present, defaults to calling user.```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Pets a user";
	}
	
	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		String targetId = "";
		if(params.isEmpty())
		{
			targetId = who.getId();
		}
		else
		{
			for(String curr: params)
			{
				Matcher mat = AuramgoldDiscordBot.mentionExtract.matcher(curr);
				if(mat.matches())
				{
					targetId = mat.group(1);
					break;
				}
			}
			
		}
		
		if(targetId.isEmpty())
		{
			return "I'm sorry, " + who.getHonorific() + ", but I couldn't"
				+ " find a user in there.";
		}
		else if(targetId.equals(Constants.botId))
		{
			return "I cannot pet myself, " + who.getHonorific() + "!";
		}
		else
		{
			return "*pets <@"+targetId+">*";
		}
	}
	
}
