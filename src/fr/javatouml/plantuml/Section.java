package fr.javatouml.plantuml;

public abstract class Section {
	private String visibility;
	private String name;
	private String type;
	private boolean abstractSection;
	private boolean staticSection;

	public Section(String visibility, String name, String type, boolean abstractSection, boolean staticSection) {
		this.visibility = visibility;
		this.name = name;
		this.type = type;
		this.abstractSection = abstractSection;
		this.staticSection = staticSection;
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

	public boolean isAbstractSection() {
		return abstractSection;
	}

	public boolean isStaticSection() {
		return staticSection;
	}
}
