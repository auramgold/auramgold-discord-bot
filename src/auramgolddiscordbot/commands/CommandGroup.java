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

/**
 *
 * @author Lauren Smith
 */
public class CommandGroup
{
	public static ArrayList<BotCommand> commands = new ArrayList<>();
	public static HashSet<String> aliases = new HashSet<>();
	
	public CommandGroup()
	{
		try
		{
			addCommand(new Help("help","list"));
			addCommand(new Get("get"));
			addCommand(new Set("set"));
			addCommand(new Meme("meme"));
			addCommand(new Say("say"));
			addCommand(new Hug("hug","glomp"));
			addCommand(new Punish("hit","whip","punish","smack","slap"));
			addCommand(new Reward("praise","reward","thank"));
			addCommand(new Fetch("fetch","fetchme","getme"));
			addCommand(new Trash("trash","disposeof","destroy","getridof"));
			addCommand(new Be("be"));
			addCommand(new Zap("zap","pew"));
			addCommand(new Outfit("dress","outfit","uniform"));
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
	
	public String runCommand(String comm,String[] args,RefUser who)
	{
		for(BotCommand command: commands)
		{
			if(command.checkAlias(comm))
			{
				return command.run(comm, args, who);
			}
		}
		return Math.random()>0.5?
				"I don't know how to "+comm+", "+who.getHonorific()+".":
				"I'm sorry, "+who.getHonorific()+", but I'm not sure I understood.";
	}
}
