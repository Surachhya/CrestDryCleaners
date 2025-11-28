# Crest Dry Cleaners

1. TECH
---------------------------
- Java Swing for UI
- MySQL for database
- JDBC for connectivity
- Recommended IDE: IntelliJ IDEA as IDE
- Architecture: MVC-style (Models, Services, UI)


2. Folder Stuructres
```bash
CrestDryCleaners/
│
├── sql/
│   └── create_and_inserts.sql # DB scripts, run this one time at first. 
│
├── src/
│   ├── app/
│   │   ├── Main.java
│   │   └── MainFrame.java
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
│   │   ├── StoreService.java
│   │   └── VanService.java
│   │
│   ├── ui/
│   │   ├── MenuPanel.java
│   │   ├── OrderPanel.java
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
│   │   └── van/
│   │       ├── AddVanDialog.java
│   │       ├── EditVanDialog.java
│   │       └── VanListPanel.java
│   │
│   └── utils/
│       └── DBConnection.java
│
├── resources/
│
├── .gitignore
├── README.md
└── CrestDryCleaners.iml

```

3. Dev Note:

Clone the repo, connect university vpn, run the scripts in `sql/`, then build and run project.




