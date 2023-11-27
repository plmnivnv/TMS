# OOP PROJECT - TMS

# Description
Hello, we want to introduce our Task Management System console application!
The application will be used by a small team of developers, 
who need to keep track of all the tasks,
surrounding a software product they are building.

## Commands

Currently, our system supports the following commands:

· CreateMember - creates a new member in the system.

· ShowAllMembers - shows all members in the system.

· ShowMembersActivity - show all members' activity.

· CreateNewTeam - creates a new team in the system.

· ShowAllTeams - shows all teams in the system.

· ShowAllTeamActivity - shows all teams' activity.

· AddMemberToTeam - adds a member to a specific team.

· ShowAllTeamMembers - shows all members of a specific team.

· CreateBoard - creates a new board in team.

· ShowTeamBoards - shows all boards of a specific team.

· ShowBoardActivity - shows all activity of a specific board.

· CreateBugInBoard - creates new bug in a specific board.

. CreateStoryInBoard - creates new story in a specific board.

. CreateFeedbackInBoard - creates new feedback in a specific board.

. ChangeBugPriority - changes priority of a specific bug.

. ChangeBugSeverity - changes severity of a specific bug.

. ChangeBugStatus - changes status of a specific bug.

. ChangeStoryPriority - changes priority of a specific story.

. ChangeStoryStatus - changes status of a specific story.

. ChangeStorySize - changes size of a specific story.

· ChangeFeedbackRating - changes rating of a specific feedback.

· ChangeFeedbackStatus - changes status of a specific feedback.

. AssignTask - assigns a task to a specific member.

. UnAssignTask - un assigns a task from a specific member.

· AddCommentToTask - adds a comment to a specific task.

## We also support a few listing commands:

. FilterTasksByTitle - filters all tasks by a specific title.

. SortTasksByTitle - sorts all tasks by title (ascending).

. FilterBugByAssignee - filters all bugs by assignee.

. FilterBugByStatus - filters all bugs by status.

. FilterBugByStatusAndAssignee - filters all bugs by status and assignee.

. FilterFeedbackByStatus - filters all feedbacks by status.

. FilterStoryByAssignee - filters all stories by assignee.

. FilterStoryByStatus - filters all stories by status.

. FilterStoryByStatusAndAssignee - filters all stories by status and assignee.

. SortBugByTitlePrioritySeverity - sorts all bugs by title/priority/severity.

. SortFeedbackByTitleRating - sorts all feedbacks by title/rating.

. SortStoryByTitlePrioritySize - sorts all stories by title/priority/size.

. FilterAssigneeTasksByAssignee - filters all tasks with assignee by assignee and sorts 
them by title.

. FilterAssigneeTasksByStatusAndAssignee - filters all tasks with assignee
by status and assignee and sorts them by title.

. FilterAssigneeTasksByStatus - filters all tasks with assignee by status
and sorts them by title.

### Sample Input

```none
Createmember Georgi
Createmember Plamen
Createmember Petar
Createmember Stefan
Createmember Stefan
CreateTeam Chereshka
CreateTeam Malina
CreateTeam Malina
AddMemberToTeam Georgi Chereshka
AddMemberToTeam Plamen Chereshka
AddMemberToTeam Petar Malina
AddMemberToTeam Stefan Malina
CreateBoard ChereBoard Chereshka
CreateBoard MalinaBord Malina
CreateBuginBoard ProblemInCore ProblemInCoreModuleCheckASAP checkInCorePackage,openRepository, high major Georgi ChereBoard
CreateBuginBoard BugBugBug2 ProblemInModel Step1,Step2 high minor Plamen ChereBoard
CreateBuginBoard BugBugBug3 ProblemWithPrint Step1,Step2,Step3 low minor Petar MalinaBord
CreateBuginBoard BugBugBug4 ProblemWithExeption Step1,Step2,Step3,Step4 medium major Stefan MalinaBord
changebugpriority 1 low
changebugseverety 1 minor
changebugStatus 1 done
Createfeedbackinboard Feedbackkk1 FeedBackForBug1 1 ChereBoard
Createfeedbackinboard Feedbackkk2 FeedBackForBug2 2 ChereBoard
Createfeedbackinboard Feedbackkk3 FeedBackForBug3 3 MalinaBord
Createfeedbackinboard Feedbackkk4 FeedBackForBug4 4 MalinaBord
changeFeedbackStatus 5 SCHEDULED
changeFeedbackRating 6 10
CREATESTORYINBOARD FixedBugff1 SuperTufffffff medium small Georgi ChereBoard
Changestorystatus 9 done
```
### Sample Output

```none
Member with name Georgi was created
####################
Member with name Plamen was created
####################
Member with name Petar was created
####################
Member with name Stefan was created
####################
Name Stefan already exist. Choose another name!
####################
Team with name Chereshka was created
####################
Team with name Malina was created
####################
Name Malina already exist. Choose another name!
####################
Member with name Georgi added to Team: Chereshka
####################
Member with name Plamen added to Team: Chereshka
####################
Member with name Petar added to Team: Malina
####################
Member with name Stefan added to Team: Malina
####################
Board with name ChereBoard was created
####################
Board with name MalinaBord was created
####################
Bug with ID 1 created in board ChereBoard
####################
Bug with ID 2 created in board ChereBoard
####################
Bug with ID 3 created in board MalinaBord
####################
Bug with ID 4 created in board MalinaBord
####################
Priority of Bug with ID 1 changed to Low.
####################
There is no changebugseverety in CommandTypes.
####################
Status of Bug with ID 1 changed to Done.
####################
Feedback with ID 5 crated in board ChereBoard
####################
Feedback with ID 6 crated in board ChereBoard
####################
Feedback with ID 7 crated in board MalinaBord
####################
Feedback with ID 8 crated in board MalinaBord
####################
Status of Feedback with ID 5 changed to Scheduled.
####################
Rating of feedback with ID 6 was changed to 10
####################
Story with ID 9 created in board ChereBoard
####################
Status of Story with ID 9 changed to Done.
####################
```