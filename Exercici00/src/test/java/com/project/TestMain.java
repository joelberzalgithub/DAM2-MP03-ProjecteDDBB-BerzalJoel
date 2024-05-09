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
            
            Accounts:
            ID: 1, Name: Emily Johnson, Nickname: john, Password: ABC, Account Creation: 1991-02-01
            ID: 2, Name: Michael Thompson, Nickname: thom, Password: DEF, Account Creation: 1991-02-01
            ID: 3, Name: Sarah Davis, Nickname: sd, Password: GHI, Account Creation: 1991-02-01
            ID: 4, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Developers:
            ID: 1, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01, Company: Id Software, Is Indie? No
            ID: 2, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11, Company: Bungie Studios, Is Indie? No
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24, Company: Valve, Is Indie? No
            ID: 4, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24, Company: Humble Games, Is Indie? Yes
            ID: 5, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10, Company: New Blood Interactive, Is Indie? Yes
            ID: 6, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16, Company: Spytihněv, Is Indie? Yes
            
            Videogames:
            ID: 1, Title: Wolfenstein 3D, Price: 4.99, Review Score: 8.0, Release Date: 1991-08-04, Developer Name: John Carmack, Developer Company: Id Software
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: John Carmack, Developer Company: Id Software
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: John Carmack, Developer Company: Id Software
            ID: 4, Title: Marathon, Price: 13.99, Review Score: 7.5, Release Date: 1994-12-21, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 5, Title: Halo: Combat Evolved, Price: 9.99, Review Score: 10.0, Release Date: 2019-12-03, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 6, Title: Destiny, Price: 0.0, Review Score: 8.5, Release Date: 2019-09-01, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 7, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 10, Title: Into The Pit, Price: 14.99, Review Score: 5.0, Release Date: 2021-10-19, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 11, Title: Prodeus, Price: 24.99, Review Score: 7.5, Release Date: 2022-09-23, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 12, Title: Signalis, Price: 4.99, Review Score: 9.5, Release Date: 2022-10-27, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 13, Title: Dusk, Price: 19.50, Review Score: 9.0, Release Date: 2018-12-10, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 16, Title: Legie, Price: 3.99, Review Score: 7.5, Release Date: 2007-09-03, Developer Name: Spytihněv, Developer Company: Spytihněv
            ID: 17, Title: Tragedy of Prince Rupert, Price: 1.99, Review Score: 6.5, Release Date: 2017-07-26, Developer Name: Spytihněv, Developer Company: Spytihněv
            ID: 18, Title: HROT, Price: 19.50, Review Score: 8.5, Release Date: 2023-05-16, Developer Name: Spytihněv, Developer Company: Spytihněv
            
            Deleting some accounts...

            Accounts deleted!

            Deleting some developers...
            
            Developers deleted!

            Deleting some videogames...
            
            Videogames deleted!

            Current accounts:
            ID: 4, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Current developers:
            ID: 1, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01, Company: Id Software, Is Indie? No
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24, Company: Valve, Is Indie? No
            ID: 5, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10, Company: New Blood Interactive, Is Indie? Yes
            
            Current videogames:
            ID: 1, Title: Wolfenstein 3D, Price: 4.99, Review Score: 8.0, Release Date: 1991-08-04, Developer Name: John Carmack, Developer Company: Id Software
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: John Carmack, Developer Company: Id Software
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: John Carmack, Developer Company: Id Software
            ID: 7, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 13, Title: Dusk, Price: 19.50, Review Score: 9.0, Release Date: 2018-12-10, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: New Blood Interactive

            Updating some accounts...

            Accounts updated!

            Updating some developers...
            
            Developers updated!

            Updating some videogames...
            
            Videogames updated!

            Current accounts:
            ID: 4, Name: Alice Johnson, Nickname: alice123, Password: A1b2C3, Account Creation: 1988-09-15
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: ValveRocks, Account Creation: 1972-11-03
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: davidszy, Password: D4v1d!23, Account Creation: 1985-06-20
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Current developers:
            ID: 1, Name: Alice Johnson, Nickname: alice123, Password: A1b2C3, Account Creation: 1988-09-15, Company: Bungie Studios, Is Indie? Yes
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: ValveRocks, Account Creation: 1972-11-03, Company: Humble Games, Is Indie? Yes
            ID: 5, Name: David Szymanski, Nickname: davidszy, Password: D4v1d!23, Account Creation: 1985-06-20, Company: Spytihněv, Is Indie? No
            
            Current videogames:
            ID: 1, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 7, Title: Prodeus, Price: 24.99, Review Score: 7.5, Release Date: 2022-09-23, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 13, Title: HROT, Price: 19.50, Review Score: 8.5, Release Date: 2023-05-16, Developer Name: David Szymanski, Developer Company: Spytihněv
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: Spytihněv
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: Spytihněv
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
        String url = "jdbc:sqlite:dades.sqlite";

        File dbFile = new File("dades.sqlite");
        assertTrue(dbFile.exists(), "The database file does not exist.");

        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData dbMetaData = conn.getMetaData();

            // Check for existence of restaurants table
            checkTableExists(dbMetaData, "accounts", "name", "kind", "tables", "pricing");

            // Check for existence of clients table
            checkTableExists(dbMetaData, "developers", "name", "birth", "isVIP");

            // Check for existence of services table
            checkTableExists(dbMetaData, "games", "id_restaurant", "id_client", "date", "expenditure");

            // Check foreign keys for services table
            checkForeignKey(dbMetaData, "developers", "accounts", "id");
            checkForeignKey(dbMetaData, "games", "developers", "id");
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
        assertMethod(clazz, "createTables", true, false, "The createTables method should be defined correctly.");
        assertMethod(clazz, "addAccounts", true, false, "The addAccounts method should be defined correctly.", String.class, String.class, String.class, String.class);
        assertMethod(clazz, "addDevelopers", true, false, "The addDevelopers method should be defined correctly.", int.class, String.class, boolean.class);
        assertMethod(clazz, "addGames", true, false, "The addGames method should be defined correctly.", int.class, String.class, double.class, double.class, String.class);
        assertMethod(clazz, "listAccounts", true, false, "The listAccounts method should be defined correctly.");
        assertMethod(clazz, "listDevelopers", true, false, "The listDevelopers method should be defined correctly.");
        assertMethod(clazz, "listGames", true, false, "The listGames method should be defined correctly.");
        assertMethod(clazz, "deleteAccount", true, false, "The deleteAccounts method should be defined correctly.", int.class, String.class, String.class, String.class, String.class);
        assertMethod(clazz, "deleteDeveloper", true, false, "The deleteDeveloper method should be defined correctly.", int.class, int.class, String.class, boolean.class);
        assertMethod(clazz, "deleteGame", true, false, "The deleteGame method should be defined correctly.", int.class, int.class, String.class, double.class, double.class, String.class);
        assertMethod(clazz, "updateAccount", true, false, "The updateAccount method should be defined correctly.", int.class);
        assertMethod(clazz, "updateDeveloper", true, false, "The updateDeveloper method should be defined correctly.", int.class);
        assertMethod(clazz, "updateGame", true, false, "The updateGame method should be defined correctly.", int.class);
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
        });

        text = text.replace("\r\n", "\n");

        String expectedOutput = """
            
            Accounts:
            ID: 1, Name: Emily Johnson, Nickname: john, Password: ABC, Account Creation: 1991-02-01
            ID: 2, Name: Michael Thompson, Nickname: thom, Password: DEF, Account Creation: 1991-02-01
            ID: 3, Name: Sarah Davis, Nickname: sd, Password: GHI, Account Creation: 1991-02-01
            ID: 4, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Developers:
            ID: 1, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01, Company: Id Software, Is Indie? No
            ID: 2, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11, Company: Bungie Studios, Is Indie? No
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24, Company: Valve, Is Indie? No
            ID: 4, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24, Company: Humble Games, Is Indie? Yes
            ID: 5, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10, Company: New Blood Interactive, Is Indie? Yes
            ID: 6, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16, Company: Spytihněv, Is Indie? Yes
            
            Videogames:
            ID: 1, Title: Wolfenstein 3D, Price: 4.99, Review Score: 8.0, Release Date: 1991-08-04, Developer Name: John Carmack, Developer Company: Id Software
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: John Carmack, Developer Company: Id Software
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: John Carmack, Developer Company: Id Software
            ID: 4, Title: Marathon, Price: 13.99, Review Score: 7.5, Release Date: 1994-12-21, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 5, Title: Halo: Combat Evolved, Price: 9.99, Review Score: 10.0, Release Date: 2019-12-03, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 6, Title: Destiny, Price: 0.0, Review Score: 8.5, Release Date: 2019-09-01, Developer Name: Jason Jones, Developer Company: Bungie Studios
            ID: 7, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 10, Title: Into The Pit, Price: 14.99, Review Score: 5.0, Release Date: 2021-10-19, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 11, Title: Prodeus, Price: 24.99, Review Score: 7.5, Release Date: 2022-09-23, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 12, Title: Signalis, Price: 4.99, Review Score: 9.5, Release Date: 2022-10-27, Developer Name: Jeff Rosen, Developer Company: Humble Games
            ID: 13, Title: Dusk, Price: 19.50, Review Score: 9.0, Release Date: 2018-12-10, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 16, Title: Legie, Price: 3.99, Review Score: 7.5, Release Date: 2007-09-03, Developer Name: Spytihněv, Developer Company: Spytihněv
            ID: 17, Title: Tragedy of Prince Rupert, Price: 1.99, Review Score: 6.5, Release Date: 2017-07-26, Developer Name: Spytihněv, Developer Company: Spytihněv
            ID: 18, Title: HROT, Price: 19.50, Review Score: 8.5, Release Date: 2023-05-16, Developer Name: Spytihněv, Developer Company: Spytihněv
            
            Deleting some accounts...

            Accounts deleted!

            Deleting some developers...
            
            Developers deleted!

            Deleting some videogames...
            
            Videogames deleted!

            Current accounts:
            ID: 4, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Current developers:
            ID: 1, Name: John Carmack, Nickname: jck, Password: JKL, Account Creation: 1991-02-01, Company: Id Software, Is Indie? No
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: OPQ, Account Creation: 1996-08-24, Company: Valve, Is Indie? No
            ID: 5, Name: David Szymanski, Nickname: dave, Password: UVW, Account Creation: 2018-01-10, Company: New Blood Interactive, Is Indie? Yes
            
            Current videogames:
            ID: 1, Title: Wolfenstein 3D, Price: 4.99, Review Score: 8.0, Release Date: 1991-08-04, Developer Name: John Carmack, Developer Company: Id Software
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: John Carmack, Developer Company: Id Software
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: John Carmack, Developer Company: Id Software
            ID: 7, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Valve
            ID: 13, Title: Dusk, Price: 19.50, Review Score: 9.0, Release Date: 2018-12-10, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: New Blood Interactive
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: New Blood Interactive

            Updating some accounts...

            Accounts updated!

            Updating some developers...
            
            Developers updated!

            Updating some videogames...
            
            Videogames updated!

            Current accounts:
            ID: 4, Name: Alice Johnson, Nickname: alice123, Password: A1b2C3, Account Creation: 1988-09-15
            ID: 5, Name: Jason Jones, Nickname: jj, Password: MNÑ, Account Creation: 1991-05-11
            ID: 6, Name: Gabe Newell, Nickname: gaben, Password: ValveRocks, Account Creation: 1972-11-03
            ID: 7, Name: Jeff Rosen, Nickname: jrsn, Password: RST, Account Creation: 2013-04-24
            ID: 8, Name: David Szymanski, Nickname: davidszy, Password: D4v1d!23, Account Creation: 1985-06-20
            ID: 9, Name: Spytihněv, Nickname: spy, Password: XYZ, Account Creation: 2020-06-16

            Current developers:
            ID: 1, Name: Alice Johnson, Nickname: alice123, Password: A1b2C3, Account Creation: 1988-09-15, Company: Bungie Studios, Is Indie? Yes
            ID: 3, Name: Gabe Newell, Nickname: gaben, Password: ValveRocks, Account Creation: 1972-11-03, Company: Humble Games, Is Indie? Yes
            ID: 5, Name: David Szymanski, Nickname: davidszy, Password: D4v1d!23, Account Creation: 1985-06-20, Company: Spytihněv, Is Indie? No
            
            Current videogames:
            ID: 1, Title: Half-Life, Price: 4.99, Review Score: 10.0, Release Date: 1998-11-08, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 2, Title: Doom, Price: 4.99, Review Score: 10.0, Release Date: 1993-06-06, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 3, Title: Quake, Price: 9.99, Review Score: 9.0, Release Date: 2007-09-17, Developer Name: Alice Johnson, Developer Company: Bungie Studios
            ID: 7, Title: Prodeus, Price: 24.99, Review Score: 7.5, Release Date: 2022-09-23, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 8, Title: Team Fortress, Price: 4.99, Review Score: 9.5, Release Date: 1999-04-01, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 9, Title: Portal, Price: 4.99, Review Score: 10.0, Release Date: 2007-10-10, Developer Name: Gabe Newell, Developer Company: Humble Games
            ID: 13, Title: HROT, Price: 19.50, Review Score: 8.5, Release Date: 2023-05-16, Developer Name: David Szymanski, Developer Company: Spytihněv
            ID: 14, Title: Amid Evil, Price: 19.50, Review Score: 8.0, Release Date: 2019-07-20, Developer Name: David Szymanski, Developer Company: Spytihněv
            ID: 15, Title: Ultrakill, Price: 24.50, Review Score: 10.0, Release Date: 2020-09-03, Developer Name: David Szymanski, Developer Company: Spytihněv
            """.replace("\r\n", "\n").replace("            ","");

        String diff = TestStringUtils.findFirstDifference(text, expectedOutput);
            assertTrue(diff.compareTo("identical") == 0, 
                ">>>>>>>>>> >>>>>>>>>>\n" +
                diff +
                "<<<<<<<<<< <<<<<<<<<<\n");
    }
}
