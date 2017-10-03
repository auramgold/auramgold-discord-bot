/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.RefUser;

/**
 * Says a thing in response to another thing
 * @author Lauren Smith
 */
public class Say extends BotCommand implements Documentable
{

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Say(String... alias)
	{
		super(alias);
	}
	
	@Override
	public String getDocumentation(String[] what)
	{
		return "```maid!say [word](...)\n"
				+ "	Try it out with anything you can think of!\n"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Used to see what I can respond to. Try it out!";
	}

	@Override
	public String run(String command, String[] params, RefUser who)
	{
		String statement = String.join(" ", params).toLowerCase();
		String honor = who.getHonorific();
		switch(statement)
		{
			case "hi":
			case "hello":
				return "Hi there";
			case "hello there":
				return "General Kenobi, you are a bold one!";
			case "execute order 66":
				return "It will be done, " + honor;
			case "what is your name":
			case "what's your name":
			case "name":
			case "what is your name?":
			case "what's your name?":
			case "name?":
				return "My name is Lexine.";
			case "are you there":
			case "are you there?":
				return "Yes I am, " + honor;
			case "what is my name":
			case "what's my name":
			case "my name":
			case "what is my name?":
			case "what's my name?":
			case "my name?":
				String nameret = who.getAuramName();
				if(!nameret.equals(honor))
				{
					return "I have your name recorded as " + nameret + ", "
							+ honor + ".";
				}
				else
				{
					return "I have no name recorded for you, " + honor + ".";
				}
			case "what the fox says":
			case "what does the fox say":
			case "what does the fox say?":
			case "fox sounds":
				return "https://youtu.be/J6NuhlibHsM?t=11s";
			case "i'm the best":
			case "im the best":
				return "Yes you are, " + honor + ".";
			case "what?":
			case "what does this command do?":
			case "what":
			case "what does this command do":
				return "`maid!say`	" + this.getShortDocumentation();
			case "":
				return "I am terribly sorry, "
						+ honor + ", but I don't "
						+ "know what you want me to say if you don't say "
						+ "anything.";
			default:
				return "I don't know what to say to that...";
		}
	}
	
}
