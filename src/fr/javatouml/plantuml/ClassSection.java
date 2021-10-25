package fr.javatouml.plantuml;

import java.util.ArrayList;
import java.util.List;

public class ClassSection extends Section {

	private List<Section> sections;
	private String superClass;
	private List<String> interfaces;

	public ClassSection(String name, boolean abstractSection) {
		super("", name, "", abstractSection, false);

		sections = new ArrayList<Section>();
		interfaces = new ArrayList<>();
		superClass = null;
	}

	public void addSection(Section section) {
		sections.add(section);
	}

	public void addSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public void implementInterface(String inter) {
		interfaces.add(inter);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String sup = "";
		String inter = "";

		String ab = "";
		if (isAbstractSection())
			ab = "abstract ";

		if (superClass != null)
			sup = "extends ".concat(superClass).concat(" ");

		if (!interfaces.isEmpty()) {
			StringBuilder sbI = new StringBuilder();
			sbI.append("implements ");
			for (int i = 0; i < interfaces.size(); i++) {
				sbI.append(interfaces.get(i));

				if (i != interfaces.size() - 1)
					sbI.append(", ");
			}

			sbI.append(" ");
			inter = sbI.toString();
		}

		sb.append(ab).append("class ").append(getName()).append(" ").append(sup).append(inter).append("{\n");

		for (Section section : sections)
			sb.append(section.toString());

		sb.append("}\n");

		return sb.toString();
	}

	public String getSuperClass() {
		return superClass;
	}

	public List<Section> getSections() {
		return sections;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}
}
