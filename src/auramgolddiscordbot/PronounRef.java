/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

/**
 * This class houses the pronouns as set by the program
 * @author auramgold
 */
public class PronounRef
{

	/**
	 * The pronoun for the subject, e.g. he, she, they
	 */
	public String subject;

	/**
	 * The pronoun for the object, e.g. him, her, them
	 */
	public String object;

	/**
	 * The pronoun for the possessive adjective, e.g. his, her, their
	 */
	public String possAdj;

	/**
	 *  The pronoun for the possessive noun, e.g. his, hers, theirs
	 */
	public String possPro;

	/**
	 * The pronoun for the reflexive reference, e.g. himself, herself, themself
	 */
	public String reflexive;

	/**
	 * The title, e.g. Mr., Ms., Mx.
	 */
	public String title;

	/**
	 * The honorific, e.g. sir, ma'am, comrade
	 */
	public String honorific;
	
	/**
	 *
	 * @param s
	 * @param o
	 * @param pA
	 * @param pP
	 * @param r
	 * @param t
	 * @param h
	 */
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
