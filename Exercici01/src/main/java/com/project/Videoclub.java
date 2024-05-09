package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Videoclub extends SaveObjects {
    private int id, ownerId, phoneNumber;
    private String name, email;
    private double openingTime, closingTime;
    private Person person;

    public Videoclub(int id, int ownerId, String name, String email, int phoneNumber, double openingTime, double closingTime) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(double openingTime) {
        this.openingTime = openingTime;
    }

    public double getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(double closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    protected void save(Connection conn) throws SQLException {
        if (this.id == 0) {
            // New person
            String query = "INSERT INTO videoclubs (owner_id, name, email, phone_number, opening_time, closing_time) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, ownerId);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setInt(4, phoneNumber);
                pstmt.setDouble(5, openingTime);
                pstmt.setDouble(6, closingTime);
                pstmt.executeUpdate();
            }
        } else {
            // Update existing person
            String query =  "UPDATE videoclubs " +
                            "SET owner_id = ?, name = ?, email = ?, phone_number = ?, opening_time = ?, closing_time = ? " +
                            "WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, ownerId);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setInt(4, phoneNumber);
                pstmt.setDouble(5, openingTime);
                pstmt.setDouble(6, closingTime);
                pstmt.setInt(7, id);
                pstmt.executeUpdate();
            }
        }

        // If it is new, obtain the generated ID
        if (this.id == 0) {
            String ownerName = "";
            String ownerEmail = "";
            String ownerDni = "";
            int ownerAge = 0;
            String ownerBirthdate = "";
            int ownerPhoneNumber = 0;

            String query =  "SELECT * FROM people WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, ownerId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        ownerName = rs.getString("name");
                        ownerEmail = rs.getString("email");
                        ownerDni = rs.getString("dni");
                        ownerAge = rs.getInt("age");
                        ownerBirthdate = rs.getString("birthdate");
                        ownerPhoneNumber = rs.getInt("phone_number");
                    }
                }
            }
            this.person = new Person(ownerId, ownerName, ownerEmail, ownerDni, ownerAge, ownerBirthdate, ownerPhoneNumber);
            this.id = Main.getVideoclubIdByName(conn, this.name);
        }
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                ", Owner's Name: " + person.getName() +
                ", Owner's Email: " + person.getEmail() +
                ", Owner's DNI: " + person.getDni() +
                ", Owner's Age: " + person.getAge() +
                ", Owner's Birthdate: " + person.getBirthdate() +
                ", Owner's Phone Number: " + person.getPhoneNumber() +
                ", Videoclub's Name: " + name +
                ", Videoclub's Email: " + email +
                ", Videoclub's Phone Number: " + phoneNumber +
                ", Videoclub's Opening Time: " + openingTime +
                ", Videoclub's Closing Time: " + closingTime;
    }
}
