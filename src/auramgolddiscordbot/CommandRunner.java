/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import auramgolddiscordbot.commands.CommandGroup;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 *
 * @author Lauren Smith
 */
public class CommandRunner
{
	public static CommandGroup commands = new CommandGroup();
	
	public static String runCommand(String[] command,RefUser who, MessageChannel chan)
	{
		String[] args = AuramgoldDiscordBot.cutOffFirst(command);
		switch(command[0])
		{
			case "ping":
				return "Pong!";
			case "breathe":
				return "https://www.youtube.com/watch?v=C1STpYAFxJk&t=2s";
			case "clean":
				if(args.length == 0)
				{
					return "*cleans up #"+chan.getName()+"*";
				}
				else
				{
					return "*cleans up "+String.join(" ",args)+"*";
				}
			case "source":
			case "code":
			case "sourcecode":
			case "github":
				return "I am an open source bot, with my source code available"
						+ " here: "
						+ "https://github.com/auramgold/auramgold-discord-bot";
			case "tableflip":
				return "I'm sorry, "+who.getHonorific()+", "+
							"but I can't flip tables. I clean them.";
			default:
				return commands.runCommand(command[0], args, who);
		}
	}
	
}
