
package ch.hearc.gui.dessin.calques;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.TitledBorder;

import ch.hearc.gui.display.Display_I;

public class JCalques extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JCalques(Display_I display)
		{
		super(BoxLayout.X_AXIS);

		this.display = display;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.calqueCheckBoxes = new ArrayList<>();
		this.calqueCounter = 0;

		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.buttonAdd = new JButton("+");
		this.buttonRemove = new JButton("-");

		this.panelCheckBoxes = new Box(BoxLayout.Y_AXIS);

		add(Box.createHorizontalStrut(10));

		this.panelBoutons.add(buttonAdd);
		this.panelBoutons.add(buttonRemove);

		add(this.panelCheckBoxes);
		add(this.panelBoutons);

		// adding a base calque
		addNewCalque();

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
		// incrémenter le numéro du calque avant de l'utiliser
		this.calqueCounter++;

		String name = "Calque " + this.calqueCounter;

		JCheckBox cb = new JCheckBox(name);
		cb.setOpaque(false);
		cb.setForeground(Color.WHITE);
		this.calqueCheckBoxes.add(cb);

		display.addCalque(name);

		cb.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				display.setCalqueVisible(name, cb.isSelected());
				}
			});

		cb.setSelected(true);
		this.panelCheckBoxes.add(cb);

		revalidate();
		repaint();
		}

	private void removeLastCalque()
		{
		if (!this.calqueCheckBoxes.isEmpty() && this.calqueCounter > 1)
			{
			JCheckBox cb = this.calqueCheckBoxes.remove(this.calqueCheckBoxes.size() - 1);

			// remove calque
			this.panelCheckBoxes.remove(cb);
			display.removeLastCalque();

			revalidate();
			repaint();

			this.calqueCounter--;
			}
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Calques", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));

		panelCheckBoxes.setOpaque(false);
		panelBoutons.setOpaque(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Display_I display;

	// Tools
	private List<JCheckBox> calqueCheckBoxes;
	private JPanel panelBoutons;
	private Box panelCheckBoxes;
	private JButton buttonAdd;
	private JButton buttonRemove;

	private int calqueCounter;

	}
