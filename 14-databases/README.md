# Databases

Completed tasks:

![0%](https://progress-bar.xyz/0)

<details>
<summary>DDL with Test Data</summary>

```SQL
-- Drop tables if exist (for clean slate)
DROP TABLE IF EXISTS AptTenants CASCADE;
DROP TABLE IF EXISTS Tenants CASCADE;
DROP TABLE IF EXISTS Requests CASCADE;
DROP TABLE IF EXISTS Apartments CASCADE;
DROP TABLE IF EXISTS Buildings CASCADE;
DROP TABLE IF EXISTS Complexes CASCADE;

-- Complexes: a complex may contain multiple buildings
CREATE TABLE Complexes (
    ComplexID   SERIAL PRIMARY KEY,
    ComplexName VARCHAR(255) NOT NULL
);

-- Buildings: each building belongs to one complex
CREATE TABLE Buildings (
    BuildingID   SERIAL PRIMARY KEY,
    BuildingName VARCHAR(255) NOT NULL,
    ComplexID    INT NOT NULL REFERENCES Complexes(ComplexID)
);

-- Apartments: each apartment belongs to one building (and thus to a complex implicitly)
CREATE TABLE Apartments (
    AptID      SERIAL PRIMARY KEY,
    BuildingID INT NOT NULL REFERENCES Buildings(BuildingID),
    UnitNumber VARCHAR(50) NOT NULL
);

-- Tenants: persons who may rent apartments
CREATE TABLE Tenants (
    TenantID   SERIAL PRIMARY KEY,
    TenantName VARCHAR(255) NOT NULL
);

-- AptTenants: many-to-many between Apartments and Tenants
-- An apartment can have multiple tenants, a tenant can rent multiple apartments
CREATE TABLE AptTenants (
    AptID    INT NOT NULL REFERENCES Apartments(AptID),
    TenantID INT NOT NULL REFERENCES Tenants(TenantID),
    PRIMARY KEY (AptID, TenantID)
);

-- Requests: maintenance / service requests from apartments
CREATE TABLE Requests (
    RequestID SERIAL PRIMARY KEY,
    AptID     INT NOT NULL REFERENCES Apartments(AptID),
    Status    VARCHAR(50) NOT NULL,
    Description TEXT
);

-- Insert complexes
INSERT INTO Complexes (ComplexName) VALUES
  ('Downtown Complex'),
  ('Riverside Gardens');

-- Insert buildings
INSERT INTO Buildings (BuildingName, ComplexID) VALUES
  ('Building A', 1),
  ('Building B', 1),
  ('Building C', 2);

-- Insert apartments
INSERT INTO Apartments (BuildingID, UnitNumber) VALUES
  (1, 'Apt 101'),
  (1, 'Apt 102'),
  (2, 'Apt 201'),
  (2, 'Apt 202'),
  (3, 'Apt 301');

-- Insert tenants
INSERT INTO Tenants (TenantName) VALUES
  ('Alice'),
  ('Bob'),
  ('Charlie'),
  ('Diana');

-- Assign tenants to apartments
INSERT INTO AptTenants (AptID, TenantID) VALUES
  (1, 1),  -- Alice in Apt 101
  (2, 2),  -- Bob in Apt 102
  (2, 3),  -- Charlie also in Apt 102 (shared)
  (3, 4),  -- Diana in Apt 201
  (4, 1),  -- Alice also rents Apt 202 (multiple apartments)
  (5, 4);  -- Diana in Apt 301

-- Create some requests
INSERT INTO Requests (AptID, Status, Description) VALUES
  (1, 'Open', 'Leaky faucet in kitchen'),
  (2, 'Closed', 'Broken window repaired'),
  (2, 'Open', 'Heating not working'),
  (4, 'Open', 'Light flickering in hallway'),
  (5, 'Closed', 'Door lock replaced');
```  
</details>

## 1. Multiple Apartments

Write a SQL query to get a list of tenants who are renting more than one apartment.

<details>
<summary>Solution</summary>

```SQL
SELECT t FROM tenants t INNER JOIN AptTenants at ON t.TenantID = at.TenantID GROUP BY t.TenantID HAVING count(t) > 1;
```
</details>
<hr/>

## 2. Open Requests

Write a SQL query to get a list of all buildings and the number of open requests (Requests in which status equals 'Open').

<hr/>

## 3. Close All Requests

Building #11 is undergoing a major renovation. Implement a query to close all requests from apartments in this building.

<hr/>

## 4. Joins

What are the different types of joins? Please explain how they differ and why certain types are better in certain situations.

<hr/>

## 5. Denormalization

What is denormalization? Explain the pros. and cons.

<hr/>

## 6. Entity-Relationship Diagram

Draw an entity-relationship diagram for a database with companies, people, and professionals (people who work for companies).

<hr/>

## 7. Design Grade Database

Imagine a simple database storing information for students' grades. Design what this database might look like and provide a SQL query to
return a list of the honor roll students (top 10%), sorted by their grade point average.

<hr/>


## Database Schema


![database schema](database-schema.png)
