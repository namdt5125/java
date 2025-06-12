/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.pkg0071;

/**
 *
 * @author Yourn
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Manager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int lastId = 0;

    /**
     * Hàm khởi tạo (Constructor) của lớp Manager.
     * Tự động thêm một số dữ liệu mẫu khi chương trình khởi động.
     */
    public Manager() {
        try {
            // Dữ liệu mẫu 1
            addTask("Dev Program", "1", "28-08-2025", "8.5", "17.5", "Dev", "Lead");
            
            // Dữ liệu mẫu 2
            addTask("Test API", "2", "29-08-2025", "9.0", "11.5", "Tester", "Manager");
            
            // Dữ liệu mẫu 3
            addTask("Design UI/UX", "3", "30-08-2025", "13.0", "17.0", "Designer", "Lead");

        } catch (Exception e) {
            // Lỗi này không nên xảy ra với dữ liệu mẫu hợp lệ,
            // nhưng try-catch là bắt buộc vì phương thức addTask có 'throws Exception'.
            System.err.println("Lỗi khi khởi tạo dữ liệu mẫu: " + e.getMessage());
        }
    }

    /**
     * Thêm một công việc mới vào danh sách.
     * @return ID của công việc mới được tạo. 
     * @throws Exception nếu dữ liệu đầu vào không hợp lệ.
     */
    public int addTask(String requirementName, String taskTypeId, String date, String planFrom, String planTo, String assignee, String reviewer) throws Exception {
        int typeId;
        double from, to;

        // Sử dụng try-catch để bắt NumberFormatException 
        try {
            typeId = Integer.parseInt(taskTypeId);
            from = Double.parseDouble(planFrom);
            to = Double.parseDouble(planTo);
        } catch (NumberFormatException e) {
            throw new Exception("Task Type, Plan From, Plan To must be numbers.");
        }

        // Kiểm tra TaskTypeID phải tồn tại (1-4) 
        if (typeId < 1 || typeId > 4) {
            throw new Exception("Task Type must be from 1 to 4.");
        }
        
        // Kiểm tra Plan From và Plan To nằm trong khoảng 8.0 - 17.5 
        if (from < 8.0 || from > 17.5 || to < 8.0 || to > 17.5) {
            throw new Exception("Planning time must be within 8.0 and 17.5.");
        }
        
        if (from % 0.5 != 0 || to % 0.5 != 0) {
            throw new Exception("Planning time must be in 0.5 hour increments (e.g., 8.0, 8.5).");
        }

        // Plan From phải nhỏ hơn Plan To 
        if (from >= to) {
            throw new Exception("Plan From must be less than Plan To.");
        }

        // Sử dụng SimpleDateFormat để xử lý ngày tháng 
        // Kiểm tra thông tin phải là ngày hợp lệ theo định dạng dd-MM-yyyy 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new Exception("Date must be in the format dd-MM-yyyy.");
        }

        // ID = ID công việc cuối cùng + 1 
        int newId = ++lastId;
        Task newTask = new Task(newId, typeId, requirementName, parsedDate, from, to, assignee, reviewer);
        taskList.add(newTask);
        return newId;
    }

    /**
     * Xóa một công việc dựa trên ID. 
     * @throws Exception nếu ID không tồn tại hoặc không hợp lệ. 
     */
    public void deleteTask(String id) throws Exception {
        int taskId;
        try {
            taskId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new Exception("ID must be a number.");
        }

        // Kiểm tra Id phải tồn tại trong DB (danh sách) 
        boolean removed = taskList.removeIf(task -> task.getId() == taskId);

        if (!removed) {
            throw new Exception("Task with this ID does not exist.");
        }
    }

    /**
     * Trả về danh sách tất cả các công việc. 
     */
    public ArrayList<Task> getDataTasks() {
        return taskList;
    }
}