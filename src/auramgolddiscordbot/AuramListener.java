/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * This class listens for events and handles them.
 * @author auramgold
 */
public class AuramListener extends ListenerAdapter
{

	/**
	 * What to do when a text message event is received
	 * @param event The received event
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
		User messSender = event.getAuthor();
		String content = message.getRawContent();
		MessageChannel channel = event.getChannel();
		String sender = messSender.getName() + "#"
						+ messSender.getDiscriminator();
		String channelStr = channel.getName();
		String recTime = "[" + AuramgoldDiscordBot.getTimeString() + "]";
		System.out.println(recTime + " " + sender 
							+ " in " + channelStr + ":" + content);
		if (event.getAuthor().isBot()) return;
		// We don't want to respond to other bot accounts, including ourself
		String cmdStart = content.substring(0, Math.min(AuramgoldDiscordBot.comStartLen,content.length()));
		if(cmdStart.equals(AuramgoldDiscordBot.commandStart))
		{
			String subCom = content.substring(AuramgoldDiscordBot.comStartLen);
			ArrayList<String> splitCommand = new ArrayList<>
				(
					Arrays.asList(subCom.split("(?U)\\s+"))
					//the U is for unicode space characters
				);
			String ret = CommandRunner.runCommand(splitCommand, new RefUser(messSender), event);
			channel.sendMessage(ret).queue();
		}
		else if(content.contains("(╯°□°）╯︵ ┻━┻")
				&&	(
					Math.random()<0.1
					||messSender.getId().equals(Constants.coderId)
					))
		{
			channel.sendMessage("┬─┬﻿ ノ( ゜-゜ノ)").queue();
		}
		else if((content.contains("thank")||content.contains("thanks")||content.contains("Thank")||content.contains("Thanks"))
				&&	(
					Math.random()<0.025
					||messSender.getId().equals(Constants.coderId)
					))
		{
			channel.sendMessage("You're Welcome!" + (ThreadLocalRandom.current().nextDouble() < 0.025
						? "<https://youtu.be/0DLzyvT4eUo?t=43s>" 
						: "<https://youtu.be/79DijItQXMM?t=38s>")).queue();
		}
	}
	
	
}
