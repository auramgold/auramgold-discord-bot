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
public class Trash extends BotCommand implements Documentable
{
		public Trash(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what) 
	{
		return "```maid!trash [item](...)\n"
				+ "	Gets rid of [item]\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation() 
	{
		return "Gets rid of something.";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		String item = String.join(" ",params);
		item = AuramgoldDiscordBot.replacePronouns(item);
		return "*gets rid of "+item+"*\nDon't worry, "+
				who.getHonorific()+", I got that out of "
				+ "your way.";
	}
}
