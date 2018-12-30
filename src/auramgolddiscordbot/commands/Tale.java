/*
 * Copyright (C) 2018 auramgold
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.RefUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 *
 * @author auramgold
 */
public class Tale extends BotCommand implements Documentable
{
	
	public Tale(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!tale [title](...)\n"
			+ "	Fetches an SCP tale with the title [title]"
			+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Fetches an SCP tale with the given title.";
	}
	
	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		String title = String.join("-",params);
		String link = "http://www.scp-wiki.net/" + title;
		boolean isPage = false;
		try
		{
			isPage = AuramgoldDiscordBot.checkPageExists(link);
		}
		catch (IOException ex)
		{
			Logger.getLogger(Tale.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if(isPage)
		{
			return "There is a tale with that title, at the URL " + link;
		}
		else
		{
			return "I'm sorry, " + who.getHonorific() + ", but there is no tale with that title.";
		}

	}
}
