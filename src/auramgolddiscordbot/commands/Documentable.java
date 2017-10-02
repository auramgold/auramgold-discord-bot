/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

/**
 *
 * @author Lauren Smith
 */
public interface Documentable
{
	public abstract String getDocumentation(String[] what);
	public abstract String getShortDocumentation();
}
