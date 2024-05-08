package com.project;

public class Main {
    public static void main(String[] args) {
        AppData db = AppData.getInstance();

        // Create tables
        createTables();

        // Add people
        addPeople(1, "John Doe", "john@example.com", "12345678A", 30, "1994-05-15", 123456789);
        addPeople(2, "Jane Smith", "jane@example.com", "87654321B", 25, "1999-11-20", 987654321);
        addPeople(3, "Michael Johnson", "michael@example.com", "56789012C", 40, "1984-07-10", 456789012);
        addPeople(4, "Emily Brown", "emily@example.com", "34567890D", 35, "1989-03-25", 234567890);
        addPeople(5, "David Lee", "david@example.com", "90123456E", 28, "1996-09-05", 345678901);
        addPeople(6, "Sarah Garcia", "sarah@example.com", "23456789F", 33, "1991-12-30", 567890123);
        addPeople(7, "Alex Martinez", "alex@example.com", "78901234G", 31, "1993-02-18", 678901234);
        addPeople(8, "Jessica Taylor", "jessica@example.com", "45678901H", 29, "1995-08-08", 789012345);
        addPeople(9, "Daniel Lopez", "daniel@example.com", "89012345I", 36, "1988-06-12", 890123456);

        // Add customers
        addCustomer(1, 1, "base", "Action, Comedy", "2023-04-15", "2023-10-15");
        addCustomer(2, 2, "premium", "Drama, Romance", "2022-09-20", "2023-09-20");
        addCustomer(3, 3, "base", "Thriller, Horror", "2024-01-10", "2024-07-10");

        // Add employees
        addEmployee(1, 4, "2023-05-10", "Software Engineer", 60000.00, 40, 20);
        addEmployee(2, 5, "2022-09-15", "Marketing Manager", 70000.00, 45, 25);
        addEmployee(3, 6, "2024-01-20", "Human Resources Assistant", 45000.00, 35, 15);

        // Add videoclubs
        addVideoclub(1, 7, "CineMania", "info@cinemania.com", 987654321, 10.00, 23.00);
        addVideoclub(2, 8, "Flicks & Chill", "contact@flicksandchill.com", 123456789, 12.00, 22.00);
        addVideoclub(3, 9, "Silver Screens", "silver.screens@email.com", 555666777, 11.00, 21.30);

        // List people
        System.out.println("\nPeople:");
        listPeople();

        // List customers
        System.out.println("\nCustomers:");
        listCustomers();

        // List employees
        System.out.println("\nEmployees:");
        listEmployees();

        // List owners
        System.out.println("\nOwners:");
        listOwners();

        // List videoclubs
        System.out.println("\nVideoclubs:");
        listVideoclubs();

        // Close the database connection
        db.close();
    }
}
