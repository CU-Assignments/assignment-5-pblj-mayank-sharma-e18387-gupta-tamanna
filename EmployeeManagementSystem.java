import java.io.*;
import java.util.*;

// Step 1: Define Employee class
class Employee implements Serializable {
    private int empId;
    private String name;
    private String designation;
    private double salary;

    public Employee(int empId, String name, String designation, double salary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Employee ID: " + empId + ", Name: " + name +
                           ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem {

    static final String FILE_NAME = "employees.ser";

    // Method to add an employee
    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, designation, salary);

            List<Employee> employeeList = readEmployees();
            employeeList.add(emp);
            writeEmployees(employeeList);

            System.out.println("Employee added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    // Method to display all employees
    public static void displayAllEmployees() {
        List<Employee> employeeList = readEmployees();
        if (employeeList.isEmpty()) {
            System.out.println("No employee records found.");
        } else {
            for (Employee emp : employeeList) {
                emp.displayInfo();
            }
        }
    }

    // Read employee list from file
    private static List<Employee> readEmployees() {
        List<Employee> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            list = (ArrayList<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // First run, file may not exist
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees: " + e.getMessage());
        }
        return list;
    }

    // Write employee list to file
    private static void writeEmployees(List<Employee> employeeList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employeeList);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);
    }
}
