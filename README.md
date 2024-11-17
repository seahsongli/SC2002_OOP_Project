# SC2002_OOP_Project
This project is a Hospital Management System implemented in Java, designed as part of the SC2002 Object-Oriented Programming course. It utilizes Maven for dependency management and supports features like appointment scheduling, patient records management, and authentication.

## Features
- Manage patient, doctor, and staff records.
- Schedule appointments with doctors and manage appointment outcomes.
- Store consultation notes and prescriptions.
- Secure user authentication using bcrypt for password hashing.

## Prerequisites

Before running this project, ensure you have the following installed on your system:

1. **Java Development Kit (JDK)**  
   Download and install the latest version of JDK from [Oracle's Java Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/).

2. **Apache Maven**  
   Maven is used for dependency management and building the project. Follow these steps to install Maven:

   - Download Maven from [Apache Maven Downloads](https://maven.apache.org/download.cgi).
   - Extract the archive to a directory of your choice.
   - Add Maven's `bin` directory to your system's `PATH` environment variable.
   - Verify the installation by running:
     ```bash
     mvn -version
     ```
     This should print Maven's version and Java details.

## Dependencies

This project requires the **bcrypt library** for secure password hashing. Maven will automatically handle the dependency. However, if manual setup is needed, ensure the following dependency is added to the `pom.xml` file:

```xml
<dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
</dependency>

<dependency>
        <groupId>org.mindrot</groupId>
        <artifactId>jbcrypt</artifactId>
        <version>0.4</version>
</dependency>

<dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.3</version>
</dependency>

<dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
</dependency>

<dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml-schemas</artifactId>
        <version>4.1.2</version>
</dependency>
        <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.4</version>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-io</artifactId>
    <version>1.3.2</version>
</dependency>

<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.13.0</version> 
</dependency>
```

To download and set up dependencies, run the following Maven command in the project directory:

```bash
mvn run install
```

## How to run
1) Clone this repository to your local machine:
```bash
git clone https://github.com/yourusername/SC2002_OOP_Project.git
cd SC2002_OOP_Project
```

2) Ensure all dependencies are installed:
```bash
mvn clean install
```

3) Run the project using Maven:
```bash
mvn exec:java -Dexec.mainClass="Main"
```
