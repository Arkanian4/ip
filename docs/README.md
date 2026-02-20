# Arkanian User Guide

[Product Screenshot](Ui.png)

## Introduction

Arkanian is a simple command-line task manager that helps you manage your tasks efficiently.

With Arkanian, you can:

* Add ToDos, Deadlines, and Events

* Mark and unmark tasks

* Delete tasks

* Find tasks using keywords

* View all tasks

* Automatically save your progress

Arkanian keeps things lightweight and straightforward so you can focus on getting things done.

<br>

## View all tasks
### Goal

Display all tasks currently stored in Arkanian.

### Format

`list`

### Example

`list`

### Expected Output (when tasks exist)
```
________________________________________
Here's the lineup of your awesome tasks:
1. [T][ ] read book
2. [D][X] submit report (by: 2026-03-10)
________________________________________
```
<br>
### Expected Output (when no tasks exist)

```
________________________________________
Empty, bruv. Nothing to do yet! 💤
________________________________________
```
<br>

## Add a ToDo
### Goal

Add a simple task without any date or time.

### Format

`todo <DESCRIPTION>`

### Example

`todo finish homework`

### Expected Output
```
________________________________________
Sweet! Added this gem:
[T][ ] finish homework
You're juggling 1 tasks now. Legendary! 🌟
________________________________________
```
<br>

## Add a Deadline
### Goal

Add a task with a deadline.

### Format

`deadline <DESCRIPTION> /by <DATE>`

DATE must follow the format supported by the system (e.g. YYYY-MM-DD or YYYY-MM-DD HH:MM).

### Example

`deadline submit assignment /by 2026-03-15`

### Expected Output
```
________________________________________
Sweet! Added this gem:
[D][ ] submit assignment (by: 2026-03-15)
You're juggling 2 tasks now. Legendary! 🌟
________________________________________
```
<br>

## Adding an Event
### Goal

Add a task with a start and end time.

### Format

`event <DESCRIPTION> /from <START> /to <END>`

### Example

`event project meeting /from 2026-03-01 14:00 /to 2026-03-01 16:00`

### Expected Output
```
________________________________________
Sweet! Added this gem:
[E][ ] project meeting (from: 2026-03-01 14:00 to: 2026-03-01 16:00)
You're juggling 3 tasks now. Legendary! 🌟
________________________________________
```
<br>

## Mark a Task as Done
### Goal

Mark a task as completed.

### Format

`mark <INDEX>`

INDEX refers to the task number shown in the list command.

### Example

`mark 1`

### Expected Output
```
________________________________________
Boom! Task completed:
[T][X] finish homework
You're crushing it 💪
________________________________________
```
<br>

## Unmark a Task
### Goal

Set a completed task back to pending.

### Format

`unmark <INDEX>`

### Example

`unmark 1`

### Expected Output
```
________________________________________
No worries, task set back to pending:
[T][ ] finish homework
Take your time 😌
________________________________________
```
<br>

## Deleting a Task
### Goal

Remove a task permanently.

### Format

`delete <INDEX>`

### Example

`delete 2`

### Expected Output
```
________________________________________
Gotcha! Removed this task:
[D][ ] submit assignment (by: 2026-03-15)
Now you have 1 tasks left. Keep it up! 🚀
________________________________________
```
<br>

## Find Tasks
### Goal

Search for tasks containing a keyword.

### Format

`find <KEYWORD>`

### Example

`find assignment`

### Expected Output (when matches are found)]
```
________________________________________
Check these out, boss! Tasks that match your search:
1. [D][ ] submit assignment (by: 2026-03-15)
________________________________________
```

### Expected Output (when no matches are found)
```
________________________________________
Hmm... couldn't find anything matching that 🤷‍♂️
________________________________________
```
<br>

## Exit Arkanian
### Goal

Close the application.

### Format

`bye`

### Expected Output
```
________________________________________
Aight, see ya! Don't forget to come back with more tasks 😎
________________________________________
```
<br>

## Error Handling

### If an unknown command is entered:

```
Huh? That command sounds funky 😅
```

If required parameters are missing (e.g. /by, /from, /to), Arkanian will display an appropriate error message.

<br>

## Data Storage

- Tasks are automatically saved after adding a task.

- Tasks are automatically saved after marking or unmarking a task.

- Tasks are automatically saved after deleting a task.

- No manual save command is required.

