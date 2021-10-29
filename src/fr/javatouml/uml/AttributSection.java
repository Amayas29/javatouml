package fr.javatouml.uml;

public class AttributSection implements Section {
    private String visibility;
    private String name;
    private String type;

    private boolean isStatic;

    public AttributSection(String visibility, String name, String type, boolean isStatic) {
        this.visibility = visibility;
        this.name = name;
        this.type = type;
        this.isStatic = isStatic;
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
        return "";
    }

    @Override
    public String ifStatic() {
        return isStatic ? "{static} " : "";
    }

    @Override
    public String toPlantUmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(getVisibility()).append(ifStatic()).append(getName()).append(": ").append(getType())
                .append("\n");
        return sb.toString();
    }
}
