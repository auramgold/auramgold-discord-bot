/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author Lauren Smith
 */
public class Baking
{
	static double getProbabilityMod(User who,double prob)
	{
		PersonalReference whoRef = RefList.getReference(who.getId());
		int spoilage = whoRef.netSpoil;
		return Math.pow
				(
					prob,
					(Math.pow(2.0,((2.0/(1.0+Math.pow(1.25,(-spoilage/4.0)))-1.0))))
				);
	}
	
	static String bake(String[] what,User who)
	{
		int probDenom = 20;
		long usrId = who.getIdLong();
		int probNumer = (int) (usrId%(probDenom-1) + 1);
		double probability = (double)((double)probNumer/(double)probDenom);
		System.out.println(probability);
		double probabilityFin = getProbabilityMod(who, probability);
		System.out.println(probabilityFin);
		PersonalReference whoRef = RefList.getReference(who.getId());
		switch(what[0])
		{
			case "cookie":
			case "cookies":
				if(Math.random()<probabilityFin)
				{
					whoRef.cookies += 12;
					return ":cookie: I successfully baked 12 cookies for you, "+
							whoRef.getPronouns().honorific+".";
				}
				else
				{
					return ":fire: I'm sorry, "+whoRef.getPronouns().honorific+
							", but this batch of cookies got burned...";
				}
			default:
				return "I don't know how to bake "+what[0]+", "+
							whoRef.getPronouns().honorific+".";
		}
	}
}
