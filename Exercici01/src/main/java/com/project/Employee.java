package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends SaveObjects {
    private int employeeId, personId, schedule, availableHolidays;
    private String hiringDate, occupation;
    private double salary;
    private Person person;

    public Employee(int employeeId, int personId, String hiringDate, String occupation, double salary, int schedule, int availableHolidays) {
        this.employeeId = employeeId;
        this.personId = personId;
        this.hiringDate = hiringDate;
        this.occupation = occupation;
        this.salary = salary;
        this.schedule = schedule;
        this.availableHolidays = availableHolidays;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public int getAvailbleHolidays() {
        return availableHolidays;
    }

    public void setAvailableHolidays(int availableHolidays) {
        this.availableHolidays = availableHolidays;
    }

    @Override
    protected void save(Connection conn) throws SQLException {
        if (this.employeeId == 0) {
            // New person
            String query = "INSERT INTO employees (person_id, hiring_date, occupation, salary, schedule, available_holidays) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, personId);
                pstmt.setString(2, hiringDate);
                pstmt.setString(3, occupation);
                pstmt.setDouble(4, salary);
                pstmt.setInt(5, schedule);
                pstmt.setInt(6, availableHolidays);
                pstmt.executeUpdate();
            }
        } else {
            // Update existing person
            String query =  "UPDATE employees " +
                            "SET person_id = ?, hiring_date = ?, occupation = ?, salary = ?, schedule = ?, available_holidays = ? " +
                            "WHERE employee_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, personId);
                pstmt.setString(2, hiringDate);
                pstmt.setString(3, occupation);
                pstmt.setDouble(4, salary);
                pstmt.setInt(5, schedule);
                pstmt.setInt(6, availableHolidays);
                pstmt.setInt(7, employeeId);
                pstmt.executeUpdate();
            }
        }

        // If it is new, obtain the generated ID
        if (this.employeeId == 0) {
            String name = "";
            String email = "";
            String dni = "";
            int age = 0;
            String birthdate = "";
            int phoneNumber = 0;

            String query =  "SELECT * FROM people WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, personId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        name = rs.getString("name");
                        email = rs.getString("email");
                        dni = rs.getString("dni");
                        age = rs.getInt("age");
                        birthdate = rs.getString("birthdate");
                        phoneNumber = rs.getInt("phone_number");
                    }
                }
            }
            this.person = new Person(personId, name, email, dni, age, birthdate, phoneNumber);
            this.employeeId = Main.getEmployeeIdByName(conn, name);
        }
    }

    @Override
    public String toString() {
        return  "ID: " + employeeId +
                ", Name: " + person.getName() +
                ", Email: " + person.getEmail() +
                ", DNI: " + person.getDni() +
                ", Age: " + person.getAge() +
                ", Birthdate: " + person.getBirthdate() +
                ", Phone Number: " + person.getPhoneNumber() +
                ", Hiring Date: " + hiringDate +
                ", Occupation: " + occupation +
                ", Salary: " + salary +
                ", Schedule: " + schedule +
                ", Available Holidays: " + availableHolidays;
    }
}
