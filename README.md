[![Stories in Ready](https://badge.waffle.io/mavaze/checkout-counter.png?label=ready&title=Ready)](https://waffle.io/mavaze/checkout-counter?utm_source=badge)
### Checkout Counter

Status: In-progress (Prototype stage)

Uses Spring boot, JPA with Hibernate, Embedded H2 database, Embedded Tomcat

1. Demonstrates basic rest api design (Note: Not all APIs are implemented, however created from demonstration perspective.).
2. Demonstrates junit tests with mockito using spring's advanced test features. (Note: tests are for demonstration purpose only and were not written from coverage perspective.)
3. Uses 'AuditorAware' to insert the user in invoice record while being updated for auditing purpose.
4. Uses locale while throwing error message following any business exception. (Append '?lang=fr|de' to your URL)
5. Uses Spring Security to protect APIs both at URI level and at method level.

### How to run the application

```sh
$ mvn clean install
$ java -jar target/checkout-counter-0.0.1-SNAPSHOT.jar
```
OR
```sh
$ mvn spring-boot:run [-Dspring.profiles.active=dev]
```

### Users and roles

Uses basic auth for authentication

Username | Password | Role
-------- | -------- | ----
admin | admin | ADMIN
inventory | inventory | INVENTORY_MANAGER
sales | sales | SALES_MANAGER
cashier | cashier | CASHIER

> Please refer to src/main/resources/data.sql

* **Create Product**: *POST* [/products](http://localhost:8080/products)
* **List Products**: *GET* [/products](http://localhost:8080/products)
* **Search Products with criteria**: *GET* [/products/search?barcode=123&categories=C1,C2](http://localhost:8080/products/search?barcode=123&categories=C1,C2)
* **Get Product by Id**: *GET* [/products/{productId}](http://localhost:8080/products/{productId})

* **Create Category**: *POST* [/categories](http://localhost:8080/categories)
* ~~**List Categories**~~: *GET* [/categories](http://localhost:8080/categories)
* ~~**Get Category by Id**~~: *GET* [/categories/{categoryId}](http://localhost:8080/categories/{categoryId})
* ~~**Update Category**~~: *PUT* [/categories/{categoryId}](http://localhost:8080/categories/{categoryId})
* ~~**List products assigned to a Category**~~: *GET* [/categories/{categoryId}/products](http://localhost:8080/categories/{categoryId}/products)
* **Assign/Unassign to/from a Category**: *POST* [/categories/{categoryId}/products?action=assign|unassign](http://localhost:8080/categories/{categoryId}/products?action=assign|unassign)

* **Generate Invoice (and save)**: *POST* [/invoices?save=true](http://localhost:8080/invoices?save=true)
* **Get Invoice by InvoiceNo**: *GET* [/invoices/{invoiceNo}](http://localhost:8080/invoices/{invoiceNo})

----------
** Powered by Open Source initiative **
