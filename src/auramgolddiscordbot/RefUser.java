/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.entities.impl.UserImpl;

/**
 * An extention of the user class that includes personal references in it.
 * @author Lauren Smith
 */
public class RefUser extends UserImpl
{

	/**
	 * The <code>`PersonalReference`</code> object for the given user
	 */
	protected PersonalReference pers;

	/**
	 * The given <code>`User`</code>
	 */
	protected User user;
	
	/**
	 * Constructs with a user ID as a long and a JDA object
	 * @param id The user ID as a long
	 * @param api The JDA object
	 */
	public RefUser(long id, JDAImpl api)
	{
		super(id, api);
		pers = RefList.getReference(Long.toString(id));
		this.user = new UserImpl(id, api);
	}
	
	/**
	 * Constructs from a <code>`User`</code> object.
	 * @param user The <code>`User`</code> object
	 */
	public RefUser(User user)
	{
		super(user.getIdLong(), (JDAImpl)user.getJDA());
		this.user = user;
		pers = RefList.getReference(Long.toString(id));
	}
	
	/**
	 * Gets the <code>`PronounRef`</code> for the person
	 * @return Their <code>`PronounRef`</code> object
	 */
	public PronounRef getPronouns()
	{
		return pers.getPronouns();
	}
	
	/**
	 * Gets their current morph state
	 * @return Their current morph state as a string
	 */
	public String getMorph()
	{
		return pers.getMorph();
	}
	
	@Override
	public String getName()
	{
		if((super.getName() != null) && !super.getName().equals("null"))
		{
			return super.getName();
		}
		else if((user.getName() != null) && !user.getName().equals("null"))
		{
			return user.getName();
		}
		else
		{
			return pers.getPronouns().subject;
		}
	}
		
	/**
	 * Gets the program name of the user
	 * @return Their program name
	 */
	public String getAuramName()
	{
		return pers.getName();
	}
	
	/**
	 * Gets the honorific (e.g. sir, ma'am) of the user
	 * @return Their honorific
	 */
	public String getHonorific()
	{
		return pers.getPronouns().honorific;
	}
}
