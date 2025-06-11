
package ch.hearc.gui.app;

import javax.swing.JFrame;

import ch.hearc.c_gui.z_tools.decorator.JFrameSimple;

public class JFrameSpray extends JFrame
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
		JApp app = new JApp();

		new JFrameSimple(app, "Spray");
		}
	}
