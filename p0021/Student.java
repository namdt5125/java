/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.l.p0021;

/**
 *
 * @author Yourn
 */
public class Student {
    private String id;
    private String studentName;
    private String semester;
    private String courseName;

    public Student(String id, String studentName, String semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Ghi đè phương thức toString để hiển thị thông tin sinh viên
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + studentName + ", Semester: " + semester + ", Course: " + courseName;
    }

    // Phương thức để hiển thị thông tin khi tìm kiếm và sắp xếp
    public String toFindSortString() {
        return "Student Name: " + studentName + ", Semester: " + semester + ", Course Name: " + courseName;
    }
}