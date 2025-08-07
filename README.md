# 🗂️ CLI Task Manager

A simple command-line task management system built in Java. It supports creating, editing, assigning, and viewing tasks and users, along with filtering by status, due date, and role. Designed to simulate real-world task-user relationships in a lightweight, object-oriented way.

---

## 📦 Features

### ✅ Task Management

- Add, edit, and remove tasks (`addT`, `editT`, `rmT`, `rmMT`)
- Set task title, description, and due date (`MM/dd/yyyy`)
- Track task status: `TODO`, `IN_PROGRESS`, `DONE`
- View single or all tasks with filtering options

### 👤 User Management

- Add, edit, and remove users (`addU`, `editU`, `rmU`, `rmMU`)
- Store user names and roles
- View individual users or filter all users by role

### 🔁 Assignment

- Assign tasks to users, and vice versa:
  - Single assignments (`asgnU`, `unasgnU`)
  - Multiple tasks to one user (`asgnMT`)
  - One task to multiple users (`asgnMU`)
  - Full batch mode (`asgnMTU`, `unasgnMTU`)

### 📊 Viewing Options

- View all tasks by:
  - Status
  - Due date
  - Assigned user
- View all users by:
  - Role

---

## 🛠️ Tech Stack

- **Java** — core language (Java 8+)
- **Object-Oriented Design** — clean separation of concerns with classes: `Task`, `User`, `TaskManager`, etc.
- **Enums** — used for task status
- **Collections** — uses `ArrayList`, `LinkedHashMap`, and more
- **Date API** — `LocalDate` for managing due dates
- **CLI Design** — interactive interface using `Scanner`

---

## 🧪 How to Run

1. **Clone the project**

   ```bash
   git clone https://github.com/yourusername/cli-task-manager.git
   cd cli-task-manager
   ```

2. **Compile**

   ```bash
   javac Main.java
   ```

3. ** Run **

## Example Commands

Would you like to add a task: command -> addT
Would you like to edit a user: command -> editU
Would you like to assign a task to a user: command -> asgnU
Type q to quit:

## 📁 Project Structure

```bash
task-manager/
│
├── Main.java           # CLI entry point
├── TaskManager.java    # Core logic controller
├── Task.java           # Task model
├── User.java           # User model
├── TaskInput.java      # Utility for multi-ID input
├── TaskStatus.java     # Enum for task statuses
├── Test.java           # Optional test file
└── README.md           # You're here
```

## 🧠 Future Improvements

- Load/save tasks and users to a file
- Unit testing with JUnit
- Connected to a frontend version of the Task Manager

## 🧑‍💻 Author

Created by Freeman Nkouka

## 📄 License

MIT License. Feel free to use, fork, and improve.
