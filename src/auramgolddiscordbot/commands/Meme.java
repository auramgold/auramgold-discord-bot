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
public class Meme extends BotCommand implements Documentable
{

	public Meme(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what) {
		return "```maid!meme [word](...)\n"
				+ "	Try the name of any prequel meme, that's a good trick.\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation() {
		return "A suprise, to be sure, but a welcome one.";
	}

	@Override
	public String run(String command, String[] params, RefUser who) {
		String statement = String.join(" ",params).toLowerCase();
		switch(statement)
		{
			case "hello there":
			case "hello there!":
				return "General "+who.getName()+", you are a bold **one**!";
			case "plagueis":
				if(who.getId().equals("242391558664486913"))
				{
					return "Did you ever hear the tragedy of Darth Plagueis the Wise? "+
							"I thought not. It's not a story the Jedi would tell you. "+
							"It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith,"+
							" so powerful and so wise he could use the Force to influence the "+
							"midichlorians to create life... He had such a knowledge of the "+
							"dark side that he could even keep the ones he cared about from "+
							"dying. The dark side of the Force is a pathway to many abilities "+
							"some consider to be unnatural. He became so powerful... the only "+
							"thing he was afraid of was losing his power, which eventually, of "+
							"course, he did. Unfortunately, he taught his apprentice everything "+
							"he knew, then his apprentice killed him in his sleep. Ironic, he could"+
							" save others from death, but not himself.";
				}
				else
				{
					return "It is only possible to learn this power from a Lauren...";
				}
			case "no":
			case "noo":
			case "nooo":
			case "noooo":
				return "https://giphy.com/gifs/reaction-animated-star-wars-xHwqspaBmfUMU";
			case "working":
			case "it's working":
			case "its working":
				return "https://media.giphy.com/media/9K2nFglCAQClO/200.gif";
			case "fine":
			case "fine addition":
				return "http://memes.ucoz.com/_nw/19/03537863.jpg";
			case "do it":
			case "dewit":
			case "dew it":
				return "https://youtu.be/XU_9ucFfhdI?t=14s";
			case "power":
			case "unlimited":
			case "unlimited power":
				return "https://www.youtube.com/watch?v=e_DqV1xdf-Y";
			case "":
				return "Try the name of any prequel meme, that's a good trick.";
			default:
				return "No meme was found for "+statement
						+". If it is not in our archives, it does not exist.";
		}
	}
	
}
