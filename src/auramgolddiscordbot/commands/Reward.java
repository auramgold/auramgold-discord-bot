/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author Lauren Smith
 */
public class Reward extends BotCommand implements Documentable
{
	
	public Reward(String... aliases)
	{
		super(aliases);
	}

	@Override
	public String getDocumentation(String[] what)
	{
		return "`maid!"+what[0]+"` has no extra parameters.";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Rewards the maid for her good behavior.";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		RefList.getReference(who.getId()).netSpoil++;
		RefList.updateFile();
		if(command.toLowerCase().equals("thank"))
		{
			return "You are very welcome, "+who.getHonorific()+".";
		}
		else
		{
			return "Thank you, "+who.getHonorific()+".";
		}
	}
	
}
