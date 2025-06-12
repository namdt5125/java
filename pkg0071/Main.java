/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.pkg0071;

/**
 *
 * @author Yourn
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        
        while (true) {
            displayMenu(); // Hiển thị menu 
            int choice = Validation.getInt("Enter your choice: ", 1, 4);
            
            // Thực hiện chức năng dựa trên lựa chọn 
            switch (choice) {
                case 1:
                    handleAdd(manager); // Thêm công việc 
                    break;
                case 2:
                    handleDelete(manager); // Xóa công việc 
                    break;
                case 3:
                    handleDisplay(manager); // Hiển thị công việc 
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!"); // Thoát chương trình 
                    return;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n========= Task program =========");
        System.out.println("1. Add Task");
        System.out.println("2. Delete task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
        System.out.println("================================");
    }

    private static void handleAdd(Manager manager) {
        System.out.println("------------Add Task---------------");
        String requirementName = Validation.getString("Requirement Name: ");
        String taskTypeId = Validation.getString("Task Type (1-Code, 2-Test, 3-Design, 4-Review): ");
        String date = Validation.getString("Date (dd-MM-yyyy): ");
        String planFrom = Validation.getString("From: ");
        String planTo = Validation.getString("To: ");
        String assignee = Validation.getString("Assignee: ");
        String reviewer = Validation.getString("Reviewer: ");

        try {
            int newId = manager.addTask(requirementName, taskTypeId, date, planFrom, planTo, assignee, reviewer);
            System.out.println("Task added successfully with ID: " + newId);
        } catch (Exception e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
    }

    private static void handleDelete(Manager manager) {
        System.out.println("---------Del Task------");
        if (manager.getDataTasks().isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }
        String id = Validation.getString("ID to delete: ");
        try {
            manager.deleteTask(id);
            System.out.println("Task deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }

    private static void handleDisplay(Manager manager) {
        System.out.println("----------------------------------------- Task ---------------------------------------");
        ArrayList<Task> tasks = manager.getDataTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.printf("%-7s%-20s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
