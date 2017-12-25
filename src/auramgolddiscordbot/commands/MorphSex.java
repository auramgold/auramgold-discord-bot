/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

/**
 *
 * @author auramgold
 */
public enum MorphSex
{
	NONE(0),
	MALE(1),
	FEMALE(2);
	
	public final int which;
			
	MorphSex(int w)
	{
		this.which = w;
	}
	
	boolean checkNotOpposite(MorphSex other)
	{
		if(this == NONE || other == NONE)
		{
			return true;
		}
		
		return this == other;
	}
}
