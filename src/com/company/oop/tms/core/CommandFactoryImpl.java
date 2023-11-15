package com.company.oop.tms.core;

import com.company.oop.tms.commands.*;
import com.company.oop.tms.commands.bug_commands.*;
import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.commands.enums.CommandType;
import com.company.oop.tms.commands.feedback_commands.*;
import com.company.oop.tms.commands.story_commands.*;
import com.company.oop.tms.core.contracts.CommandFactory;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {


    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, SystemRepository systemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATEMEMBER:
                return new CreateMemberCommand(systemRepository);
            case SHOWMEMBERS:
                return new ShowAllMembersCommand(systemRepository);
//            case SHOWMEMBERSACTIVITY:
//                return new ShowMembersActivity(systemRepository);
            case CREATETEAM:
                return new CreateNewTeamCommand(systemRepository);
            case SHOWTEAMS:
                return new ShowAllTeamsCommand(systemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowAllTeamActivityCommand(systemRepository);
            case ADDMEMBER:
                return new AddMemberToTeamCommand(systemRepository);
            case SHOWTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(systemRepository);
            case CREATEBOARD:
                return new CreateBoardCommand(systemRepository);
            case SHOWTEAMBOARDS:
                return new ShowTeamBoardsCommand(systemRepository);
//            case SHOWBOARDACTIVITY:
//                return new ShowBoardActivityCommand(systemRepository);
            case CREATEBUGINBOARD:
                return new CreateBugInBoardCommand(systemRepository);
            case CREATESTORYINBOARD:
                return new CreateStoryInBoardCommand(systemRepository);
            case CREATEFEEDBACKINBOARD:
                return new CreateFeedbackInBoardCommand(systemRepository);
            case CHANGEBUGSTATUS:
                return new ChangeBugStatusCommand(systemRepository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverityCommand(systemRepository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriorityCommand(systemRepository);
            case CHANGESTORYSTATUS:
                return new ChangeStoryStatusCommand(systemRepository);
            case CHANGESTORYSIZE:
                return new ChangeStorySizeCommand(systemRepository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriorityCommand(systemRepository);
            case CHANGEFEEDBACKSTATUS:
                return new ChangeFeedbackStatusCommand(systemRepository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRatingCommand(systemRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(systemRepository);
            case UNASSIGNTASK:
                return new UnAssignTaskCommand(systemRepository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTaskCommand(systemRepository);
            default:
                throw new IllegalArgumentException();
        }

    }
}
