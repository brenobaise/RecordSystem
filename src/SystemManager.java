import java.util.ArrayList;
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


    /**
     * Constructor for the system.
     * Initializes the Scanner Object for the SystemManager class
     */
    public SystemManager(){

        this.scanner = new Scanner(System.in);
    }


            /* Getters */

    /**
     * Getter method for storing the Student Objects
     *
     * @return the Arraylist of Students
     */
    private ArrayList<Student> getStudentRecords() {

        return studentRecords;
    }

    /**
     * Getter method for the array storing Module Objects
     *
     * @return the Arraylist of Modules
     */
    private ArrayList<Module> getModuleRecords() {

        return moduleRecords;
    }


            /* Array CRUD */

    /**
     * Searches for a Student Object based on a studentID
     * @param studentID The key to be searched within the StudentRecords
     * @return the Object of the searched Student.
     */
    private Student getStudent(String studentID){
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
    private void viewStudentRecords(){

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
    private Module getModule(String moduleID){
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
    private void viewModuleRecords(){

        for (Module module:
                getModuleRecords()) {
            System.out.println("Name: " + module.getModuleName() +
                    " ID: " + module.getModuleID());

        }
    }


            /* System Methods */

    /**
     * Starts the application
     */
    public void run() {
        int userSelection;
        MenuOptions selectedOption;
        boolean isValid = false;


        System.out.println("Welcome to the Student Record System!");
        System.out.println("What would you like to do ?");
        printMenu();

        do{
            userSelection = getUserInt();
            try {
                selectedOption = MenuOptions.values()[userSelection - 1];
                menu(selectedOption);
                isValid = true;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid option. Please try again.");
            }

        }while (!isValid);


    }

    /**
     * Displays to the console the Menu.
     */
    private static void printMenu() {
        String menu = """
                =========== Student Management System ===========
                1. Add a new student record
                2. Add a new module
                3. Enrol a student to a module
                4. Add mark for a student in a module
                5. Display all student records
                6. Display a specific student record
                7. Calculate the final mark of a specific student
                8. Calculate the average mark of all students
                9. Exit
                ==================================================
                """;
        System.out.println(menu);
    }

    /**
     * Validates a String from the user.
     * Restrictions : Upper to lower case  A - z, and digits from 0 to 9.
     *
     * @return the valid String.
     */
    private String getUserString(String prompt){

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
     * Requirements: The regex pattern  matches one or more digits ([1-9]).
     * Age restriction >= 0 and <= 100
     *
     * @return the validated int.
     */
    private int getUserInt() {
        int digit = -1;
        boolean isValid;

        do {
            String input = scanner.nextLine();
            isValid = input.matches("[1-9]");

            if (isValid) {
                digit = Integer.parseInt(input);
                isValid = digit >= 1 && digit <= 100;
            }else {
                System.out.println("[SYSTEM] >>> Invalid Input");

            }

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
    private static <T, R> boolean validateObject(ArrayList<T> targetList, R target, Function<T, R> getter) {
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


    private void menu(MenuOptions selectedOption) {


            switch (selectedOption) {
                case ADD_STUDENT -> {
                    // Add a new student record
                    // this option should allow the user to input a new student's information and
                    //add it to the array of student records.
                    String studentName = getUserString("Input Student Name: ");
                    String studentID = getUserString("Input Student ID: ");
                    createStudent(studentName, studentID);

                }
                case ADD_MODULE -> {
                    // add a new module
                    String moduleName = getUserString("Input Module Name: ");
                    String moduleID = getUserString("Input Module ID: ");
                    createModule(moduleName, moduleID);
                }
                case ENROLL_STUDENT -> {
                    // enrol a student to a module
                    enrollStudent();
                    System.out.println("3");
                }
                case ADD_MARK -> {
                    // add a mark
                    System.out.println("4");
                }
                case VIEW_ALL_RECORDS -> {
                    // Display all student records
                    System.out.println("5");
                    viewStudentRecords();
                    viewModuleRecords();
                }
                case VIEW_STUDENT_RECORD -> {
                    // Display a specific student record
                    System.out.println("6");
                }
                case CALCULATE_FINAL_MARK -> {
                    // Calculate the final mark of a specific student
                    System.out.println("7");
                }
                case CALCULATE_AVERAGE -> {
                    // Calculate the average of all students
                    System.out.println("Hello");
                }
                case EXIT -> {
                    System.exit(200);
                    getStudentRecords().clear();
                    getModuleRecords().clear();
                }
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
    private void createStudent(String name, String studentID){

        Student newStudent = new Student(name, studentID);
        getStudentRecords().add(newStudent);
        System.out.println("[SYSTEM] >>> Student added to records.");

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
    private void createModule(String moduleName, String moduleID) {

        Module newModule = new Module(moduleName, moduleID);
        getModuleRecords().add(newModule);
        System.out.println("[SYSTEM >>> Module added to records.]");
    }

    /**
     * Validates an ID based on the object type and prompt provided.
     * <p>
     * This method is used to validate a given ID based on the object type (Student or Module).
     * It prompts the user to enter the ID and checks if it exists in the corresponding records (student or module records).
     * If the ID is not found in the records, it prompts the user to re-enter the ID until a valid one is provided.
     * </p>
     *
     * @param objectID The object type (Student or Module) to determine which records to search.
     * @param prompt   The prompt to be displayed to the user when entering the ID (e.g., "Student ID" or "Module ID").
     * @param <T>      The generic type representing the object type (Student or Module).
     * @return The valid ID that exists in the records.
     * @see Student
     * @see Module
     */
    private <T> String validateID(T objectID, String prompt) {
        boolean isValid = true;
        String possibleID = null;

        if (objectID instanceof Student) {
            while (isValid) {
                possibleID = getUserString("Insert " + prompt);
                if (!validateObject(getStudentRecords(), possibleID, Student::getStudentID)) {
                    System.out.println("[SYSTEM] >>> Student not found.");
                    isValid = false;
                }
            }
        }

        if (objectID instanceof Module) {
            while (isValid) {
                possibleID = getUserString("Insert " + prompt);
                if (!validateObject(getModuleRecords(), possibleID, Module::getModuleID)) {
                    System.out.println("[SYSTEM] >>> Module not found.");
                    isValid = false;
                }
            }
        }

        return possibleID;
    }

    private void enrollStudent() {
        String studentID = null;
        String moduleID = null;

        // Validate inputs
        studentID = validateID(studentID, "StudentID");
        moduleID = validateID(moduleID, "ModuleID");

        // GET the object of the specified ID's
        Module module = getModule(moduleID);
        Student student = getStudent(studentID);

        // Enroll the student to the module
        module.addStudent(student);
        System.out.println("[SYSTEM] >>> Student" + student.getStudentID() + " enrolled to Module: " +
                module.getModuleName());
    }

    private void addMark() {
        String studentID = null;
        String moduleID = null;

        String markOne;
        String markTwo;

        // Validate inputs
        studentID = validateID(studentID, "StudentID");
        moduleID = validateID(moduleID, "ModuleID");

        // GET the object of the specified ID's
        Module module = getModule(moduleID);
        Student student = getStudent(studentID);

        markOne = getUserString("Insert 1st mark for the module: ");
        markTwo = getUserString("Insert 2nd mark for the module: ");

        double doubleMarkOne = Double.parseDouble(markOne);
        double doubleMarkTwo = Double.parseDouble(markTwo);
        if (UtilityMaths.isSumGreaterThanMax(doubleMarkOne, doubleMarkTwo, 200)) {
            double grade = UtilityMaths.findAverage(doubleMarkOne,doubleMarkTwo);
            module.addStudent(student, grade);
        }
    }
}


