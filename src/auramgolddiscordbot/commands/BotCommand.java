/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


/**
 *
 * @author auramgold
 */
public abstract class BotCommand
{

	/**
	 *
	 */
	public HashSet<String> aliases;
	
	/**
	 * Constructs with given aliases
	 * @param alias The given aliases
	 */
	public BotCommand(String... alias)
	{
		this.aliases = new HashSet<>();
		this.aliases.addAll(Arrays.asList(alias));
	}
	
	/**
	 * Checks whether or not a command has a given alias
	 * @param what The string alias to check
	 * @return A boolean of whether or not the alias is there
	 */
	public boolean checkAlias(String what)
	{
		return this.aliases.contains(what);
	}
	
	/**
	 * Executes the command
	 * @param command The command that is being run
	 * @param params The space-exploded list of parameters
	 * @param who The User who is running the command
	 * @param event the value of event
	 * @return the java.lang.String
	 */
	public abstract String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event);
}
