# Joel Berzal Álamo #

## Exercici 1 (5 punts) ##

Fent servir el Singleton 'AppData' i una base de dades *sqlite* anomenada **dades.sqlite**, desenvolupa una aplicació que comptes, videojocs i desenvolupadors.

**Taula people:**

```sql
id (INTEGER): identificador únic
name (TEXT): nom
email (TEXT): correu electrònic
dni (TEXT): DNI
age (INTEGER): edat
birthdate (DATE): data de naixement
phone_number (INTEGER): número de telèfon
```

**Taula customers:**

```sql
customer_id (INTEGER): identificador únic
person_id (INTEGER): clau forana que enllaça amb id de la taula People
type (TEXT): tipus de membresia (base o prèmium)
genre_preferencies (TEXT): gèneres de pel·lícules preferits
starting_date (DATE): data en la qual va començar a ser membre del vídeoclub
expiration_date (DATE): data en la qual es deixarà de ser membre en cas de deixar de pagar la subscripció
```

**Taula employees:**

```sql
employee_id (INTEGER): identificador únic
person_id (INTEGER): clau forana que enllaça amb id de la taula People
hiring_date (DATE): data de contractació
occupation (TEXT): càrrec
salary (REAL): salari
schedule (INTEGER): quantitat d''hores de l''horari
available_holidays (INTEGER): dies de vacances disponibles
```

**Taula videoclubs:**

```sql
id (INTEGER): identificador únic
owner_id (INTEGER): identificador del propietari del vídeoclub
name (TEXT): nom del vídeoclub
email (TEXT): correu de contacte
phone_number (INTEGER): número de contacte
opening_time (REAL): hora d''apertura
closing_time (REAL): hora de tancament
```

Els camps 'membership_id', 'employee_id' i 'owner_id' es relacionen amb les respectives taules.

Implementa objectes per contenir la informació de cada taula de la base de dades anterior. Tingues en compte que tots els atributs són privats, i calen Getters i Setters.

**Classe People:** Classe base

```class
*Atributs:*

* **id** (int): Un identificador únic per a cada país. Quan és 0, encara no s'ha afegit l'element a la base de dades.
* **name** (String): El nom de la persona
* **email** (String): El correu electrònic de la persona
* **dni** (String): El DNI de la persona
* **age** (int): L'edat de la persona
* **birthdate** (Date): La data de naixement de la persona
* **phoneNumber** (int): El número de telèfon de la persona

*Constructor:* Inicialitza tots els atributs de l'objecte People.

*Mètodes:*

* **getters/setters** dels atributs.
* **save()**: Guarda o actualitza la persona a la base de dades fent ús del singleton AppData.
* **toString()**: Retorna una cadena de text amb la informació de la persona.
```

**Classe Customer:** subclasse

```class
*Atributs adicionals:*

* **customerId** (int): Un identificador únic per a cada client.
* **personId** (int): La clau forana que enllaça amb l'id de la taula People.
* **type** (String): El tipus de membresia del client (base o prèmium).
* **genrePreferencies** (String): Els gèneres de pel·lícules preferits del client.
* **startingDate** (Date): La data en la qual el client va començar a ser membre del vídeoclub.
* **expirationDate** (Date): La data en la qual el client deixarà de ser membre en cas de deixar de pagar la subscripció.

*Constructor:* Inicialitza tots els atributs de l'objecte Customer.

*Mètodes:*

* **getters/setters** dels atributs.
* **save()**: Guarda o actualitza l'espècie de planta a la base de dades utilitzant AppData.
* **toString()**: Retorna una cadena de text amb la informació del client. 
```

**Classe Employee:** subclasse

```class
*Atributs adicionals:*

* **employeeId** (int): Un identificador únic per a cada empleat.
* **personId** (int): La clau forana que enllaça amb l'id de la taula People.
* **hiringDate** (Date): La data de contractació de l'empleat.
* **occupation** (String): El càrrec de l'empleat.
* **salary** (double): El salari de l'empleat.
* **schedule** (int): La quantitat d'hores de l'horari de l'empleat.
* **availableHolidays** (int): Els dies de vacances que l'empleat té disponibles.

*Constructor:* Inicialitza tots els atributs de l'objecte Employee.

*Mètodes:*

* **getters/setters** dels atributs.
* **save()**: Guarda o actualitza l'espècie de planta a la base de dades utilitzant AppData.
* **toString()**: Retorna una cadena de text amb la informació de l'empleat. 
```

**Classe Videoclub:**

```class
*Atributs:*

* **id** (int): Un identificador únic per a cada vídeoclub.
* **ownerId** (int): La clau forana que enllaça amb l'id de la taula People.
* **name** (String): El nom del vídeoclub.
* **email** (String): El correu de contacte del vídeoclub.
* **phoneNumber** (int): El número de contacte del vídeoclub
* **openingTime** (double): L'hora d'apertura del vídeoclub.
* **closingTime** (double): L'hora de tancament del vídeoclub.

*Constructor:* Inicialitza tots els atributs de l'objecte Videoclub.

*Mètodes:*

* **getters/setters** dels atributs.
* **save()**: Guarda o actualitza l'espècie animal a la base de dades utilitzant AppData.
* **toString()**: Retorna una cadena de text amb la informació del vídeoclub. 
```

Defineix també les següents funcions, i mira l'exemple de sortida per saber com mostren les dades:

```java
// Crear les taules (i esborrar les antigues si cal)
void createTables() 

// Afegir una persona
void addPerson(int id, String name, String email, String dni, int age, String birthdate, int phoneNumber)

// Afegir un client
void addCustomer(int customerId, int personId, String type, String genrePreferences, String startingDate, String expirationDate)

// Afegir un empleat
void addEmployee(int employeeId, int personId, String hiringDate, String occupation, double salary, int schedule, int availableHolidays)

// Afegir un vídeoclub
void addVideoclub(int id, int ownerId, String name, String email, int phoneNumber, double openingTime, double closingTime)

// Obtenir l'id d'un client en funció d'un nom
void getCustomerIdByName()

// Obtenir l'id d'un empleat en funció d'un nom
void getEmployeeIdByName()

// Obtenir l'id d'un propietari en funció d'un nom
void getOwnerIdByName()

// Obtenir l'id d'un vídeoclub en funció d'un nom
void getVideoclubIdByName()

// Llista a totes les persones amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listPeople()

// Llista als clients amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listCustomers()

// Llita als empleats amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listEmployees()

// Llita als empleats amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listOwners()

// Llita els vídeoclubs amb format:
// ID: 1, Name: The Gourmet Kitchen, Kind: Italian, Tables: 10, Pricing: High
void listVideoclubs()
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
