import java.util.HashMap;

/**
 *The Module class represents a module in a student record management system.
 * <p>It stores information about a specific module, including its name, ID, and the students enrolled in the module along with their grades.</p>
 */
public class Module {

    private String moduleName;
    private String moduleID;

    // Stores students for this module
    private HashMap<Student, Integer> studentsEnrolled = new HashMap<>();

            /*  Constructors */

    /**
     * Constructor for a Module without ID.
     * @param moduleName The name of the module.
     */
    public Module(String moduleName){
        this.moduleName = moduleName;
    }

    /**
     * Constructor for a Module with ID.
     * @param moduleName The name of the module.
     * @param moduleID The ID of the module.
     */
    public Module(String moduleName, String  moduleID){
        this.moduleName = moduleName;
        this.moduleID = moduleID;
    }

            /* Getters */

    /**
     * Getter for the name of the module.
     * @return the name of the module.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Getter for the ID of the module.
     * @return the ID of the module.
     */
    public String getModuleID() {
        return moduleID;
    }

    /**
     * Getter for the HashMap storing the Student object and it's grade.
     * @return the HashMap object.
     */
    public HashMap<Student, Integer> getStudentsEnrolled() {
        return studentsEnrolled;
    }

            /* Array CRUD */

    /**
     * Adds a Student to the student records Array without a grade
     * @param student The student to be added.
     */
    public void addStudent(Student student){
        getStudentsEnrolled().put(student, null);
    }

    /**
     * Adds a Student to the student records Array with a grade
     * @param student The student to be added.
     * @param grade The grade to be added.
     */
    public void addStudent(Student student, int grade){
        getStudentsEnrolled().put(student, grade);
    }

    /**
     * Displays all the students within the Array.
     */
    public void viewEnrolledStudents(){
        for (Student student:
                getStudentsEnrolled().keySet()) {
            System.out.println(student.getStudentName()
                    + " Grade : " + getStudentsEnrolled().get(student));
        }
    }

    // insert search for specific student and remove it from the array
}
