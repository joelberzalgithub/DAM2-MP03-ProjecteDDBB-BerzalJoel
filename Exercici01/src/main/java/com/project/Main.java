package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String URL = "jdbc:mysql://localhost:3308/entertainment_hub?useSSL=false&allowPublicKeyRetrieval=true";
    private static String USER = "root";
    private static String PASSWORD = "pwd";

    private static List<Person> people = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Videoclub> videoclubs = new ArrayList<>();

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // Create tables
            createTables(conn);

            // Add people
            addPerson(conn, "John Doe", "john@example.com", "12345678A", 30, "1994-05-15", 123456789);
            addPerson(conn, "Jane Smith", "jane@example.com", "87654321B", 25, "1999-11-20", 987654321);
            addPerson(conn, "Michael Johnson", "michael@example.com", "56789012C", 40, "1984-07-10", 456789012);
            addPerson(conn, "Emily Brown", "emily@example.com", "34567890D", 35, "1989-03-25", 234567890);
            addPerson(conn, "David Lee", "david@example.com", "90123456E", 28, "1996-09-05", 345678901);
            addPerson(conn, "Sarah Garcia", "sarah@example.com", "23456789F", 33, "1991-12-30", 567890123);
            addPerson(conn, "Alex Martinez", "alex@example.com", "78901234G", 31, "1993-02-18", 678901234);
            addPerson(conn, "Jessica Taylor", "jessica@example.com", "45678901H", 29, "1995-08-08", 789012345);
            addPerson(conn, "Daniel Lopez", "daniel@example.com", "89012345I", 36, "1988-06-12", 890123456);

            // Add customers
            addCustomer(conn, 1, "base", "Action", "2023-04-15", "2023-10-15");
            addCustomer(conn, 2, "premium", "Drama", "2022-09-20", "2023-09-20");
            addCustomer(conn, 3, "base", "Thriller", "2024-01-10", "2024-07-10");

            // Add employees
            addEmployee(conn, 4, "2023-05-10", "Software Engineer", 60000.00, 40, 20);
            addEmployee(conn, 5, "2022-09-15", "Marketing Manager", 70000.00, 45, 25);
            addEmployee(conn, 6, "2024-01-20", "Human Resources Assistant", 45000.00, 35, 15);

            // Add videoclubs
            addVideoclub(conn, 7, "CineMania", "info@cinemania.com", 987654321, 10.00, 23.00);
            addVideoclub(conn, 8, "Flicks & Chill", "contact@flicksandchill.com", 123456789, 12.00, 22.00);
            addVideoclub(conn, 9, "Silver Screens", "silver.screens@email.com", 555666777, 11.00, 21.30);
            
            // List people
            listPeople(conn);

            // List customers
            listCustomers(conn);

            // List employees
            listEmployees(conn);

            // List videoclubs
            listVideoclubs(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables(Connection conn) throws SQLException{
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("SET FOREIGN_KEY_CHECKS=0;");

            // Create the 'people' table
            stmt.execute("DROP TABLE IF EXISTS people");
            stmt.execute("CREATE TABLE IF NOT EXISTS people (" +
                                        "id INTEGER AUTO_INCREMENT, " +
                                        "name TEXT, " +
                                        "email TEXT, " +
                                        "dni TEXT, " +
                                        "age INTEGER, " +
                                        "birthdate DATE, " +
                                        "phone_number INTEGER, " +
                                        "PRIMARY KEY (id))");
            
            // Create the 'customers' table
            stmt.execute("DROP TABLE IF EXISTS customers");
            stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                                        "customer_id INTEGER AUTO_INCREMENT, " +
                                        "person_id INTEGER, " +
                                        "type TEXT, " +
                                        "genre_preferences TEXT, " +
                                        "starting_date DATE, " +
                                        "expiration_date DATE, " +
                                        "PRIMARY KEY (customer_id), " +
                                        "FOREIGN KEY (person_id) REFERENCES people (id))");
                                        
            // Create the 'employees' table
            stmt.execute("DROP TABLE IF EXISTS employees");
            stmt.execute("CREATE TABLE IF NOT EXISTS employees (" +
                                        "employee_id INTEGER AUTO_INCREMENT, " +
                                        "person_id INTEGER, " +
                                        "hiring_date DATE, " +
                                        "occupation TEXT, " +
                                        "salary REAL, " +
                                        "schedule INTEGER, " +
                                        "available_holidays INTEGER, " +
                                        "PRIMARY KEY (employee_id), " +
                                        "FOREIGN KEY (person_id) REFERENCES people (id))");
                                        
            // Create the 'videoclubs' table
            stmt.execute("DROP TABLE IF EXISTS videoclubs");
            stmt.execute("CREATE TABLE IF NOT EXISTS videoclubs (" +
                                        "id INTEGER AUTO_INCREMENT, " +
                                        "owner_id INTEGER, " +
                                        "name TEXT, " +
                                        "email TEXT, " +
                                        "phone_number INTEGER, " +
                                        "opening_time REAL, " +
                                        "closing_time REAL, " +
                                        "PRIMARY KEY (id), " +
                                        "FOREIGN KEY (owner_id) REFERENCES people (id))");

        }
    }

    public static void addPerson(Connection conn, String name, String email, String dni, int age, String birthdate, int phoneNumber) throws SQLException {
        // Create a new 'people' object
        Person person = new Person(0, name, email, dni, age, birthdate, phoneNumber);

        // Save the information to the DDBB
        person.save(conn);
        
        // Add the new object to the local people list
        int id = getPersonIdByName(conn, name);
        if (id != -1) {
            person.setId(id); // Update the ID with the value obtained from the DDBB
            people.add(person);
        }
    }

    public static void addCustomer(Connection conn, int personId, String type, String genrePreferences, String startingDate, String expirationDate) throws SQLException {
        // Create a new 'customer' object
        Customer customer = new Customer(0, personId, type, genrePreferences, startingDate, expirationDate);

        // Save the information to the DDBB
        customer.save(conn);
        
        // Add the new object to the local customers list
        String query =  "SELECT name FROM people WHERE id = ?";
        String name = "";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, personId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        }
        int customerId = getCustomerIdByName(conn, name);
        if (customerId != -1) {
            customer.setCustomerId(customerId); // Update the ID with the value obtained from the DDBB
            customers.add(customer);
        }
    }

    public static void addEmployee(Connection conn, int personId, String hiringDate, String occupation, double salary, int schedule, int availableHolidays) throws SQLException {
        // Create a new 'employee' object
        Employee employee = new Employee(0, personId, hiringDate, occupation, salary, schedule, availableHolidays);

        // Save the information to the DDBB
        employee.save(conn);
        
        // Add the new object to the local customers list
        String query =  "SELECT name FROM people WHERE id = ?";
        String name = "";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, personId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        }
        int employeeId = getEmployeeIdByName(conn, name);
        if (employeeId != -1) {
            employee.setEmployeeId(employeeId); // Update the ID with the value obtained from the DDBB
            employees.add(employee);
        }
    }

    public static void addVideoclub(Connection conn, int ownerId, String name, String email, int phoneNumber, double openingTime, double closingTime) throws SQLException {
        // Create a new 'videoclub' object
        Videoclub videoclub = new Videoclub(0, ownerId, name, email, phoneNumber, openingTime, closingTime);

        // Save the information to the DDBB
        videoclub.save(conn);
        
        // Add the new object to the local customers list
        int id = getVideoclubIdByName(conn, name);
        if (id != -1) {
            videoclub.setId(id); // Update the ID with the value obtained from the DDBB
            videoclubs.add(videoclub);
        }
    }

    public static int getPersonIdByName(Connection conn, String name) throws SQLException {
        String query = "SELECT id FROM people WHERE name = ?";
        int id = -1;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        }
        return id;
    }

    public static int getCustomerIdByName(Connection conn, String name) throws SQLException {
        String query =  "SELECT c.customer_id " +
                        "FROM customers c " +
                        "JOIN people p ON c.person_id = p.id " +
                        "WHERE p.name = ?";
        int id = -1;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("customer_id");
                }
            }
        }
        return id;
    }

    public static int getEmployeeIdByName(Connection conn, String name) throws SQLException {
        String query =  "SELECT e.employee_id " +
                        "FROM employees e " +
                        "JOIN people p ON e.person_id = p.id " +
                        "WHERE p.name = ?";
        int id = -1;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("employee_id");
                }
            }
        }
        return id;
    }

    public static int getVideoclubIdByName(Connection conn, String name) throws SQLException {
        String query = "SELECT id FROM videoclubs WHERE name = ?";
        int id = -1;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        }
        return id;
    }

    public static void listPeople(Connection conn) throws SQLException {
        System.out.println("\nPeople List:");
        for (Person person : people) {
            System.out.println(person.toString());
        }

        System.out.println("\nPeople List from DDBB:");
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM people")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println( "ID: " + rs.getInt("id")  +
                                        ", Name: " + rs.getString("name") +
                                        ", Email: " +rs.getString("email") +
                                        ", DNI: " +  rs.getString("dni") +
                                        ", Age: " + rs.getInt("age") +
                                        ", Birthdate: " + rs.getString("birthdate") +
                                        ", Phone Number: " + rs.getInt("phone_number"));
                }
            }
        }
    }

    public static void listCustomers(Connection conn) throws SQLException {
        System.out.println("\nCustomers List:");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        System.out.println("\nCustomers List from DDBB:");
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT c.customer_id, p.name, p.email, p.dni, p.age, p.birthdate, p.phone_number, " +
                                                                    "c.type, c.genre_preferences, c.starting_date, c.expiration_date " +
                                                             "FROM customers c " +
                                                             "JOIN people p ON c.person_id = p.id")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println( "ID: " + rs.getInt("customer_id") +
                                        ", Name: " + rs.getString("name") +
                                        ", Email: " +rs.getString("email") +
                                        ", DNI: " +  rs.getString("dni") +
                                        ", Age: " + rs.getInt("age") +
                                        ", Birthdate: " + rs.getString("birthdate") +
                                        ", Phone Number: " + rs.getInt("phone_number") +
                                        ", Type: " + rs.getString("type") +
                                        ", Genre Preferences: " + rs.getString("genre_preferences") +
                                        ", Starting Date: " + rs.getString("starting_date") +
                                        ", Expiration Date: " + rs.getString("expiration_date"));
                }
            }
        }
    }

    public static void listEmployees(Connection conn) throws SQLException {
        System.out.println("\nEmployees List:");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("\nEmployees List from DDBB:");
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT e.employee_id, p.name, p.email, p.dni, p.age, p.birthdate, p.phone_number, " +
                                                                    "e.hiring_date, e.occupation, e.salary, e.schedule, e.available_holidays " +
                                                             "FROM employees e " +
                                                             "JOIN people p ON e.person_id = p.id")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println( "ID: " + rs.getInt("employee_id") +
                                        ", Name: " + rs.getString("name") +
                                        ", Email: " +rs.getString("email") +
                                        ", DNI: " +  rs.getString("dni") +
                                        ", Age: " + rs.getInt("age") +
                                        ", Birthdate: " + rs.getString("birthdate") +
                                        ", Phone Number: " + rs.getInt("phone_number") +
                                        ", Hiring Date: " + rs.getString("hiring_date") +
                                        ", Occupation: " + rs.getString("occupation") +
                                        ", Salary: " + rs.getDouble("salary") +
                                        ", Schedule: " + rs.getInt("schedule") +
                                        ", Available Holidays: " + rs.getInt("available_holidays"));
                }
            }
        }
    }

    public static void listVideoclubs(Connection conn) throws SQLException {
        System.out.println("\nVideoclubs List:");
        for (Videoclub videoclub : videoclubs) {
            System.out.println(videoclub.toString());
        }

        System.out.println("\nVideoclubs List from DDBB:");
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT v.id, " +
                                                                    "p.name AS owner_name, " +
                                                                    "p.email AS owner_email, " +
                                                                    "p.dni AS owner_dni, " +
                                                                    "p.age AS owner_age, " +
                                                                    "p.birthdate AS owner_birthdate, " +
                                                                    "p.phone_number AS owner_phone_number, " +
                                                                    "v.name, " +
                                                                    "v.email, " +
                                                                    "v.phone_number, " +
                                                                    "v.opening_time, " +
                                                                    "v.closing_time " +
                                                             "FROM videoclubs v " +
                                                             "JOIN people p ON v.owner_id = p.id")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println( "ID: " + rs.getInt("id") +
                                        ", Owner's Name: " + rs.getString("owner_name") +
                                        ", Owner's Email: " +rs.getString("owner_email") +
                                        ", Owner's DNI: " +  rs.getString("owner_dni") +
                                        ", Owner's Age: " + rs.getInt("owner_age") +
                                        ", Owner's Birthdate: " + rs.getString("owner_birthdate") +
                                        ", Owner's Phone Number: " + rs.getInt("owner_phone_number") +
                                        ", Videoclub's Name: " + rs.getString("name") +
                                        ", Videoclub's Email: " + rs.getString("email") +
                                        ", Videoclub's Phone Number: " + rs.getInt("phone_number") +
                                        ", Videoclub's Opening Time: " + rs.getDouble("opening_time") +
                                        ", Videoclub's Closing Time: " + rs.getDouble("closing_time"));
                }
            }
        }
    }
}
