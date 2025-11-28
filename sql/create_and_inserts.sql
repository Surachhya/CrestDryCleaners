-- This will completly reset the db objects and contents to initial phase
USE sadhika4db;
-- Disable Foreign key for cleanup
SET FOREIGN_KEY_CHECKS = 0;
-- DROP CHILD TABLES FIRST
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Van;
DROP TABLE IF EXISTS Employee;

-- THEN DROP PARENT TABLES
DROP TABLE IF EXISTS Store;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS CorporateClient;
-- Disable Foreign key after cleanup
SET FOREIGN_KEY_CHECKS = 1;

-- =========================================================
-- TABLE: Customer
-- =========================================================
CREATE TABLE Customer (
    Customer_ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    FName       VARCHAR(50) NOT NULL,
    LName       VARCHAR(50) NOT NULL,
    Phone       VARCHAR(20) UNIQUE,
    Email       VARCHAR(100) UNIQUE,
    Address     VARCHAR(255),
    PaymentInfo VARCHAR(100)
);

-- =========================================================
-- TABLE: Store
-- =========================================================
CREATE TABLE Store (
    Store_ID      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Location      VARCHAR(150) NOT NULL,
    Manager_ID    INT UNSIGNED,
    Supervisor_ID INT UNSIGNED
);

-- =========================================================
-- TABLE: Employee
-- =========================================================
CREATE TABLE Employee (
    Employee_ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(100) NOT NULL,
    Role        ENUM('Front Desk','Cleaner','Presser','Tailor','Delivery Driver',
                     'Store Manager','Corporate Coordinator','Supervisor') NOT NULL,
    Store_ID    INT UNSIGNED,
    Phone       VARCHAR(20),
    FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
        ON UPDATE CASCADE ON DELETE SET NULL
);

-- Now link Manager_ID and Supervisor_ID AFTER Employee exists
ALTER TABLE Store 
ADD CONSTRAINT fk_store_manager
    FOREIGN KEY (Manager_ID) REFERENCES Employee(Employee_ID)
    ON UPDATE CASCADE ON DELETE SET NULL,
ADD CONSTRAINT fk_store_supervisor
    FOREIGN KEY (Supervisor_ID) REFERENCES Employee(Employee_ID)
    ON UPDATE CASCADE ON DELETE SET NULL;

-- =========================================================
-- TABLE: Order
-- =========================================================
CREATE TABLE Orders (
    Order_ID     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Customer_ID  INT UNSIGNED NOT NULL,
    OrderDate    DATE NOT NULL,
    DueDate      DATE NOT NULL,
    TotalPieces  INT UNSIGNED NOT NULL,
    Status       ENUM('Received','Processing','Ready','Delivered','Cancelled') NOT NULL,
    TotalAmount  DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- =========================================================
-- TABLE: Item (Clothing items)
-- =========================================================
CREATE TABLE Item (
    Item_ID        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Order_ID       INT UNSIGNED NOT NULL,
    Type           VARCHAR(50) NOT NULL,
    Brand          VARCHAR(50),
    Color          VARCHAR(30),
    Material       VARCHAR(50),
    Pattern        VARCHAR(50),
    SpecialRequest VARCHAR(255),
    Barcode        VARCHAR(50) UNIQUE,
    FOREIGN KEY (Order_ID) REFERENCES Orders(Order_ID)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- =========================================================
-- TABLE: Van
-- =========================================================
CREATE TABLE Van (
    Van_ID      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Store_ID    INT UNSIGNED NOT NULL,
    PlateNumber VARCHAR(20) UNIQUE NOT NULL,
    Model       VARCHAR(50),
    Year        YEAR,
    Capacity    INT UNSIGNED,
    FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- =========================================================
-- TABLE: CorporateClient
-- =========================================================
CREATE TABLE CorporateClient (
    Corporate_ID    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Company_Name    VARCHAR(100) NOT NULL,
    Contact_Person  VARCHAR(100) NOT NULL,
    Discount_Rate   DECIMAL(5,2) DEFAULT 0.00
);

-- Lets insert data
INSERT INTO Customer (FName, LName, Phone, Email, Address, PaymentInfo) VALUES
('John', 'Smith', '4105551122', 'john.smith@gmail.com', '123 Main St, Baltimore MD', 'Visa **** 4421'),
('Priya', 'Sharma', '4435558765', 'priya.sharma@yahoo.com', '55 Towson Ave, Towson MD', 'MasterCard **** 7789'),
('Michael', 'Brown', '4105553344', 'michael.brown@hotmail.com', '89 Harbor Rd, Baltimore MD', 'Amex **** 9910'),
('Emily', 'Johnson', '4435552233', 'emily.johnson@gmail.com', '12 Chase St, Towson MD', 'Visa **** 0081');


INSERT INTO Store (Location) VALUES
('Baltimore Downtown'),
('Towson Center'),
('Glen Burnie Branch');


INSERT INTO Employee (Name, Role, Store_ID, Phone) VALUES
('Robert King', 'Store Manager', 1, '4105559001'),
('Sarah Lee', 'Supervisor', 1, '4105559002'),
('David Chen', 'Front Desk', 1, '4105559003'),
('Maria Gomez', 'Cleaner', 1, '4105559004'),
('Jason White', 'Delivery Driver', 1, '4105559005'),

('Anita Patel', 'Store Manager', 2, '4435557001'),
('Liam Davis', 'Supervisor', 2, '4435557002'),
('Karen Hill', 'Tailor', 2, '4435557003'),
('Kevin Clark', 'Presser', 2, '4435557004'),
('Ravi Kapoor', 'Delivery Driver', 2, '4435557005');


UPDATE Store 
SET Manager_ID = 1, Supervisor_ID = 2
WHERE Store_ID = 1;

UPDATE Store 
SET Manager_ID = 6, Supervisor_ID = 7
WHERE Store_ID = 2;


INSERT INTO Van (Store_ID, PlateNumber, Model, Year, Capacity) VALUES
(1, 'MD12345', 'Ford Transit', 2020, 500),
(1, 'MD98765', 'Mercedes Sprinter', 2022, 650),
(2, 'MD44556', 'Ram ProMaster', 2021, 600);

INSERT INTO Orders (Customer_ID, OrderDate, DueDate, TotalPieces, Status, TotalAmount) VALUES
(1, '2025-01-10', '2025-01-12', 5, 'Received', 45.00),
(2, '2025-01-11', '2025-01-14', 8, 'Processing', 78.00),
(3, '2025-01-12', '2025-01-15', 3, 'Ready', 25.00),
(4, '2025-01-13', '2025-01-16', 10, 'Delivered', 120.00);

INSERT INTO Item (Order_ID, Type, Brand, Color, Material, Pattern, SpecialRequest, Barcode) VALUES
-- Order 1
(1, 'Shirt', 'Ralph Lauren', 'Blue', 'Cotton', 'Solid', NULL, 'BC10001'),
(1, 'Pants', 'Levis', 'Black', 'Denim', 'None', 'No starch', 'BC10002'),

-- Order 2
(2, 'Dress', 'Zara', 'Red', 'Polyester', 'Floral', NULL, 'BC20001'),
(2, 'Coat', 'North Face', 'Grey', 'Wool', 'None', NULL, 'BC20002'),
(2, 'Scarf', 'H&M', 'White', 'Wool', 'Striped', NULL, 'BC20003'),

-- Order 3
(3, 'Jacket', 'Gap', 'Brown', 'Leather', 'None', NULL, 'BC30001'),

-- Order 4
(4, 'Shirt', 'Uniqlo', 'White', 'Cotton', 'Solid', 'Extra soft press', 'BC40001'),
(4, 'Pants', 'Dockers', 'Navy', 'Cotton', 'None', NULL, 'BC40002'),
(4, 'Blouse', 'Banana Republic', 'Pink', 'Silk', 'Solid', NULL, 'BC40003');


INSERT INTO CorporateClient (Company_Name, Contact_Person, Discount_Rate) VALUES
('Amazon Fulfillment Center', 'Mark Reynolds', 12.5),
('Johns Hopkins Hospital', 'Lisa Gregory', 10.0),
('Baltimore Law Group', 'Jennifer Stone', 8.0);

