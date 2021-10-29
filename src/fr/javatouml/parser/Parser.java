package fr.javatouml.parser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithPrivateModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithProtectedModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithPublicModifier;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import fr.javatouml.uml.AttributSection;
import fr.javatouml.uml.ClassSection;
import fr.javatouml.uml.EnumSection;
import fr.javatouml.uml.InterfaceSection;
import fr.javatouml.uml.MethodSection;
import fr.javatouml.uml.Uml;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import fr.javatouml.uml.Section;

public class Parser {

	private String filename;
	private List<Section> sections;

	public Parser(String filename) {
		this.filename = filename;
		sections = new ArrayList<>();
	}

	private String getVisibility(Node node) {

		if (node instanceof NodeWithPublicModifier && ((NodeWithPublicModifier<?>) node).isPublic())
			return Uml.PUBLIC;

		if (node instanceof NodeWithPrivateModifier && ((NodeWithPrivateModifier<?>) node).isPrivate())
			return Uml.PRIVATE;

		if (node instanceof NodeWithProtectedModifier && ((NodeWithProtectedModifier<?>) node).isProtected())
			return Uml.PROTECTED;

		return Uml.PACKAGE;
	}

	private void generateClassUml(ClassOrInterfaceDeclaration classDeclaration) {
		ClassSection classUml = new ClassSection(classDeclaration.getNameAsString(), classDeclaration.isAbstract());

		classDeclaration.findAll(Node.class).forEach(node -> {
			if (node instanceof FieldDeclaration) {
				FieldDeclaration fieldDecls = (FieldDeclaration) node;
				for (VariableDeclarator var : fieldDecls.getVariables())
					classUml.addSection(new AttributSection(getVisibility(fieldDecls), var.getNameAsString(),
							fieldDecls.getElementType().asString(), fieldDecls.isStatic()));
				return;
			}

			if (node instanceof MethodDeclaration) {
				MethodDeclaration methodDecl = (MethodDeclaration) node;
				MethodSection method = new MethodSection(getVisibility(methodDecl), methodDecl.getNameAsString(),
						methodDecl.getType().asString(), methodDecl.isAbstract(), methodDecl.isStatic());

				String vargs;
				for (Parameter param : methodDecl.getParameters()) {
					vargs = "";
					if (param.isVarArgs())
						vargs = "...";
					method.addArg(String.format("%s%s %s", param.getType().asString(), vargs, param.getNameAsString()));
				}

				classUml.addSection(method);
			}
		});

		for (ClassOrInterfaceType i : classDeclaration.getImplementedTypes())
			classUml.implementsInterface(i.getNameAsString());

		for (ClassOrInterfaceType c : classDeclaration.getExtendedTypes()) {
			classUml.extendsSection(c.getNameAsString());
			break;
		}

		sections.add(classUml);

	}

	private void generateInterfaceUml(ClassOrInterfaceDeclaration interfaceDeclaration) {
		InterfaceSection interfaceUml = new InterfaceSection(interfaceDeclaration.getNameAsString());

		interfaceDeclaration.findAll(MethodDeclaration.class).forEach(methodDecl -> {

			MethodSection method = new MethodSection(getVisibility(methodDecl), methodDecl.getNameAsString(),
					methodDecl.getType().asString(), methodDecl.isAbstract(), methodDecl.isStatic());

			String vargs;
			for (Parameter param : methodDecl.getParameters()) {
				vargs = "";
				if (param.isVarArgs())
					vargs = "...";
				method.addArg(String.format("%s%s %s", param.getType().asString(), vargs, param.getNameAsString()));
			}

			interfaceUml.addSection(method);

		});

		for (ClassOrInterfaceType i : interfaceDeclaration.getExtendedTypes())
			interfaceUml.extendsSection(i.getNameAsString());

		sections.add(interfaceUml);
	}

	private void generateEnumUml(EnumDeclaration enumDeclaration) {
		EnumSection enumUml = new EnumSection(enumDeclaration.getNameAsString());

		for (EnumConstantDeclaration entry : enumDeclaration.getEntries())
			enumUml.addValue(entry.getNameAsString());

		sections.add(enumUml);
	}

	private void generateUml() {

		CompilationUnit unit;
		try {

			unit = StaticJavaParser.parse(new File(filename));

			unit.findAll(Node.class).forEach(node -> {
				if (node instanceof ClassOrInterfaceDeclaration) {
					ClassOrInterfaceDeclaration nodeClassInterface = (ClassOrInterfaceDeclaration) node;

					if (nodeClassInterface.isInterface()) {
						generateInterfaceUml(nodeClassInterface);
						return;
					}

					generateClassUml(nodeClassInterface);
					return;
				}

				if (node instanceof EnumDeclaration)
					generateEnumUml((EnumDeclaration) node);

			});
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private String getUmlResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("@startuml\n\n");
		sb.append("skinparam groupInheritance 2\n\n");

		for (Section c : sections)
			sb.append(c.toPlantUmlString()).append("\n");

		sb.append("@enduml");
		return sb.toString();
	}

	private void save(String output, String umlResult) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(String.format("%s.uml", output))))) {
			writer.write(umlResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateUmlDiagram(String output, String result) {

		SourceStringReader reader = new SourceStringReader(result);

		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			reader.outputImage(os, new FileFormatOption(FileFormat.PNG));

			InputStream is = new ByteArrayInputStream(os.toByteArray());
			BufferedImage bi = ImageIO.read(is);

			ImageIO.write(bi, "png", new File(String.format("%s.png", output)));

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void parse(String output) {
		generateUml();
		String result = getUmlResult();
		save(output, result);
		generateUmlDiagram(output, result);
	}
}