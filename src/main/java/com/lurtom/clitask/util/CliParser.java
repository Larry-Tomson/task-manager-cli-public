package com.lurtom.clitask.util;

import java.util.Arrays;

import com.lurtom.clitask.logger.Logger;

public class CliParser {
    private static final Logger logger = new Logger();

    public CliParser() {
        // implicit
    }

    public String[] parse(String input) {
        logger.info("parsing input= {}", input);

        if (input == null || input.strip().isEmpty()) {
            return new String[0];
        }

        String[] inputParts = input.split(" ", 2);
        logger.debug("input split once = {}", Arrays.asList(inputParts));
        if (inputParts.length < 2) {
            logger.debug("input length < 2 return list = {}", Arrays.asList(inputParts));
            return inputParts;
        }

        int quoteIndex = inputParts[1].indexOf('"');
        logger.debug("quote index in parser= {}", quoteIndex);

        String[] threeParts = input.split(" ", 3);
        logger.debug("args list split twice = {}", Arrays.asList(threeParts));

        if (quoteIndex == -1 && threeParts.length == 3) {
            logger.debug("no quote returning args list: {}", Arrays.asList(threeParts));
            return threeParts;
        }

        if (quoteIndex == -1) {
            return inputParts;
        }

        if (quoteIndex != 0 && inputParts[1].charAt(quoteIndex - 1) == ' ') {
            String[] parsedPart = inputParts[1].split(" ", 2);
            logger.debug("space before quote found, second split= {}", Arrays.asList(parsedPart));
            return removeQuote(parsedPart);
        }
        return removeQuote(inputParts);
    }

    private String[] removeQuote(String[] in) {
        logger.info("Removing string");

        for (int i = 0; i < in.length; i++) {
            String part = in[i];

            // Process only if part contains a quote and is not blank.
            if (part.contains("\"") && !part.isBlank()) {
                int firstQuoteIndex = part.indexOf('"');
                int lastQuoteIndex = part.lastIndexOf('"');
                logger.trace("[{}] quote index [first, last]= [{}, {}]", i, firstQuoteIndex, lastQuoteIndex);

                if (lastQuoteIndex == firstQuoteIndex) {
                    logger.warn("[{}] only one quote found, but still handle no quote for string", i);
                    CLIRenderer.warn("Description must be enclosed with \"");
                } else {
                    String temp = part.substring(firstQuoteIndex + 1, lastQuoteIndex);
                    logger.trace("[{}] removed quote string= {}", i, temp);

                    // Avoid returning a null or empty string.
                    if (!temp.trim().isEmpty()) {
                        logger.trace("[{}] appending not empty index element {}: {}", i, temp);
                        in[i] = temp;
                    }
                }
            } else {
                logger.trace("[{}] blank args or no quote, skipping", i);
            }
        }
        logger.debug("returning input {} ", Arrays.asList(in));
        return in;
    }

}
