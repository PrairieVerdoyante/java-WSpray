
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.gui.utils.SprayShape;

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
	public void setColor(Color color)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setNbGouttes(int nbGouttes)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setTaille(int taille)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setForme(SprayShape shape)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void setCalques(int n)
		{
		// TODO Auto-generated method stub

		}

	@Override
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
