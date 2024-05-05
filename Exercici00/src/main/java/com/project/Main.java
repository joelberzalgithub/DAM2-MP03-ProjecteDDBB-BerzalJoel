package com.project;

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
        addAccount("David Szymanski", "UVW", "mno", "2018-01-10");
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
        addGame(5, "Half-Life", 4.99, 10.0, "1998-11-08");
        addGame(5, "Team Fortress", 4.99, 9.5, "1999-04-01");
        addGame(5, "Portal", 4.99, 10.0, "2007-10-10");
        addGame(6, "Marathon", 13.99, 7.5, "1994-12-21");
        addGame(6, "Halo: Combat Evolved", 9.99, 10.0, "2019-12-03");
        addGame(6, "Destiny", 0.0, 8.5, "2019-09-01");
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
        deleteAccount(1);
        deleteAccount(2);
        deleteAccount(3);

        // Delete some developers
        deleteDeveloper(2);
        deleteDeveloper(4);
        deleteDeveloper(6);

        // Delete some videogames
        deleteGame(4);
        deleteGame(5);
        deleteGame(6);
        deleteGame(10);
        deleteGame(11);
        deleteGame(12);
        deleteGame(16);
        deleteGame(17);
        deleteGame(18);

        // List accounts again to check if the changes were successfull
        System.out.println("\nAccounts:");
        listAccounts();

        // List developers again to check if the changes were successfull
        System.out.println("\nDevelopers:");
        listDevelopers();

        // List videogames again to check if the changes were successfull
        System.out.println("\nVideogames:");
        listGames();

        // Update some accounts
        updateAccount(4, "Alice Johnson", "alice123", "A1b2C3", "1988-09-15");
        updateAccount(6, "Gabe Newell", "gaben", "ValveRocks", "1972-11-03");
        updateAccount(8, "David Szymanski", "davidszy", "D4v1d!23", "1985-06-20");

        // Update some developers
        updateDeveloper(1, 4, "Bungie Studios", true);
        updateDeveloper(3, 6, "Humble Games", true);
        updateDeveloper(5, 8, "Spytihněv", false);

        // Update some videogames
        updateGame(1, 1, "Half-Life", 4.99, 10.0, "1998-11-08");
        updateGame(7, 3, "Prodeus", 24.99, 7.5, "2022-09-23");
        updateGame(13, 5, "HROT", 19.50, 8.5, "2023-05-16");

        // List accounts again to check if the changes were successfull
        System.out.println("\nAccounts:");
        listAccounts();

        // List developers again to check if the changes were successfull
        System.out.println("\nDevelopers:");
        listDevelopers();

        // List videogames again to check if the changes were successfull
        System.out.println("\nVideogames:");
        listGames();

        // Close the database connection
        db.close();
    }
}
