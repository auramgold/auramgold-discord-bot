/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auramgolddiscordbot.commands;

import java.util.ArrayList;

/**
 *
 * @author Lauren Smith
 */
public interface Documentable
{

	/**
	 * Gets the documentation for the command
	 * @param what The input after the help command
	 * @return The documentation
	 */
	public abstract String getDocumentation(ArrayList<String> what);

	/**
	 * Gets the short documentation for the command
	 * @return
	 */
	public abstract String getShortDocumentation();
}
