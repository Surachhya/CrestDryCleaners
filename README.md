# Crest Dry Cleaners

This project is a desktop management system for a dry‑cleaning business, built with **Java Swing** and **MySQL**.

It manages:
- Customers
- Orders
- Clothing items
- Employees
- Vans
- Store locations

The UI uses a **card‑based layout** with reusable components, consistent styling, and a left‑side navigation menu.

Data is stored in a MySQL database with a clear schema and proper foreign‑key relationships.  
Each module (customers, vans, orders, etc.) has its own service class that handles database operations using **JDBC**.

The reporting module includes several auto‑generated reports:
- Total sales
- Customer list
- Vans per store
- Employees per store
- Orders by status

Each report uses a reusable header and a **print‑ready layout**.  
A global print button allows printing any report, and a preview window shows what the report will look like before printing.

## Dev Info:

1. General tech requirement
---------------------------
- Java Swing for UI
- MySQL for database
- JDBC for connectivity
- Recommended IDE: IntelliJ IDEA as IDE
- Architecture: MVC-style (Models, Services, UI)


2. Folder Structures
```bash
CrestDryCleaners/
├── pom.xml
├── README.md
├── sql/
│   └── create_and_inserts.sql
└── src/
    └── main/
        ├── java/
        │   ├── app/
        │   │   └── Main.java
        │   │
        │   ├── models/
        │   │   ├── CorporateClient.java
        │   │   ├── Customer.java
        │   │   ├── Employee.java
        │   │   ├── Item.java
        │   │   ├── Order.java
        │   │   ├── Store.java
        │   │   └── Van.java
        │   │
        │   ├── services/
        │   │   ├── CorporateClientService.java
        │   │   ├── CustomerService.java
        │   │   ├── EmployeeService.java
        │   │   ├── ItemService.java
        │   │   ├── OrderService.java
        │   │   ├── ReportService.java
        │   │   ├── StoreService.java
        │   │   └── VanService.java
        │   │
        │   ├── ui/
        │   │   ├── home/
        │   │   │   ├── MainFrame.java
        │   │   │   ├── MenuPanel.java
        │   │   │   ├── StartupPanel.java
        │   │   │   └── TopBarPanel.java
        │   │   │
        │   │   ├── corporateclient/
        │   │   │   ├── AddCorporateClientDialog.java
        │   │   │   ├── CorporateClientListPanel.java
        │   │   │   └── EditCorporateClientDialog.java
        │   │   │
        │   │   ├── customer/
        │   │   │   ├── AddCustomerDialog.java
        │   │   │   ├── CustomerListPanel.java
        │   │   │   └── EditCustomerDialog.java
        │   │   │
        │   │   ├── employee/
        │   │   │   ├── EmployeeAddPanel.java
        │   │   │   ├── EmployeeEditPanel.java
        │   │   │   └── EmployeeListPanel.java
        │   │   │
        │   │   ├── item/
        │   │   │   ├── AddItemDialog.java
        │   │   │   ├── EditItemDialog.java
        │   │   │   └── ItemListPanel.java
        │   │   │
        │   │   ├── order/
        │   │   │   ├── OrderAddPanel.java
        │   │   │   ├── OrderEditPanel.java
        │   │   │   └── OrderListPanel.java
        │   │   │
        │   │   ├── store/
        │   │   │   ├── AddStoreDialog.java
        │   │   │   ├── EditStoreDialog.java
        │   │   │   └── StoreListPanel.java
        │   │   │
        │   │   ├── van/
        │   │   │   ├── AddVanDialog.java
        │   │   │   ├── EditVanDialog.java
        │   │   │   └── VanListPanel.java
        │   │   │
        │   │   └── reports/
        │   │       ├── ReportHeaderPanel.java
        │   │       ├── ReportPrinter.java
        │   │       ├── ReportsPage.java
        │   │       └── documents/
        │   │           ├── ReportCustomersPanel.java
        │   │           ├── ReportEmployeesPanel.java
        │   │           ├── ReportStatusPanel.java
        │   │           ├── ReportTotalSalesPanel.java
        │   │           └── ReportVansPanel.java
        │   │
        │   └── utils/
        │       └── DBConnection.java
        │
        └── resources/


```

3. Contributor Note:

Clone the repo, connect university vpn, run the scripts in `sql/`, then build and run project.




