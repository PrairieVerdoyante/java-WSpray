
package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import ch.hearc.gui.dessin.image.ImageShop;
import ch.hearc.gui.spray.display.JSprayConfigurable_I;
import ch.hearc.gui.utils.SprayShape;

public class JSprayParameters extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayParameters(JSprayConfigurable_I spray)
		{
		super(BoxLayout.Y_AXIS);

		this.spray = spray;

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
		this.buttonCercle = new JToggleButton(ImageShop.CERCLE);
		this.buttonCarre = new JToggleButton(ImageShop.CARRE);

		// Groupement exclusif des boutons
		ButtonGroup groupForme = new ButtonGroup();
		groupForme.add(buttonCercle);
		groupForme.add(buttonCarre);

		// Panel pour aligner les boutons sur la même ligne
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		panelButtons.setOpaque(false); // Rendre le panel transparent
		panelButtons.add(buttonCercle);
		panelButtons.add(Box.createHorizontalStrut(10)); // Espacement entre les boutons
		panelButtons.add(buttonCarre);

		// Label pour la forme de sprayage
		JLabel labelForme = new JLabel("Forme de sprayage");
		labelForme.setOpaque(false);
		labelForme.setForeground(Color.WHITE);

		// Ajout dans le composant
		this.add(labelForme);
		this.add(panelButtons);

		this.sliderDimension = new JSlider(5, 100, 20);
		sliderDimension.setOpaque(false);

		JLabel labelDimension = new JLabel("Dimension de la forme");
		labelDimension.setOpaque(false);
		labelDimension.setForeground(Color.WHITE);

		// Label de valeur
		labelDimensionValue = new JLabel(String.valueOf(sliderDimension.getValue()));
		labelDimensionValue.setForeground(Color.WHITE);

		// Panel horizontal
		JPanel panelDimension = new JPanel();
		panelDimension.setLayout(new BoxLayout(panelDimension, BoxLayout.X_AXIS));
		panelDimension.setOpaque(false);
		panelDimension.add(sliderDimension);
		panelDimension.add(Box.createHorizontalStrut(10));
		panelDimension.add(labelDimensionValue);

		this.add(labelDimension);
		this.add(panelDimension);

		this.sliderTaille = new JSlider(1, 30, 5);
		sliderTaille.setOpaque(false);

		JLabel labelTaille = new JLabel("Taille des gouttelettes");
		labelTaille.setOpaque(false);
		labelTaille.setForeground(Color.WHITE);

		// Label de valeur
		labelTailleValue = new JLabel(String.valueOf(sliderTaille.getValue()));
		labelTailleValue.setForeground(Color.WHITE);

		// Panel horizontal
		JPanel panelTaille = new JPanel();
		panelTaille.setLayout(new BoxLayout(panelTaille, BoxLayout.X_AXIS));
		panelTaille.setOpaque(false);
		panelTaille.add(sliderTaille);
		panelTaille.add(Box.createHorizontalStrut(10));
		panelTaille.add(labelTailleValue);

		this.add(labelTaille);
		this.add(panelTaille);

		this.sliderNbGouttes = new JSlider(10, 200, 50);
		sliderNbGouttes.setOpaque(false);

		JLabel labelNbGouttes = new JLabel("Nombre de gouttelettes");
		labelNbGouttes.setOpaque(false);
		labelNbGouttes.setForeground(Color.WHITE);

		// Label de valeur
		labelNbGouttesValue = new JLabel(String.valueOf(sliderNbGouttes.getValue()));
		labelNbGouttesValue.setForeground(Color.WHITE);

		// Panel horizontal
		JPanel panelNbGouttes = new JPanel();
		panelNbGouttes.setLayout(new BoxLayout(panelNbGouttes, BoxLayout.X_AXIS));
		panelNbGouttes.setOpaque(false);
		panelNbGouttes.add(sliderNbGouttes);
		panelNbGouttes.add(Box.createHorizontalStrut(10));
		panelNbGouttes.add(labelNbGouttesValue);

		this.add(labelNbGouttes);
		this.add(panelNbGouttes);

		}

	private void control()
		{

		this.buttonCercle.addActionListener(e -> {
		spray.setForme(SprayShape.CERCLE);
		System.out.println("Spray en cercle sélectionné");
		});

		this.buttonCarre.addActionListener(e -> {
		spray.setForme(SprayShape.CARRE);
		System.out.println("Spray en carre sélectionné");
		});

		this.sliderDimension.addChangeListener(e -> {
		int dimension = sliderDimension.getValue();
		spray.setDimension(dimension);
		labelDimensionValue.setText(String.valueOf(dimension));
		System.out.println("Nouvelle dimension de la forme : " + dimension);
		});

		this.sliderTaille.addChangeListener(e -> {
		int taille = sliderTaille.getValue();
		spray.setTaille(taille);
		labelTailleValue.setText(String.valueOf(taille));
		System.out.println("Nouvelle taille de gouttelette : " + taille);
		});

		this.sliderNbGouttes.addChangeListener(e -> {
		int nb = sliderNbGouttes.getValue();
		spray.setNbGouttes(nb);
		labelNbGouttesValue.setText(String.valueOf(nb));
		System.out.println("Nouveau nombre de gouttelettes : " + nb);
		});

		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Paramètres du Spray", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JSprayConfigurable_I spray;

	// Tools
	private JSlider sliderDimension;
	private JSlider sliderTaille;
	private JSlider sliderNbGouttes;
	private JToggleButton buttonCercle;
	private JToggleButton buttonCarre;
	private JLabel labelDimensionValue;
	private JLabel labelTailleValue;
	private JLabel labelNbGouttesValue;

	}
