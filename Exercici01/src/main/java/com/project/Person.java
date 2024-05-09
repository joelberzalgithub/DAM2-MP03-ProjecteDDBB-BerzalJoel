package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Person extends SaveObjects {
    private int id, age, phoneNumber;
    private String name, email, dni, birthdate;

    public Person(int id, String name, String email, String dni, int age, String birthdate, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dni = dni;
        this.age = age;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void save(Connection conn) throws SQLException {
        if (this.id == 0) {
            // New person
            String query = "INSERT INTO people (name, email, dni, age, birthdate, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, dni);
                pstmt.setInt(4, age);
                pstmt.setString(5, birthdate);
                pstmt.setInt(6, phoneNumber);
                pstmt.executeUpdate();
            }
        } else {
            // Update existing person
            String query = "UPDATE people SET name = ?, email = ?, dni = ?, age = ?, birthdate = ?, phone_number = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, dni);
                pstmt.setInt(4, age);
                pstmt.setString(5, birthdate);
                pstmt.setInt(6, phoneNumber);
                pstmt.setInt(7, id);
                pstmt.executeUpdate();
            }
        }

        // If it is new, obtain the generated ID
        if (this.id == 0) {
            this.id = Main.getPersonIdByName(conn, this.name);
        }
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                ", Name: " + name +
                ", Email: " + email +
                ", DNI: " + dni +
                ", Age: " + age +
                ", Birthdate: " + birthdate +
                ", Phone Number: " + phoneNumber;
    }
}
