package com.lurtom.clitask.command;

<<<<<<< HEAD
import com.lurtom.clitask.util.*;

import java.util.Optional;

=======
>>>>>>> main
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.Task;
import com.lurtom.clitask.model.TimeFormat;
import com.lurtom.clitask.repository.Repository;
<<<<<<< HEAD

public class Update extends BaseCommand implements Command {
    private final Logger logger = new Logger();
    private static final int ARGS_COUNT = 3;
=======
import com.lurtom.clitask.util.*;
import java.util.Optional;

public class Update extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 3;
    private final Logger logger = new Logger();
>>>>>>> main

    public Update(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    public void execute(String[] args) throws NumberFormatException {
        String updDesId = args[1];
        String newDes = args[2];

        String taskInfoFormat = confLoader.getValue("update.format");
        String inputNullOrEmpty = confLoader.getValue("update.error.input.empty");
        String inputInvalid = confLoader.getValue("update.error.input.invalid");
        String outputNullErr = confLoader.getValue("update.error.output.null");

        int parsedId = 0;
        try {
            parsedId = Integer.parseInt(updDesId);
            if (newDes.isEmpty()) {
                logger.warn("input is empty ");
                CLIRenderer.error(inputNullOrEmpty);
                return;
            }
            if (parsedId < 0) {
                CLIRenderer.error(inputInvalid);
                logger.warn("id is valid= {} ", parsedId);
                return;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(inputInvalid);
        }

        Optional<Task> task = repository.update(parsedId, newDes);

<<<<<<< HEAD
        task.ifPresentOrElse(t -> CLIRenderer.message(String.format(taskInfoFormat,
                t.getId(),
                CLIColor.GREEN + t.getDescription() + CLIColor.RESET,
                t.getStatus().getValueStr(),
                CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG),
                CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG))),
=======
        task.ifPresentOrElse(t -> CLIRenderer.message(String.format(taskInfoFormat, t.getId(), //
                CLIColor.GREEN + t.getDescription() + CLIColor.RESET, //
                t.getStatus().getValueStr(), //
                CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG), //
                CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG))), //
>>>>>>> main
                () -> {
                    logger.warn("Task created failed, task is null");
                    CLIRenderer.message(outputNullErr);
                });
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("update.helpMessage");
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
