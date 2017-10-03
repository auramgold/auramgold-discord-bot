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
 *
 * @author Lauren Smith
 */
public class RefUser extends UserImpl
{
	protected PersonalReference pers;
	protected User user;
	
	public RefUser(long id, JDAImpl api)
	{
		super(id, api);
		pers = RefList.getReference(Long.toString(id));
		this.user = new UserImpl(id, api);
	}
	
	public RefUser(User user)
	{
		super(user.getIdLong(), (JDAImpl)user.getJDA());
		this.user = user;
		pers = RefList.getReference(Long.toString(id));
	}
	
	public PronounRef getPronouns()
	{
		return pers.getPronouns();
	}
	
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
		
	public String getAuramName()
	{
		return pers.getName();
	}
	
	public String getHonorific()
	{
		return pers.getPronouns().honorific;
	}
}
