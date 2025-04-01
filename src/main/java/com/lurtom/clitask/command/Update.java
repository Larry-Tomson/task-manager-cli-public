package com.lurtom.clitask.command;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.Task;
import com.lurtom.clitask.model.TimeFormat;
import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.*;
import java.util.Optional;

public class Update extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 3;
    private final Logger logger = new Logger();

    public Update(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    public void execute(String[] args) throws NumberFormatException {
        final String updDesId = args[1];
        final String newDes = args[2];

        final String taskInfoFormat = confLoader.getValue("update.format");
        final String inputNullOrEmpty = confLoader.getValue("update.error.input.empty");
        final String inputInvalid = confLoader.getValue("update.error.input.invalid");
        final String outputNullErr = confLoader.getValue("update.error.output.null");

        int parsedId = 0;
        try {
            parsedId = Integer.parseInt(updDesId);
            if (newDes.isEmpty()) {
                logger.warn("input is empty");
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

        final Optional<Task> task = repository.update(parsedId, newDes);

        task.ifPresentOrElse(t -> CLIRenderer.message(String.format(taskInfoFormat, t.getId(), //
                CLIColor.GREEN + t.getDescription() + CLIColor.RESET, //
                t.getStatus().getValueStr(), //
                CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG), //
                CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG))), //
                () -> {
                    logger.warn("Task created failed, task is null");
                    CLIRenderer.message(outputNullErr);
                });
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("update.helpMessage");
    }
}
