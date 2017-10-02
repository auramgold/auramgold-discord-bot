/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

/**
 *
 * @author Lauren Smith
 */

public class PersonalReference
{
	public static PronounRef[] gender = 
	{
		new PronounRef("they","them","their","theirs","themself","Mx.","comrade"),
		new PronounRef("he","him","his","his","himself","Mr.","sir"),
		new PronounRef("she","her","her","hers","herself","Ms.","ma'am"),
		new PronounRef("ze","zir","zir","zirs","zirself","Mx.","comrade"),
		new PronounRef("xe","xem","xyr","xyrs","xemself","Mx.","comrade"),
		new PronounRef("ey","em","eir","eirs","eirself","Mx.","comrade"),
		new PronounRef("ze","hir","hir","hirs","hirself","Mx.","comrade"),
	};
	
	protected String name;
	public int cookies = 0;
	public int cakes = 0;
	public String morphState;
	public int netSpoil = 0;
	protected int pronounIndex;
	
	public PersonalReference()
	{
		this(0,"",0,"");
	}
	
	public PersonalReference(int ind, String nam, int spoil, String morph)
	{
		pronounIndex = ind;
		name = nam;
		netSpoil = spoil;
		morphState = morph;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String setName(String nam)
	{
		name = nam.replace("\\|", "");
		return name;
	}
	
	public PronounRef getPronouns()
	{
		return gender[pronounIndex];
	}
	
	public int getPronounIndex()
	{
		return pronounIndex;
	}
	
	public PronounRef setPronouns(int index)
	{
		if(0<=index && index <= gender.length)
		{
			pronounIndex = index;
		}
		return gender[pronounIndex];
	}
	
	public String getMorph()
	{
		if(!morphState.equals(""))
		{
			String article = AuramgoldDiscordBot.getArticle(morphState);
			String extraSpace = morphState.endsWith(" ")?"":" ";
			return article+" "+morphState+extraSpace+"form";
		}
		else
		{
			return getPronouns().possAdj+" default form";
		}
	}
	
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

	@Override
	public String toString()
	{
		return this.getPronounIndex()+"|"+this.getName();
	}
	
	
}
