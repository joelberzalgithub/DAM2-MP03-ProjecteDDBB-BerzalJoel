package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends SaveObjects {
    private int customerId, personId;
    private String type, genrePreferences, startingDate, expirationDate;
    private Person person;

    public Customer(int customerId, int personId, String type, String genrePreferences, String startingDate, String expirationDate) {
        this.customerId = customerId;
        this.personId = personId;
        this.type = type;
        this.genrePreferences = genrePreferences;
        this.startingDate = startingDate;
        this.expirationDate = expirationDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenrePreferences() {
        return genrePreferences;
    }

    public void setGenrePreferences(String genrePreferences) {
        this.genrePreferences = genrePreferences;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    protected void save(Connection conn) throws SQLException {
        if (this.customerId == 0) {
            // New person
            String query = "INSERT INTO customers (person_id, type, genre_preferences, starting_date, expiration_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, personId);
                pstmt.setString(2, type);
                pstmt.setString(3, genrePreferences);
                pstmt.setString(4, startingDate);
                pstmt.setString(5, expirationDate);
                pstmt.executeUpdate();
            }
        } else {
            // Update existing person
            String query =  "UPDATE customers " +
                            "SET person_id = ?, type = ?, genre_preferences = ?, starting_date = ?, expiration_date = ? " +
                            "WHERE customer_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, personId);
                pstmt.setString(2, type);
                pstmt.setString(3, genrePreferences);
                pstmt.setString(4, startingDate);
                pstmt.setString(5, expirationDate);
                pstmt.setInt(6, customerId);
                pstmt.executeUpdate();
            }
        }

        // If it is new, obtain the generated ID
        if (this.customerId == 0) {
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
            this.customerId = Main.getCustomerIdByName(conn, name);
        }
    }
    
    @Override
    public String toString() {
        return  "ID: " + customerId +
                ", Name: " + person.getName() +
                ", Email: " + person.getEmail() +
                ", DNI: " + person.getDni() +
                ", Age: " + person.getAge() +
                ", Birthdate: " + person.getBirthdate() +
                ", Phone Number: " + person.getPhoneNumber() +
                ", Type: " + type +
                ", Genre Preferences: " + genrePreferences +
                ", Starting Date: " + startingDate +
                ", Expiration Date: " + expirationDate;
    }
}
