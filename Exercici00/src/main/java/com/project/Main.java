package com.project;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AppData db = AppData.getInstance();

        // Create tables
        createTables();

        // Add accounts
        addAccount("Emily Johnson", "john", "ABC", "1991-02-01");
        addAccount("Michael Thompson", "thom", "DEF", "1991-02-01");
        addAccount("Sarah Davis", "sd", "GHI", "1991-02-01");
        addAccount("John Carmack", "jck", "JKL", "1991-02-01");
        addAccount("Jason Jones", "jj", "MNÑ", "1991-05-11");
        addAccount("Gabe Newell", "gaben", "OPQ", "1996-08-24");
        addAccount("Jeff Rosen", "jrsn", "RST", "2013-04-24");
        addAccount("David Szymanski", "dave", "UVW", "2018-01-10");
        addAccount("Spytihněv", "spy", "XYZ", "2020-06-16");

        // Add developers
        addDevelopers(4, "Id Software", false);
        addDevelopers(5, "Bungie Studios", false);
        addDevelopers(6, "Valve", false);
        addDevelopers(7, "Humble Games", true);
        addDevelopers(8, "New Blood Interactive", true);
        addDevelopers(9, "Spytihněv", true);

        // Add videogames
        addGame(4, "Wolfenstein 3D", 4.99, 8.0, "1991-08-04");
        addGame(4, "Doom", 4.99, 10.0, "1993-06-06");
        addGame(4, "Quake", 9.99, 9.0, "2007-09-17");
        addGame(5, "Marathon", 13.99, 7.5, "1994-12-21");
        addGame(5, "Halo: Combat Evolved", 9.99, 10.0, "2019-12-03");
        addGame(5, "Destiny", 0.0, 8.5, "2019-09-01");
        addGame(6, "Half-Life", 4.99, 10.0, "1998-11-08");
        addGame(6, "Team Fortress", 4.99, 9.5, "1999-04-01");
        addGame(6, "Portal", 4.99, 10.0, "2007-10-10");
        addGame(7, "Into The Pit", 14.99, 5.0, "2021-10-19");
        addGame(7, "Prodeus", 24.99, 7.5, "2022-09-23");
        addGame(7, "Signalis", 4.99, 9.5, "2022-10-27");
        addGame(8, "Dusk", 19.50, 9.0, "2018-12-10");
        addGame(8, "Amid Evil", 19.50, 8.0, "2019-07-20");
        addGame(8, "Ultrakill", 24.50, 10.0, "2020-09-03");
        addGame(9, "Legie", 3.99, 7.5, "2007-09-03");
        addGame(9, "Tragedy of Prince Rupert", 1.99, 6.5, "2017-07-26");
        addGame(9, "HROT", 19.50, 8.5, "2023-05-16");

        // List accounts
        System.out.println("\nAccounts:");
        listAccounts();

        // List developers
        System.out.println("\nDevelopers:");
        listDevelopers();

        // List videogames
        System.out.println("\nVideogames:");
        listGames();

        // Delete some accounts
        System.out.println("\nDeleting some accounts...");
        deleteAccount(1);
        deleteAccount(2);
        deleteAccount(3);
        System.out.println("\nAccounts deleted!");

        // Delete some developers
        System.out.println("\nDeleting some developers...");
        deleteDeveloper(2);
        deleteDeveloper(4);
        deleteDeveloper(6);
        System.out.println("\nDevelopers deleted!");

        // Delete some videogames
        System.out.println("\nDeleting some videogames...");
        deleteGame(4);
        deleteGame(5);
        deleteGame(6);
        deleteGame(10);
        deleteGame(11);
        deleteGame(12);
        deleteGame(16);
        deleteGame(17);
        deleteGame(18);
        System.out.println("\nVideogames deleted!");

        // List accounts again to check if the changes were successfull
        System.out.println("\nCurrent accounts:");
        listAccounts();

        // List developers again to check if the changes were successfull
        System.out.println("\nCurrent developers:");
        listDevelopers();

        // List videogames again to check if the changes were successfull
        System.out.println("\nCurrent videogames:");
        listGames();

        // Update some accounts
        System.out.println("\nUpdating some accounts...");
        updateAccount(4, "Alice Johnson", "alice123", "A1b2C3", "1988-09-15");
        updateAccount(6, "Gabe Newell", "gaben", "ValveRocks", "1972-11-03");
        updateAccount(8, "David Szymanski", "davidszy", "D4v1d!23", "1985-06-20");
        System.out.println("\nAccounts updated!");

        // Update some developers
        System.out.println("\nUpdating some developers...");
        updateDeveloper(1, 4, "Bungie Studios", true);
        updateDeveloper(3, 6, "Humble Games", true);
        updateDeveloper(5, 8, "Spytihněv", false);
        System.out.println("\nDevelopers updated!");

        // Update some videogames
        System.out.println("\nUpdating some videogames...");
        updateGame(1, 1, "Half-Life", 4.99, 10.0, "1998-11-08");
        updateGame(7, 3, "Prodeus", 24.99, 7.5, "2022-09-23");
        updateGame(13, 5, "HROT", 19.50, 8.5, "2023-05-16");
        System.out.println("\nVideogames updated!");

        // List accounts again to check if the changes were successfull
        System.out.println("\nCurrent accounts:");
        listAccounts();

        // List developers again to check if the changes were successfull
        System.out.println("\nCurrent developers:");
        listDevelopers();

        // List videogames again to check if the changes were successfull
        System.out.println("\nCurrent videogames:");
        listGames();

        // Close the database connection
        db.close();
    }

    public static void createTables() {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Create the 'accounts' table
        db.update("DROP TABLE IF EXISTS accounts");
        db.update("CREATE TABLE IF NOT EXISTS accounts (" +
                                "id INTEGER, " +
                                "name TEXT, " + 
                                "nickname TEXT, " + 
                                "password TEXT, " +
                                "account_creation DATE, " +
                                "PRIMARY KEY (id))");

        // Create the 'developers' table
        db.update("DROP TABLE IF EXISTS developers");
        db.update("CREATE TABLE IF NOT EXISTS developers (" +
                                "id INTEGER, " +
                                "id_account INTEGER, " + 
                                "company TEXT, " + 
                                "is_indie BOOLEAN, " +
                                "PRIMARY KEY (id), " +
                                "FOREIGN KEY (id_account) REFERENCES accounts (id))");

        // Create the 'games' table
        db.update("DROP TABLE IF EXISTS games");
        db.update("CREATE TABLE IF NOT EXISTS games (" +
                                "id INTEGER, " +
                                "id_developer INTEGER, " + 
                                "title TEXT, " + 
                                "price REAL, " +
                                "review_score REAL, " +
                                "release DATE, " +
                                "PRIMARY KEY (id), " +
                                "FOREIGN KEY (id_developer) REFERENCES accounts (id))");
    }

    public static void addAccount(String name, String nickname, String password, String accountCreation) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Insert data into the 'accounts' table
        db.update("INSERT INTO accounts (name, nickname, password, account_creation) " +
                                "VALUES ('" + name + "', '" + nickname + "', '" + password + "', '" + accountCreation + "')");
    }

    public static void addDevelopers(int idAccount, String company, boolean isIndie) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Insert data into the 'developers' table
        db.update("INSERT INTO developers (id_account, company, is_indie) " +
                                "VALUES (" + idAccount + ", '" + company + "', " + isIndie + ")");
    }

    public static void addGame(int idDeveloper, String title, double price, double reviewScore, String release) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Insert data into the 'games' table
        db.update("INSERT INTO games (id_developer, title, price, review_score, release) " +
                                "VALUES (" + idDeveloper + ", '" + title + "', " + price + ", " + reviewScore + ", '" + release + "')");
    }

    public static void listAccounts() {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        List<Map<String, Object>> rows = db.query("SELECT * FROM accounts");

        // List the query rows
        for (Map<String, Object> row : rows) {
            System.out.println( "ID: " + row.get("id") +
                                ", Name: " + row.get("name") +
                                ", Nickname: " + row.get("nickname") +
                                ", Password: " + row.get("password") +
                                ", Account Creation: " + row.get("account_creation"));
        }
    }

    public static void listDevelopers() {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        List<Map<String, Object>> rows = db.query(  "SELECT d.id, a.name, a.nickname, a.password, a.account_creation, d.company, d.is_indie " +
                                                    "FROM developers d " +
                                                    "JOIN accounts a ON d.id_account = a.id");

        // List the query rows
        for (Map<String, Object> row : rows) {
            String isIndie = "No";
            if (row.get("is_indie").equals(true)) {
                isIndie = "Yes";
            }
            System.out.println( "ID: " + row.get("id") +
                                ", Name: " + row.get("name") +
                                ", Nickname: " + row.get("nickname") +
                                ", Password: " + row.get("password") +
                                ", Account Creation: " + row.get("account_creation") +
                                ", Company: " + row.get("company") +
                                ", Is Indie? " + isIndie);
        }
    }

    public static void listGames() {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        List<Map<String, Object>> rows = db.query(  "SELECT g.id, g.title, g.price, g.review_score, g.release, a.name, d.company " +
                                                    "FROM games g " +
                                                    "JOIN developers d ON g.id_developer = d.id " +
                                                    "JOIN accounts a ON d.id_account = a.id");

        // List the query rows
        for (Map<String, Object> row : rows) {
            System.out.println( "ID: " + row.get("id") +
                                ", Title: " + row.get("title") +
                                ", Price: " + row.get("price") +
                                ", Review Score: " + row.get("review_score") +
                                ", Release Date: " + row.get("release_date") +
                                ", Developer Name: " + row.get("name") +
                                ", Developer Company: " + row.get("company"));
        }
    }

    public static void deleteAccount(int id) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'accounts' table
        db.update("DELETE FROM accounts WHERE id = " + id);
    }

    public static void deleteDeveloper(int id) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'developers' table
        db.update("DELETE FROM developers WHERE id = " + id);
    }

    public static void deleteGame(int id) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'games' table
        db.update("DELETE FROM games WHERE id = " + id);
    }

    public static void updateAccount(int id, String name, String nickname, String password, String accountCreation) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'accounts' table
        db.update(  "UPDATE accounts " +
                    "SET name = '" + name + "', nickname = '" + nickname + "', password = '" + password + "', account_creation = '" + accountCreation + "' " +
                    "WHERE id = " + id);
    }

    public static void updateDeveloper(int id, int idAccount, String company, boolean isIndie) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'accounts' table
        db.update(  "UPDATE developers " +
                    "SET id_account = " + idAccount + ", company = '" + company + "', is_indie = " + isIndie + " " +
                    "WHERE id = " + id);
    }

    public static void updateGame(int id, int idDeveloper, String title, double price, double reviewScore, String release) {
        // Get a pointer to the DDBB singleton
        AppData db = AppData.getInstance();

        // Delete data from the 'accounts' table
        db.update(  "UPDATE games " +
                    "SET id_developer = " + idDeveloper + ", title = '" + title + "', price = " + price + ", review_score = " + reviewScore + ", release = '" + release + "' " +
                    "WHERE id = " + id);
    }
}
