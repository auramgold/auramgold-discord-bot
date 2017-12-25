/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import auramgolddiscordbot.commands.MorphSex;

/**
 *
 * @author auramgold
 */

public class PersonalReference
{

	/**
	 * The list of <code>`PronounRef`</code>s that the program can use.
	 */
	public static PronounRef[] gender = 
	{
		new PronounRef("they", "them", "their", "theirs", "themself", "Mx.", "comrade"),
		new PronounRef("he", "him", "his", "his", "himself", "Mr.", "sir"),
		new PronounRef("she", "her", "her", "hers", "herself", "Ms.", "ma'am"),
		new PronounRef("ze", "zir", "zir", "zirs", "zirself", "Mx.", "comrade"),
		new PronounRef("xe", "xem", "xyr", "xyrs", "xemself", "Mx.", "comrade"),
		new PronounRef("ey", "em", "eir", "eirs", "eirself", "Mx.", "comrade"),
		new PronounRef("ze", "hir", "hir", "hirs", "hirself", "Mx.", "comrade"),
	};
	
	/**
	 * The set name of a given user
	 */
	protected String name;

	/**
	 * What morph the user is currently in
	 */
	public String morphState;
	
	public MorphSex defaultOverride;

	/**
	 * The net amount the bot is "spoiled" by the given user.<br/>
	 * Incremented for maid!praise, decremented for maid!punish
	 */
	public int netSpoil = 0;

	/**
	 * The index of the pronouns they use in the list.
	 */
	protected int pronounIndex;
	
	/**
	 * Default constructor
	 */
	public PersonalReference()
	{
		this(0, "", 0, "", MorphSex.NONE);
	}
	
	/**
	 * Constructs a user with the given properties.
	 * @param ind What pronoun index they use
	 * @param nam Their name (not username!) as given to the bot
	 * @param spoil Their net spoilage
	 * @param morph The morph state they are currently in
	 */
	public PersonalReference(int ind, String nam, int spoil, String morph, MorphSex over)
	{
		pronounIndex = ind;
		name = nam;
		netSpoil = spoil;
		morphState = morph;
		defaultOverride = over;
	}
	
	/**
	 * Gets the program name of a user
	 * @return Their program name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the program name of a user
	 * @param nam The name to set it to
	 * @return <code>nam</code>
	 */
	public String setName(String nam)
	{
		name = nam.replace("\\|", "");
		return name;
	}
	
	/**
	 * Gets the pronoun reference of a user
	 * @return The <code>`PronounRef`</code> object of their pronoun index
	 */
	public PronounRef getPronouns()
	{
		return gender[pronounIndex];
	}
	
	/**
	 * Gets the pronoun index of a user
	 * @return Their pronoun index
	 */
	public int getPronounIndex()
	{
		return pronounIndex;
	}
	
	/**
	 * Sets the pronoun index of a user
	 * @param index The index to set it to
	 * @return Their new PronounRef.
	 */
	public PronounRef setPronouns(int index)
	{
		if(0 <= index && index < gender.length)
		{
			pronounIndex = index;
		}
		return gender[pronounIndex];
	}
	
	/**
	 * Gets the current morph state of a user
	 * @return Their current morph state
	 */
	public String getMorph()
	{
		if(!morphState.equals(""))
		{
			String article = AuramgoldDiscordBot.getArticle(morphState);
			String extraSpace = morphState.endsWith(" ") ? "" : " ";
			return article + " " + morphState + extraSpace + "form";
		}
		else
		{
			return getPronouns().possAdj + " default form";
		}
	}
	
	public MorphSex getOverrideSex()
	{
		return this.defaultOverride;
	}
	
	public void setOverrideSex(MorphSex what)
	{
		this.defaultOverride = what;
	}
	
	/**
	 * Gets the index of a given pronoun string, used to convert the inputs from the
	 * command into usable things.
	 * @param str The inputted string
	 * @return The pronoun index of that string
	 */
	public static int getIndexOfString(String str)
	{
		switch(str)
		{
			case "he":
			case "1":
			case "him":
			case "male":
			case "boy":
			case "man":
			case "he/him":
				return 1; //he/him
			case "she":
			case "2":
			case "her":
			case "girl":
			case "woman":
			case "female":
			case "she/her":
				return 2; //she/her
			case "ze":
			case "ze/zir":
			case "3":
				return 3; //ze/zir
			case "xe":
			case "xe/xem":
			case "4":
				return 4;//xe/xem
			case "ey":
			case "ey/em":
			case "5":
				return 5;//ey/em
			case "ze/hir":
			case "6":
				return 6;//ze/hir
			default:
				return 0; //they/them
		}
	}

	/**
	 * Converts the object to a string.
	 * @return The string representation of the object
	 */
	@Override
	public String toString()
	{
		return this.getPronounIndex() + "|" + this.getName();
	}
	
	
}
