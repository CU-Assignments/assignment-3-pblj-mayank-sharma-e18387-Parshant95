import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private List<String> prerequisites;
    private List<String> enrolledStudents;

    public Course(String name, int capacity, List<String> prerequisites) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisites = prerequisites;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(String studentName, List<String> completedCourses) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Error: CourseFullException - " + name + " is full.");
        }

        for (String prerequisite : prerequisites) {
            if (!completedCourses.contains(prerequisite)) {
                throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + prerequisite + " before enrolling in " + name + ".");
            }
        }

        enrolledStudents.add(studentName);
        System.out.println("Enrollment successful for " + studentName + " in " + name + ".");
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> prerequisites = new ArrayList<>();
        prerequisites.add("Core Java");

        Course advancedJava = new Course("Advanced Java", 2, prerequisites);

        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter completed courses (comma separated): ");
        String[] completed = scanner.nextLine().split(",");
        List<String> completedCourses = new ArrayList<>();
        for (String course : completed) {
            completedCourses.add(course.trim());
        }

        try {
            advancedJava.enrollStudent(studentName, completedCourses);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
