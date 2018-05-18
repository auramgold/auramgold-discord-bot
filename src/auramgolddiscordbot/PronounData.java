/*
 * Copyright (C) 2018 auramgold
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package auramgolddiscordbot;

/**
 *
 * @author auramgold
 */
public enum PronounData
{
	THEYTHEM	(0, "they", "them", "their", "theirs", "themself", "Mx.", "comrade", "enby"),
	HEHIM		(1, "he", "him", "his", "his", "himself", "Mr.", "sir", "boy"),
	SHEHER		(2, "she", "her", "her", "hers", "herself", "Ms.", "ma'am", "girl"),
	ZEZIR		(3, "ze", "zir", "zir", "zirs", "zirself", "Mx.", "comrade", "enby"),
	XEXEM		(4, "xe", "xem", "xyr", "xyrs", "xemself", "Mx.", "comrade", "enby"),
	EYEM		(5, "ey", "em", "eir", "eirs", "eirself", "Mx.", "comrade", "enby"),
	ZEHIR		(6, "ze", "hir", "hir", "hirs", "hirself", "Mx.", "comrade", "enby");
	
	public final int id;
	
	/**
	 * The pronoun for the subject, e.g. he, she, they
	 */
	public final String subject;

	/**
	 * The pronoun for the object, e.g. him, her, them
	 */
	public final String object;

	/**
	 * The pronoun for the possessive adjective, e.g. his, her, their
	 */
	public final String possAdj;

	/**
	 *  The pronoun for the possessive noun, e.g. his, hers, theirs
	 */
	public final String possPro;

	/**
	 * The pronoun for the reflexive reference, e.g. himself, herself, themself
	 */
	public final String reflexive;

	/**
	 * The title, e.g. Mr., Ms., Mx.
	 */
	public final String title;

	/**
	 * The honorific, e.g. sir, ma'am, comrade
	 */
	public final String honorific;
	
	public final String littleName;
	
	PronounData(int i, String s, String o, String pA, String pP, String r, String t, String h, String ln)
	{
		id = i;
		subject = s;
		object = o;
		possAdj = pA;
		possPro = pP;
		reflexive = r;
		title = t;
		honorific = h;
		littleName = ln;
	}
}
