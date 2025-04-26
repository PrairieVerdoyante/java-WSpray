
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

/**
 * singleton
 */
public class JDisplay extends JPanel implements Display_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void setBrushColor(Color color)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setBackgroundColor(Color color)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setCalques(int n)
		{
		// TODO Auto-generated method stub

		}

	public List<String> getSelectedCalques()
		{
		// TODO
		return null;
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized JDisplay getInstance()
		{
		if (instance == null)
			{
			instance = new JDisplay();
			}

		return instance;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// zone de dessin
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//debug
			{
			setBackground(Color.WHITE);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	// Inputs

	// Tools

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	private static JDisplay instance = null;

	}
