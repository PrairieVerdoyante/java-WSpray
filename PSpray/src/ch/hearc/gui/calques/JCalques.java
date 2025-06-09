
package ch.hearc.gui.calques;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import ch.hearc.gui.spray.display.JSprayConfigurable_I;

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
		this.buttonAdd.addActionListener(e -> addNewCalque());

		this.buttonRemove.addActionListener(e -> removeLastCalque());
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Calques", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));

		panelCheckBoxes.setOpaque(false);
		panelBoutons.setOpaque(false);
		}

	private void addNewCalque()
		{
		this.calqueCounter++;
		String name = "Calque " + this.calqueCounter;

		spray.addCalque(name);
		JCheckBox cb = createCheckBoxForCalque(name);
		this.calqueCheckBoxes.add(cb);
		this.panelCheckBoxes.add(cb);

		revalidate();
		repaint();
		}

	private JCheckBox createCheckBoxForCalque(String name)
		{
		JCheckBox cb = new JCheckBox(name);
		cb.setOpaque(false);
		cb.setForeground(Color.WHITE);
		cb.setSelected(true);

		cb.addActionListener(e -> spray.setCalqueVisible(name, cb.isSelected()));

		cb.addMouseListener(new MouseAdapter()
			{

			@Override
			public void mouseClicked(MouseEvent e)
				{
				if (SwingUtilities.isRightMouseButton(e))
					{
					JPopupMenu menu = new JPopupMenu();
					JMenuItem itemSupprimer = new JMenuItem("Supprimer");
					JMenuItem itemSelectionner = new JMenuItem("Selectionner ce calque");

					itemSupprimer.addActionListener(ev -> {
					List<DCalque> copy = new ArrayList<>(spray.getCalques());
					for(DCalque calque:copy)
						{
						if (calque.getName().equals(name))
							{
							spray.removeCalque(calque);
							break;
							}
						}
					resetCalques();
					});

					itemSelectionner.addActionListener(ev -> {
					for(DCalque calque:spray.getCalques())
						{
						if (calque.getName().equals(name))
							{
							spray.setCurrentCalque(calque);
							break;
							}
						}
					});

					menu.add(itemSupprimer);
					menu.add(itemSelectionner);
					menu.show(cb, e.getX(), e.getY());
					}
				}
			});

		return cb;
		}

	private void removeLastCalque()
		{
		if (!this.calqueCheckBoxes.isEmpty() && this.calqueCounter > 1)
			{
			JCheckBox cb = this.calqueCheckBoxes.remove(this.calqueCheckBoxes.size() - 1);
			this.panelCheckBoxes.remove(cb);
			spray.removeLastCalque();
			this.calqueCounter--;

			revalidate();
			repaint();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void resetCalques()
		{
		this.panelCheckBoxes.removeAll();
		this.calqueCheckBoxes.clear();
		this.calqueCounter = 0;

		List<DCalque> calques = spray.getCalques();

		for(DCalque calque:calques)
			{
			this.calqueCounter++;
			JCheckBox cb = createCheckBoxForCalque(calque.getName());
			cb.setSelected(calque.isVisible());
			this.calqueCheckBoxes.add(cb);
			this.panelCheckBoxes.add(cb);
			}

		revalidate();
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JSprayConfigurable_I spray;

	private List<JCheckBox> calqueCheckBoxes;
	private JPanel panelBoutons;
	private Box panelCheckBoxes;
	private JButton buttonAdd;
	private JButton buttonRemove;

	private int calqueCounter;
	}
