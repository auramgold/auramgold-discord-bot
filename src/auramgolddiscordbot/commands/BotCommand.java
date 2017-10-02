/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefUser;
import java.util.Arrays;
import java.util.HashSet;


/**
 *
 * @author Lauren Smith
 */
public abstract class BotCommand
{
	public HashSet<String> aliases;
	
	public BotCommand(String... alias)
	{
		this.aliases = new HashSet<>();
		this.aliases.addAll(Arrays.asList(alias));
	}
	
	public boolean checkAlias(String what)
	{
		return this.aliases.contains(what);
	}
	
	public abstract String run(String command, String[] params, RefUser who);
}
