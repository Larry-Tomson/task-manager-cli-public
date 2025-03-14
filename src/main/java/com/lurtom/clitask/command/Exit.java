package com.lurtom.clitask.command;

import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.CLIRenderer;
import com.lurtom.clitask.util.ConfigurationLoader;

public class Exit extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 1;

    public Exit(Repository repository, ConfigurationLoader configurationLoader) {
        super(repository, configurationLoader, ARGS_COUNT);
    }

<<<<<<< HEAD

=======
>>>>>>> main
    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        CLIRenderer.info("Cleaning up, exiting");
        repository.write();
    }

    @Override
    public String getHelp() {
        // this will never call
        return confLoader.getValue("delete.helpMessage");
    }
}
