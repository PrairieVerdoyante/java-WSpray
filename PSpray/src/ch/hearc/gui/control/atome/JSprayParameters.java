
package ch.hearc.gui.control.atome;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSprayParameters extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayParameters()
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
		// Surface de sprayage
		this.radioCercle = new JRadioButton("Cercle");
		this.radioRectangle = new JRadioButton("Rectangle");

		ButtonGroup groupForme = new ButtonGroup();
		groupForme.add(radioCercle);
		groupForme.add(radioRectangle);

		this.add(new JLabel("Forme de sprayage"));
		this.add(radioCercle);
		this.add(radioRectangle);

		// Taille des gouttelettes
		this.sliderTaille = new JSlider(1, 100, 10); // min 1, max 100, initial 10
		this.add(new JLabel("Taille des gouttelettes"));
		this.add(sliderTaille);

		// Nombre de gouttelettes
		this.sliderNbGouttes = new JSlider(1, 500, 50); // min 1, max 500, initial 50
		this.add(new JLabel("Nombre de gouttelettes"));
		this.add(sliderNbGouttes);
		}

	private void control()
		{
		this.sliderTaille.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				int taille = sliderTaille.getValue();
				System.out.println("Nouvelle taille de gouttelette : " + taille);
				}
			});

		this.radioCercle.addActionListener(e -> System.out.println("Spray en cercle sélectionné"));
		this.radioRectangle.addActionListener(e -> System.out.println("Spray en rectangle sélectionné"));

		this.sliderNbGouttes.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				int nb = sliderNbGouttes.getValue();
				System.out.println("Nouveau nombre de gouttelettes : " + nb);
				}
			});
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder("Paramètres du Spray"));

		// debug
		setOpaque(true);
		setBackground(Color.BLUE);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JSlider sliderTaille;
	private JSlider sliderNbGouttes;
	private JRadioButton radioCercle;
	private JRadioButton radioRectangle;
	}
