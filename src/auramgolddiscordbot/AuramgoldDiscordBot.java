/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.impl.GameImpl;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.managers.Presence;
import net.dv8tion.jda.core.managers.impl.PresenceImpl;

/**
 *
 * @author Lauren Smith
 */
public class AuramgoldDiscordBot
{
	public static final String commandStart = "maid!";
	public static final int comStartLen = AuramgoldDiscordBot.commandStart.length();
	public static JDA api;
	public static Pattern userExtract = Pattern.compile("^(?:\\<\\@)?\\!?(\\d+)\\>?$");
	
	public static String locationOfMapText = "C:\\Users\\User\\Desktop\\Development Stuff\\genderlistfile.txt";
	
	public static String[] cutOffFirst(String[] what)
	{
		return Arrays.copyOfRange(what, 1, what.length);
	}
	
	public static String[] cutOffLast(String[] what)
	{
		return Arrays.copyOfRange(what, 0, what.length-1);
	}
	
	public static void updateMapFromFile() throws IOException
	{
		Files.lines(Paths.get(locationOfMapText)).forEach((String v)->
			{
				String[] split = v.split("\\|",-1);
				RefList.referenceList.put
				(split[0],
					new PersonalReference
						(
							Integer.parseInt(split[1]),
							split[2],
							Integer.parseInt(split.length>3?split[3]:"0"),
							split[4]
						)
				);
			}
		);
	}
	
	public static String getTimeString()
	{
		LocalTime tim = LocalTime.now();
		return tim.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static String replacePronouns(String input)
	{
		//i'm sorry for this
		String repl = input.replaceAll("\\b(your)\\b","||FIRST_POSS||");
		repl = repl.replaceAll("\\b(my)\\b","||SECOND_POSS||");
		repl = repl.replaceAll("\\b(you)\\b", "||FIRST_OBJ||");
		repl = repl.replaceAll("\\b(me)\\b", "||SECOND_OBJ||");
		//now replace the processings with the real ones
		repl = repl.replace("||FIRST_OBJ||","me");
		repl = repl.replace("||FIRST_POSS||","my");
		repl = repl.replace("||SECOND_OBJ||","you");
		repl = repl.replace("||SECOND_POSS||","your");
		return repl;
	}
	
	public static String capitalizeFirstLetter(String original)
	{
		if (original == null || original.length() == 0)
		{
			return original;
		}
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	public static String getArticle(String noun)
	{
		String sta = noun.substring(0,1);
		switch(sta)
		{
			case "a":
			case "e":
			case "i":
			case "o":
			case "u":
			case "M":
			case "F":
				return "an";
			default:
				return "a";
		}
	}
	
	public static void main(String[] args) throws LoginException, IllegalArgumentException, RateLimitedException, InterruptedException 
	{
		try {updateMapFromFile();} catch (IOException ex) {}
		api = new JDABuilder(AccountType.BOT)
					.setToken("MzQyNzU3NDcwMDQ2NzgxNDUw.DGUSqg.UHMK7nc4miCX6cRCU0S8PifSb9U").buildBlocking();
		Presence pres = new PresenceImpl((JDAImpl)api); 
		pres.setGame
			(
				new GameImpl
					(
						"maid!help for assistance",
						"",
						Game.GameType.DEFAULT
					)
			);
		api.addEventListener(new AuramListener());
	}
	
}
