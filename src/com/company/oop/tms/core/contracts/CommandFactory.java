package com.company.oop.tms.core.contracts;

import com.company.oop.tms.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, SystemRepository systemRepository);

}
