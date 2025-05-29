/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.l.p0021;

/**
 *
 * @author Yourn
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagement {

    private ArrayList<Student> studentList; // Sử dụng ArrayList để lưu trữ danh sách sinh viên [cite: 5]
    private Scanner scanner;

    public StudentManagement() {
        this.studentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Hàm tạo sinh viên [cite: 10]
    public void createStudent() {
        int count = 0;
        do {
            if (studentList.size() >= 10 && count > 0) { // Kiểm tra sau khi đã tạo ít nhất 10 sinh viên và đây không phải lần nhập đầu tiên trong vòng lặp này
                System.out.print("Do you want to continue (Y/N)? "); // [cite: 11]
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    break; // Quay về màn hình chính nếu chọn N [cite: 12]
                }
            }
            // Nếu đã có sinh viên, kiểm tra số lượng trước khi yêu cầu nhập mới
            if (studentList.size() >= 10 && count == 0 && studentList.size() % 10 == 0) { // Điều kiện này áp dụng khi đã có sẵn 10, 20,... sinh viên
                boolean firstStudentInBatch = studentList.size() % 10 == 0 && studentList.size() > 0;
                if (firstStudentInBatch) {
                    System.out.print("Number of students is " + studentList.size() + ". Do you want to continue (Y/N)? ");
                    String choice = scanner.nextLine().trim().toUpperCase();
                    if (!choice.equals("Y")) {
                        break;
                    }
                }
            }

            System.out.println("Enter student information:");
            String id;
            boolean idExists;
            do {
                System.out.print("ID: ");
                id = scanner.nextLine().trim();
                idExists = false;
                for (Student s : studentList) {
                    if (s.getId().equalsIgnoreCase(id)) {
                        System.out.println("Error: Student ID already exists. Please enter a unique ID.");
                        idExists = true;
                        break;
                    }
                }
            } while (id.isEmpty() || idExists);

            System.out.print("Student Name: ");
            String name = scanner.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Student Name cannot be empty. Please enter Student Name: ");
                name = scanner.nextLine().trim();
            }

            System.out.print("Semester: ");
            String semester = scanner.nextLine().trim();
            while (semester.isEmpty()) {
                System.out.print("Semester cannot be empty. Please enter Semester: ");
                semester = scanner.nextLine().trim();
            }

            String course;
            while (true) {
                System.out.print("Course Name (Java, .Net, C/C++): ");
                course = scanner.nextLine().trim();
                if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase(".Net") || course.equalsIgnoreCase("C/C++")) {
                    break;
                } else {
                    System.out.println("Invalid course name. Please choose from Java, .Net, C/C++.");
                }
            }

            studentList.add(new Student(id, name, semester, course));
            System.out.println("Student created successfully.");
            count++;

            if (studentList.size() < 10) { // Nếu chưa đủ 10 sinh viên, hỏi có muốn tạo tiếp không
                System.out.print("Do you want to create another student (Y/N)? ");
                String choice = scanner.nextLine().trim().toUpperCase();
                if (!choice.equals("Y")) {
                    if (studentList.size() < 10) { // Kiểm tra lại nếu người dùng không muốn tiếp tục và số lượng < 10
                        System.out.println("You must create at least 10 students.");
                        // Vòng lặp sẽ tiếp tục vì điều kiện studentList.size() < 10 vẫn đúng
                    } else {
                        break;
                    }
                }
            }

        } while (studentList.size() < 10 || (studentList.size() >= 10 && continueCreating())); // Đảm bảo tạo ít nhất 10 sinh viên [cite: 11]
    }

    private boolean continueCreating() {
        // Hàm này được gọi khi studentList.size() >= 10
        System.out.print("Do you want to continue creating more students (Y/N)? ");
        String choice = scanner.nextLine().trim().toUpperCase();
        return choice.equals("Y");
    }

    // Hàm tìm và sắp xếp sinh viên [cite: 12]
    public void findAndSortStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.print("Enter student name or a part of student name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase(); // [cite: 13]

        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(searchName)) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("No student found with that name.");
        } else {
            // Sắp xếp danh sách tìm thấy theo tên sinh viên [cite: 19]
            Collections.sort(foundStudents, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
                }
            });

            System.out.println("Found students (sorted by name):");
            for (Student student : foundStudents) {
                System.out.println(student.toFindSortString()); // Hiển thị: Student name, semester và Course Name [cite: 12]
            }
        }
    }

    // Hàm cập nhật hoặc xóa sinh viên [cite: 8, 14]
    public void updateOrDeleteStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No students to update or delete.");
            return;
        }

        System.out.print("Enter student ID to update/delete: ");
        String idToFind = scanner.nextLine().trim();

        Student studentToModify = null;
        int studentIndex = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(idToFind)) {
                studentToModify = studentList.get(i);
                studentIndex = i;
                break;
            }
        }

        if (studentToModify == null) {
            System.out.println("Student with ID " + idToFind + " not found.");
        } else {
            System.out.println("Found student: " + studentToModify);
            System.out.print("Do you want to update (U) or delete (D) student? "); // [cite: 15]
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("U")) {
                // Cập nhật thông tin sinh viên (không cho phép cập nhật ID)
                System.out.println("Enter new information (leave blank to keep current value):");

                System.out.print("New Student Name (" + studentToModify.getStudentName() + "): ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) {
                    studentToModify.setStudentName(newName);
                }

                System.out.print("New Semester (" + studentToModify.getSemester() + "): ");
                String newSemester = scanner.nextLine().trim();
                if (!newSemester.isEmpty()) {
                    studentToModify.setSemester(newSemester);
                }

                String newCourse;
                while (true) {
                    System.out.print("New Course Name (Java, .Net, C/C++) (" + studentToModify.getCourseName() + "): ");
                    newCourse = scanner.nextLine().trim();
                    if (newCourse.isEmpty()) { // Cho phép giữ nguyên giá trị cũ nếu để trống
                        break;
                    }
                    if (newCourse.equalsIgnoreCase("Java") || newCourse.equalsIgnoreCase(".Net") || newCourse.equalsIgnoreCase("C/C++")) {
                        studentToModify.setCourseName(newCourse);
                        break;
                    } else {
                        System.out.println("Invalid course name. Please choose from Java, .Net, C/C++.");
                    }
                }
                System.out.println("Student information updated successfully.");

            } else if (choice.equals("D")) {
                studentList.remove(studentIndex); // [cite: 7]
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Hàm báo cáo [cite: 15]
    public void report() {
        // Kiểm tra xem danh sách sinh viên có trống không
        if (studentList.isEmpty()) {
            System.out.println("No students to report."); // Không có sinh viên nào để báo cáo.
            return; // Kết thúc phương thức nếu không có sinh viên
        }

        // Sử dụng một Map để lưu trữ số lượng khóa học cho mỗi sinh viên.
        // Key của Map ngoài cùng là tên sinh viên (String).
        // Value của Map ngoài cùng là một Map khác, nơi lưu trữ các khóa học và số lần xuất hiện của chúng cho sinh viên đó.
        //      Trong Map bên trong: Key là tên khóa học (String), Value là số lần sinh viên đó học khóa học đó (Integer).
        // Ví dụ: "Nguyen Van A" -> {"Java" -> 2, ".Net" -> 1}
        Map<String, Map<String, Integer>> studentCourseCounts = new HashMap<>();

        // Bắt đầu duyệt qua từng đối tượng Student trong studentList
        for (Student student : studentList) {
            String studentName = student.getStudentName(); // Lấy tên của sinh viên hiện tại
            String courseName = student.getCourseName();   // Lấy tên khóa học của sinh viên hiện tại

            // Bước 1: Đảm bảo có một mục (entry) cho studentName trong studentCourseCounts.
            // studentCourseCounts.putIfAbsent(studentName, new HashMap<>());
            // - putIfAbsent(key, value): Nếu 'key' (studentName) chưa tồn tại trong Map,
            //   thì thêm 'key' với 'value' (một HashMap mới cho các khóa học của sinh viên này) vào Map.
            // - Nếu 'key' đã tồn tại, phương thức này không làm gì cả.
            // Mục đích: Nếu đây là lần đầu tiên gặp sinh viên này, tạo một Map mới để lưu trữ số lượng các khóa học của họ.
            studentCourseCounts.putIfAbsent(studentName, new HashMap<>());

            // Bước 2: Lấy Map chứa số lượng khóa học cho sinh viên hiện tại.
            // Map này (courseCountsForStudent) sẽ chứa các cặp (tên khóa học -> số lần học).
            Map<String, Integer> courseCountsForStudent = studentCourseCounts.get(studentName);

            // Bước 3: Cập nhật số lượng cho khóa học (courseName) cụ thể của sinh viên hiện tại.
            // courseCountsForStudent.put(courseName, courseCountsForStudent.getOrDefault(courseName, 0) + 1);
            // - getOrDefault(key, defaultValue): Lấy giá trị tương ứng với 'key' (courseName).
            //   Nếu 'key' không tồn tại (sinh viên này chưa được ghi nhận học khóa này), trả về 'defaultValue' (là 0).
            // - ... + 1: Tăng số lượng lên 1 (nếu mới thì 0 + 1 = 1, nếu đã có thì count + 1).
            // - put(key, value): Cập nhật lại số lượng mới cho courseName trong Map của sinh viên này.
            int currentCourseCount = courseCountsForStudent.getOrDefault(courseName, 0);
            courseCountsForStudent.put(courseName, currentCourseCount + 1);
        }

        // Dữ liệu đầu vào ví dụ: [cite: 16]
        // Nguyen Van A,Java
        // Nguyen Van A,Java
        // Nguyen Van B,.Net
        // Nguyen Van B,Java
        System.out.println("\nStudent Report:"); // In tiêu đề báo cáo
        // Hiển thị báo cáo theo định dạng: Student name | Course | Total of Course [cite: 17]

        // Bắt đầu duyệt qua Map studentCourseCounts để in báo cáo
        // Map.Entry<String, Map<String, Integer>> entry đại diện cho mỗi cặp (tên sinh viên -> Map các khóa học của họ)
        for (Map.Entry<String, Map<String, Integer>> studentEntry : studentCourseCounts.entrySet()) {
            String studentName = studentEntry.getKey(); // Lấy tên sinh viên từ key của entry
            Map<String, Integer> courses = studentEntry.getValue(); // Lấy Map các khóa học của sinh viên này

            // Duyệt qua Map các khóa học (courses) của sinh viên hiện tại
            // Map.Entry<String, Integer> courseEntry đại diện cho mỗi cặp (tên khóa học -> số lần học)
            for (Map.Entry<String, Integer> courseEntry : courses.entrySet()) {
                String courseName = courseEntry.getKey();   // Lấy tên khóa học
                Integer total = courseEntry.getValue(); // Lấy tổng số lần học khóa đó

                // In thông tin theo định dạng yêu cầu
                System.out.println(studentName + " | " + courseName + " | " + total);
            }
        }
        // Dựa trên dữ liệu đầu vào ví dụ[cite: 16]:
        // Nguyen Van A,Java
        // Nguyen Van A,Java
        // Nguyen Van B,.Net
        // Nguyen Van B,Java
        // Kết quả mong đợi sẽ là:
        // Nguyen Van A | Java | 2
        // Nguyen Van B | .Net | 1
        // Nguyen Van B | Java | 1
        // (Lưu ý: ví dụ trong đề bài [cite: 17] có "Nguyen Van C | Java | 1" có thể không khớp với dữ liệu đầu vào mẫu [cite: 16])
    }

    public void displayMenu() {
        System.out.println("\nWELCOME TO STUDENT MANAGEMENT"); // [cite: 9]
        System.out.println("1. Create"); // [cite: 9]
        System.out.println("2. Find and Sort"); // [cite: 9]
        System.out.println("3. Update/Delete"); // [cite: 9]
        System.out.println("4. Report"); // [cite: 9]
        System.out.println("5. Exit"); // [cite: 9]
        System.out.print("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program): "); // [cite: 9]
    }

    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();
        Scanner mainScanner = new Scanner(System.in);
        int choice;

        do {
            sm.displayMenu();
            while (!mainScanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                mainScanner.next(); // Xóa token không hợp lệ
                sm.displayMenu();
            }
            choice = mainScanner.nextInt();
            mainScanner.nextLine(); // Tiêu thụ dòng mới còn lại

            switch (choice) {
                case 1:
                    sm.createStudent();
                    break;
                case 2:
                    sm.findAndSortStudents();
                    break;
                case 3:
                    sm.updateOrDeleteStudent();
                    break;
                case 4:
                    sm.report();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 5);

        mainScanner.close();
        sm.scanner.close(); // Đóng scanner của lớp StudentManagement
    }
}
