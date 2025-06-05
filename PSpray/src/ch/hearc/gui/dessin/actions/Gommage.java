
package ch.hearc.gui.dessin.actions;

import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;

public class Gommage extends Geste
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public Gommage(DCalque calque, boolean logImmediately, List<Sprayage> stroke)
		{
		super();
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{/*
		// Example erasing logic: remove sprays near (x, y) from calque
		List<DSpray> toRemove = new ArrayList<>();
		for(Sprayage spray:calque.getSprays())
			{
			if (spray.isNear(x, y))
				{
				toRemove.add(spray);
				stroke.add(spray); // log erased spray
				}
			}
		calque.getSprays().removeAll(toRemove);

		if (logImmediately)
			{
			// You can implement log for erasures if needed
			}
			*/
		}
	}
