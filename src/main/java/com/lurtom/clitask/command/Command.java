package com.lurtom.clitask.command;

public interface Command {
    default boolean validateArgsCount(String[] args) throws IllegalArgumentException {
        return args.length == this.getArgsCount();
    }

    int getArgsCount();

    void execute(String[] args) throws IllegalArgumentException;

    String getHelp();
}