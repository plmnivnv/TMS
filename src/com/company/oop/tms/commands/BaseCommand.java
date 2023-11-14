package com.company.oop.tms.commands;

import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;

public abstract class BaseCommand {

    private final SystemRepository systemRepository;

    protected BaseCommand(SystemRepository systemRepository) {
        this.systemRepository = new SystemRepositoryImpl();
    }
}
