# Joel Berzal Álamo #

## Exercici 0 (5 punts) ##

Fent servir el Singleton 'AppData' i una base de dades *sqlite* anomenada **dades.sqlite**, desenvolupa una aplicació que comptes, videojocs i desenvolupadors.

**Taula accounts:**

```sql
id (INTEGER): identificador únic
name (TEXT): nom real
nickname (TEXT): nom del compte
password (TEXT): contrasenya
account_creation (DATE): data de creació del compte
```

**Taula developers:**

```sql
id (INTEGER): identificador únic
id_account (INTEGER): identificador de compte
company (TEXT): companyia per la que treballa
isIndie (BOOLEAN): és un desenvolupador independent
```

**Taula games:**

```sql
id (INTEGER): identificador únic
id_developer (INTEGER): identificador de desenvolupador
title (TEXT): nom
price (REAL): preu
review_score (REAL): puntuació
release (DATE): data de llançament
```

Els camps 'id_account' i 'id_developer' es relacionen amb les respectives taules.

Defineix també les següents funcions, i mira l'exemple de sortida per saber com mostren les dades:

```java
// Crear les taules (i esborrar les antigues si cal)
void createTables() 

// Afegir un compte
void addAccount(String name, String nickname, String password, String accountCreation)

// Afegir un desenvolupador
void addDeveloper(int accountId, String company, boolean isIndie)

// Afegir un videojoc
void addGame(int developerId, String title, double price, double reviewScore, String release)

// Llista els comptes amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listAccounts()

// Llista els desenvolupadors amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listDevelopers()

// Llita els videojocs amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listGames()

// Actualitza les dades d´un compte
void updateAccount(int id, String newName, String newNickname, String newPassword, String newAccountCreatio)

// Actualitza les dades d´un desenvolupador
void updateDeveloper(int id, int newAccountId, String newCompany, boolean newIsIndie)

// Actualitza les dades d´un videojoc
void updateGame(int id, int newDeveloperId, String newTitle, double newPrice, double newReviewScore, String newRelease)

// Esborra un element de la taula de comptes
void deleteAccount(int id)

// Esborra un element de la taula de desenvolupador
void deleteDeveloper(int id)

// Esborra un element de la taula de videojocs
void deleteGame(int id)
```

Cal el 'Maven' per compilar el projecte

```bash
mvn clean
mvn compile
```

Per executar el projecte a Windows cal

```bash
.\run.ps1 com.project.Main
```

Per executar el projecte a Linux/macOS cal

```bash
./run.sh com.project.Main
```
