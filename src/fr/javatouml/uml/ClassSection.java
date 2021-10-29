package fr.javatouml.uml;

import java.util.List;

public class ClassSection extends InterfaceSection {

    private boolean isAbstract;
    private String superClass;

    public ClassSection(String name, boolean isAbstract) {
        super(name);
        this.isAbstract = isAbstract;
        superClass = null;
    }

    @Override
    public String getType() {
        return "class ";
    }

    @Override
    public String ifAbstract() {
        return isAbstract ? "abstract " : "";
    }

    @Override
    public void extendsSection(String section) {
        if (section == null)
            return;

        superClass = section;
    }

    public void implementsInterface(String interfaceSection) {
        super.extendsSection(interfaceSection);
    }

    @Override
    public String toPlantUmlString() {
        StringBuilder sb = new StringBuilder(Uml.toBaseUml(this));
        String sup = "", inter = "";

        if (superClass != null)
            sup = "extends ".concat(superClass).concat(" ");

        List<String> interfaces = getInterfaces();
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

        sb.append(sup).append(inter).append("{\n");

        for (Section section : getSections())
            sb.append(section.toPlantUmlString()).append("\n");

        sb.append("}\n");
        return sb.toString();
    }
}
