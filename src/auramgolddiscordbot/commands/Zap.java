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
import static auramgolddiscordbot.commands.MorphType.CAREER;
import static auramgolddiscordbot.commands.MorphType.HAIR;
import static auramgolddiscordbot.commands.MorphType.OTHER;
import static auramgolddiscordbot.commands.MorphType.SEX;
import static auramgolddiscordbot.commands.MorphType.SIZE;
import static auramgolddiscordbot.commands.MorphType.WEIGHT;
import static auramgolddiscordbot.commands.MorphType.HEIGHT;
import static auramgolddiscordbot.commands.MorphType.BCOUNT;
import static auramgolddiscordbot.commands.MorphType.FUR;
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
	BCOUNT (4),
	FUR  (5),
	HAIR (5),
	SEX (6),
	ANIMAL (7),
	CAREER (8);
	
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
 * @author auramgold
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
					new Morph("teenage", AGE), new Morph("childlike", AGE),
					new Morph("young adult", AGE)
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
					new Morph("muscular", OTHER), new Morph("busty", OTHER),
					new Morph("collared", OTHER), new Morph("aquatic", OTHER),
					new Morph("ditzy", OTHER), new Morph("illiterate", OTHER),
					new Morph("mute", OTHER)
				)
			)
		);
		transformations.put
		(
			BCOUNT,
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("four-breasted", OTHER), new Morph("six-breasted", OTHER),
					new Morph("eight-breasted", OTHER), new Morph("ten-breasted", OTHER)
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
					new Morph("eevee", ANIMAL), new Morph("vulpix", ANIMAL),
					new Morph("golden retriever", ANIMAL), new Morph("saint bernard", ANIMAL),
					new Morph("wolf", ANIMAL), new Morph("guinea pig", ANIMAL),
					new Morph("crow", ANIMAL), new Morph("raven", ANIMAL),
					new Morph("pony", ANIMAL), new Morph("poodle", ANIMAL),
					new Morph("honey badger", ANIMAL), new Morph("skunk", ANIMAL),
					new Morph("poodle", ANIMAL), new Morph("vaporeon", ANIMAL),
					new Morph("jolteon", ANIMAL), new Morph("flareon", ANIMAL),
					new Morph("espeon", ANIMAL), new Morph("umbreon", ANIMAL),
					new Morph("leafeon", ANIMAL), new Morph("glaceon", ANIMAL),
					new Morph("sylveon", ANIMAL), new Morph("zorua", ANIMAL),
					new Morph("zoroark", ANIMAL)
				)
			)
		);
		
		for(Morph currMorph : (ArrayList<Morph>)transformations.get(ANIMAL).clone())
		{
			Morph altMorph = new Morph("feral " + currMorph.name, ANIMAL);
			transformations.get(ANIMAL).add(altMorph);
		}
		
		transformations.put
		(
			FUR, 
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("black-furred", HAIR), new Morph("brown-furred", HAIR),
					new Morph("red-furred", HAIR), new Morph("orange-furred", HAIR), 
					new Morph("yellow-furred", HAIR), new Morph("green-furred", HAIR),
					new Morph("blue-furred", HAIR), new Morph("purple-furred", HAIR),
					new Morph("pink-furred", HAIR)
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
					new Morph("extremely tall", HEIGHT), new Morph("extremely short", HEIGHT),
					new Morph("tall", HEIGHT), new Morph("very tall", HEIGHT), 
					new Morph("short", HEIGHT), new Morph("very short", HEIGHT)
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
					new Morph("extremely heavy", WEIGHT), new Morph("extremely thin", WEIGHT),
					new Morph("heavy", WEIGHT), new Morph("very heavy", WEIGHT),
					new Morph("thin", WEIGHT), new Morph("very thin", WEIGHT)
				)
			)
		);
		transformations.put
		(
			CAREER,
			new ArrayList<>
			(
				Arrays.asList
				(
					new Morph("cheerleader", CAREER), new Morph("maid", CAREER),
					new Morph("baseball player", CAREER), new Morph("jedi", CAREER),
					new Morph("sith", CAREER), new Morph("stormtrooper", CAREER),
					new Morph("magician", CAREER), new Morph("sensei", CAREER),
					new Morph("nurse", CAREER), new Morph("police", CAREER),
					new Morph("veterinarian", CAREER)
				)
			)
		);
		ArrayList<Morph> a = new ArrayList<>();
		for(int i = 1; i <= 10; ++i)
		{
			a.add(new Morph("MV" + i, SEX, MorphSex.MALE));
			a.add(new Morph("FV" + i, SEX, MorphSex.FEMALE));
		}
		transformations.put(SEX, a);
		
		maxpo = transformations.size() + transformations.get(OTHER).size();
	}
	
	/**
	 * Generates a random morph with a random length
	 * @param what What MorphSex setting to generate the morph with
	 * @param who The user ID of the person being zapped
	 * @return A string of a random morph
	 */
	protected String generateMorph(MorphSex what, String who)
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
		return generateMorph(count, what, who);
	}
	
	/**
	 * Generates a random morph of a given length.
	 * If the <code>count</code> is invalid, returns null.
	 * Returns empty string to signify morph reset.
	 * 
	 * @param count the length of the form
	 * @param what What MorphSex setting to generate the morph with
	 * @param who The user ID of the person being zapped
	 * @return A string of a random morph
	 */
	protected String generateMorph(int count, MorphSex what, String who)
	{
		if(count <= 0 || count >= maxpo) return null;
		String ret = "";
		ArrayList<MorphType> types = new ArrayList<>(Arrays.asList(MorphType.class.getEnumConstants()));
		int typesLen = types.size();
		ArrayList<Morph> others = (ArrayList<Morph>)transformations.get(OTHER).clone();
		int othersLen = others.size();
		Random currand = ThreadLocalRandom.current();
		
		ArrayList<Morph> apps = new ArrayList<>();
		if(who.equals("242391558664486913"))
		{
			apps.add(new Morph("feral eevee", ANIMAL));
			types.remove(ANIMAL);
			apps.add(new Morph("maid", CAREER));
			types.remove(CAREER);
			count -= 2;
			typesLen -= 2;
		}
		if(count > 0)
		{
			for(int i = 0; i < count; ++i)
			{
				Morph addition;
				int mindex = currand.nextInt(typesLen);
				MorphType m = types.get(mindex < typesLen ? mindex : 0);
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
		
		// check for various cases that would be the person zapping themself
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
			// if none of the self zapping heuristics were gotten,
			// try a check at the end of the params array
			person = params.get(params.size() - 1);
			notSelfTarget = true;
		}

		// try to extract the user from the match
		Matcher mat = AuramgoldDiscordBot.mentionExtract.matcher(person);
		String otherId;
		if(mat.matches())
		{
			otherId = mat.group(1);
			
			// if the user mention was found at the end, remove the mention
			if(!params.isEmpty() && notSelfTarget)
			{
				params.remove(params.size() - 1);
			}
		}
		else
		{
			// if we didn't find a mention at the end, try and find one at the beginning
			Matcher mat2 = AuramgoldDiscordBot.mentionExtract.matcher(params.get(0));
			
			// if a mention was found at the begnning, remove the 0th parameter
			if(mat2.matches() && notSelfTarget)
			{
				otherId = mat2.group(1);
				params.remove(0);
			}
			else
			{
				// if a mention wasn't found, default to the calling user 
				otherId = who.getId();
			}
		}
		
		// if not zapping this bot
		// TODO: make the hardcoded IDs constants
		if(!otherId.equals("342757470046781450"))
		{
			ArrayList<String> morph;
			morph = (ArrayList<String>)params.clone();
			// the cast is there because java can't have nice things
			Iterator<String> morphIt = morph.iterator();
			MorphSex morphSex = RefList.getReference(otherId).getOverrideSex();
			
			// TODO: move argument parsing block to separate private method
			
			while(morphIt.hasNext())
			{
				String currParam = morphIt.next();
				
				// params starting with - are interpreted as arguments
				if(currParam.startsWith("-"))
				{
					morphIt.remove();
					String argument = currParam.substring(1);
					String[] argParts = argument.split(":");
					// params starting with - that don't have a colon are
					// treated as malformed arguments and ignored
					if(argParts.length == 2)
					{
						switch(argParts[0].toLowerCase())
						{
							// why do i conflate gender and sex in all of these?
							// the world may never know
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
									case "x":
									case "reset":
										morphSex = MorphSex.NONE;
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
			
			// end of the argument parsing block
			
			String form;
			int len = 0;
			// the ArrayList morph now has all argument elements removed
			// so we check if it is of size 1 to try and parse for a length
			if(morph.size() == 1)
			{
				// there is literally no good way to do this non-exceptionally
				// and i'm sorry
				try
				{
					len = Integer.parseInt(morph.get(0));
				}
				catch(NumberFormatException ex){}
			}
			
			// if the arraylist is of length 0, generate a morph from scratch
			if(morph.isEmpty())
			{
				form = generateMorph(morphSex, otherId);
			}
			else if(len != 0)
			{
				// if the parsing of the length suceeded, try and generate a morph
				// with that length
				form = generateMorph(len, morphSex, otherId);
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
				form = form.replace("|", "");
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
