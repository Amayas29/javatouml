package fr.javatouml.plantuml;

public class PlantUml {

	public static final String PUBLIC = "public";
	public static final String PRIVATE = "private";
	public static final String PROTECTED = "protected";
	public static final String PACKAGE = "";

	public static String getVisibilitySymbol(String visibility) {
		if (visibility == PUBLIC)
			return "+";

		if (visibility == PRIVATE)
			return "-";

		if (visibility == PROTECTED)
			return "#";

		return "";
	}
}
