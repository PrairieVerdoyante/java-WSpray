
package ch.hearc.gui.control.atome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import ch.hearc.c_gui.b_layout.b_composition.d_final.display.Display_I;

public class JCalques extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JCalques()
		{
		super(BoxLayout.Y_AXIS);

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
		this.add(this.labelCalques);
		this.calqueCheckBoxes = new ArrayList<>();

		// Imaginons une liste de noms de calques
		List<String> calques = List.of("Calque 1", "Calque 2", "Calque 3", "Calque 4");

		for(String nomCalque:calques)
			{
			JCheckBox cb = new JCheckBox(nomCalque);
			this.calqueCheckBoxes.add(cb);
			this.add(cb);
			}
		}

	private void control()
		{

		for(JCheckBox cb:this.calqueCheckBoxes)
			{
			cb.addActionListener(new ActionListener()
				{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (cb.isSelected())
						{
						System.out.println(cb.getText() + " sélectionné");
						}
					else
						{
						System.out.println(cb.getText() + " désélectionné");
						}
					}
				});
			}

		}

	private void appearance()
		{
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Calques");
		setBorder(titledBorder);
		}

	// TODO deplacer dans une interface
	public List<String> getSelectedCalques()
		{
		List<String> selected = new ArrayList<>();
		for(JCheckBox cb:calqueCheckBoxes)
			{
			if (cb.isSelected())
				{
				selected.add(cb.getText());
				}
			}
		return selected;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Display_I display;

	// Tools
	JLabel labelCalques;
	private List<JCheckBox> calqueCheckBoxes;

	}
