import java.util.ArrayList;

public class Student {
    private String studentName;
    private String studentID;
    private ArrayList<Number> grades = new ArrayList<>();

    public Student(String studentName){
        this.studentName = studentName;
    }

    public Student(String studentName, String studentID){
        this.studentName = studentName;
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }

}
