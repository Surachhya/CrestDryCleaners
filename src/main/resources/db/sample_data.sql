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

UPDATE Store SET Manager_ID = 1, Supervisor_ID = 2 WHERE Store_ID = 1;
UPDATE Store SET Manager_ID = 6, Supervisor_ID = 7 WHERE Store_ID = 2;

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
(1, 'Shirt', 'Ralph Lauren', 'Blue', 'Cotton', 'Solid', NULL, 'BC10001'),
(1, 'Pants', 'Levis', 'Black', 'Denim', 'None', 'No starch', 'BC10002'),

(2, 'Dress', 'Zara', 'Red', 'Polyester', 'Floral', NULL, 'BC20001'),
(2, 'Coat', 'North Face', 'Grey', 'Wool', 'None', NULL, 'BC20002'),
(2, 'Scarf', 'H&M', 'White', 'Wool', 'Striped', NULL, 'BC20003'),

(3, 'Jacket', 'Gap', 'Brown', 'Leather', 'None', NULL, 'BC30001'),

(4, 'Shirt', 'Uniqlo', 'White', 'Cotton', 'Solid', 'Extra soft press', 'BC40001'),
(4, 'Pants', 'Dockers', 'Navy', 'Cotton', 'None', NULL, 'BC40002'),
(4, 'Blouse', 'Banana Republic', 'Pink', 'Silk', 'Solid', NULL, 'BC40003');

INSERT INTO CorporateClient (Company_Name, Contact_Person, Discount_Rate) VALUES
('Amazon Fulfillment Center', 'Mark Reynolds', 12.5),
('Johns Hopkins Hospital', 'Lisa Gregory', 10.0),
('Baltimore Law Group', 'Jennifer Stone', 8.0);