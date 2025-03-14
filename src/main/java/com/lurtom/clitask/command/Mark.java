package com.lurtom.clitask.command;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;
import com.lurtom.clitask.model.TimeFormat;
import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.ConfigurationLoader;
import java.util.Optional;

public class Mark extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 3;
    private final Logger logger = new Logger();

    public Mark(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {

        String taskInfoFormat = confLoader.getValue("mark.format");
        String inputNullOrEmpty = confLoader.getValue("mark.error.input.empty");
        String outputNullErr = confLoader.getValue("mark.error.output.null");

        String id = args[1];
        int markId = parseInt(id);

        String status = args[2];
        Status stt = Status.getEnum(status);
        if (stt == null) {
            CLIRenderer.error(inputNullOrEmpty);
            return;
        }

        Optional<Task> task = repository.mark(markId, stt);
        task.ifPresentOrElse(t -> {
            logger.info("Mark task id: " + t.getId() + "-> " + stt.getValueStr());
            CLIRenderer.message(String.format(taskInfoFormat, t.getId(), t.getDescription(),
                            t.getStatus().getColorStr(), CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG),
                            CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG)));
        }, () -> {
            logger.warn("Mark task failed, task is null");
            CLIRenderer.error(outputNullErr);
        });
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("mark.helpMessage");
    }

    private int parseInt(String id) {
        String inputInvalid = confLoader.getValue("mark.error.input.invalid");
        int markId = 0;
        try {
            markId = Integer.parseInt(id);
            if (markId > 0) {
                return markId;
            } else {
                throw new IllegalArgumentException(inputInvalid);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(inputInvalid);
        }
    }
}
