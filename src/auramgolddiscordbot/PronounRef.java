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
public class PronounRef
{
	public String subject;
	public String object;
	public String possAdj;
	public String possPro;
	public String reflexive;
	public String title;
	public String honorific;
	
	public PronounRef(String s, String o, String pA, String pP, String r, String t, String h)
	{
		this.subject = s;
		this.object = o;
		this.possAdj = pA;
		this.possPro = pP;
		this.reflexive = r;
		this.title = t;
		this.honorific = h;
	}
}
