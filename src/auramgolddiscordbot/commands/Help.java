/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import static auramgolddiscordbot.commands.CommandGroup.commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Gets info on commands and/or a given command.
 * @author auramgold
 */
public class Help extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Help(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!" + what.get(0) + " [command]\n"
				+ "	Shows detailed options of [command].\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Gets info about commands.";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		if(params.isEmpty())
		{
			String ret = "List of commands:";
			for(BotCommand com: CommandGroup.commands)
			{
				if(com instanceof Documentable)
				{
					Documentable doccom = (Documentable)com;
					if(!doccom.getShortDocumentation().equals(""))
					{
						String addition = "\n";
						for(String alia: com.aliases)
						{
							addition += "`maid!" + alia + "`,";
						}
						addition = addition.substring(0,addition.length()-1)+":	";
						addition += doccom.getShortDocumentation();
						ret += addition;
					}
				}
			}
			return ret;
		}
		else
		{
			for(BotCommand com: commands)
			{
				if(com instanceof Documentable)
				{
					Documentable doccom = (Documentable)com;
					if(com.checkAlias(params.get(0)))
					{
						return doccom.getDocumentation(params);
					}
				}
			}
			return "I am sorry, " + who.getHonorific() + ", but "
					+ params.get(0) + " is not a valid documentable command.";
		}
	}
	
}
