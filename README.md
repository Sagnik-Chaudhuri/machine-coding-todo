**TODO Task Tracker**

Develop a TODO task tracking application which allows its users to maintain TODO lists. 
TODO tasks in the list can have a deadline and tags associated with them for easier grouping and management. You can demonstrate the working of the application using a driver program. 
There is no requirement to use a database for persistence (instead, use memory to store data) or write a web service.

**Features**
- Users should be able to update the TODO list at any point in time - add, modify and remove tasks
- A task can be marked as completed and once it is completed, it is automatically removed from the TODO list. Tasks can also be added to appear in the TODO list
at a future date.
- Users should be able to see an activity log which describes additions, modifications and removals of tasks from the TODO list during a particular time period.
- Users should also be able to see statistics around how
many tasks were added, completed and spilled over the deadline during a particular period of time.

**Implementation Requirements**
Your solution should implement the following functions. Feel free to use the representation for objects you deem fit for the problem and the provided use cases. 
The functions are ordered in the decreasing order of importance (highest to lowest). We understand that you may not be able to complete the implementation for all the functions listed here. 
So try to implement them in the order in which they are declared down below.
- addTask (task)
- getTask (taskId) -> a task modifyTask (task) removeTask (taskId)
- listTasks (...) → a list of tasks which match the given filters and sortBys
- getStatistics (optional [timePeriod]) -> statistics for a given time period. if time period is not provided, consider the whole range (from start to current time)
- getActivityLog (optional [timePeriod]) -> activity log for a given time period. if time period is not provided, consider the whole range (from start to current time)
