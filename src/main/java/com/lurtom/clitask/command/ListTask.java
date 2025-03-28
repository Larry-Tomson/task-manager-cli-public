package com.lurtom.clitask.command;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.util.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListTask extends BaseCommand implements Command {
    private static final int ARGS_COUNT_LIST_ALL = 1;
    private static final int ARGS_COUNT_LIST_BY_ID = 2;
    private static final String LINE_FORMAT = "|  %-61s  |%n";

    private static final String LINE_FORMAT_NO_NEWLINE = "|  %-61s  |";
    private static final Logger logger = new Logger();

    public ListTask(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, 0);
    }

    public void execute(String[] args) {
        final List<Task> tasks = repository.list();
        logger.info("listing tasks, count= {}", tasks.size());
        final String listEmptyMessage = confLoader.getValue("list.empty.message");

        if (tasks.isEmpty()) {
            logger.info("task-list is empty");
            CLIRenderer.message(listEmptyMessage);
            return;
        }

        if (args.length > 1) {
            logger.info("Argument > 1, trying to parse int");
            int id = 0;
            try {
                id = Integer.parseInt(args[1]);
                logger.info("Found int= {}, rendering", id);
            } catch (IllegalArgumentException e) {
                final String listInvalidIdMsg = confLoader.getValue("list.invalid.id");
                logger.error("Error while parsing int", e);
                throw new IllegalArgumentException(listInvalidIdMsg);
            }
            listTaskById(tasks, id);
            return;
        }
        renderTaskList(tasks);
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("list.helpMessage");
    }

    @Override
    public boolean validateArgsCount(String[] args) {
        return args.length == ARGS_COUNT_LIST_ALL || args.length == ARGS_COUNT_LIST_BY_ID;
    }

    private String formatDescription(String description) {
        if (description.length() > 65) {
            return formatLongDescription(description);
        } else {
            return formatShortDescription(description);
        }
    }

    private String formatLongDescription(String description) {
        logger.debug("description length > 65 ({}), rendering.", description.length());

        final String[] words = description.split(" ");
        final StringBuilder paragraph = new StringBuilder();
        final StringBuilder sentence = new StringBuilder();

        paragraph.append(String.format(LINE_FORMAT, ""));

        int lineCount = 0;
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            final String candidate = sentence.length() == 0 ? word : sentence + " " + word;
            if (candidate.length() < 61) {
                if (sentence.length() != 0) {
                    sentence.append(" ");
                }
                sentence.append(word);
                if (i == words.length - 1) {
                    paragraph.append(String.format(LINE_FORMAT, sentence.toString()));
                    paragraph.append(String.format(LINE_FORMAT_NO_NEWLINE, ""));
                }
            } else {
                lineCount++;
                paragraph.append(String.format(LINE_FORMAT, sentence.toString()));
                sentence.setLength(0);
                sentence.append(word);
            }
        }
        logger.debug("Description formatted with {} lines", lineCount);
        return paragraph.toString();
    }

    private String formatShortDescription(String description) {
        logger.debug("description length <= 65 ({}), rendering.", description.length());
        return String.format(LINE_FORMAT, "") + //
                String.format(LINE_FORMAT, description) + //
                String.format(LINE_FORMAT_NO_NEWLINE, "");
    }

    private void listTaskById(List<Task> tasks, int id) {
        logger.info("Listing task with id {}", id);
        tasks.stream().filter(t -> t.getId() == id) //
                .findFirst() //
                .ifPresentOrElse(this::renderTask, () -> { //
                    logger.warn("Task with id {} not found", id);
                    CLIRenderer.warn("task " + id + " is not found");
                });
    }

    private void renderTask(Task task) {
        logger.info("task {} found, rendering task detail", task.getId());
        final String listByIdTitleFormat = "\n+------ TASK ------< Id %-3s > ----- < Status  %-22s >-----+";
        final String title = String.format(listByIdTitleFormat, task.getId(), task.getStatus().getColorStr());

        final String listByIdFooterFormat = "+---    Created: %-14s     Updated: %-15s   ---+%n";
        final String footer = String.format(listByIdFooterFormat,
                CLIRenderer.formatTime(task.getCreatedTime(), TimeFormat.LONG),
                CLIRenderer.formatTime(task.getUpdatedTime(), TimeFormat.LONG));

        final String formattedDescription = formatDescription(task.getDescription());

        CLIRenderer.message(title);
        CLIRenderer.message(formattedDescription);
        CLIRenderer.message(footer);
    }

    private void renderTaskList(List<Task> tasks) {
        final String listTableTitle = confLoader.getValue("list.table.title");
        final String listTableDateFormat = confLoader.getValue("list.table.date.format");
        final String listTableRow = confLoader.getValue("list.table.row.format");

        CLIRenderer.message(listTableTitle);
        logger.info("rendering list for {} items ", tasks.size());
        for (Task task : tasks) {
            String description = task.getDescription();
            // Truncate description if too long for table view
            if (description.length() > 16) {
                logger.debug("Description length exceeding 16[] truncating", description.length());
                description = description.substring(0, 16) + "...";
            }
            final String status = task.getStatus().getColorStr();
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(listTableDateFormat);
            CLIRenderer.message(
                    String.format(listTableRow, task.getId(), status, task.getCreatedTime().format(formatter),
                            task.getUpdatedTime().format(formatter), description));
        }
        CLIRenderer.message("");
    }
}
