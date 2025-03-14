package com.lurtom.clitask.command;

import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.ConfigurationLoader;

public class Help implements Command {
    ConfigurationLoader configurationLoader;

    public Help(ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
    }

    public void execute(String[] args) throws IllegalArgumentException {
        CLIRenderer.message(configurationLoader.getValue("help.message"));
    }

    @Override
<<<<<<< HEAD
    public boolean validateArgsCount(String[] args) {
        // do nothing
        return true;
    }

    @Override
=======
>>>>>>> main
    public int getArgsCount() {
        // do nothing
        return 0;
    }

    @Override
    public String getHelp() {
        return "";
        // do nothing
    }
<<<<<<< HEAD
=======

    @Override
    public boolean validateArgsCount(String[] args) {
        // do nothing
        return true;
    }
>>>>>>> main
}
