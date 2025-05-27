
package ch.hearc.gui.dessin.composition;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

import ch.hearc.c_gui.z_tools.DessinAnimable;
import ch.hearc.c_gui.z_tools.animation.moo.animator.AnimatorFactory;
import ch.hearc.c_gui.z_tools.animation.moo.animator.Animator_I;

public class JComposition extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JComposition()
		{
		this.dessinAnimable = new DComposition(this);
		this.animator = AnimatorFactory.create(dessinAnimable);

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
		// rien
		}

	private void control()
		{
		addComponentListener(createComponentListener());
		}

	private void appearance()
		{
		// rien
		}

	private ComponentListener createComponentListener()
		{
		return new ComponentAdapter()
			{

			@Override
			public void componentResized(ComponentEvent e)
				{
				JComposition.this.animator.start();
				}

			};
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	private DessinAnimable dessinAnimable;
	private Animator_I animator;
	}
