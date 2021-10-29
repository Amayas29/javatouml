package fr.javatouml.uml;

import java.util.ArrayList;
import java.util.List;

public class InterfaceSection implements Section {

    private String name;
    private List<String> interfaces;
    private List<Section> sections;

    public InterfaceSection(String name) {
        this.name = name;
        interfaces = new ArrayList<>();
        sections = new ArrayList<>();
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
        return "interface ";
    }

    @Override
    public String ifAbstract() {
        return "";
    }

    @Override
    public String ifStatic() {
        return "";
    }

    public void extendsSection(String section) {
        if (section == null)
            return;

        interfaces.add(section);
    }

    public void addSection(Section section) {
        if (section == null || section instanceof InterfaceSection)
            return;

        sections.add(section);
    }

    @Override
    public String toPlantUmlString() {
        StringBuilder sb = new StringBuilder(Uml.toBaseUml(this));
        StringBuilder supB = new StringBuilder();

        for (int i = 0; i < interfaces.size(); i++) {

            String inter = interfaces.get(i);

            if (supB.isEmpty())
                supB.append("extends ");

            supB.append(inter);

            if (i != interfaces.size() - 1)
                supB.append(", ");
        }

        supB.append(" ");
        String sup = supB.toString();

        sb.append(sup).append("{\n");

        for (Section section : sections)
            sb.append(section.toPlantUmlString()).append("\n");

        sb.append("}\n");

        return sb.toString();
    }

    public List<String> getInterfaces() {
        return new ArrayList<>(interfaces);
    }

    public List<Section> getSections() {
        return new ArrayList<>(sections);
    }
}
