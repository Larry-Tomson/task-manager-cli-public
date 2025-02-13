package com.lurtom.clitask.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;

public class JsonParser {
    private static final Logger logger = new Logger();

    public JsonParser() {
        // no args constructor
    }

    public List<Task> parseString(String input) {
        logger.info("Parsing String into task list");
        List<Task> taskList = new ArrayList<>();
        String inp1 = input.substring(2, input.length() - 2);
        String[] taskArrayStrings = inp1.split("\\}, \\{");
        for (String taskString : taskArrayStrings) {
            String[] taskKeyValues = taskString.split(", \"");
            Task task = parseTask(taskKeyValues);
            taskList.add(task);
        }
        return taskList;
    }

    public Task parseTask(String[] taskKeyValues) {
        Task task = new Task();
        for (String keyValueString : taskKeyValues) {
            String[] keyValueArrayString = keyValueString.split("\":\"");
            String key = keyValueArrayString[0];
            String value = keyValueArrayString[1].substring(0, keyValueArrayString[1].length() - 1);
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
