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
import static auramgolddiscordbot.commands.MorphType.HEIGHT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;



enum MorphType implements Comparable<MorphType>
{
	SIZE (0),
	AGE (1),
	HEIGHT (2),
	WEIGHT (3),
	OTHER (4),
	HAIR (5),
	SEX (6),
	ANIMAL (7);
	
	public final int order;
	
	MorphType(int order)
	{
		this.order = order;
	}
}

class Morph implements Comparable<Morph>
{
	
	
	public final MorphType type;
	public final MorphSex morphSex;
	public final String name;
	
	public Morph(String name, MorphType type)
	{
		this.type = type;
		this.name = name;
		this.morphSex = MorphSex.NONE;
	}
	
	public Morph(String name, MorphType type, MorphSex morphSex)
	{
		this.type = type;
		this.name = name;
		this.morphSex = morphSex;
	}
	
	public boolean equals(Morph o)
	{
		if (o == null) return false;
		
		if (o == this) return true;
		
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
	public int maxpo;

	/**
	 * The HashMap of morphs
	 */
	private final HashMap<MorphType, ArrayList<Morph>> transformations;

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
					new Morph("middle-aged", AGE), new Morph("elderly", AGE),
					new Morph("baby", AGE), new Morph("young adult", AGE),
					new Morph("teenage", AGE), new Morph("childlike", AGE)
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
					new Morph("four-breasted", OTHER), new Morph("muscular", OTHER),
					new Morph("busty", OTHER), new Morph("ditzy", OTHER),
					new Morph("cheerleader", OTHER)
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
					new Morph("cow", ANIMAL), new Morph("pikachu", ANIMAL),
					new Morph("golden retriever", ANIMAL), new Morph("saint bernard", ANIMAL)
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
					new Morph("quarter-sized", SIZE), new Morph("one-third-sized", SIZE),
					new Morph("half-sized", SIZE), new Morph("double-sized", SIZE), 
					new Morph("triple-sized", SIZE), new Morph("quadruple-sized", SIZE)
				)
			)
		);
		transformations.put
		(
			HEIGHT,
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("tall", SIZE), new Morph("very tall", SIZE), 
					new Morph("short", SIZE), new Morph("very short", SIZE)
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
					new Morph("heavy", WEIGHT), new Morph("very heavy", WEIGHT),
					new Morph("thin", WEIGHT), new Morph("very thin", WEIGHT)
				)
			)
		);
		ArrayList<Morph> a = new ArrayList<>();
		for(int i = 1; i <= 5; i++)
		{
			a.add(new Morph("MV" + i, SEX, MorphSex.MALE));
			a.add(new Morph("FV" + i, SEX, MorphSex.FEMALE));
		}
		transformations.put(SEX, a);
		maxpo = transformations.size() + transformations.get(OTHER).size();
	}
	
	/**
	 * Generates a random morph with a random length
	 * @return A string of a random morph
	 */
	protected String generateMorph(MorphSex what)
	{
		// this produces an integer in the range 1 to maxpo
		// with greater probability closer to 1
		Random currand = ThreadLocalRandom.current();
		int count = 1 + (currand.nextInt(1 + currand.nextInt(maxpo)) % maxpo);
		// this means that at maxpo, the least likely number to be picked
		// only then will it reset the form
		// this is to prevent form resets from happening ALL THE TIME
		if(count == maxpo)
		{
			return "";
		}
		return generateMorph(count, what);
	}
	
	/**
	 * Generates a random morph of a given length.
	 * If the <code>count</code> is invalid, returns null.
	 * Returns empty string to signify morph reset.
	 * 
	 * @param count the length of the form
	 * @return A string of a random morph
	 */
	protected String generateMorph(int count, MorphSex what)
	{
		if(count <= 0 || count >= maxpo) return null;
		String ret = "";
		ArrayList<MorphType> types = new ArrayList<>(Arrays.asList(MorphType.class.getEnumConstants()));
		int typesLen = types.size();
		ArrayList<Morph> others = (ArrayList<Morph>)transformations.get(OTHER).clone();
		int othersLen = others.size();
		Random currand = ThreadLocalRandom.current();
		
		ArrayList<Morph> apps = new ArrayList<>();
		for(int i = 0; i < count; i++)
		{
			Morph addition;
			int mindex = currand.nextInt(typesLen);
			MorphType m = types.get(mindex);
			if(m != OTHER)
			{
				ArrayList<Morph> currentType = (ArrayList<Morph>)transformations.get(m).clone();
				do
				{
					int tracount = currentType.size();
					int curr = currand.nextInt(tracount);
					addition = currentType.get(curr);
				}
				while(!what.checkNotOpposite(addition.morphSex));
				
				types.remove(m);
			}
			else
			{
				if(othersLen != 1)
				{
					int index = currand.nextInt(othersLen);
					addition = others.get(index);
					others.remove(index);
				}
				else
				{
					addition = others.get(0);
					types.remove(OTHER);
				}
				othersLen--;
			}
			typesLen = types.size();
			apps.add(addition);
			if(typesLen == 0) break;
		}
		Collections.sort(apps);
		ret = apps.stream().map((app) -> app.name + " ").reduce(ret, String::concat);
		return ret;
	}
	
	@Override
	public String getDocumentation(ArrayList<String> what)
	{
		return "```maid!zap (morph)... (usermention)\n"
				+ "OR maid!zap (usermention) (morph)...\n"
				+ "	Zaps (usermention) with (morph)\n"
				+ "\n"
				+ "		(usermention) is either an @mention or their user ID number\n"
				+ "		If not put, defaults to using executing user.\n"
				+ "\n"
				+ "		(morph) is a string of words separated by spaces\n"
				+ "		If not specified, defaults to random morph\n"
				+ "		If numeric, tries to generate a form morph with that many components.\n"
				+ "		If argument -sex:(M/F) is included, makes sure a sex morph that is generated is that one.\n"
				+ "		If 'default' or 'normal', resets form"
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
		String person;
		if(params.isEmpty())
		{
			person = "<@"+who.getId()+">";
		}
		else if(params.get(params.size() - 1).equals("me"))
		{
			person = "<@"+who.getId()+">";
			params.remove(params.size() - 1);
		}
		else if(params.get(0).equals("me"))
		{
			person = "<@"+who.getId()+">";
			params.remove(0);
		}
		else
		{
			person = params.get(params.size() - 1);
			notSelfTarget = true;
		}

		Matcher mat = AuramgoldDiscordBot.mentionExtract.matcher(person);
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
			Matcher mat2 = AuramgoldDiscordBot.mentionExtract.matcher(params.get(0));
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
			ArrayList<String> morph;
			morph = (ArrayList<String>)params.clone();
			Iterator<String> morphIt = morph.iterator();
			MorphSex morphSex = MorphSex.NONE;
			while(morphIt.hasNext())
			{
				String currParam = morphIt.next();
				if(currParam.startsWith("-"))
				{
					morphIt.remove();
					String argument = currParam.substring(1);
					String[] argParts = argument.split(":");
					if(argParts.length == 2)
					{
						switch(argParts[0].toLowerCase())
						{
							case "gender":
							case "sex":
								switch(argParts[1].toLowerCase())
								{
									case "m":
									case "male":
									case "boy":
									case "man":
										morphSex = MorphSex.MALE;
										break;
									case "f":
									case "female":
									case "girl":
									case "lady":
									case "woman":
										morphSex = MorphSex.FEMALE;
										break;
									default:
										break;
								}
								break;
							default:
								break;
						}
					}
				}
			}
			String form;
			int len = 0;
			if(morph.size() == 1)
			{
				try
				{
					len = Integer.parseInt(morph.get(0));
				}
				catch(NumberFormatException ex){}
			}
			
			if(morph.isEmpty())
			{
				form = generateMorph(morphSex);
			}
			else if(len != 0)
			{
				form = generateMorph(len, morphSex);
				if(form == null)
				{
					return "I'm sorry, " + who.getHonorific() + " but I don't"
							+ " have enough options to generate something that "
							+ "long.";
				}
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
				return "*zaps " + "<@!" + otherId + ">" + " back to "
						+ RefList.getReference(otherId).getPronouns().possAdj
						+ " default form.*";
			}
			if(!(morph.isEmpty() || len != 0))
			{
				form += " ";
				RefList.getReference(otherId).morphState = form;
			}
			String article = AuramgoldDiscordBot.getArticle(form);
			RefList.updateFile();
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
