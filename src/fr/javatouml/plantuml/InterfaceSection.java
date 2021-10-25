package fr.javatouml.plantuml;

public class InterfaceSection extends ClassSection {

    public InterfaceSection(String name) {
        super(name, false);
    }

    @Override
    public void addSuperClass(String superClass) {
        implementInterface(superClass);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        StringBuilder supB = new StringBuilder();

        for (int i = 0; i < getInterfaces().size(); i++) {

            String inter = getInterfaces().get(i);

            if (supB.isEmpty())
                supB.append("extends ");

            supB.append(inter);

            if (i != getInterfaces().size() - 1)
                supB.append(", ");
        }

        supB.append(" ");
        String sup = supB.toString();

        sb.append("interface ").append(getName()).append(" ").append(sup).append("{\n");

        for (Section section : getSections())
            sb.append(section.toString());

        sb.append("}\n");

        return sb.toString();
    }

}
