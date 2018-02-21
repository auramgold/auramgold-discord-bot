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
public class MorphGenData
{
	public final MorphSex sex;
	public final int maxLen;
	public final int weight;
	
	public MorphGenData(MorphSex s, int mlen, int w)
	{
		sex = s;
		maxLen = mlen;
		weight = Math.max(w, 1);
	}
	
}
