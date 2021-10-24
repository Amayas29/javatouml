package fr.javatouml.plantuml;

import java.util.ArrayList;
import java.util.List;

public class MethodSection extends Section {

	private List<String> args;

	public MethodSection(String visibility, String name, String type) {
		super(visibility, name, type);
		args = new ArrayList<String>();
	}

	public void addArg(String pattern) {
		args.add(pattern);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(" ").append(PlantUml.getVisibilitySymbol(getVisibility())).append(" ").append(getType()).append(" ")
				.append(getName()).append("(");

		for (int i = 0; i < args.size(); i++) {
			sb.append(args.get(i));

			if (i != args.size() - 1)
				sb.append(", ");
		}

		sb.append(")\n");

		return sb.toString();
	}
}
