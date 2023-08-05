import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

/**
 * <p>The SystemManager class represents a student record management system
 * that allows adding, viewing, and manipulating student and module records.
 * It provides methods for validating user input and performing CRUD operations
 * on the student and module records.</p>
 *
 *<br>
 * <p>Student and module records are stored in separate ArrayLists, and students
 * are represented by objects of the Student class, while Modules are represented
 * by objects of the Module class. The SystemManager class offers initializer
 * methods createStudent() and createModule() to add new student and module records
 * respectively to their corresponding ArrayLists.</p>
 *
 * @see Student
 * @see Module
 */
public class SystemManager {
    private final Scanner scanner;

    // List of student objects
    private ArrayList<Student> studentRecords= new ArrayList<>();

    // List of module objects
    private ArrayList<Module> moduleRecords = new ArrayList<>();

    // Getters
    /**
     * Constructor for the system.
     * Initializes the Scanner Object for the SystemManager class
     */
    public SystemManager(){

        this.scanner = new Scanner(System.in);
    }

    /**
     * Getter method for storing the Student Objects
     *
     * @return the Arraylist of Students
     */
    public ArrayList<Student> getStudentRecords() {

        return studentRecords;
    }

    /**
     * Getter method for the array storing Module Objects
     *
     * @return the Arraylist of Modules
     */
    public ArrayList<Module> getModuleRecords() {

        return moduleRecords;
    }

    // Array CRUD

    /**
     * Searches for a Student Object based on a studentID
     * @param studentID The key to be searched within the StudentRecords
     * @return the Object of the searched Student.
     */
    public Student getStudent(String studentID){
        Student target = null;

        for (Student student:
             getStudentRecords()) {
            if(student.getStudentID().equals(studentID)){
                target = student;
            }
        }
        return target;
    }

    /**
     * Displays all students within the system.
     */
    public void viewStudentRecords(){

        for (Student student:
             getStudentRecords()) {
            System.out.println("Name: " + student.getStudentName() +
                               " ID: " + student.getStudentID());

        }
    }

    /**
     * Searches for a module based on a moduleID
     * @param moduleID The key to be searched within the ModuleRecords
     * @return the Object of the searched Module.
     */
    public Module getModule(String moduleID){
        Module target = null;
        for (Module module:
             getModuleRecords()) {
            if(module.getModuleID().equals(moduleID)){
                target = module;
            }
        }
        return target;
    }

    /**
     * Displays all Modules in the system.
     */
    public void viewModuleRecords(){

        for (Module module:
                getModuleRecords()) {
            System.out.println("Name: " + module.getModuleName() +
                    " ID: " + module.getModuleID());

        }
    }

    // System Methods
    /**
     * Validates a String from the user.
     * Restrictions : Upper to lower case  A - z, and digits from 0 to 9.
     *
     * @return the valid String.
     */
    public String getUserString(String prompt){

        String input;
        boolean isValidInput = false;
        do {
            System.out.println(prompt);
            System.out.print("> ");
            input = scanner.nextLine();
            if(input.matches("^[a-zA-Z0-9]+$")){
                isValidInput = true;
            }else {
                System.out.println("[SYSTEM] Invalid Input.");
            }
        }while (!isValidInput);


        return input;
    }

    /**
     * Validates an int from the user.
     * Requirements: The regex pattern "\\d+" matches one or more digits (0-9).
     * Age restriction >= 0 and <= 100
     *
     * @return the validated int.
     */
    public int getUserInt() {
        int digit = -1;
        boolean isValid;

        do {
            String input = scanner.nextLine();
            isValid = input.matches("\\d+");

            if (isValid) {
                digit = Integer.parseInt(input);
                isValid = digit >= 0 && digit <= 100;
            }
            System.out.println("[SYSTEM] >>> Invalid Input");

        } while (!isValid);

        return digit;
    }


    /**
     * A generic method to search for an Object attribute in a list.
     * <p>
         * A for loop iterates over the targetList, using a getter method to store the value of the attribute to be compared.
         * Then an if statement compares that attribute with the parsed target.
     * </p>
     * @param targetList The list to be searched.
     * @param target The attribute to be searched.
     * @param getter The getter method of that Object
     * @return True/False based on the validation
     * @param <T> Generic Type of the ArrayList<>   Ex. Module/Student
     * @param <R> Generic Type of the target.       Ex. Integer/String
     * @see Function
     */
    public static <T, R> boolean validateObject(ArrayList<T> targetList, R target, Function<T, R> getter) {
        for (T item : targetList) {
            // Get the value of the property for the current item
            R itemValue = getter.apply(item);
            // getter.apply() simply uses the getter method of that object.

            // Compare the property values of the current item and the target object
            // If they are equal, return true (found)
            if (itemValue.equals(target)) {
                return true;
            }
        }
        // If the loop completes without finding a match, return false (not found)
        return false;
    }


    public void menu(int key){
        switch (key) {
            case 0 -> System.exit(200);
            case 1 -> {
                // Add a new student record
                // this option should allow the user to input a new student's information and
                //add it to the array of student records.
                String studentName = getUserString("Input Student Name: ");
                String studentID = getUserString("Input Student ID: ");
                createStudent(studentName, studentID);

            }
            case 2 -> {
                // add a new module
                String moduleName = getUserString("Input Module Name: ");
                String moduleID = getUserString("Input Module ID: ");
                createModule(moduleName, moduleID);
            }
            case 3 ->
                // enrol a student to a module

                    System.out.println("3");
            case 4 ->
                // add a mark
                    System.out.println("4");
            case 5 -> {
                // Display all student records
                System.out.println("5");
                viewStudentRecords();
                viewModuleRecords();
            }
            case 6 ->
                // Display a specific student record
                    System.out.println("6");
            case 7 ->
                // Calculate the final mark of a specific student
                    System.out.println("7");
            case 8 ->
                // Calculate the average of all students
                    System.out.println("8");
        }
    }

    /**
     * Initializer method for creating a Student Object.
     *
     * <p>This method creates a generic Student Object, and
     * assigns the params to the Object.</p>
     * Once the Object has been created, it's added to the studentRecords Array.
     *
     * @param name The name for the Student Object.
     * @param studentID The unique identifier for the Student Object.
     */
    public void createStudent(String name, String studentID){

        Student newStudent = new Student(name, studentID);
        getStudentRecords().add(newStudent);
        System.out.println("[SYSTEM] >>> Student added to records.");

//        System.out.println(validateObject(getStudentRecords(), "w123", Student::getStudentID));

    }

    /**
     * Initializer method for creating a Module Object.
     *
     * <p>This method creates a generic Module Object, and
     * assigns the params to the Object.</p>
     * Once the Object has been created, it's added to the moduleRecords Array.
     *
     * @param moduleName The name for the Module Object.
     * @param moduleID The unique identifier for the Module Object.
     */
    public void createModule(String moduleName, String moduleID) {

        Module newModule = new Module(moduleName, moduleID);
        getModuleRecords().add(newModule);
        System.out.println("[SYSTEM >>> Module added to records.]");


    }

    public void enrolStudent(){

        String studentID = getUserString("Insert StudentID: ");
        if(!validateObject(getStudentRecords(), studentID, Student::getStudentID)){
            System.out.println("[SYSTEM] >>> Student does not exist.");
        }

        String moduleID = getUserString("Insert ModuleID: ");
        if(!validateObject(getModuleRecords(), moduleID, Module::getModuleID)){
            System.out.println("[SYSTEM] >>> Module does not exist.");
        }


        // GET the object of the specified ID's
        Module module = getModule(moduleID);
        Student student = getStudent(studentID);

        // Enroll the student to the module
        module.enrollStudent(student);
        System.out.println("[SYSTEM] >>> Student" + student.getStudentID() + " enrolled to Module: " +
                module.getModuleName());

    }
}
