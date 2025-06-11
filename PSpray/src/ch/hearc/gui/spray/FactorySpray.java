
package ch.hearc.gui.spray;

import java.io.IOException;

public class FactorySpray
	{

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public static JSpray_I create() throws IOException
		{
		return new JSpray();
		}

	}
