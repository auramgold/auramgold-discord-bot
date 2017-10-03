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
public class Punish extends BotCommand implements Documentable
{
	
	public Punish(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what)
	{
		return "`maid!" + what[0] + "` has no extra parameters.";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Punishes the maid's bad behavior";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		RefList.getReference(who.getId()).netSpoil--;
		RefList.updateFile();
		return "Oww... I'll try to behave better, "
				+ who.getHonorific() + ".";
	}
	
}
