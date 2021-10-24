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
import fr.javatouml.plantuml.MethodSection;

public class Parser {

  private final String command = "npx prettier --print-width 9999999999999999999 --tab-width 4 --use-tabs true --trailing-comma none --write ";

  private String filename;
  private List<ClassSection> classes;

  public Parser(String filename) {
    this.filename = filename;
    classes = new ArrayList<>();
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
    ClassSection c = new ClassSection("public", "Test", "");

    c.addSuperClass("Objet");
    c.implementInterface("Mutable");
    c.implementInterface("Incremable");

    c.addSection(new AttributSection("public", "a", "int"));
    c.addSection(new MethodSection("private", "getA", "int"));

    classes.add(c);
  }

  public void save(String output) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));) {
      StringBuilder sb = new StringBuilder();
      sb.append("@startuml\n");

      for (ClassSection c : classes)
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
