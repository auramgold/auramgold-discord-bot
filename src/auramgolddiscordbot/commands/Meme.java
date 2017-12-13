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
 * Gets a meme for a given input.
 * @author Lauren Smith
 */
public class Meme extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Meme(String... alias)
	{
		super(alias);
	}

	@Override
	public String getDocumentation(ArrayList<String> what) {
		return "```maid!meme [word](...)\n"
				+ "	Try the name of any prequel meme, that's a good trick.\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation() {
		return "A suprise, to be sure, but a welcome one.";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event) {
		String statement = String.join(" ", params).toLowerCase();
		switch(statement)
		{
			case "hello there":
			case "hello there!":
				return "General " + who.getName() + ", you are a bold **one**!";
			case "plagueis":
				if(who.getId().equals("242391558664486913"))
				{
					return "Did you ever hear the tragedy of Darth Plagueis the Wise? "
							+ "I thought not. It's not a story the Jedi would tell you. "
							+ "It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith,"
							+ " so powerful and so wise he could use the Force to influence the "
							+ "midichlorians to create life... He had such a knowledge of the "
							+ "dark side that he could even keep the ones he cared about from "
							+ "dying. The dark side of the Force is a pathway to many abilities "
							+ "some consider to be unnatural. He became so powerful... the only "
							+ "thing he was afraid of was losing his power, which eventually, of "
							+ "course, he did. Unfortunately, he taught his apprentice everything "
							+ "he knew, then his apprentice killed him in his sleep. Ironic, he could"
							+ " save others from death, but not himself.";
				}
				else
				{
					return "It is only possible to learn this power from a Lauren...";
				}
			case "sand":
			case "i hate sand":
				return "I hate sand, it's rough, irritating, and gets everywhere";
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
			case "older":
			case "older meme":
			case "it's an older meme":
			case "its an older meme":
				return "http://i0.kym-cdn.com/entries/icons/original/000/017/492/bUNXwFD.jpg";
			case "both":
			case "why not both":
			case "both?":
			case "why not both?":
				return "https://i.imgur.com/c7NJRa2.gif";
			case "you're welcome":
			case "youre welcome":
			case "welcome":
				return "https://youtu.be/79DijItQXMM?t=38s";
			case "demiguy":
				return "https://youtu.be/79DijItQXMM?t=50s";
			case "why":
			case "but why":
			case "why?":
			case "but why?":
				return "https://youtu.be/PEZWYXPvmS8";
			case "i'm out":
			case "im out":
			case "fuck this shit":
			case "fuck this shit i'm out":
			case "fuck this shit im out":
				return "https://youtu.be/5FjWe31S_0g";
			case "":
				return "Try the name of any prequel meme, that's a good trick.";
			default:
				return "No meme was found for " + statement
						+ ". If it is not in our archives, it does not exist.";
		}
	}
	
}
