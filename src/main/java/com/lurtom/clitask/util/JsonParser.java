package com.lurtom.clitask.util;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final Logger logger = new Logger();

    public JsonParser() {
        // no args constructor
    }

    public List<Task> parseString(String input) {
        logger.info("Parsing String into task list");
        final List<Task> taskList = new ArrayList<>();
        final String inp1 = input.substring(2, input.length() - 2);
        final String[] taskArrayStrings = inp1.split("\\}, \\{");
        for (String taskString : taskArrayStrings) {
            final String[] taskKeyValues = taskString.split(", \"");
            final Task task = parseTask(taskKeyValues);
            taskList.add(task);
        }
        return taskList;
    }

    public Task parseTask(String[] taskKeyValues) {
        final Task task = new Task();
        for (String keyValueString : taskKeyValues) {
            final String[] keyValueArrayString = keyValueString.split("\":\"");
            final String key = keyValueArrayString[0];
            final String value = keyValueArrayString[1].substring(0, keyValueArrayString[1].length() - 1);
            switch (key) {
                case "\"id":
                    task.setId(Integer.parseInt(value));
                    break;
                case "status":
                    task.setStatus(Status.getEnum(value));
                    break;
                case "description":
                    task.setDescription(value);
                    break;
                case "createdTime":
                    task.setCreatedTime(LocalDateTime.parse(value));
                    break;
                case "updatedTime":
                    task.setUpdatedTime(LocalDateTime.parse(value));
                    break;
                default:
                    break;
            }
        }
        return task;
    }
}
