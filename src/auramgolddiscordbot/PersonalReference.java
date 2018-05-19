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
	 * The set name of a given user
	 */
	protected String name;

	/**
	 * What morph the user is currently in
	 */
	public String morphState;
	
	public MorphSex defaultOverride;
	
	public boolean acceptsZaps;

	/**
	 * The net amount the bot is "spoiled" by the given user.<br/>
	 * Incremented for maid!praise, decremented for maid!punish
	 */
	public int netSpoil = 0;

	/**
	 * The index of the pronouns they use in the list.
	 */
	protected int pronounIndex;
	
	protected PronounData pronouns;
	
	/**
	 * Default constructor
	 */
	public PersonalReference()
	{
		this(0, "", 0, "", MorphSex.NONE, true);
	}
	
	/**
	 * Constructs a user with the given properties.
	 * @param ind What pronoun index they use
	 * @param nam Their name (not username!) as given to the bot
	 * @param spoil Their net spoilage
	 * @param morph The morph state they are currently in
	 */
	public PersonalReference(int ind, String nam, int spoil, String morph, MorphSex over, boolean zappable)
	{
		pronounIndex = ind;
		name = nam;
		netSpoil = spoil;
		morphState = morph;
		defaultOverride = over;
		acceptsZaps = zappable;
		pronouns = PronounData.values()[ind];
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
	public PronounData getPronouns()
	{
		return pronouns;
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
	public PronounData setPronouns(int index)
	{
		pronounIndex = index;
		pronouns = PronounData.values()[index];
		return PronounData.values()[index];
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
	
	public String zappableString()
	{
		return acceptsZaps?"true":"false";
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
			case "it":
			case "it/it":
				return 7;
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
