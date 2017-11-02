/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import java.util.ArrayList;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Punishes the bot
 * @author Lauren Smith
 */
public class Punish extends BotCommand implements Documentable
{
	
	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
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
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		RefList.getReference(who.getId()).netSpoil--;
		RefList.updateFile();
		return "Oww... I'll try to behave better, "
				+ who.getHonorific() + ".";
	}
	
}
