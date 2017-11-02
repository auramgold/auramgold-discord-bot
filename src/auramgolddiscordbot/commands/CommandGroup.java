/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 *
 * @author Lauren Smith
 */
public class CommandGroup
{

	/**
	 * The ArrayList of commands.
	 */
	public static ArrayList<BotCommand> commands = new ArrayList<>();

	/**
	 * The list of aliases that are used.
	 */
	public static HashSet<String> aliases = new HashSet<>();
	
	/**
	 * Constructs the command group.
	 */
	public CommandGroup()
	{
		try
		{
			addCommand(new Help("help", "list"));
			addCommand(new Get("get"));
			addCommand(new Set("set"));
			addCommand(new Meme("meme"));
			addCommand(new Say("say"));
			addCommand(new Hug("hug", "glomp"));
			addCommand(new Punish("hit", "whip", "punish", "smack", "slap", "bap"));
			addCommand(new Reward("praise", "reward", "thank"));
			addCommand(new Fetch("fetch", "fetchme", "getme"));
			addCommand(new Trash("trash", "disposeof", "destroy", "getridof","begonewith"));
			addCommand(new Be("be"));
			addCommand(new Zap("zap", "pew"));
			addCommand(new Outfit("dress", "outfit", "uniform"));
		}
		catch (Exception ex)
		{
			Logger.getLogger(CommandGroup.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void addCommand(BotCommand comm) throws NoSuchMethodException, InstantiationException
	{
		aliases.addAll(comm.aliases);
		commands.add(comm);
	}
	
	/**
	 * Runs the given command
	 * @param comm The command to be run
	 * @param args The space-exploded arguments array
	 * @param who The User who called the command
	 * @param event the value of event
	 * @return the java.lang.String
	 */
	public String runCommand(String comm, ArrayList<String> args, RefUser who, MessageReceivedEvent event)
	{
		for(BotCommand command: commands)
		{
			if(command.checkAlias(comm))
			{
				return command.run(comm, args, who, event);
			}
		}
		return Math.random() > 0.5
			? "I don't know how to " + comm + ", " + who.getHonorific() + "."
			: "I'm sorry, " + who.getHonorific() + ", but I'm not sure I understood.";
	}
}
