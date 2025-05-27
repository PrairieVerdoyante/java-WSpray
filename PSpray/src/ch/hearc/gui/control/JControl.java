
package ch.hearc.gui.control;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.gui.control.atome.JSprayColors;
import ch.hearc.gui.control.atome.JSprayParameters;
import ch.hearc.gui.dessin.calques.JCalques;
import ch.hearc.gui.display.Display_I;

public class JControl extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JControl(Display_I display)
		{
		super(BoxLayout.Y_AXIS);

		this.display = display;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.jSprayParameters = new JSprayParameters(this.display);
		this.jSprayColors = new JSprayColors(this.display);
		this.jCalques = new JCalques(this.display);

		add(createVerticalGlue());
		add(this.jCalques);
		add(createVerticalGlue());
		add(this.jSprayParameters);
		add(createVerticalGlue());
		add(this.jSprayColors);
		add(createVerticalGlue());
		}

	private void control()
		{
		// Rien pour l'instant
		}

	private void appearance()
		{
		// debug
		setOpaque(true);
		setBackground(new Color(50, 50, 50, 255));
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public JCalques getJCalques()
		{
		return this.jCalques;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Display_I display;

	// Tools
	private JSprayParameters jSprayParameters;
	private JSprayColors jSprayColors;
	private JCalques jCalques;
	}
