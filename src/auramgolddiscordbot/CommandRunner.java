/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import auramgolddiscordbot.commands.CommandGroup;
import java.util.Collections;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * This class processes running commands
 * @author Lauren Smith
 */
public class CommandRunner
{

	/**
	 * The command group to check commands from
	 */
	public static CommandGroup commands = new CommandGroup();
	
	/**
	 * Runs a given command.
	 * @param command The input command, exploded on spaces
	 * @param who The user who sent the command
	 * @param chan The channel it was sent in.
	 * @return What text to return to the user.
	 */
	public static String runCommand(String[] command, RefUser who, MessageReceivedEvent event)
	{
		MessageChannel chan = event.getChannel();
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
			case "clear":
				return "Clearing: \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
						+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
						+ ".";
			case "tableflip":
				return "I'm sorry, "+who.getHonorific()+", "+
							"but I can't flip tables. I clean them.";
			default:
				return commands.runCommand(command[0], args, who, event);
		}
	}
	
}
