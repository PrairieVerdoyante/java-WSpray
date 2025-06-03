
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

import ch.hearc.gui.display.JSprayConfigurable_I;

public class JCalques extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JCalques(JSprayConfigurable_I spray)
		{
		super(BoxLayout.X_AXIS);

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

		// add a base calque
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

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Calques", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));

		panelCheckBoxes.setOpaque(false);
		panelBoutons.setOpaque(false);
		}

	private void addNewCalque()
		{
		// adds the first default layer
		this.calqueCounter++;

		String name = "Calque " + this.calqueCounter;

		JCheckBox cb = new JCheckBox(name);
		cb.setOpaque(false);
		cb.setForeground(Color.WHITE);
		this.calqueCheckBoxes.add(cb);

		spray.addCalque(name);

		cb.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				spray.setCalqueVisible(name, cb.isSelected());
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
			spray.removeLastCalque();

			revalidate();
			repaint();

			this.calqueCounter--;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void resetCalques()
		{
		this.removeAll(); // remove everything including buttons, layout
		this.panelCheckBoxes.removeAll();
		this.calqueCheckBoxes.clear();
		this.calqueCounter = 0;

		// Rebuild layout structure (just like geometry())
		add(Box.createHorizontalStrut(10));
		add(this.panelCheckBoxes);
		add(this.panelBoutons);

		// Rebuild checkboxes from display's calques
		List<DCalque> calques = spray.getCalques();

		for(DCalque calque:calques)
			{
			String name = calque.getName();

			JCheckBox cb = new JCheckBox(name);
			cb.setOpaque(false);
			cb.setForeground(Color.WHITE);
			cb.setSelected(calque.isVisible());

			cb.addActionListener(e -> spray.setCalqueVisible(name, cb.isSelected()));

			this.calqueCheckBoxes.add(cb);
			this.panelCheckBoxes.add(cb);

			this.calqueCounter++;
			}

		revalidate();
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JSprayConfigurable_I spray;

	// Tools
	private List<JCheckBox> calqueCheckBoxes;
	private JPanel panelBoutons;
	private Box panelCheckBoxes;
	private JButton buttonAdd;
	private JButton buttonRemove;

	private int calqueCounter;
	private JCalques calques; // Add this field

	}
