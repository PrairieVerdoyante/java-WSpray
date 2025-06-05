
package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import ch.hearc.gui.spray.JSprayConfigurable_I;

public class JSprayColors extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayColors(JSprayConfigurable_I spray)
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
		JPanel panelColors = new JPanel(new GridLayout(2, 6, 5, 5));
		panelColors.setOpaque(false);

		ButtonGroup groupColor = new ButtonGroup();

		// Create buttons for predefined colors
		for(Color color:predefinedColors)
			{
			JToggleButton button = createColorButton(color);
			colorButtons.put(color, button);
			groupColor.add(button);
			panelColors.add(button);
			}

		// Create the custom color ("+")
		buttonPersonnalisee = createColorButton(Color.GRAY);
		buttonPersonnalisee.setText("+");
		buttonPersonnalisee.setFont(new Font("Arial", Font.BOLD, 40));
		buttonPersonnalisee.setForeground(Color.BLACK);
		buttonPersonnalisee.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonPersonnalisee.setVerticalTextPosition(SwingConstants.CENTER);

		groupColor.add(buttonPersonnalisee);
		panelColors.add(buttonPersonnalisee);

		this.add(panelColors);
		}

	private JToggleButton createColorButton(Color color)
		{
		JToggleButton button = new JToggleButton();
		button.setBackground(color);
		button.setPreferredSize(new Dimension(40, 40));
		button.setMaximumSize(new Dimension(40, 40));
		button.setMinimumSize(new Dimension(40, 40));
		button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		button.setFocusPainted(false);

		// Ajout du "+" si c'est la couleur personnalisée
		if (color.equals(Color.GRAY))
			{
			button.setText("+");
			button.setFont(new Font("Arial", Font.BOLD, 40)); // Optionnel : grosse police pour bien voir le +
			button.setForeground(Color.BLACK); // Couleur du texte
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setVerticalTextPosition(SwingConstants.CENTER);
			}

		return button;
		}

	private void control()
		{
		ActionListener listener = new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				Color couleur = null;

				if (e.getSource() == buttonPersonnalisee)
					{
					couleur = JColorChooser.showDialog(null, "Choisissez votre couleur", Color.WHITE);
					if (couleur != null)
						{
						couleurPersonnalisee = couleur;
						buttonPersonnalisee.setBackground(couleur);
						}
					}
				else
					{
					couleur = getSelectedColor();
					}

				updateDisplayColor(couleur);
				}
			};

		// Assign listener to all buttons
		for(JToggleButton button:colorButtons.values())
			{
			button.addActionListener(listener);
			}

		buttonPersonnalisee.addActionListener(listener);
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Couleur du Spray", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));
		}

	/*------------------------------------------------------------------*\
	|*							Public Method							*|
	\*------------------------------------------------------------------*/

	public Color getSelectedColor()
		{
		for(Map.Entry<Color, JToggleButton> entry:colorButtons.entrySet())
			{
			if (entry.getValue().isSelected())
				{ return entry.getKey(); }
			}

		if (buttonPersonnalisee.isSelected() && couleurPersonnalisee != null)
			{ return couleurPersonnalisee; }

		return Color.BLACK;
		}

	private void updateDisplayColor(Color couleur)
		{
		if (couleur != null)
			{
			spray.setColor(couleur);
			System.out.println("Couleur mise à jour : " + couleur);
			}

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JSprayConfigurable_I spray;

	// Tools
	private final Color[] predefinedColors = { Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.WHITE };

	private final Map<Color, JToggleButton> colorButtons = new LinkedHashMap<>();
	private JToggleButton buttonPersonnalisee;
	private Color couleurPersonnalisee;
	}
