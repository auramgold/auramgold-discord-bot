/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.RefUser;

/**
 *
 * @author Lauren Smith
 */
public class Fetch extends BotCommand implements Documentable
{
	public Fetch(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what) 
	{
		return "```maid!fetch [item](...))\n"
				+ "	Brings [item] to you\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation() 
	{
		return "Brings you something.";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		String item = String.join(" ", params);
		item = AuramgoldDiscordBot.replacePronouns(item);
		return "*brings you " + item + "*\nHere you go, "
				+ who.getHonorific() + ".";
	}
	
}
