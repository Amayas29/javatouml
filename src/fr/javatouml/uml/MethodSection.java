package fr.javatouml.uml;

import java.util.ArrayList;
import java.util.List;

public class MethodSection implements Section {

    private String visibility;
    private String name;
    private String type;
    private List<String> args;

    private boolean isAbstract;
    private boolean isStatic;

    public MethodSection(String visibility, String name, String type, boolean isAbstract, boolean isStatic) {
        this.visibility = visibility;
        this.name = name;
        this.type = type;
        this.isAbstract = isAbstract;
        this.isStatic = isStatic;
        args = new ArrayList<>();
    }

    public void addArg(String arg) {
        if (arg == null)
            return;

        args.add(arg);
    }

    @Override
    public String getVisibility() {
        return Uml.getVisibilitySymbol(visibility);
    }

    @Override
    public String getName() {
        return name.concat(" ");
    }

    @Override
    public String getType() {
        return type.concat(" ");
    }

    @Override
    public String ifAbstract() {
        return isAbstract ? "{abstract} " : "";
    }

    @Override
    public String ifStatic() {
        return isStatic ? "{static} " : "";
    }

    @Override
    public String toPlantUmlString() {
        StringBuilder sb = new StringBuilder(" ");
        sb.append(Uml.toBaseUml(this));

        sb.append("(");

        for (int i = 0; i < args.size(); i++) {
            sb.append(args.get(i));

            if (i != args.size() - 1)
                sb.append(", ");
        }

        sb.append(")\n");

        return sb.toString();
    }

}
