
package ch.hearc.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.hearc.gui.control.JControl;
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

		setLayout(new BorderLayout());

		add(this.jControl, BorderLayout.EAST);
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
	private JDisplay jDisplay;

	}
