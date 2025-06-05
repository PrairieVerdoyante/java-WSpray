
package ch.hearc.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.hearc.gui.calques.JCalques;
import ch.hearc.gui.control.JControl;
import ch.hearc.gui.control.JToolBox;
import ch.hearc.gui.menu.JOptionsMenu;
import ch.hearc.gui.spray.JDisplay;

public class JApp extends JPanel
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	
	public JApp()
		{
		geometry();
		control();
		appearance();
		}
		
	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/
	
	private void geometry()
		{
		this.jSpray = JDisplay.getInstance();
		this.jControl = new JControl(this.jSpray);
		this.jToolBox = new JToolBox();
		this.jOptionsMenu = new JOptionsMenu();
		
		this.jCalques = this.jControl.getJCalques();
		this.jSpray.reset(); // call reset once to init jDisplay
		this.jSpray.setJCalques(jCalques);
		
		setLayout(new BorderLayout());
		
		add(this.jSpray, BorderLayout.CENTER);
		add(this.jOptionsMenu, BorderLayout.NORTH);
		add(this.jControl, BorderLayout.EAST);
		add(this.jToolBox, BorderLayout.WEST);
		
		}
		
	private void control()
		{
		// rien
		}
		
	private void appearance()
		{
		// rien
		}
		
	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	
	// Inputs
	
	// Tools
	private JControl jControl;
	private JCalques jCalques;
	private JToolBox jToolBox;
	private JDisplay jSpray;
	private JOptionsMenu jOptionsMenu;
	
	}
