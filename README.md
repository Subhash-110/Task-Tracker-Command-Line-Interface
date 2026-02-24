📌 Task Controller CLI (Java + Jackson)

A simple **Command Line Interface (CLI)** based Task Management application built using **Java**.
This application allows users to **add, list, update, and delete tasks**, with data persisted in a local JSON file using the **Jackson library**.

---

🚀 Features

* ✅ Add new tasks
* 📋 List all tasks
* ✏️ Update existing tasks
* ❌ Delete tasks
* 💾 Persistent storage using `tasks.json`
* 🕒 Automatic timestamp update when task status changes
* 📄 Pretty-printed JSON output

---

🛠️ Technologies Used

* **Java**
* **Jackson Databind**

  * `ObjectMapper`
  * `TypeReference`
  * `SerializationFeature`
* Java Time API (`LocalDateTime`)
* File Handling (Java IO & NIO)

---

📂 Project Structure


org.example
│
├── Main.java
└── tasks.json (generated automatically)


---

## ⚙️ How It Works

When the application starts:

1. It loads existing tasks from `tasks.json` (if available).
2. Displays a CLI menu:


   1. List Tasks
   2. Add Task
   3. Update Task
   4. Delete Task
   5. Exit

3. Performs the selected operation.
4. Saves changes automatically to `tasks.json`.

---

📦 Task Model

Each task contains:

json
{
  "id": 1,
  "description": "Complete Java project",
  "status": "In Progress",
  "updatedAt": "2026-02-24T10:30:00"
}


Fields

| Field       | Type   | Description            |
| ----------- | ------ | ---------------------- |
| id          | int    | Unique task ID         |
| description | String | Task description       |
| status      | String | Current task status    |
| updatedAt   | String | Last updated timestamp |

---

▶️ How to Run

1️⃣ Compile

If using command line:

bash
javac -cp ".;jackson-databind-2.x.x.jar;jackson-core-2.x.x.jar;jackson-annotations-2.x.x.jar" org/example/Main.java


2️⃣ Run

bash
java org.example.Main


---

📌 Example Usage


Task Controller CLI:
1. List Tasks
2. Add Task
3. Update Task
4. Delete Task
5. Exit
Enter your choice: 2

Enter task description: Learn Jackson
Enter task status:
Pending

Task added.


---

🧠 Design Decisions

* Uses `ObjectMapper` for easy JSON serialization/deserialization.
* Uses `TypeReference<List<Task>>` to properly read generic list data.
* Uses `removeIf()` for safe deletion.
* Automatically generates task ID based on the last task.
* Pretty JSON output for better readability.

---

📈 Possible Improvements

* Add status validation (Pending, In Progress, Completed)
* Add search feature
* Add filtering by status
* Add sorting options
* Convert into REST API using Spring Boot
* Add unit tests
* Add input validation for better error handling

---

📜 License

This project is open-source and free to use for learning purposes.
