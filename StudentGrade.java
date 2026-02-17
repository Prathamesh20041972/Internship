public class StudentGrade {

    public static void main(String[] args) {

        // Store student names
        String[] names = {"Rahul", "Amit", "Sneha", "Priya", "Karan"};

        // Store student marks
        int[] marks = {85, 72, 91, 65, 78};

        // Loop through students
        for (int i = 0; i < names.length; i++) {

            String grade;

            if (marks[i] >= 90) {
                grade = "A";
            } 
            else if (marks[i] >= 75) {
                grade = "B";
            } 
            else if (marks[i] >= 60) {
                grade = "C";
            } 
            else {
                grade = "Fail";
            }

            // Display result
            System.out.println("Student Name: " + names[i]);
            System.out.println("Marks: " + marks[i]);
            System.out.println("Grade: " + grade);
            System.out.println("------------------------");
        }
    }
}
