package fr.javatouml.uml;

import java.util.ArrayList;
import java.util.List;

public class EnumSection implements Section {

    private String name;
    private List<String> values;

    public EnumSection(String name) {
        this.name = name;
        values = new ArrayList<>();
    }

    public void addValue(String value) {
        if (value == null)
            return;

        values.add(value);
    }

    @Override
    public String getVisibility() {
        return "";
    }

    @Override
    public String getName() {
        return name.concat(" ");
    }

    @Override
    public String getType() {
        return "enum ";
    }

    @Override
    public String ifAbstract() {
        return "";
    }

    @Override
    public String ifStatic() {
        return "";
    }

    @Override
    public String toPlantUmlString() {
        StringBuilder sb = new StringBuilder(Uml.toBaseUml(this));

        sb.append("{\n");

        for (String value : values)
            sb.append(" ").append(value).append("\n");

        sb.append("}\n");
        return sb.toString();
    }
}
