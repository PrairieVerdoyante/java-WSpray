package ch.hearc.gui.app;

import javax.swing.JFrame;

import ch.hearc.c_gui.z_tools.decorator.JFrameSimple;

public class JFrameSpray extends JFrame
{
    /*------------------------------------------------------------------*\
    |*                          Methods Public                        *|
    \*------------------------------------------------------------------*/

    public static void main(String[] args)
    {
        JApp app = new JApp();

        // You can add an icon if needed:
        // ImageIcon icon = new ImageIcon("res/icon.png");
        // new JFrameSimple(app, "Spray", icon);

        new JFrameSimple(app, "Spray"); // icon possible
    }
}
