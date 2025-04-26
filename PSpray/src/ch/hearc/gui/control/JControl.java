
package ch.hearc.gui.control;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.c_gui.b_layout.b_composition.d_final.Settings;
import ch.hearc.gui.control.atome.JSprayColors;
import ch.hearc.gui.control.atome.JSprayParameters;

public class JControl extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JControl()
		{
		super(BoxLayout.Y_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.jSprayParameters = new JSprayParameters();
		this.jSprayColorSquaresGrid = new JSprayColors();

		add(createVerticalStrut(Settings.GAP));
		add(this.jSprayParameters);
		add(createVerticalStrut(Settings.GAP));
		add(this.jSprayColorSquaresGrid);
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
		setBackground(Color.PINK);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JSprayParameters jSprayParameters;
	private JSprayColors jSprayColorSquaresGrid;
	}
