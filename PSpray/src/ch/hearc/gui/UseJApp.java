
package ch.hearc.gui;

import javax.swing.JFrame;

import ch.hearc.c_gui.z_tools.decorator.JFrameSimple;
import ch.hearc.c_gui.z_tools.decorator.JMarge;

public class UseJApp extends JFrame
	{
	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		new JFrameSimple(new JMarge(new JApp(), 0), "Spray App");
		//new JFrameSimple(new JApp(), "Spray App");
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	}
