package fr.javatouml.plantuml;

import java.util.ArrayList;
import java.util.List;

public class MethodSection extends Section {

	private List<String> args;

	public MethodSection(String visibility, String name, String type, boolean abstractSection, boolean staticSection) {
		super(visibility, name, type, abstractSection, staticSection);
		args = new ArrayList<String>();
	}

	public void addArg(String pattern) {
		args.add(pattern);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String ab = "";
		if (isAbstractSection())
			ab = "{abstract} ";

		String st = "";
		if (isStaticSection())
			st = "{static} ";

		sb.append(" ").append(PlantUml.getVisibilitySymbol(getVisibility())).append(" ").append(st).append(ab)
				.append(getType()).append(" ").append(getName()).append("(");

		for (int i = 0; i < args.size(); i++) {
			sb.append(args.get(i));

			if (i != args.size() - 1)
				sb.append(", ");
		}

		sb.append(")\n");

		return sb.toString();
	}
}
