/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import auramgolddiscordbot.AuramgoldDiscordBot;
import auramgolddiscordbot.RefList;
import auramgolddiscordbot.RefUser;
import static auramgolddiscordbot.commands.MorphType.AGE;
import static auramgolddiscordbot.commands.MorphType.ANIMAL;
import static auramgolddiscordbot.commands.MorphType.HAIR;
import static auramgolddiscordbot.commands.MorphType.OTHER;
import static auramgolddiscordbot.commands.MorphType.SEX;
import static auramgolddiscordbot.commands.MorphType.SIZE;
import static auramgolddiscordbot.commands.MorphType.WEIGHT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

enum MorphType
{
	SIZE (0),
	AGE (1),
	WEIGHT (2),
	OTHER (3),
	HAIR (4),
	SEX (5),
	ANIMAL (6);
	
	public final int order;
	
	MorphType(int order)
	{
		this.order = order;
	}
}

class Morph implements Comparable<Morph>
{
	
	
	public final MorphType type;
	public final String name;
	
	public Morph(String name, MorphType type)
	{
		this.type = type;
		this.name = name;
	}
	
	public boolean checkSame(Morph o)
	{
		return (o.type.equals(type) && !o.type.equals(OTHER))
				|| o.name.equals(name);
	}

	@Override
	public int compareTo(Morph t)
	{
		return this.type.order - t.type.order;
	}
}

/**
 * Zaps a user with a morph
 * @author Lauren Smith
 */
public class Zap extends BotCommand implements Documentable
{

	/**
	 * The HashMap of morphs
	 */
	public HashMap<MorphType, ArrayList<Morph>> transformations;

	/**
	 * Constructs the command with given aliases.
	 * @param alias
	 */
	public Zap(String... alias)
	{
		super(alias);
		transformations = new HashMap<>();
		transformations.put
		(
			AGE, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("old", AGE), new Morph("young", AGE)
				)
			)
		);
		transformations.put
		(
			OTHER, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("four-breasted", OTHER), new Morph("muscular", OTHER)
				)
			)
		);
		transformations.put
		(
			ANIMAL, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("cat", ANIMAL), new Morph("dog", ANIMAL),
					new Morph("horse", ANIMAL), new Morph("chipmunk", ANIMAL),
					new Morph("mouse", ANIMAL), new Morph("raccoon", ANIMAL),
					new Morph("jeremy-like", ANIMAL), new Morph("fox", ANIMAL),
					new Morph("squirrel", ANIMAL), new Morph("bunny", ANIMAL),
					new Morph("cow", ANIMAL), new Morph("pikachu", ANIMAL)
				)
			)
		);
		transformations.put
		(
			HAIR, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("blonde-haired", HAIR), new Morph("black-haired", HAIR),
					new Morph("brown-haired", HAIR), new Morph("red-haired", HAIR),
					new Morph("orange-haired", HAIR), new Morph("yellow-haired", HAIR),
					new Morph("green-haired", HAIR), new Morph("blue-haired", HAIR),
					new Morph("purple-haired", HAIR), new Morph("pink-haired", HAIR)
				)
			)
		);
		transformations.put
		(
			SIZE, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("half-sized", SIZE), new Morph("double-sized", SIZE)
				)
			)
		);
		transformations.put
		(
			WEIGHT, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("heavy", WEIGHT), new Morph("thin", WEIGHT)
				)
			)
		);
		ArrayList<Morph> a = new ArrayList<>();
		for(int i = 1; i <= 5; i++)
		{
			a.add(new Morph("MV" + i, SEX));
			a.add(new Morph("FV" + i, SEX));
		}
		transformations.put(SEX, a);
	}
	
	/**
	 * Generates a random morph
	 * @return A string of a random morph
	 */
	protected String generateMorph()
	{
		Random currand = ThreadLocalRandom.current();
		int count = 1+(currand.nextInt(1+currand.nextInt(7)) % 7);
		if(count == 7)
		{
			count = 0;
		}
		ArrayList<Morph> apps = new ArrayList<>();
		if(count == 0)
		{
			return "";
		}
		for(int i = 0; i < count; i++)
		{
			boolean cont;
			Morph addition;
			do
			{
				int mindex = currand.nextInt(MorphType.values().length);
				MorphType m = MorphType.values()[mindex];
				int tracount = transformations.get(m).size();
				addition = transformations.get(m).get(currand.nextInt(tracount));
				boolean toAdd = true;
				for(Morph app: apps)
				{
					if(app.checkSame(addition))
					{
						toAdd = false;
						break;
					}
				}
				cont = toAdd;
			}
			while(!cont);
			apps.add(addition);
		}
		String ret = "";
		Collections.sort(apps);
		ret = apps.stream().map((app) -> app.name + " ").reduce(ret, String::concat);
		return ret;
	}
	
	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!zap (morph)... (usermention)\n"
				+ "	Zaps (usermention) with (morph)\n"
				+ "\n"
				+ "		(usermention) is either an @mention or their user ID number\n"
				+ "		If not put, defaults to using executing user.\n"
				+ "\n"
				+ "		(morph) is a string of words separated by spaces\n"
				+ "		If not specified, defaults to random morph"
				+ "```";
	}

	@Override
	public String getShortDocumentation()
	{
		return "Zaps a person with a TF gun enchant";
	}

	@Override
	public String run(String command, ArrayList<String> params, RefUser who, MessageReceivedEvent event)
	{
		boolean notSelfTarget = false;
		GuildController ctrlr = event.getGuild().getController();
		String person;
		if(params.isEmpty())
		{
			person = who.getId();
		}
		else if(params.get(params.size() - 1).equals("me"))
		{
			person = who.getId();
			params.remove(params.size() - 1);
		}
		else if(params.get(0).equals("me"))
		{
			person = who.getId();
			params.remove(0);
		}
		else
		{
			person = params.get(params.size() - 1);
			notSelfTarget = true;
		}

		Matcher mat = AuramgoldDiscordBot.userExtract.matcher(person);
		String otherId;
		if(mat.matches())
		{
			otherId = mat.group(1);
			if(!params.isEmpty() && notSelfTarget)
			{
				params.remove(params.size() - 1);
			}
		}
		else
		{
			Matcher mat2 = AuramgoldDiscordBot.userExtract.matcher(params.get(0));
			if(mat2.matches() && notSelfTarget)
			{
				otherId = mat2.group(1);
				params.remove(0);
			}
			else
			{
				otherId = who.getId();
			}
		}
			
		if(!otherId.equals("342757470046781450"))
		{
			RefUser targ = new RefUser(Long.parseLong(otherId),(JDAImpl)AuramgoldDiscordBot.api);
			Member targM = event.getGuild().getMember(targ);
			ArrayList<String> morph;
			morph = (ArrayList<String>)params.clone();
			String form;
			if(morph.isEmpty())
			{
				form = generateMorph();
			}
			else
			{
				form = String.join(" ",morph);
			}
			if(form.equals("normal") || form.equals("default") || form.equals(""))
			{
				form = "";
			}
			RefList.getReference(otherId).morphState = form;
			if(form.equals(""))
			{
//				try
//				{
//					ctrlr.setNickname(targM,"").completeAfter(250, TimeUnit.MILLISECONDS);
//				}
//				catch(Exception ex)
//				{
//					System.out.println("    Couldn't nickname the user.");
//				}
				return "*zaps " + "<@!" + otherId + ">" + " back to "
						+ RefList.getReference(otherId).getPronouns().possAdj
						+ " default form.*";
			}
			if(!morph.isEmpty())
			{
				form += " ";
				RefList.getReference(otherId).morphState = form;
			}
			String article = AuramgoldDiscordBot.getArticle(form);
			RefList.updateFile();
//			String nick = AuramgoldDiscordBot.capitalizeFirstLetter(form)
//							+ (!targ.getAuramName().equals(targ.getPronouns().subject)
//								?targ.getAuramName()
//								:targM.getEffectiveName());
//			System.out.println("    Attempted to name "
//								+ targM.getEffectiveName() + " \""
//								+ nick + "\"");
//			try
//			{
//				ctrlr.setNickname(targM,nick).queueAfter(250, TimeUnit.MILLISECONDS);
//			}
//			catch(Exception ex)
//			{
//				System.out.println("    Couldn't nickname the user.");
//			}
			return "*zaps " + "<@!" + otherId + ">" + " with " + article + " "
					+ form + "morph.*";
		}
		else
		{
			return "I'm sorry, " + who.getHonorific() + ", "
					+ "but I can't zap myself.";
		}
		
	}
}
