import java.util.ArrayList;
import java.util.HashMap;

public class Module {
    /**
     *
     */
    private String moduleName;
    private String moduleID;

    // Stores students for this module
//    private ArrayList<Student> studentsEnrolled = new ArrayList<>();
    private HashMap<Student, Integer> studentsEnrolled = new HashMap<>();

    public Module(String moduleName){
        this.moduleName = moduleName;
    }

    public Module(String moduleName, String  moduleID){
        this.moduleName = moduleName;
        this.moduleID = moduleID;
    }

    // Getters
    public String getModuleName() {
        return moduleName;
    }

    public String getModuleID() {
        return moduleID;
    }

    public HashMap<Student, Integer> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    // Array CRUD
    public void enrollStudent(Student student){
        getStudentsEnrolled().put(student, null);
    }
    public void enrollStudent(Student student, int grade){
        getStudentsEnrolled().put(student, grade);
    }

    public void viewEnrolledStudents(){
        for (Student student:
                getStudentsEnrolled().keySet()) {
            System.out.println(student.getStudentName()
                    + " Grade : " + getStudentsEnrolled().get(student));
        }
    }

    // insert search for specific student and remove it from the array
}
