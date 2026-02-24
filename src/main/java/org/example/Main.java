package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Task {
    public int id;
    public String description;
    public String status;
    public String updatedAt;

    // Default constructor for Jackson
    public Task() {}

    public Task(int id, String description, String status, String timeStamp) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.updatedAt = timeStamp;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", description='" + description + "', status=" + status + ", timeStamp=" + updatedAt + "}";
    }
}


public class Main {
    private static final String JSON_FILE = "tasks.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static List<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // For pretty print
        loadTasks();
        while (true) {
            System.out.println("\nTask Controller CLI:");
            System.out.println("1. List Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        listTasks();
                        break;
                    case "2":
                        addTask();
                        break;
                    case "3":
                        updateTask();
                        break;
                    case "4":
                        deleteTask();
                        break;
                    case "5":
                        saveTasks();
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadTasks() {
        try {
            File file = Paths.get(JSON_FILE).toFile();
            if (file.exists()) {
                tasks = mapper.readValue(file, new TypeReference<List<Task>>(){});
            }
        } catch (IOException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
        }
    }


    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void addTask() throws IOException {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.println("Enter task status: ");
        String status = scanner.nextLine();
        int id = tasks.isEmpty() ? 1 : tasks.getLast().id + 1;
        String updatedAt = String.valueOf(LocalDateTime.now());
        tasks.add(new Task(id, description, status, updatedAt));
        saveTasks();
        System.out.println("Task added.");
    }
    private static void saveTasks() throws IOException {
        mapper.writeValue(Paths.get(JSON_FILE).toFile(), tasks);
    }

    private static void updateTask() throws IOException {
        System.out.print("Enter task ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task taskToUpdate = tasks.stream().filter(t -> t.id == id).findFirst().orElse(null);

        if (taskToUpdate != null) {
            System.out.print("Enter new description (or press Enter to keep current): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                taskToUpdate.description = description;
            }

            System.out.print("Update Status: ");
            String newStatus = scanner.nextLine();
            // Check if user entered a new status
            if (!newStatus.isEmpty()) {
                taskToUpdate.status = newStatus;
                taskToUpdate.updatedAt = String.valueOf(LocalDateTime.now());
            }
            saveTasks();
            System.out.println("Task updated.");
        } else {
            System.out.println("Task not found.");
        }

    }

    private static void deleteTask() throws IOException {
        System.out.print("Enter task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Using iterator for safe removal during iteration
        boolean removed = tasks.removeIf(task -> task.id == id);

        if (removed) {
            saveTasks();
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }
}