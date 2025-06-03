
package ch.hearc.gui.display;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.hearc.gui.JApp;
import ch.hearc.gui.control.JControl;

public class JFrameSpray extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeur							*|
	\*------------------------------------------------------------------*/

	public JFrameSpray() throws IOException
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry() throws IOException
		{
		// Display (dessin)
		this.jSpray = JSpray.getInstance();

		// Control panel (paramètres + calques)
		this.jControl = new JControl(jSpray);
		jSpray.setJCalques(jControl.getJCalques());

		// Layout
		setLayout(new BorderLayout());
		add(jControl, BorderLayout.WEST);
		add(jSpray, BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("JSpray");
		setSize(1200, 800);
		setLocationRelativeTo(null); // Centré à l'écran
		setVisible(true);
		setContentPane(new JApp());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JSpray jSpray;
	private JControl jControl;

	}
