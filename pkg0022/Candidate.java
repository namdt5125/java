/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.l.pkg0022;

/**
 *
 * @author Yourn
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

// Lớp Candidate (Lớp cha) [cite: 8, 22]
abstract class Candidate {
    protected String candidateId;
    protected String firstName;
    protected String lastName;
    protected int birthDate; // Storing year as int [cite: 12]
    protected String address;
    protected String phone; // Storing as String to keep leading zeros and check length [cite: 12]
    protected String email;
    protected int candidateType; // 0 for Experience, 1 for Fresher, 2 for Intern [cite: 9]

    public Candidate(String candidateId, String firstName, String lastName, int birthDate, String address, String phone, String email, int candidateType) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
    }

    // Getters
    public String getCandidateId() {
        return candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getCandidateType() {
        return candidateType;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + birthDate + " | " + address + " | " + phone + " | " + email + " | " + candidateType;
    }
}

// Lớp Experience Candidate [cite: 8]
class Experience extends Candidate {
    private int expInYear;
    private String proSkill;

    public Experience(String candidateId, String firstName, String lastName, int birthDate, String address, String phone, String email, int expInYear, String proSkill) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 0); // CandidateType = 0 [cite: 9]
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    @Override
    public String toString() {
        return super.toString(); // Format is common for search results [cite: 17]
    }
}

// Lớp Fresher Candidate [cite: 10]
class Fresher extends Candidate {
    private String graduationDate; // Can be year or more specific, assuming String for flexibility
    private String graduationRank;
    private String education; // University

    public Fresher(String candidateId, String firstName, String lastName, int birthDate, String address, String phone, String email, String graduationDate, String graduationRank, String education) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 1); // CandidateType = 1 [cite: 9]
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public String getEducation() {
        return education;
    }

     @Override
    public String toString() {
         return super.toString(); // Format is common for search results [cite: 17]
    }
}

// Lớp Intern Candidate [cite: 10]
class Intern extends Candidate {
    private String majors;
    private String semester;
    private String universityName;

    public Intern(String candidateId, String firstName, String lastName, int birthDate, String address, String phone, String email, String majors, String semester, String universityName) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 2); // CandidateType = 2 [cite: 9]
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public String getSemester() {
        return semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    @Override
    public String toString() {
        return super.toString(); // Format is common for search results [cite: 17]
    }
}

// Lớp Validation [cite: 11, 12, 13]
class Validation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MIN_BIRTH_YEAR = 1900;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    public static boolean isValidBirthDate(int year) { // [cite: 12]
        return year >= MIN_BIRTH_YEAR && year <= CURRENT_YEAR;
    }

    public static boolean isValidPhone(String phone) { // [cite: 12]
        return phone != null && phone.matches("\\d{10,}"); // Minimum 10 characters, all digits
    }

    public static boolean isValidEmail(String email) { // [cite: 12, 13]
        // Basic email format: <account name>@<domain>
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isValidExpInYear(int expInYear) { // [cite: 13]
        return expInYear >= 0 && expInYear <= 100;
    }

    public static boolean isValidGraduationRank(String rank) { // [cite: 13]
        String lowerRank = rank.toLowerCase();
        return lowerRank.equals("excellence") || lowerRank.equals("good") || lowerRank.equals("fair") || lowerRank.equals("poor");
    }

    public static String getRequiredString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }
    
    public static int getInt(String prompt, int min, int max) {
        int number;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static int getValidatedBirthDate(String prompt) {
        int year;
        while (true) {
            year = getInt(prompt, MIN_BIRTH_YEAR, CURRENT_YEAR);
            if (isValidBirthDate(year)) {
                return year;
            }
            // getInt already constrains to MIN_BIRTH_YEAR, CURRENT_YEAR, so additional check is redundant here if min/max in getInt are set correctly
             System.out.println("Invalid Birth Date. Must be between " + MIN_BIRTH_YEAR + " and " + CURRENT_YEAR + "."); // [cite: 12]
        }
    }
    
    public static String getValidatedPhone(String prompt) {
        String phone;
        while(true) {
            phone = getRequiredString(prompt);
            if (isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Invalid phone number. Must be a number with minimum 10 characters."); // [cite: 12]
        }
    }

    public static String getValidatedEmail(String prompt) {
        String email;
        while(true) {
            email = getRequiredString(prompt);
            if (isValidEmail(email)) {
                return email;
            }
            System.out.println("Invalid email format. Expected: <account name>@<domain> (e.g., annguyen@fpt.edu.vn)"); // [cite: 12, 13]
        }
    }
    
    public static int getValidatedExpInYear(String prompt) {
        int exp;
        while(true) {
            exp = getInt(prompt, 0, 100);
             if (isValidExpInYear(exp)) { // [cite: 13]
                return exp;
            }
            // getInt already constrains, this is for logical completeness if getInt had wider range.
             System.out.println("Year of experience must be between 0 and 100.");
        }
    }

    public static String getValidatedGraduationRank(String prompt) {
        String rank;
        while(true) {
            rank = getRequiredString(prompt).trim();
            if (isValidGraduationRank(rank)) { // [cite: 13]
                return rank; // Return the original casing or a normalized one (e.g. capitalize first letter)
            }
            System.out.println("Invalid rank. Must be one of: Excellence, Good, Fair, Poor.");
        }
    }
}

// Lớp CandidateManager
class CandidateManager {
    private ArrayList<Candidate> candidates;
    private Scanner scanner;

    public CandidateManager() {
        this.candidates = new ArrayList<>(); // [cite: 11]
        this.scanner = new Scanner(System.in);
    }

    public void createCandidate(int candidateType) {
        do {
            System.out.println("\nCreating new " + getTypeName(candidateType) + " Candidate:");
            String id = Validation.getRequiredString("Enter Candidate ID: ");
            // Optional: Check for ID uniqueness
            // for (Candidate c : candidates) { if (c.getCandidateId().equals(id)) { System.out.println("ID already exists."); continue outer; } }

            String firstName = Validation.getRequiredString("Enter First Name: ");
            String lastName = Validation.getRequiredString("Enter Last Name: ");
            int birthDate = Validation.getValidatedBirthDate("Enter Birth Date (YYYY): "); // [cite: 12]
            String address = Validation.getRequiredString("Enter Address: ");
            String phone = Validation.getValidatedPhone("Enter Phone number: "); // [cite: 12]
            String email = Validation.getValidatedEmail("Enter Email: "); // [cite: 12]

            switch (candidateType) {
                case 0: // Experience [cite: 9]
                    int expInYear = Validation.getValidatedExpInYear("Enter Year of Experience (0-100): "); // [cite: 13]
                    String proSkill = Validation.getRequiredString("Enter Professional Skill: ");
                    candidates.add(new Experience(id, firstName, lastName, birthDate, address, phone, email, expInYear, proSkill));
                    break;
                case 1: // Fresher [cite: 9]
                    String gradDate = Validation.getRequiredString("Enter Graduation Date (e.g., YYYY-MM or YYYY): ");
                    String gradRank = Validation.getValidatedGraduationRank("Enter Rank of Graduation (Excellence, Good, Fair, Poor): "); // [cite: 13]
                    String education = Validation.getRequiredString("Enter University of Graduation: ");
                    candidates.add(new Fresher(id, firstName, lastName, birthDate, address, phone, email, gradDate, gradRank, education));
                    break;
                case 2: // Intern [cite: 9]
                    String majors = Validation.getRequiredString("Enter Majors: ");
                    String semester = Validation.getRequiredString("Enter Semester: ");
                    String uniName = Validation.getRequiredString("Enter University Name: ");
                    candidates.add(new Intern(id, firstName, lastName, birthDate, address, phone, email, majors, semester, uniName));
                    break;
                default:
                    System.out.println("Invalid candidate type.");
                    return; // Should not happen if called from main menu correctly
            }
            System.out.println("Candidate created successfully.");

            System.out.print("Do you want to continue (Y/N)? "); // [cite: 14]
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("N")) { // [cite: 15]
                displayAllCandidates("\nList of created candidates:"); // [cite: 15]
                break;
            }
            if (!choice.equalsIgnoreCase("Y")){ // Treat anything other than Y (case-insensitive) as N
                 displayAllCandidates("\nList of created candidates:");
                 break;
            }
        } while (true);
    }

    public void searchCandidates() { // [cite: 16]
        if (candidates.isEmpty()) {
            System.out.println("No candidates in the system to search.");
            return;
        }
        displayAllCandidates("\nList of candidate:"); // [cite: 16, 18]

        System.out.print("Input Candidate name (First name or Last name): "); // [cite: 16]
        String nameQuery = scanner.nextLine().trim().toLowerCase();
        System.out.print("Input type of candidate (0: Experience, 1: Fresher, 2: Intern): "); // [cite: 16]
        int typeQuery;
        try {
            typeQuery = Integer.parseInt(scanner.nextLine().trim());
            if (typeQuery < 0 || typeQuery > 2) {
                System.out.println("Invalid candidate type for search.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid type format. Please enter a number (0-2).");
            return;
        }
        

        System.out.println("\nThe candidates found:");
        boolean found = false;
        for (Candidate cand : candidates) {
            boolean nameMatch = cand.getFirstName().toLowerCase().contains(nameQuery) ||
                                cand.getLastName().toLowerCase().contains(nameQuery);
            boolean typeMatch = cand.getCandidateType() == typeQuery;

            if (nameMatch && typeMatch) {
                System.out.println(cand.toString()); // [cite: 17, 19]
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching candidates found.");
        }
    }

    public void displayAllCandidates(String title) {
        if (candidates.isEmpty() && title.toLowerCase().contains("created")) { // Only show if no candidates yet for this specific call
             System.out.println("No candidates have been created yet.");
             return;
        }
         if (candidates.isEmpty()) {
             System.out.println("No candidates in the system.");
             return;
        }

        System.out.println(title);
        System.out.println("===========EXPERIENCE CANDIDATE============"); // [cite: 18]
        candidates.stream()
            .filter(c -> c instanceof Experience)
            .forEach(c -> System.out.println(c.getFirstName() + " " + c.getLastName()));

        System.out.println("==========FRESHER CANDIDATE=============="); // [cite: 18]
        candidates.stream()
            .filter(c -> c instanceof Fresher)
            .forEach(c -> System.out.println(c.getFirstName() + " " + c.getLastName()));

        System.out.println("===========INTERN CANDIDATE=============="); // [cite: 18]
        candidates.stream()
            .filter(c -> c instanceof Intern)
            .forEach(c -> System.out.println(c.getFirstName() + " " + c.getLastName()));
        System.out.println("==========================================");
    }
    
    private String getTypeName(int type) {
        switch(type) {
            case 0: return "Experience";
            case 1: return "Fresher";
            case 2: return "Intern";
            default: return "Unknown";
        }
    }
}


// Lớp Main
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CandidateManager manager = new CandidateManager();

        while (true) {
            System.out.println("\nCANDIDATE MANAGEMENT SYSTEM"); // [cite: 10]
            System.out.println("1. Experience"); // [cite: 10]
            System.out.println("2. Fresher"); // [cite: 10]
            System.out.println("3. Internship"); // [cite: 10]
            System.out.println("4. Searching"); // [cite: 10]
            System.out.println("5. Exit"); // [cite: 10]
            System.out.print("(Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate, 3 to Internship Candidate, 4 to Searching and 5 to Exit program): "); // [cite: 10]

            String choiceStr = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number (1-5).");
                continue;
            }


            switch (choice) {
                case 1:
                    manager.createCandidate(0); // 0 for Experience [cite: 9]
                    break;
                case 2:
                    manager.createCandidate(1); // 1 for Fresher [cite: 9]
                    break;
                case 3:
                    manager.createCandidate(2); // 2 for Intern [cite: 9]
                    break;
                case 4:
                    manager.searchCandidates(); // [cite: 16]
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close(); // Close scanner before exiting
                    // It's good practice to close scanners associated with System.in,
                    // but Validation class also has one. Managing scanner lifecycle carefully is important.
                    // For simplicity here, closing the main one.
                    return;
                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }
    }
}
