package fr.javatouml.uml;

public interface Section {

    public String getVisibility();

    public String getName();

    public String getType();

    public String ifAbstract();

    public String ifStatic();

    public String toPlantUmlString();
}
