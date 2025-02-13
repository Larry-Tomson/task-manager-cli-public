package com.lurtom.clitask.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.lurtom.clitask.util.*;
import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.*;

public class ListTask extends BaseCommand implements Command {
    private static final int ARGS_COUNT_LIST_ALL = 1;
    private static final int ARGS_COUNT_LIST_BY_ID = 2;
    private static final Logger logger = new Logger();

    private static final String LINE_FORMAT = "|  %-61s  |%n";
    private static final String LINE_FORMAT_NO_NEWLINE = "|  %-61s  |";

    public ListTask(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, 0);
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("list.helpMessage");
    }

    @Override
    public boolean validateArgsCount(String[] args) {
        return args.length == ARGS_COUNT_LIST_ALL || args.length == ARGS_COUNT_LIST_BY_ID;
    }

    public void execute(String[] args) {
        List<Task> tasks = repository.list();
        logger.info("listing tasks, count= {}", tasks.size());
        String listEmptyMessage = confLoader.getValue("list.empty.message");

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
                String listInvalidIdMsg = confLoader.getValue("list.invalid.id");
                logger.error("Error while parsing int", e);
                throw new IllegalArgumentException(listInvalidIdMsg);
            }
            listTaskById(tasks, id);
            return;
        }
        renderTaskList(tasks);
    }

    private void listTaskById(List<Task> tasks, int id) {
        logger.info("Listing task with id {}", id);
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresentOrElse(this::renderTask,
                        () -> {
                            logger.warn("Task with id {} not found", id);
                            CLIRenderer.warn(confLoader.getValue("list.notfound.id") + " " + id);
                        });
    }

    private void renderTaskList(List<Task> tasks) {
        String listTableTitle = confLoader.getValue("list.table.title");
        String listTableDateFormat = confLoader.getValue("list.table.date.format");
        String listTableRow = confLoader.getValue("list.table.row.format");

        CLIRenderer.message(listTableTitle);
        logger.info("rendering list for {} items ", tasks.size());
        for (Task task : tasks) {
            String description = task.getDescription();
            // Truncate description if too long for table view
            if (description.length() > 16) {
                logger.debug("Description length exceeding 16[] truncating", description.length());
                description = description.substring(0, 16) + "...";
            }
            String status = task.getStatus().getColorStr();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(listTableDateFormat);
            CLIRenderer.message(String.format(
                    listTableRow,
                    task.getId(),
                    status,
                    task.getCreatedTime().format(formatter),
                    task.getUpdatedTime().format(formatter),
                    description));
        }
        CLIRenderer.message("");
    }

    public static void main(String[] args) {
        Task task = new Task("hello", Status.PENDING, LocalDateTime.now(), LocalDateTime.now());
        ListTask lt = new ListTask(new JsonRepository(), new ConfigurationLoader());
        lt.renderTask(task);
    }

    private void renderTask(Task task) {
        logger.info("task {} found, rendering task detail", task.getId());
        String listByIdTitleFormat = "\n+------ TASK ------< Id %-3s > ----- < %-6s  %-20s >-----+";
        String title = String.format(listByIdTitleFormat,
                task.getId(),
                confLoader.getValue("list.render.status"),
                task.getStatus().getColorStr());
        String listByIdFooterFormat = "+--- %6s: %-14s    %6s: %-15s ---+%n";
        System.out.println(listByIdFooterFormat.length());
        String footer = String.format(listByIdFooterFormat,
                confLoader.getValue("list.render.updatedAt"),
                CLIRenderer.formatTime(task.getCreatedTime(), TimeFormat.LONG),
                confLoader.getValue("list.render.createdAt"),
                CLIRenderer.formatTime(task.getUpdatedTime(), TimeFormat.LONG));

        String formattedDescription = formatDescription(task.getDescription());

        CLIRenderer.message(title);
        CLIRenderer.message(formattedDescription);
        CLIRenderer.message(footer);
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
        String[] words = description.split(" ");
        StringBuilder paragraph = new StringBuilder();
        paragraph.append(String.format(LINE_FORMAT, ""));
        StringBuilder sentence = new StringBuilder();
        int lineCount = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String candidate = sentence.length() == 0 ? word : sentence + " " + word;
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
        return String.format(LINE_FORMAT, "") +
                String.format(LINE_FORMAT, description) +
                String.format(LINE_FORMAT_NO_NEWLINE, "");
    }

}
