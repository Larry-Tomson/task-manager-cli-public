package com.lurtom.clitask.util;

<<<<<<< HEAD
=======
import com.lurtom.clitask.logger.Logger;
>>>>>>> main
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

<<<<<<< HEAD
import com.lurtom.clitask.logger.Logger;

=======
>>>>>>> main
public class ConfigurationLoader {
    private static final String DEFAULT_BUNDLE_STRING = "messages";
    private static final Logger logger = new Logger();
    private Locale defaultLocale;
    private ResourceBundle rscBundle;

    public ConfigurationLoader() {
        initialLanguage();
        System.out.println(getValue("welcome"));
<<<<<<< HEAD

=======
>>>>>>> main
    }

    public String getValue(String key) {
        return rscBundle.getString(key);
    }

    public void initialLanguage() throws InputMismatchException, IllegalArgumentException {
        System.out.println("Interface language ? \n1.English\n2.Japanese");
        try {
            @SuppressWarnings("resource") // memory leak or something idk, closing it mean halt the app
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
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
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input" + e.getMessage());
        }
    }

    private ResourceBundle loadBundle() {
        ResourceBundle rb = ResourceBundle.getBundle(DEFAULT_BUNDLE_STRING, defaultLocale);
        logger.info("Initiating configuration with Bundle package= {}, locale= {}, key set size= {} ",
<<<<<<< HEAD
                rb.getBaseBundleName(),
                rb.getLocale().getLanguage(),
                rb.keySet().size());
=======
                        rb.getBaseBundleName(), rb.getLocale().getLanguage(), rb.keySet().size());
>>>>>>> main
        return rb;
    }
}
