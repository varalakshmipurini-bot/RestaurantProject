# 🍽️ Restaurant Management System (Java + JDBC + PostgreSQL)

## 📌 Project Overview
Restaurant Management System is a **console-based Java application** developed using **Core Java and JDBC** with **PostgreSQL** as the database.  
The application helps manage restaurant operations such as **admin login, order management, order cancellation, and refund processing** through a menu-driven interface.

This project follows a **layered architecture** and is suitable for learning **JDBC, DAO pattern, and database-driven applications**.

---

## 🚀 Features
- 🔐 Admin Login Authentication
- 📝 Place New Orders
- 📋 View All Orders
- ✏️ Update Order Details
- 🔄 Update Order Status
- ❌ Cancel Orders
- 🗑️ Delete Orders
- 💰 Partial Refund Processing
- 👀 View Refund Orders
- 🖥️ Menu-driven Console Application

---

## 🛠️ Technologies Used
- **Java (JDK 21)**
- **JDBC**
- **PostgreSQL**
- **Eclipse IDE**

---

## 🧱 Project Structure
RestaurantProject
│
├── src
│ ├── controller
│ │ └── OrderController.java
│ │
│ ├── dao
│ │ ├── AdminDAO.java
│ │ └── OrderDAO.java
│ │
│ ├── model
│ │ ├── Admin.java
│ │ └── Order.java
│ │
│ ├── util
│ │ └── DBUtil.java
│ │
│ ├── view
│ │ ├── OrderView.java
│ │ └── RestaurantProject.java
│
├── module-info.java
└── README.md


restaurantdb


### Tables Used
- `admin`
- `orders`
- `refund_orders`

---

## 🧾 Table Structure

### 🔐 admin table

(Only username & password)

CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

📝 orders table

(All order + refund-related columns)

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    subtotal DOUBLE PRECISION,
    gst DOUBLE PRECISION,
    discount DOUBLE PRECISION,
    service_charge DOUBLE PRECISION,
    final_amount DOUBLE PRECISION,
    status VARCHAR(30),

    refund_amount DOUBLE PRECISION,
    refund_status VARCHAR(30),
    refund_type VARCHAR(30)
);
);

💰 refund_orders table

(Refund history table)

CREATE TABLE refund_orders (
    refund_id SERIAL PRIMARY KEY,
    order_id INT,
    item_name VARCHAR(100),
    quantity INT,
    price DOUBLE PRECISION,
    subtotal DOUBLE PRECISION,
    gst DOUBLE PRECISION,
    discount DOUBLE PRECISION,
    service_charge DOUBLE PRECISION,
    final_amount DOUBLE PRECISION,
    status VARCHAR(30),

    refund_amount DOUBLE PRECISION,
    refund_status VARCHAR(30),

    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
        REFERENCES orders(order_id)
        ON DELETE CASCADE
);

✅ Optional: Insert sample admin
INSERT INTO admin (username, password)
VALUES ('admin', 'admin123');


⚙️ Database Configuration

Update PostgreSQL connection details in:

src/util/DBUtil.java


Example:

String url = "jdbc:postgresql://localhost:5432/testdb";
String user = "postgres";
String password = "your_password";

▶️ How to Run the Project

1.Clone the repository

git clone https://github.com/varalakshmipurini-bot/RestaurantProject.git


2.Open the project in Eclipse IDE
3.Add PostgreSQL JDBC Driver to the project
4.Configure database details in DBUtil.java
5.Run the application:

OrderController.java

🔐 Admin Login (Sample)
Username: admin
Password: admin123

📚 Learning Outcomes

.JDBC with PostgreSQL
.DAO Design Pattern
.CRUD operations
.Menu-driven console application
.Database connectivity in Java
.Clean project structure

✅ Why this design is good (Interview point ⭐)

orders → main order + refund tracking

refund_orders → keeps refund history

Foreign key ensures data integrity

SERIAL used for auto-increment

PostgreSQL compatible (DOUBLE PRECISION)

👩‍💻 Author

Varalakshmi Purini
Java Full Stack Trainee
