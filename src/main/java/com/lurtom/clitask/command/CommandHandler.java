package com.lurtom.clitask.command;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.CLIColor;
import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.ConfigurationLoader;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final ConfigurationLoader configurationLoader;
    private final Repository repository;
    private final Logger logger = new Logger();

    public CommandHandler(Repository repository, ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
        this.repository = repository;
    }

    public void runCmd(String[] input) {
        if (input == null || input.length == 0) {
            CLIRenderer.error("No command entered!");
            logger.debug("input empty");
            return;
        }

        final String cmdStr = input[0].toLowerCase();
        final Command cmd = createCommand(cmdStr);

        if (cmd == null) {
            logger.warn("Unknown command: " + "{}", cmdStr);
            CLIRenderer.error(configurationLoader.getValue("input.unknown") + CLIColor.RESET + cmdStr);
            CLIRenderer.message(configurationLoader.getValue("help.message"));
            return;
        }
        CLIRenderer.info("[TASK] >> " + cmdStr.toUpperCase());

        try {
            if (!cmd.validateArgsCount(input)) {
                CLIRenderer.warn(configurationLoader.getValue("input.invalid"));
                CLIRenderer.message(cmd.getHelp());
                return;
            }
            logger.info("Execute {} with valid args", cmdStr);
            cmd.execute(input);
            CLIRenderer.success("");

        } catch (IllegalArgumentException e) {
            CLIRenderer.error(e.getMessage());
        }
    }

    private Command createCommand(String commandString) {
        switch (commandString.toLowerCase()) {
            case "add":
                return new Add(repository, configurationLoader);
            case "delete":
                return new Delete(repository, configurationLoader);
            case "list":
                return new ListTask(repository, configurationLoader);
            case "update":
                return new Update(repository, configurationLoader);
            case "mark":
                return new Mark(repository, configurationLoader);
            case "help":
                return new Help(configurationLoader);
            case "exit":
                return new Exit(repository, configurationLoader);
            default:
                return null;
        }
    }

}
