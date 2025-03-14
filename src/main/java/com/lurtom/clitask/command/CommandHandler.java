package com.lurtom.clitask.command;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

=======
>>>>>>> main
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.CLIColor;
import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.ConfigurationLoader;
<<<<<<< HEAD

public class CommandHandler {
    private final Map<String, Command> commands = new HashMap<>();
    private final Logger logger = new Logger();
    private final ConfigurationLoader configurationLoader;
=======
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<String, Command> commands = new HashMap<>();
    private final ConfigurationLoader configurationLoader;
    private final Logger logger = new Logger();
>>>>>>> main

    public CommandHandler(Repository repository, ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
        commands.put("add", new Add(repository, configurationLoader));
        commands.put("delete", new Delete(repository, configurationLoader));
        commands.put("list", new ListTask(repository, configurationLoader));
        commands.put("update", new Update(repository, configurationLoader));
        commands.put("mark", new Mark(repository, configurationLoader));
        commands.put("help", new Help(configurationLoader));
        commands.put("exit", new Exit(repository, configurationLoader));
    }

    public void runCmd(String[] input) {
        if (input == null || input.length == 0) {
            CLIRenderer.error("No command entered!");
            logger.debug("input empty");
            return;
        }

        String cmdStr = input[0].toLowerCase();
        Command cmd = commands.get(cmdStr);

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
<<<<<<< HEAD
}
=======
}
>>>>>>> main
