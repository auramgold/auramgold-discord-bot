/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import auramgolddiscordbot.commands.MorphSex;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.managers.Presence;
import net.dv8tion.jda.core.managers.impl.PresenceImpl;

/**
 * The main class for the bot
 * @author auramgold
 */
public class AuramgoldDiscordBot
{

	/**
	 * What string the command detection starts with.
	 */
	public static final String commandStart = "maid!";

	/**
	 * The length of AuramgoldDiscordBot.commandStart
	 */
	public static final int comStartLen = AuramgoldDiscordBot.commandStart.length();

	/**
	 * The API access object
	 */
	public static JDA api;

	/**
	 * The regex pattern to extract user IDs.
	 */
	public static Pattern userExtract = Pattern.compile("^(?:\\<\\@)?\\!?(\\d+)\\>?$");
	public static Pattern mentionExtract = Pattern.compile("^\\<\\@\\!?(\\d+)\\>$");
	
	/**
	 * Where the map file is located.
	 */
	public static String locationOfMapText = "genderlistfile.txt";

	/**
	 * Where the bot token file is located
	 */
	public static String locationOfBotToken = "bot_token.txt";

	/**
	 * Contains the token for the bot
	 */
	public static String botToken;
	
	/**
	 * Gets the map file and extracts it to a format the program can use.
	 * @throws IOException When something in the IO library fails.
	 */
	public static void updateMapFromFile() throws IOException
	{
		Path path = Paths.get(locationOfMapText);
		BufferedReader reader = Files.newBufferedReader(path ,Charset.forName("UTF-8"));
		Stream<String> stream = reader.lines();
		stream.forEach(
			(String v)->
			{
				String[] split = v.split("\\|",-1);
				RefList.referenceList.put
				(split[0],
					new PersonalReference
						(
							Integer.parseInt(split[1]),
							split[2],
							Integer.parseInt(split.length>3?split[3]:"0"),
							split[4],
							MorphSex.values()[(Integer.parseInt(split[5]))],
							split[6].equals("true")
						)
				);
			}
		);
	}
	
	/**
	 * Gets the bot token from the file.
	 * @throws IOException When something in the IO library fails
	 */
	public static void getBotToken() throws IOException
	{
		botToken = Files.readAllLines(Paths.get(locationOfBotToken)).get(0);
	}
	
	/**
	 * Formats a time string nicely and simply
	 * @return A time string in the format HH:mm:ss
	 */
	public static String getTimeString()
	{
		LocalTime tim = LocalTime.now();
		return tim.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	/**
	 * Swaps singular first-person pronouns for second-person ones<br/>
	 * e.g. "I took your cookie" becomes "You took my cookie" 
	 * @param input The string to swap
	 * @return
	 */
	public static String replacePronouns(String input)
	{
		//i'm sorry for this
		String repl = input.replaceAll("\\b(your)\\b", "||FIRST_POSS||");
		repl = repl.replaceAll("\\b(my)\\b", "||SECOND_POSS||");
		repl = repl.replaceAll("\\b(you)\\b", "||FIRST_OBJ||");
		repl = repl.replaceAll("\\b(me)\\b", "||SECOND_OBJ||");
		//now replace the processings with the real ones
		repl = repl.replace("||FIRST_OBJ||", "me");
		repl = repl.replace("||FIRST_POSS||", "my");
		repl = repl.replace("||SECOND_OBJ||", "you");
		repl = repl.replace("||SECOND_POSS||", "your");
		return repl;
	}
	
	/**
	 * Capitalizes the first letter of a string.
	 * @param original The string to capitalize
	 * @return The capitalized string.
	 */
	public static String capitalizeFirstLetter(String original)
	{
		if (original == null || original.length() == 0)
		{
			return original;
		}
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	public static boolean checkPageExists(String link) throws MalformedURLException, IOException
	{
		URL url = new URL(link);
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setRequestMethod("HEAD");
		int status = connect.getResponseCode();
		return status != 404;
	}
	
	/**
	 * Gets the article to use for a noun between 'a' and 'an'
	 * @param noun The noun to check the article of
	 * @return 'a' or 'an' depending on what should be used.
	 */
	public static String getArticle(String noun)
	{
		String sta = noun.substring(0, 1);
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
	
	/**
	 * Main method, runs at runtime.
	 * @param args Command-line arguments, unused
	 * @throws LoginException
	 * @throws IllegalArgumentException
	 * @throws RateLimitedException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws LoginException, IllegalArgumentException, RateLimitedException, InterruptedException 
	{
		try
		{
			updateMapFromFile();
			getBotToken();
		} 
		catch (IOException ex) 
		{}
		api = new JDABuilder(AccountType.BOT).setToken(botToken).buildBlocking();
		Presence pres = new PresenceImpl((JDAImpl)api); 
		pres.setGame(Game.of("maid!help for docs"));
		api.addEventListener(new AuramListener());
	}
	
}
