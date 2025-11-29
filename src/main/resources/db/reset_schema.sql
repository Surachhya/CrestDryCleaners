USE sadhika4db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Van;
DROP TABLE IF EXISTS Employee;

DROP TABLE IF EXISTS Store;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS CorporateClient;

SET FOREIGN_KEY_CHECKS = 1;

-- CREATE TABLE Customer
CREATE TABLE Customer (
    Customer_ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    FName       VARCHAR(50) NOT NULL,
    LName       VARCHAR(50) NOT NULL,
    Phone       VARCHAR(20) UNIQUE,
    Email       VARCHAR(100) UNIQUE,
    Address     VARCHAR(255),
    PaymentInfo VARCHAR(100)
);

-- CREATE TABLE Store
CREATE TABLE Store (
    Store_ID      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Location      VARCHAR(150) NOT NULL,
    Manager_ID    INT UNSIGNED,
    Supervisor_ID INT UNSIGNED
);

-- CREATE TABLE Employee
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

-- Add constraints to Store
ALTER TABLE Store
ADD CONSTRAINT fk_store_manager
    FOREIGN KEY (Manager_ID) REFERENCES Employee(Employee_ID)
    ON UPDATE CASCADE ON DELETE SET NULL,
ADD CONSTRAINT fk_store_supervisor
    FOREIGN KEY (Supervisor_ID) REFERENCES Employee(Employee_ID)
    ON UPDATE CASCADE ON DELETE SET NULL;

-- CREATE TABLE Orders
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

-- CREATE TABLE Item
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

-- CREATE TABLE Van
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

-- CREATE TABLE CorporateClient
CREATE TABLE CorporateClient (
    Corporate_ID    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Company_Name    VARCHAR(100) NOT NULL,
    Contact_Person  VARCHAR(100) NOT NULL,
    Discount_Rate   DECIMAL(5,2) DEFAULT 0.00
);