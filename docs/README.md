# Gin – Task Manager Chatbot

Gin is a command-line chatbot that helps you track and manage your tasks.
Type commands to add, view, find, and organise your to-do list — Gin handles the rest.

---

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `ip.jar` from the releases page.
3. Run the application:
   ```
   java -jar ip.jar
   ```
4. Type a command to get started. Try `todo read book`.

---

## Features

### Add a To-Do Task: `todo`

Adds a basic task with no date or time attached.

Format: `todo <description>`

Example:
```
todo read book
```
```
------------------------------------------------------------
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task(s) in the list.
------------------------------------------------------------
```

---

### Add a Deadline Task: `deadline`

Adds a task with a due date and/or time.

Format: `deadline <description> /by <due date/time>`

Example:
```
deadline submit essay /by Sunday
```
```
------------------------------------------------------------
Got it. I've added this task:
  [D][ ] submit essay (by: Sunday)
Now you have 2 task(s) in the list.
------------------------------------------------------------
```

---

### Add an Event Task: `event`

Adds a task with a start and end date and/or time.

Format: `event <description> /from <start> /to <end>`

Example:
```
event team retreat /from Monday /to Wednesday
```
```
------------------------------------------------------------
Got it. I've added this task:
  [E][ ] team retreat (from: Monday to: Wednesday)
Now you have 3 task(s) in the list.
------------------------------------------------------------
```

---

### List All Tasks: `list`

Displays all tasks currently in your list, numbered from 1.

Format: `list`

Example:
```
------------------------------------------------------------
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit essay (by: Sunday)
3. [E][ ] team retreat (from: Monday to: Wednesday)
------------------------------------------------------------
```

---

### Mark a Task as Done: `mark`

Marks the task at the given index as done.

Format: `mark <index>`

Example:
```
mark 1
```
```
------------------------------------------------------------
Nice! I've marked this task as done:
  [T][X] read book
------------------------------------------------------------
```

---

### Mark a Task as Not Done: `unmark`

Marks the task at the given index as not done.

Format: `unmark <index>`

Example:
```
unmark 1
```
```
------------------------------------------------------------
OK, I've marked this task as not done yet:
  [T][ ] read book
------------------------------------------------------------
```

---

### Delete a Task: `delete`

Removes the task at the given index from the list.

Format: `delete <index>`

Example:
```
delete 2
```
```
------------------------------------------------------------
Noted. I've removed this task:
  [D][ ] submit essay (by: Sunday)
Now you have 2 task(s) in the list.
------------------------------------------------------------
```

---

### Find Tasks by Keyword: `find`

Searches all task descriptions for the given keyword. The search is case-insensitive.

Format: `find <keyword>`

Example:
```
find book
```
```
------------------------------------------------------------
Here are the matching tasks in your list:
1. [T][ ] read book
------------------------------------------------------------
```

---

### Clear All Tasks: `clear`

Removes all tasks from the list at once.

Format: `clear`

Example:
```
------------------------------------------------------------
All 3 task(s) have been cleared.
------------------------------------------------------------
```

---

### Exit: `bye`

Exits the Gin application.

Format: `bye`

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```

---

## Command Summary

| Command | Format |
|---|---|
| Add to-do | `todo <description>` |
| Add deadline | `deadline <description> /by <due date/time>` |
| Add event | `event <description> /from <start> /to <end>` |
| List all tasks | `list` |
| Mark as done | `mark <index>` |
| Mark as not done | `unmark <index>` |
| Delete a task | `delete <index>` |
| Find by keyword | `find <keyword>` |
| Clear all tasks | `clear` |
| Exit | `bye` |