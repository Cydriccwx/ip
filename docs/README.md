# Cydric User Guide

**Cydric** is a desktop application for managing tasks, optimised for use via a Command Line Interface (CLI). If you can type fast, Cydric can manage your daily tasks, deadlines, and events faster than traditional GUI apps.

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Cydric.jar` from the releases page.
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Cydric.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.

---

## Features

### 1. Adding a Todo: `todo`
Adds a simple task without any date or time attached to it.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo read CS2113 textbook`
```
 Got it. I've added this task:
   [T][ ] read CS2113 textbook
 Now you have 1 tasks in the list.
```

### 2. Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date/time.
* **Format:** `deadline DESCRIPTION /by DATETIME`
* **Example:** `deadline submit assignment /by Friday 2359`
```
Got it. I've added this task:
   [D][ ] submit assignment (by: Friday 2359)
 Now you have 2 tasks in the list.
```

### 3. Adding an Event: `event`
Adds a task that starts and ends at a specific time.
* **Format:** `event DESCRIPTION /from DATETIME /to DATETIME`
* **Example:** `event project meeting /from Aug 6th 2pm /to 4pm`
```
Got it. I've added this task:
   [E][ ] project meeting (From: Aug 6th 2pm To: 4pm)
 Now you have 3 tasks in the list.
```

### 4. Listing all tasks: `list`
Shows a numbered list of all the tasks you have currently tracked.
* **Format:** `list`
```
Here are the tasks in your list:
  1. [T][ ] read CS2113 textbook
  2. [D][ ] submit assignment (by: Friday 2359)
  3. [E][ ] project meeting (From: Aug 6th 2pm To: 4pm)
```

### 5. Marking a task as done: `mark`
Marks a specific task in your list as completed (indicated by a ✅).
* **Format:** `mark INDEX`
    * *The index refers to the number shown in the `list` command.*
* **Example:** `mark 2`
```
Nice! I've marked this task as done:
  [D][✅] submit assignment (by: Friday 2359)
```

### 6. Unmarking a task: `unmark`
Marks a specific completed task as not done yet.
* **Format:** `unmark INDEX`
* **Example:** `unmark 2`
```
Alright! I've marked this task as not done yet:
  [D][ ] submit assignment (by: Friday 2359)
```

### 7. Deleting a task: `delete`
Deletes the specified task from the list permanently.
* **Format:** `delete INDEX`
* **Example:** `delete 1`
```
Alright! I have removed this task:
   [T][ ] read CS2113 textbook
 Now you have 2 tasks in the list.
```

### 8. Finding a task: `find`
Finds tasks whose descriptions contain the given keyword.
* **Format:** `find KEYWORD`
* **Example:** `find meeting`
```
Here are the matching tasks in your list:
  1.[E][ ] project meeting (From: Aug 6th 2pm To: 4pm)
```

### 9. Exiting the program: `bye`
Exits the application.
* **Format:** `bye`
```
Bye. Hope to see you again soon!
```

---

## Data Storage
Cydric data is saved automatically as a text file at `[JAR_FILE_LOCATION]/data/cydric.txt` after any command that changes the data. There is no need to save manually.