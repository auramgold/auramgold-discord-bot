/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Makes the bot be something.
 * @author Lauren Smith
 */
public class Be extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Be(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(ArrayList<String> what) 
	{
		return "```maid!be [something](...))\n"
				+ "	Makes the maid be [something]\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation() 
	{
		return "Makes me be something.";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		String item = String.join(" ",params);
		item = AuramgoldDiscordBot.replacePronouns(item);
		return "*is "+item+"*";
	}
}
