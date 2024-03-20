/*-- Parent Tables
CREATE TABLE Customer (
                          customer_id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100),
                          email VARCHAR(100),
                          phone_number VARCHAR(20)
);

CREATE TABLE Location (
                          location_id INT AUTO_INCREMENT PRIMARY KEY,
                          city VARCHAR(50),
                          country VARCHAR(50)
);

CREATE TABLE Warehouse (
                           warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
                           location_id INT,
                           FOREIGN KEY (location_id) REFERENCES Location(location_id)
);

CREATE TABLE Carrier (
                         carrier_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100),
                         email VARCHAR(100),
                         phone_number VARCHAR(20)
);

CREATE TABLE Employee (
                          employee_id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100),
                          email VARCHAR(100),
                          phone_number VARCHAR(20),
                          warehouse_id INT,
                          FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id)
);

-- Tables with Foreign Keys
CREATE TABLE Shipment (
                          shipment_id INT AUTO_INCREMENT PRIMARY KEY,
                          tracking_number VARCHAR(50) NOT NULL,
                          status VARCHAR(50),
                          estimated_delivery DATE,
                          delivered_at DATE,
                          customer_id INT,
                          from_location_id INT,
                          to_location_id INT,
                          FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
                          FOREIGN KEY (from_location_id) REFERENCES Location(location_id),
                          FOREIGN KEY (to_location_id) REFERENCES Location(location_id)
);

CREATE TABLE Package (
                         package_id INT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT,
                         weight DECIMAL,
                         value DECIMAL,
                         shipment_id INT,
                         from_warehouse_id INT,
                         to_warehouse_id INT,
                         FOREIGN KEY (shipment_id) REFERENCES Shipment(shipment_id),
                         FOREIGN KEY (from_warehouse_id) REFERENCES Warehouse(warehouse_id),
                         FOREIGN KEY (to_warehouse_id) REFERENCES Warehouse(warehouse_id)
);

CREATE TABLE PackageStatus (
                               status_id INT AUTO_INCREMENT PRIMARY KEY,
                               description TEXT
);
*/