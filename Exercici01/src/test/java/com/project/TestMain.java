package com.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.stefanbirkner.systemlambda.SystemLambda;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.File;
import java.sql.*;

public class TestMain {

    @Test
    public void testMainOutput() throws Exception {

        System.setProperty("environment", "test");

        String text = SystemLambda.tapSystemOut(() -> {
            // Executa el main per a provar la seva sortida
            String[] args = {}; // Passa els arguments necessaris si n'hi ha
            Main.main(args);
        });
        text = text.replace("\r\n", "\n");

        // Comprova que la sortida conté el text esperat
        String expectedOutput = """
            
            People List:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012
            ID: 4, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890
            ID: 5, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901
            ID: 6, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123
            ID: 7, Name: Alex Martinez, Email: alex@example.com, DNI: 78901234G, Age: 31, Birthdate: 1993-02-18, Phone Number: 678901234
            ID: 8, Name: Jessica Taylor, Email: jessica@example.com, DNI: 45678901H, Age: 29, Birthdate: 1995-08-08, Phone Number: 789012345
            ID: 9, Name: Daniel Lopez, Email: daniel@example.com, DNI: 89012345I, Age: 36, Birthdate: 1988-06-12, Phone Number: 890123456
            
            People List from DDBB:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012
            ID: 4, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890
            ID: 5, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901
            ID: 6, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123
            ID: 7, Name: Alex Martinez, Email: alex@example.com, DNI: 78901234G, Age: 31, Birthdate: 1993-02-18, Phone Number: 678901234
            ID: 8, Name: Jessica Taylor, Email: jessica@example.com, DNI: 45678901H, Age: 29, Birthdate: 1995-08-08, Phone Number: 789012345
            ID: 9, Name: Daniel Lopez, Email: daniel@example.com, DNI: 89012345I, Age: 36, Birthdate: 1988-06-12, Phone Number: 890123456
            
            Customers List:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789, Type: base, Genre Preferences: Action, Starting Date: 2023-04-15, Expiration Date: 2023-10-15
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321, Type: premium, Genre Preferences: Drama, Starting Date: 2022-09-20, Expiration Date: 2023-09-20
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012, Type: base, Genre Preferences: Thriller, Starting Date: 2024-01-10, Expiration Date: 2024-07-10
            
            Customers List from DDBB:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789, Type: base, Genre Preferences: Action, Starting Date: 2023-04-15, Expiration Date: 2023-10-15
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321, Type: premium, Genre Preferences: Drama, Starting Date: 2022-09-20, Expiration Date: 2023-09-20
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012, Type: base, Genre Preferences: Thriller, Starting Date: 2024-01-10, Expiration Date: 2024-07-10
            
            Employees List:
            ID: 1, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890, Hiring Date: 2023-05-10, Occupation: Software Engineer, Salary: 60000.0, Schedule: 40, Available Holidays: 20
            ID: 2, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901, Hiring Date: 2022-09-15, Occupation: Marketing Manager, Salary: 70000.0, Schedule: 45, Available Holidays: 25
            ID: 3, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123, Hiring Date: 2024-01-20, Occupation: Human Resources Assistant, Salary: 45000.0, Schedule: 35, Available Holidays: 15
            
            Employees List from DDBB:
            ID: 1, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890, Hiring Date: 2023-05-10, Occupation: Software Engineer, Salary: 60000.0, Schedule: 40, Available Holidays: 20
            ID: 2, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901, Hiring Date: 2022-09-15, Occupation: Marketing Manager, Salary: 70000.0, Schedule: 45, Available Holidays: 25
            ID: 3, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123, Hiring Date: 2024-01-20, Occupation: Human Resources Assistant, Salary: 45000.0, Schedule: 35, Available Holidays: 15
            
            Videoclubs List:
            ID: 1, Owner's Name: Alex Martinez, Owner's Email: alex@example.com, Owner's DNI: 78901234G, Owner's Age: 31, Owner's Birthdate: 1993-02-18, Owner's Phone Number: 678901234, Videoclub's Name: CineMania, Videoclub's Email: info@cinemania.com, Videoclub's Phone Number: 987654321, Videoclub's Opening Time: 10.0, Videoclub's Closing Time: 23.0
            ID: 2, Owner's Name: Jessica Taylor, Owner's Email: jessica@example.com, Owner's DNI: 45678901H, Owner's Age: 29, Owner's Birthdate: 1995-08-08, Owner's Phone Number: 789012345, Videoclub's Name: Flicks & Chill, Videoclub's Email: contact@flicksandchill.com, Videoclub's Phone Number: 123456789, Videoclub's Opening Time: 12.0, Videoclub's Closing Time: 22.0
            ID: 3, Owner's Name: Daniel Lopez, Owner's Email: daniel@example.com, Owner's DNI: 89012345I, Owner's Age: 36, Owner's Birthdate: 1988-06-12, Owner's Phone Number: 890123456, Videoclub's Name: Silver Screens, Videoclub's Email: silver.screens@email.com, Videoclub's Phone Number: 555666777, Videoclub's Opening Time: 11.0, Videoclub's Closing Time: 21.3
            
            Videoclubs List from DDBB:
            ID: 1, Owner's Name: Alex Martinez, Owner's Email: alex@example.com, Owner's DNI: 78901234G, Owner's Age: 31, Owner's Birthdate: 1993-02-18, Owner's Phone Number: 678901234, Videoclub's Name: CineMania, Videoclub's Email: info@cinemania.com, Videoclub's Phone Number: 987654321, Videoclub's Opening Time: 10.0, Videoclub's Closing Time: 23.0
            ID: 2, Owner's Name: Jessica Taylor, Owner's Email: jessica@example.com, Owner's DNI: 45678901H, Owner's Age: 29, Owner's Birthdate: 1995-08-08, Owner's Phone Number: 789012345, Videoclub's Name: Flicks & Chill, Videoclub's Email: contact@flicksandchill.com, Videoclub's Phone Number: 123456789, Videoclub's Opening Time: 12.0, Videoclub's Closing Time: 22.0
            ID: 3, Owner's Name: Daniel Lopez, Owner's Email: daniel@example.com, Owner's DNI: 89012345I, Owner's Age: 36, Owner's Birthdate: 1988-06-12, Owner's Phone Number: 890123456, Videoclub's Name: Silver Screens, Videoclub's Email: silver.screens@email.com, Videoclub's Phone Number: 555666777, Videoclub's Opening Time: 11.0, Videoclub's Closing Time: 21.3
            """.replace("\r\n", "\n").replace("            ","");
            String diff = TestStringUtils.findFirstDifference(text, expectedOutput);
            assertTrue(diff.compareTo("identical") == 0, 
                "\n>>>>>>>>>> >>>>>>>>>>\n" +
                diff +
                "<<<<<<<<<< <<<<<<<<<<\n");
    }

    @Test
    public void testMainTables() throws SQLException {
        System.setProperty("environment", "test");
        String url = "jdbc:mysql://localhost:3308/entertainment_hub?user=root&password=pwd";

        File dbFile = new File("dades.sqlite");
        assertTrue(dbFile.exists(), "The database file does not exist.");

        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData dbMetaData = conn.getMetaData();

            // Check for existence of people table
            checkTableExists(dbMetaData, "people", "id", "name", "email", "dni", "age", "birthdate", "phone_number");

            // Check for existence of customers table
            checkTableExists(dbMetaData, "customers", "customer_id", "person_id", "type", "genre_preferences", "starting_date", "expiration_date");

            // Check foreign keys for customers table
            checkForeignKey(dbMetaData, "customers", "people", "person_id");

            // Check for existence of employees table
            checkTableExists(dbMetaData, "employees", "employee_id", "person_id", "hiring_date", "occupation", "salary", "schedule", "available_holidays");

            // Check foreign keys for employees table
            checkForeignKey(dbMetaData, "employees", "people", "person_id");

            // Check for existence of videoclubs table
            checkTableExists(dbMetaData, "videoclubs", "id", "owner_id", "name", "email", "phone_number", "opening_time", "closing_time");

            // Check foreign keys for videoclubs table
            checkForeignKey(dbMetaData, "videoclubs", "people", "owner_id");
        }
    }

    private void checkTableExists(DatabaseMetaData metaData, String tableName, String... columnNames) throws SQLException {
        try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
            assertTrue(rs.next(), "Table " + tableName + " does not exist.");
        }

        for (String columnName : columnNames) {
            try (ResultSet rs = metaData.getColumns(null, null, tableName, columnName)) {
                assertTrue(rs.next(), "Column " + columnName + " does not exist in table " + tableName);
            }
        }
    }

    private void checkForeignKey(DatabaseMetaData metaData, String tableName, String pkTableName, String fkColumnName) throws SQLException {
        try (ResultSet rs = metaData.getImportedKeys(null, null, tableName)) {
            boolean foundFK = false;
            while (rs.next()) {
                if (pkTableName.equals(rs.getString("PKTABLE_NAME")) && fkColumnName.equals(rs.getString("FKCOLUMN_NAME"))) {
                    foundFK = true;
                    break;
                }
            }
            assertTrue(foundFK, "The table " + tableName + " does not have the correct foreign key relation with " + pkTableName);
        }
    }


    @Test
    public void testMainCalls() throws Exception {
        Class<Main> clazz = Main.class;

        // Check for methods related to creating, adding, and listing entries
        assertMethod(clazz, "createTables", true, false, "The createTables method should be defined correctly.", Connection.class);
        assertMethod(clazz, "addPerson", true, false, "The addPerson method should be defined correctly.", Connection.class, String.class, String.class, String.class, int.class, String.class, int.class);
        assertMethod(clazz, "addCustomer", true, false, "The addCustomer method should be defined correctly.", Connection.class, int.class, String.class, String.class, String.class, String.class);
        assertMethod(clazz, "addEmployee", true, false, "The addEmployee method should be defined correctly.", Connection.class, int.class, String.class, String.class, double.class, int.class, int.class);
        assertMethod(clazz, "addVideoclub", true, false, "The addVideoclub method should be defined correctly.", Connection.class, int.class, String.class, String.class, int.class, double.class, double.class);
        assertMethod(clazz, "getPersonIdByName", true, false, "The getPersonIdByName method should be defined correctly.", Connection.class, String.class);
        assertMethod(clazz, "getCustomerIdByName", true, false, "The getCustomerIdByName method should be defined correctly.", Connection.class, String.class);
        assertMethod(clazz, "getEmployeeIdByName", true, false, "The getEmployeeIdByName method should be defined correctly.", Connection.class, String.class);
        assertMethod(clazz, "getVideoclubIdByName", true, false, "The getVideoclubIdByName method should be defined correctly.", Connection.class, String.class);
        assertMethod(clazz, "listPeople", true, false, "The listPeople method should be defined correctly.", Connection.class);
        assertMethod(clazz, "listCustomers", true, false, "The listCustomers method should be defined correctly.", Connection.class);
        assertMethod(clazz, "listEmployees", true, false, "The listEmployees method should be defined correctly.", Connection.class);
        assertMethod(clazz, "listVideoclubs", true, false, "The listVideoclubs method should be defined correctly.", Connection.class);
    }

    private void assertMethod(Class<?> clazz, String methodName, boolean shouldBeStatic, boolean shouldBePrivate, String message, Class<?>... parameterTypes) throws NoSuchMethodException {
        // Utilitza getDeclaredMethod per accedir a mètodes privats
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
    
        // Comprova si el mètode és estàtic
        boolean isStatic = Modifier.isStatic(method.getModifiers());
        assertEquals(shouldBeStatic, isStatic, message + " El mètode hauria de ser " + (shouldBeStatic ? "static" : "no static") + ".");
    
        // Comprova si el mètode és privat
        boolean isPrivate = Modifier.isPrivate(method.getModifiers());
        assertEquals(shouldBePrivate, isPrivate, message + " El mètode hauria de ser " + (shouldBePrivate ? "privat" : "no privat") + ".");
    }

    @Test
    public void testMainExtra() throws Exception {
        String text = SystemLambda.tapSystemOut(() -> {
            String URL = "jdbc:mysql://localhost:3308/entertainment_hub?useSSL=false&allowPublicKeyRetrieval=true";
            String USER = "root";
            String PASSWORD = "pwd";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                conn.setAutoCommit(false);
    
                // Create tables
                Main.createTables(conn);
    
                // Add people
                Main.addPerson(conn, "John Doe", "john@example.com", "12345678A", 30, "1994-05-15", 123456789);
                Main.addPerson(conn, "Jane Smith", "jane@example.com", "87654321B", 25, "1999-11-20", 987654321);
                Main.addPerson(conn, "Michael Johnson", "michael@example.com", "56789012C", 40, "1984-07-10", 456789012);
                Main.addPerson(conn, "Emily Brown", "emily@example.com", "34567890D", 35, "1989-03-25", 234567890);
                Main.addPerson(conn, "David Lee", "david@example.com", "90123456E", 28, "1996-09-05", 345678901);
                Main.addPerson(conn, "Sarah Garcia", "sarah@example.com", "23456789F", 33, "1991-12-30", 567890123);
                Main.addPerson(conn, "Alex Martinez", "alex@example.com", "78901234G", 31, "1993-02-18", 678901234);
                Main.addPerson(conn, "Jessica Taylor", "jessica@example.com", "45678901H", 29, "1995-08-08", 789012345);
                Main.addPerson(conn, "Daniel Lopez", "daniel@example.com", "89012345I", 36, "1988-06-12", 890123456);
    
                // Add customers
                Main.addCustomer(conn, 1, "base", "Action", "2023-04-15", "2023-10-15");
                Main.addCustomer(conn, 2, "premium", "Drama", "2022-09-20", "2023-09-20");
                Main.addCustomer(conn, 3, "base", "Thriller", "2024-01-10", "2024-07-10");
    
                // Add employees
                Main.addEmployee(conn, 4, "2023-05-10", "Software Engineer", 60000.00, 40, 20);
                Main.addEmployee(conn, 5, "2022-09-15", "Marketing Manager", 70000.00, 45, 25);
                Main.addEmployee(conn, 6, "2024-01-20", "Human Resources Assistant", 45000.00, 35, 15);
    
                // Add videoclubs
                Main.addVideoclub(conn, 7, "CineMania", "info@cinemania.com", 987654321, 10.00, 23.00);
                Main.addVideoclub(conn, 8, "Flicks & Chill", "contact@flicksandchill.com", 123456789, 12.00, 22.00);
                Main.addVideoclub(conn, 9, "Silver Screens", "silver.screens@email.com", 555666777, 11.00, 21.30);
                
                // List people
                Main.listPeople(conn);
    
                // List customers
                Main.listCustomers(conn);
    
                // List employees
                Main.listEmployees(conn);
    
                // List videoclubs
                Main.listVideoclubs(conn);
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        text = text.replace("\r\n", "\n");

        String expectedOutput = """
            
            People List:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012
            ID: 4, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890
            ID: 5, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901
            ID: 6, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123
            ID: 7, Name: Alex Martinez, Email: alex@example.com, DNI: 78901234G, Age: 31, Birthdate: 1993-02-18, Phone Number: 678901234
            ID: 8, Name: Jessica Taylor, Email: jessica@example.com, DNI: 45678901H, Age: 29, Birthdate: 1995-08-08, Phone Number: 789012345
            ID: 9, Name: Daniel Lopez, Email: daniel@example.com, DNI: 89012345I, Age: 36, Birthdate: 1988-06-12, Phone Number: 890123456
            
            People List from DDBB:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012
            ID: 4, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890
            ID: 5, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901
            ID: 6, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123
            ID: 7, Name: Alex Martinez, Email: alex@example.com, DNI: 78901234G, Age: 31, Birthdate: 1993-02-18, Phone Number: 678901234
            ID: 8, Name: Jessica Taylor, Email: jessica@example.com, DNI: 45678901H, Age: 29, Birthdate: 1995-08-08, Phone Number: 789012345
            ID: 9, Name: Daniel Lopez, Email: daniel@example.com, DNI: 89012345I, Age: 36, Birthdate: 1988-06-12, Phone Number: 890123456
            
            Customers List:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789, Type: base, Genre Preferences: Action, Starting Date: 2023-04-15, Expiration Date: 2023-10-15
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321, Type: premium, Genre Preferences: Drama, Starting Date: 2022-09-20, Expiration Date: 2023-09-20
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012, Type: base, Genre Preferences: Thriller, Starting Date: 2024-01-10, Expiration Date: 2024-07-10
            
            Customers List from DDBB:
            ID: 1, Name: John Doe, Email: john@example.com, DNI: 12345678A, Age: 30, Birthdate: 1994-05-15, Phone Number: 123456789, Type: base, Genre Preferences: Action, Starting Date: 2023-04-15, Expiration Date: 2023-10-15
            ID: 2, Name: Jane Smith, Email: jane@example.com, DNI: 87654321B, Age: 25, Birthdate: 1999-11-20, Phone Number: 987654321, Type: premium, Genre Preferences: Drama, Starting Date: 2022-09-20, Expiration Date: 2023-09-20
            ID: 3, Name: Michael Johnson, Email: michael@example.com, DNI: 56789012C, Age: 40, Birthdate: 1984-07-10, Phone Number: 456789012, Type: base, Genre Preferences: Thriller, Starting Date: 2024-01-10, Expiration Date: 2024-07-10
            
            Employees List:
            ID: 1, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890, Hiring Date: 2023-05-10, Occupation: Software Engineer, Salary: 60000.0, Schedule: 40, Available Holidays: 20
            ID: 2, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901, Hiring Date: 2022-09-15, Occupation: Marketing Manager, Salary: 70000.0, Schedule: 45, Available Holidays: 25
            ID: 3, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123, Hiring Date: 2024-01-20, Occupation: Human Resources Assistant, Salary: 45000.0, Schedule: 35, Available Holidays: 15
            
            Employees List from DDBB:
            ID: 1, Name: Emily Brown, Email: emily@example.com, DNI: 34567890D, Age: 35, Birthdate: 1989-03-25, Phone Number: 234567890, Hiring Date: 2023-05-10, Occupation: Software Engineer, Salary: 60000.0, Schedule: 40, Available Holidays: 20
            ID: 2, Name: David Lee, Email: david@example.com, DNI: 90123456E, Age: 28, Birthdate: 1996-09-05, Phone Number: 345678901, Hiring Date: 2022-09-15, Occupation: Marketing Manager, Salary: 70000.0, Schedule: 45, Available Holidays: 25
            ID: 3, Name: Sarah Garcia, Email: sarah@example.com, DNI: 23456789F, Age: 33, Birthdate: 1991-12-30, Phone Number: 567890123, Hiring Date: 2024-01-20, Occupation: Human Resources Assistant, Salary: 45000.0, Schedule: 35, Available Holidays: 15
            
            Videoclubs List:
            ID: 1, Owner's Name: Alex Martinez, Owner's Email: alex@example.com, Owner's DNI: 78901234G, Owner's Age: 31, Owner's Birthdate: 1993-02-18, Owner's Phone Number: 678901234, Videoclub's Name: CineMania, Videoclub's Email: info@cinemania.com, Videoclub's Phone Number: 987654321, Videoclub's Opening Time: 10.0, Videoclub's Closing Time: 23.0
            ID: 2, Owner's Name: Jessica Taylor, Owner's Email: jessica@example.com, Owner's DNI: 45678901H, Owner's Age: 29, Owner's Birthdate: 1995-08-08, Owner's Phone Number: 789012345, Videoclub's Name: Flicks & Chill, Videoclub's Email: contact@flicksandchill.com, Videoclub's Phone Number: 123456789, Videoclub's Opening Time: 12.0, Videoclub's Closing Time: 22.0
            ID: 3, Owner's Name: Daniel Lopez, Owner's Email: daniel@example.com, Owner's DNI: 89012345I, Owner's Age: 36, Owner's Birthdate: 1988-06-12, Owner's Phone Number: 890123456, Videoclub's Name: Silver Screens, Videoclub's Email: silver.screens@email.com, Videoclub's Phone Number: 555666777, Videoclub's Opening Time: 11.0, Videoclub's Closing Time: 21.3
            
            Videoclubs List from DDBB:
            ID: 1, Owner's Name: Alex Martinez, Owner's Email: alex@example.com, Owner's DNI: 78901234G, Owner's Age: 31, Owner's Birthdate: 1993-02-18, Owner's Phone Number: 678901234, Videoclub's Name: CineMania, Videoclub's Email: info@cinemania.com, Videoclub's Phone Number: 987654321, Videoclub's Opening Time: 10.0, Videoclub's Closing Time: 23.0
            ID: 2, Owner's Name: Jessica Taylor, Owner's Email: jessica@example.com, Owner's DNI: 45678901H, Owner's Age: 29, Owner's Birthdate: 1995-08-08, Owner's Phone Number: 789012345, Videoclub's Name: Flicks & Chill, Videoclub's Email: contact@flicksandchill.com, Videoclub's Phone Number: 123456789, Videoclub's Opening Time: 12.0, Videoclub's Closing Time: 22.0
            ID: 3, Owner's Name: Daniel Lopez, Owner's Email: daniel@example.com, Owner's DNI: 89012345I, Owner's Age: 36, Owner's Birthdate: 1988-06-12, Owner's Phone Number: 890123456, Videoclub's Name: Silver Screens, Videoclub's Email: silver.screens@email.com, Videoclub's Phone Number: 555666777, Videoclub's Opening Time: 11.0, Videoclub's Closing Time: 21.3
            """.replace("\r\n", "\n").replace("            ","");

        String diff = TestStringUtils.findFirstDifference(text, expectedOutput);
            assertTrue(diff.compareTo("identical") == 0, 
                ">>>>>>>>>> >>>>>>>>>>\n" +
                diff +
                "<<<<<<<<<< <<<<<<<<<<\n");
    }
}
