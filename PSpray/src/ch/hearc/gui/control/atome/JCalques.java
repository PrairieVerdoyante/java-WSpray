
package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import ch.hearc.gui.display.Display_I;

public class JCalques extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JCalques(Display_I display)
		{
		super(BoxLayout.Y_AXIS);

		this.display = display;

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
		this.calqueCheckBoxes = new ArrayList<>();
		this.calqueCounter = 1;

		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.buttonAdd = new JButton("+");
		this.buttonRemove = new JButton("-");

		this.panelBoutons.add(buttonAdd);
		this.panelBoutons.add(buttonRemove);

		add(this.panelBoutons);
		add(Box.createVerticalStrut(19));

		}

	private void control()
		{

		this.buttonAdd.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				addNewCalque();
				}
			});

		this.buttonRemove.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				removeLastCalque();
				}
			});

		}

	private void addNewCalque()
		{
		JCheckBox cb = new JCheckBox("Calque " + this.calqueCounter++);
		this.calqueCheckBoxes.add(cb);

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

		add(cb);

		// mettre a jour le nb de calques
		display.setCalques(this.calqueCounter);
		revalidate();
		repaint();
		}

	private void removeLastCalque()
		{
		if (!this.calqueCheckBoxes.isEmpty())
			{
			JCheckBox cb = this.calqueCheckBoxes.remove(this.calqueCheckBoxes.size() - 1);

			if (cb.isSelected())
				{
				System.out.println(cb.getText() + "Déselectionné");
				}

			remove(cb);

			// mettre a jour le nb de calques
			display.setCalques(calqueCounter);
			revalidate();
			repaint();

			this.calqueCounter--;
			}
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder("Calques"));

		// debug
		setOpaque(true);
		setBackground(Color.GREEN);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Display_I display;

	// Tools
	private List<JCheckBox> calqueCheckBoxes;
	private JPanel panelBoutons;
	private JButton buttonAdd;
	private JButton buttonRemove;

	private int calqueCounter;

	}
