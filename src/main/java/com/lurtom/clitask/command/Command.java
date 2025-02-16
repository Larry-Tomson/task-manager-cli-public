package com.lurtom.clitask.command;

public interface Command {
    void execute(String[] args) throws IllegalArgumentException;

    int getArgsCount();

    String getHelp();

    default boolean validateArgsCount(String[] args) throws IllegalArgumentException {
        return args.length == this.getArgsCount();
    }
}
