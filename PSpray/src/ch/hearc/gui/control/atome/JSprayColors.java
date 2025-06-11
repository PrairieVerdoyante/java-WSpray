
package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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

import ch.hearc.gui.spray.display.JSprayConfigurable_I;

public class JSprayColors extends Box
	{
	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayColors(JSprayConfigurable_I spray)
		{
		super(BoxLayout.Y_AXIS);

		this.spray = spray;
		this.couleursRecentes = new LinkedList<>();

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// ---------- Section Couleurs Prédéfinies ----------
		JPanel panelCouleursPredefinies = new JPanel(new GridLayout(2, 5, 5, 5));
		panelCouleursPredefinies.setOpaque(false);

		groupColor = new ButtonGroup();

		for(Color color:predefinedColors)
			{
			JToggleButton button = createStyledColorButton(color);
			colorButtons.put(color, button);
			groupColor.add(button);
			panelCouleursPredefinies.add(button);
			}

		this.add(panelCouleursPredefinies);

		// ---------- Section Bouton Personnalisé ----------
		JPanel panelBoutonPersonnalise = new JPanel();
		panelBoutonPersonnalise.setOpaque(false);

		buttonPersonnalisee = createStyledColorButton(Color.LIGHT_GRAY);
		buttonPersonnalisee.setText("+");
		buttonPersonnalisee.setFont(new Font("Arial", Font.BOLD, 30));
		buttonPersonnalisee.setForeground(Color.BLACK);
		buttonPersonnalisee.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonPersonnalisee.setVerticalTextPosition(SwingConstants.CENTER);

		groupColor.add(buttonPersonnalisee);
		panelBoutonPersonnalise.add(buttonPersonnalisee);

		this.add(panelBoutonPersonnalise);

		// ---------- Section Couleurs Personnalisées Récentes ----------
		panelCouleursRecentes = new JPanel(new GridLayout(1, 5, 5, 5));
		panelCouleursRecentes.setOpaque(false);
		this.add(panelCouleursRecentes);
		}

	private JToggleButton createStyledColorButton(Color color)
		{
		JToggleButton button = new JToggleButton();
		button.setBackground(color);
		button.setPreferredSize(new Dimension(50, 50));
		button.setMaximumSize(new Dimension(50, 50));
		button.setMinimumSize(new Dimension(50, 50));
		button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);

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
						ajouterCouleurRecente(couleur);
						}
					}
				else
					{
					couleur = getSelectedColor();
					}

				updateDisplayColor(couleur);
				}
			};

		// Assigner listener à tous les boutons prédéfinis
		for(JToggleButton button:colorButtons.values())
			{
			button.addActionListener(listener);
			}

		buttonPersonnalisee.addActionListener(listener);
		}

	private void ajouterCouleurRecente(Color couleur)
		{
		couleursRecentes.remove(couleur); // Supprime les doublons
		couleursRecentes.add(0, couleur); // Ajoute en début de liste

		if (couleursRecentes.size() > 5)
			{
			couleursRecentes = couleursRecentes.subList(0, 5); // Limite à 5 couleurs
			}

		rafraichirCouleursRecentes();
		}

	private void rafraichirCouleursRecentes()
		{
		panelCouleursRecentes.removeAll();
		ButtonGroup groupRecents = new ButtonGroup();

		for(Color couleur:couleursRecentes)
			{
			JToggleButton boutonRecent = createStyledColorButton(couleur);

			boutonRecent.addActionListener(e -> {
			spray.setColor(couleur);
			System.out.println("Couleur récente sélectionnée : " + couleur);

			// Désélectionner tous les boutons principaux
			groupColor.clearSelection();
			});

			groupRecents.add(boutonRecent);
			panelCouleursRecentes.add(boutonRecent);
			}

		panelCouleursRecentes.revalidate();
		panelCouleursRecentes.repaint();
		}

	private void updateDisplayColor(Color couleur)
		{
		if (couleur != null)
			{
			spray.setColor(couleur);
			System.out.println("Couleur mise à jour : " + couleur);
			}
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Palette de Couleurs", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JSprayConfigurable_I spray;

	// Tools
	private final Color[] predefinedColors = { Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.WHITE };
	private final Map<Color, JToggleButton> colorButtons = new LinkedHashMap<>();
	private ButtonGroup groupColor;

	private JToggleButton buttonPersonnalisee;
	private Color couleurPersonnalisee;

	private List<Color> couleursRecentes;
	private JPanel panelCouleursRecentes;
	}
