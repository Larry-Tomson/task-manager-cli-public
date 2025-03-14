package com.lurtom.clitask;

import java.util.Arrays;
import java.util.Scanner;

import com.lurtom.clitask.command.CommandHandler;
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.repository.JsonRepository;
import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.CliParser;
import com.lurtom.clitask.util.ConfigurationLoader;

public class App {
    private static final CliParser cliParser = new CliParser();
    private static final Logger logger = new Logger();

    public static void run(CommandHandler ih) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String[] cmdArgs = cliParser.parse(sc.nextLine());
                logger.info("input= {}", Arrays.asList(cmdArgs));

                if (cmdArgs.length == 0) {
                    logger.warn("input is empty= {}", Arrays.asList(cmdArgs).isEmpty());
                    CLIRenderer.error("Input is empty");
                    continue;
                }

                ih.runCmd(cmdArgs);
                if (cmdArgs[0].equals("exit")) {
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("Failed due to {}", e);
        }
    }

    public static void main(String[] args) {
        JsonRepository jr = new JsonRepository();
        ConfigurationLoader cl = new ConfigurationLoader();
        CommandHandler ih = new CommandHandler(jr, cl);
        Logger.logDebug();
        Logger.logTrace();
        if (args.length == 0) {
            App.run(ih);
        } else {
            ih.runCmd(args);
            ih.runCmd(new String[] { "exit" });
        }
    }
}
