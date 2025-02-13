package com.lurtom.clitask.command;

import com.lurtom.clitask.repository.Repository;
import com.lurtom.clitask.util.ConfigurationLoader;

public abstract class BaseCommand {
    protected final Repository repository;
    protected final ConfigurationLoader confLoader;
    protected final int argsCount;

    protected BaseCommand(Repository repository, ConfigurationLoader configurationLoader, int argsCount) {
        this.repository = repository;
        this.confLoader = configurationLoader;
        this.argsCount = argsCount;
    }

    public int getArgsCount() {
        return this.argsCount;
    }
}
