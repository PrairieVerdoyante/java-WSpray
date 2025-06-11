
package ch.hearc.gui.utils;

public enum SprayShape
	{

CERCLE("Cercle"), CARRE("Carre");

	private final String displayName;

	SprayShape(String displayName)
		{
		this.displayName = displayName;
		}

	public String getDisplayName()
		{
		return displayName;
		}
	}
