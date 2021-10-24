package fr.javatouml.plantuml;

public class AttributSection extends Section {

	public AttributSection(String visibility, String name, String type) {
		super(visibility, name, type);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(" ").append(PlantUml.getVisibilitySymbol(getVisibility())).append(" ").append(getName()).append(": ")
				.append(getType()).append("\n");

		return sb.toString();
	}
}
