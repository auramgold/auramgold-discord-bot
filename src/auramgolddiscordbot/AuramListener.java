/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 *
 * @author Lauren Smith
 */
public class AuramListener extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
		User messSender = event.getAuthor();
		String content = message.getRawContent();
		MessageChannel channel = event.getChannel();
		String sender = messSender.getName()+"#"+messSender.getDiscriminator();
		String channelStr = channel.getName();
		String recTime = "["+AuramgoldDiscordBot.getTimeString()+"]";
		System.out.println(recTime+" "+sender+" in "+channelStr+":"+content);
		if (event.getAuthor().isBot()) return;
		// We don't want to respond to other bot accounts, including ourself
		String cmdStart = content.substring(0, Math.min(AuramgoldDiscordBot.comStartLen,content.length()));
		if(cmdStart.equals(AuramgoldDiscordBot.commandStart))
		{
			String subCom = content.substring(AuramgoldDiscordBot.comStartLen);
			String[] splitCommand = subCom.split(" ");
			String ret = CommandRunner.runCommand(splitCommand, new RefUser(messSender), channel);
			channel.sendMessage(ret).queue();
		}
		else if(content.contains("(╯°□°）╯︵ ┻━┻")&&(Math.random()<0.1||messSender.getId().equals("242391558664486913")))
		{
			channel.sendMessage("┬─┬﻿ ノ( ゜-゜ノ)").queue();
		}
	}
	
	
}
