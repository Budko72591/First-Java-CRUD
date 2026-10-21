# First-Java-CRUD

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-6DB33F?logo=springboot&logoColor=white)
![Vaadin](https://img.shields.io/badge/Vaadin-24.6-00B4F0?logo=vaadin&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/DB-H2%20in--memory-1021FF)
![License](https://img.shields.io/badge/license-educational-lightgrey)

A simple CRUD web application in Java, built by following the
[Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/) guide.
The main goal of this project is to practise **Java, Git, GitHub, Pull Requests and collaboration**.

## Features

| Operation | How |
|-----------|-----|
| **Create** | `New customer` button, fill in the form, `Save` |
| **Read**   | Grid with all customers, filter by last name |
| **Update** | Click a row, edit the fields, `Save` |
| **Delete** | Click a row, `Delete` |

## Tech stack

- Java 21
- Spring Boot 3.4 (Web, Data JPA)
- Vaadin Flow 24 (UI written in pure Java)
- H2 in-memory database
- Maven

## Project structure

```text
First-Java-CRUD
├── doc/                          # screenshots documenting the work
├── src
│   ├── main
│   │   ├── java/com/example/crud
│   │   │   ├── Application.java        # entry point + sample data
│   │   │   ├── Customer.java           # JPA entity
│   │   │   ├── CustomerRepository.java # Spring Data repository
│   │   │   ├── CustomerEditor.java     # form: create / update / delete
│   │   │   └── MainView.java           # grid + filter + "New customer"
│   │   └── resources/application.properties
│   └── test/java/com/example/crud/CustomerRepositoryTest.java
└── pom.xml
```

## Getting started

**Requirements:** JDK 17+ (21 recommended), Maven 3.9+ (or use your IDE), Node.js is downloaded automatically by Vaadin on first run.

```bash
git clone https://github.com/Budko72591/First-Java-CRUD.git
cd First-Java-CRUD
mvn spring-boot:run
```

The first start takes a few minutes because Vaadin downloads and builds the frontend.
When the console shows something like `Started Application in ... seconds`, open:

**http://localhost:8080**

Run the tests:

```bash
mvn test
```

## Code examples

The entity:

```java
@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    // constructors, getters, setters
}
```

The repository, with a derived query used by the filter field:

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
```

The main view:

```java
@Route
public class MainView extends VerticalLayout {
    // Grid<Customer> + TextField filter + "New customer" button + CustomerEditor
}
```

## Collaboration (Git / GitHub workflow)

1. Fork your partner's repository.
2. Create a branch: `git checkout -b feature/my-change`.
3. Make a small change (text, field, button, docs…), then commit and push.
4. Open a Pull Request to the partner's repository.
5. The repository owner reviews and merges it.
6. The commit history now contains commits from both students.

## Documentation / screenshots

All screenshots are in the [`doc`](doc) directory:

- application running locally
- CRUD functionality examples
- application startup logs
- GitHub commit history with both students' commits

## Author

Student project, Vistula University, Computer Science (Engineering of Artificial Intelligence).
