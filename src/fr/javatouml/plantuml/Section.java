package fr.javatouml.plantuml;

public abstract class Section {
	private String visibility;
	private String name;
	private String type;

	public Section(String visibility, String name, String type) {
		this.visibility = visibility;
		this.name = name;
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
