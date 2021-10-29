package fr.javatouml.uml;

public class Uml {

	public static final String PUBLIC = "public";
	public static final String PRIVATE = "private";
	public static final String PROTECTED = "protected";
	public static final String PACKAGE = "";

	public static String getVisibilitySymbol(String visibility) {
		if (visibility.equals(PUBLIC))
			return "+ ";

		if (visibility.equals(PRIVATE))
			return "- ";

		if (visibility.equals(PROTECTED))
			return "# ";

		return "";
	}

	public static String toBaseUml(Section section) {
		StringBuilder sb = new StringBuilder();
		sb.append(section.getVisibility()).append(section.ifStatic()).append(section.ifAbstract())
				.append(section.getType()).append(section.getName());
		return sb.toString();
	}
}
