package com.lurtom.clitask.util;

import com.lurtom.clitask.logger.Logger;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConfigurationLoader {
    private static final String DEFAULT_BUNDLE_STRING = "messages";
    private static final Logger logger = new Logger();
    private Locale defaultLocale;
    private ResourceBundle rscBundle;

    public ConfigurationLoader() {
        initialLanguage();
        System.out.println(getValue("welcome"));
    }

    public String getValue(String key) {
        return rscBundle.getString(key);
    }

    public void initialLanguage() throws InputMismatchException, IllegalArgumentException {
        System.out.println("Interface language ? \n1.English\n2.Japanese");
        try {
            @SuppressWarnings("resource") // memory leak or something idk, closing it mean halt the app
            final Scanner sc = new Scanner(System.in);
            final int input = sc.nextInt();
            switch (input) {
                case 1:
                    this.defaultLocale = Locale.ENGLISH;
                    break;
                case 2:
                    this.defaultLocale = Locale.JAPANESE;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported language selection: " + input);
            }
            this.rscBundle = loadBundle();
        } catch (final InputMismatchException e) {
            throw new InputMismatchException("Invalid input" + e.getMessage());
        }
    }

    private ResourceBundle loadBundle() {
        final ResourceBundle rb = ResourceBundle.getBundle(DEFAULT_BUNDLE_STRING, defaultLocale);
        logger.info("Initiating configuration with Bundle package= {}, locale= {}, key set size= {} ",
                rb.getBaseBundleName(), rb.getLocale().getLanguage(), rb.keySet().size());
        return rb;
    }
}
