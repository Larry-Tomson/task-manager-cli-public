package com.lurtom.clitask.command;

public interface Command {
<<<<<<< HEAD
    default boolean validateArgsCount(String[] args) throws IllegalArgumentException {
        return args.length == this.getArgsCount();
    }

    int getArgsCount();

    void execute(String[] args) throws IllegalArgumentException;

    String getHelp();
}
=======
    void execute(String[] args) throws IllegalArgumentException;

    int getArgsCount();

    String getHelp();

    default boolean validateArgsCount(String[] args) throws IllegalArgumentException {
        return args.length == this.getArgsCount();
    }
}
>>>>>>> main
