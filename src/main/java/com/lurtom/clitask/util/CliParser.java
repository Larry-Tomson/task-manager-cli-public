package com.lurtom.clitask.util;

import com.lurtom.clitask.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class CliParser {
    private static final Logger logger = new Logger();

    public CliParser() {
    }

    /**
     * use case: parse string with `"` character
     * example : add "hello world" -> [add, hello world]
     * example : update 1 "hello world" -> [update, 1, hello world]
     * example : update 1 hello world -> [update, 1, hello world]
     * @param input
     * @return 
      */
    public String[] parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }

        final List<String> args = new ArrayList<>();
        StringBuilder currentArg = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (Character.isWhitespace(c) && !insideQuotes) {

                if (currentArg.length() > 0) {
                    args.add(currentArg.toString());
                    currentArg = new StringBuilder();
                }
            } else {
                currentArg.append(c);
            }
        }

        if (insideQuotes) {
            return new String[] { "parser.invalid" }; // return resourceBundle value;
        }

        if (currentArg.length() > 0) {
            args.add(currentArg.toString());
        }

        return args.toArray(new String[0]);
    }

    // public static void main(String[] args) {
    //     CliParser parser = new CliParser();
    //     String input = "hello \"world this is a long ass string";
    //     String[] result = parser.parse(input);
    //     for (String arg : result) {
    //         System.out.println(arg);
    //     }
    // }
}