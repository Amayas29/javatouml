package fr.javatouml.main;

import fr.javatouml.parser.Parser;

public class JavaToUmlMain {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("<Invalid number of arguments> Usage : java Parser <java filename>");
            System.exit(1);
        }

        Parser parser = new Parser(args[0]);
        parser.parse(args[0].substring(0, args[0].length() - 5));
    }

}
