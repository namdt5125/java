/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.pkg0071;

/**
 *
 * @author Yourn
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private int taskTypeId;
    private String requirementName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;

    public Task(int id, int taskTypeId, String requirementName, Date date, double planFrom, double planTo, String assignee, String reviewer) {
        this.id = id;
        this.taskTypeId = taskTypeId;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    // Getters
    public int getId() {
        return id;
    }
    
    // Phương thức để lấy tên loại công việc từ ID
    public String getTaskTypeName() {
        switch (this.taskTypeId) {
            case 1:
                return "Code";
            case 2:
                return "Test";
            case 3:
                return "Design";
            case 4:
                return "Review";
            default:
                return "Unknown";
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        double time = planTo - planFrom;
        
        // Định dạng chuỗi hiển thị theo yêu cầu
        return String.format("%-7d%-20s%-15s%-15s%-15.1f%-15s%-15s",
                id,
                requirementName,
                getTaskTypeName(),
                dateFormat.format(date),
                time,
                assignee,
                reviewer);
    }
}
