/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author Lauren Smith
 */
public class RefList
{
	public static Map<String, PersonalReference> referenceList = new HashMap<>();
	
	public static PersonalReference getReference(String id)
	{
		if(!referenceList.containsKey(id))
		{
			referenceList.put(id, new PersonalReference());
			updateFile();
		}
		return referenceList.get(id);
	}
	
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
	
	public static PronounRef getPronounRef(User user)
	{
		PersonalReference r = getReference(user.getId());
		return r.getPronouns();
	}
	
	public static PronounRef getPronounRef(String id)
	{
		PersonalReference r = getReference(id);
		return r.getPronouns();
	}
	
	public static void updateFile()
	{
		String out = "";
		for(Map.Entry<String, PersonalReference> entry : referenceList.entrySet())
		{
			String k = entry.getKey();
			PersonalReference v = entry.getValue();
			out = out.concat(k + "|" + v.getPronounIndex() + "|" + v.getName()
							+ "|" + v.netSpoil + "|" + v.morphState + "\n");
		}
		File fnew=new File(AuramgoldDiscordBot.locationOfMapText);
		
		try
		{
			FileWriter write = new FileWriter(fnew, false);
			write.write(out);
			write.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
}
