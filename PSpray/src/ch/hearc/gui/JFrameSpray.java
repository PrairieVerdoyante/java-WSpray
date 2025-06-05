
package ch.hearc.gui;

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
		
		// You can add an icon if needed:
		// ImageIcon icon = new ImageIcon("res/icon.png");
		// new JFrameSimple(app, "Spray", icon);
		
		new JFrameSimple(app, "Spray"); // icon possible
		}
	}
