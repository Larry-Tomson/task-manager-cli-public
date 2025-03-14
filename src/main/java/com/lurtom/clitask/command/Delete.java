package com.lurtom.clitask.command;

<<<<<<< HEAD
import com.lurtom.clitask.util.*;
import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.logger.*;

=======
import com.lurtom.clitask.logger.*;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.util.*;
>>>>>>> main
import java.util.Optional;

public class Delete extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 2;
    private final Logger logger = new Logger();

    public Delete(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    public void execute(String[] args) {
        String taskInfoFormat = confLoader.getValue("delete.format");
        String inputNullOrEmpty = confLoader.getValue("delete.error.input.null");
        String inputInvalidErr = confLoader.getValue("delete.error.input.invalid");
        String outputNullErr = confLoader.getValue("delete.error.output.null");

        int deleteId = 0;
        try {
            deleteId = Integer.parseInt(args[1]);
            if (deleteId <= 0) {
                CLIRenderer.warn(inputInvalidErr);
                return;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(inputNullOrEmpty, args[1]));
        }

        Optional<Task> deletedTask = repository.delete(deleteId);

<<<<<<< HEAD
        deletedTask.ifPresentOrElse(t -> CLIRenderer.message(String.format(
                CLIColor.RED + taskInfoFormat + CLIColor.RESET,
                t.getId(),
                t.getDescription(),
                t.getStatus().getValueStr(),
                CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG),
                CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG))),
                () -> {
                    logger.warn("Can't delete task, task is null?");
                    CLIRenderer.message(outputNullErr);
                });
=======
        deletedTask.ifPresentOrElse(
                        t -> CLIRenderer.message(String.format(CLIColor.RED + taskInfoFormat + CLIColor.RESET,
                                        t.getId(), t.getDescription(), t.getStatus().getValueStr(),
                                        CLIRenderer.formatTime(t.getCreatedTime(), TimeFormat.LONG),
                                        CLIRenderer.formatTime(t.getUpdatedTime(), TimeFormat.LONG))),
                        () -> {
                            logger.warn("Can't delete task, task is null?");
                            CLIRenderer.message(outputNullErr);
                        });
>>>>>>> main
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("delete.helpMessage");
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
