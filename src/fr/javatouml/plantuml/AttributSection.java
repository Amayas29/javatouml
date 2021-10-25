package fr.javatouml.plantuml;

public class AttributSection extends Section {

	public AttributSection(String visibility, String name, String type, boolean staticSection) {
		super(visibility, name, type, false, staticSection);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		String st = "";
		if (isStaticSection())
			st = "{static} ";

		sb.append(" ").append(PlantUml.getVisibilitySymbol(getVisibility())).append(" ").append(st).append(getName())
				.append(": ").append(getType()).append("\n");

		return sb.toString();
	}
}
