
package ch.hearc.gui;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JPanel;

import ch.hearc.gui.control.JControl;
import ch.hearc.gui.control.JToolBox;
import ch.hearc.gui.display.JDisplay;

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
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.jDisplay = JDisplay.getInstance();
		this.jControl = new JControl(this.jDisplay);
		this.jToolBox = new JToolBox();
		this.jOptionsMenu = new JMenu();

		setLayout(new BorderLayout());

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
	private JToolBox jToolBox;
	private JDisplay jDisplay;
	private JMenu jOptionsMenu;

	}
