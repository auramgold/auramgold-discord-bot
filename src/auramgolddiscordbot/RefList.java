/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author auramgold
 */
public class RefList
{

	/**
	 * The map of references for users
	 */
	public static Map<String, PersonalReference> referenceList = new HashMap<>();
	
	/**
	 * Gets the <code>`PersonalReference`</code> object for a given user
	 * @param id Their user ID as a string
	 * @return Their <code>`PersonalReference`</code> object
	 */
	public static PersonalReference getReference(String id)
	{
		if(!referenceList.containsKey(id))
		{
			referenceList.put(id, new PersonalReference());
			updateFile();
		}
		return referenceList.get(id);
	}
	
	/**
	 * Gets the program name of a user
	 * @param user The <code>`User`</code> object to get it from
	 * @return Their program name
	 */
	public static String getName(User user)
	{
		PersonalReference r = getReference(user.getId());
		if(r.getName().equals(""))
		{
			return user.getName();
		}
		else
		{
			return r.getName();
		}
	}
	
	/**
	 * Gets the program name for a given user id
	 * @param id The user id as a string
	 * @return Their program name
	 */
	public static String getName(String id)
	{
		PersonalReference r = getReference(id);
		if(r.getName().equals(""))
		{
			return r.getPronouns().honorific;
		}
		else
		{
			return r.getName();
		}
	}
	
	/**
	 * Gets the <code>`PronounRef`</code> of a user
	 * @param user The <code>`User`</code> object to get it from
	 * @return Their program name
	 */
	public static PronounData getPronounRef(User user)
	{
		PersonalReference r = getReference(user.getId());
		return r.getPronouns();
	}
	
	/**
	 * Gets the <code>`PronounRef`</code> from a user id
	 * @param id Their user id as a string
	 * @return Their program name
	 */
	public static PronounData getPronounRef(String id)
	{
		PersonalReference r = getReference(id);
		return r.getPronouns();
	}
	
	/**
	 * Saves changes to file
	 */
	public static void updateFile()
	{
		String out = "";
		for(Map.Entry<String, PersonalReference> entry : referenceList.entrySet())
		{
			String k = entry.getKey();
			PersonalReference v = entry.getValue();
			out = out.concat(k + "|" + v.getPronounIndex() + "|" + v.getName()
							+ "|" + v.netSpoil + "|" + v.morphState 
							+ "|" + v.defaultOverride.which
							+ "|" + v.zappableString() + "\n");
		}
		File fnew=new File(AuramgoldDiscordBot.locationOfMapText);
		
		try
		{
			Writer write = new BufferedWriter(new OutputStreamWriter
			(
				new FileOutputStream(fnew), "UTF-8"
			));
			write.write(out);
			write.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
}
