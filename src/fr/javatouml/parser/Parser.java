package fr.javatouml.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.javatouml.plantuml.AttributSection;
import fr.javatouml.plantuml.ClassSection;
import fr.javatouml.plantuml.InterfaceSection;
import fr.javatouml.plantuml.MethodSection;
import fr.javatouml.plantuml.Section;

public class Parser {

  private final String command = "npx prettier --print-width 9999999999999999999 --tab-width 4 --use-tabs true --trailing-comma none --write ";

  private String filename;
  private List<Section> sections;

  public Parser(String filename) {
    this.filename = filename;
    sections = new ArrayList<>();
  }

  private void format() {
    String cmd = command.concat(filename);

    try {
      Process process = Runtime.getRuntime().exec(cmd);
      process.waitFor();
      BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = "";
      while ((line = buf.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void generateUml() {
    ClassSection c = new ClassSection("Test", true);

    c.addSuperClass("Objet");
    c.implementInterface("Mutable");
    c.implementInterface("Incremable");

    c.addSection(new AttributSection("public", "a", "int", true));
    c.addSection(new MethodSection("private", "getA", "int", true, true));

    sections.add(c);

    InterfaceSection i = new InterfaceSection("Mutable");
    i.addSection(new MethodSection("public", "getA", "int", false, false));
    sections.add(i);
  }

  public void save(String output) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));) {
      StringBuilder sb = new StringBuilder();
      sb.append("@startuml\n");

      for (Section c : sections)
        sb.append(c.toString()).append("\n");

      sb.append("@enduml");
      writer.write(sb.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void parse(String output) {
    format();
    generateUml();
    save(output);
  }
}
