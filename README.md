# TechNexusHub

Welcome to **TechNexusHub**, your go-to destination for cutting-edge electronics and technology solutions. This project is designed to provide a comprehensive platform for tech enthusiasts and professionals, offering seamless user interaction and robust backend support.

---

## 📜 Recent Updates

### Commit Message: Initial Project Setup and HomeController Addition  
**Commit Details:**  
- **HomeController:** A new `HomeController` class has been added to handle requests for the home page. This controller is responsible for managing primary interactions on the main page of the application.  
- **Initial Project Setup:**  
  - `.gitignore`: Specifies files and directories to ignore in git operations.  
  - Maven Wrapper Files (`.mvn/`, `mvnw`, `mvnw.cmd`): Enables easy Maven build execution without installing Maven.  
  - `pom.xml`: Maven Project Object Model for dependency and build configuration.  
  - `TechNexusHubApplication.java`: Main application class that bootstraps the Spring Boot application.  
  - `src/main/resources/`: Contains configuration and static files.  
  - `src/test/`: Includes test classes to ensure the application functions correctly.

---

## 🏗️ Project Structure

Below is a detailed breakdown of the project's folder and file structure, along with their purposes:

```plaintext
src/
├── main/
│   ├── java/com/codeneeti/technexushub/
│   │   ├── config/                          # Application-wide configuration settings
│   │   │   └── ProjectConfig.java
│   │   ├── controllers/                     # Handles user requests and responses
│   │   │   ├── CartController.java          # Manages cart-related operations
│   │   │   ├── CategoryController.java      # Handles category CRUD operations
│   │   │   ├── HomeController.java          # Manages home page interactions
│   │   │   ├── OrderController.java         # Manages order processing
│   │   │   ├── ProductController.java       # Handles product-related operations
│   │   │   └── UserController.java          # Manages user-related actions
│   │   ├── dtos/                            # Data Transfer Objects for application layers
│   │   │   ├── AddItemToCartRequest.java    # DTO for adding items to the cart
│   │   │   ├── ApiResponseMessage.java      # DTO for standard API responses
│   │   │   ├── CartDto.java                 # Represents cart details
│   │   │   ├── ProductDto.java              # Represents product information
│   │   │   └── UserDto.java                 # Represents user information
│   │   ├── entities/                        # Database entities (models)
│   │   │   ├── Cart.java                    # Represents the cart entity
│   │   │   ├── Order.java                   # Represents the order entity
│   │   │   └── Product.java                 # Represents the product entity
│   │   ├── exceptions/                      # Custom exceptions and handlers
│   │   │   ├── GlobalExceptionHandler.java  # Handles global application exceptions
│   │   │   └── ResourceNotFoundException.java # Handles not-found errors
│   │   ├── helper/                          # Utility classes
│   │   │   └── Helper.java
│   │   ├── repositories/                    # Interfaces for data persistence
│   │   │   ├── CartRepository.java
│   │   │   ├── ProductRepository.java
│   │   │   └── UserRepository.java
│   │   ├── services/                        # Business logic layer
│   │   │   ├── impl/                        # Service implementations
│   │   │   │   ├── CartServiceImpl.java
│   │   │   │   └── ProductServiceImpl.java
│   │   │   ├── CartService.java             # Defines cart-related business logic
│   │   │   └── UserService.java             # Manages user-related operations
│   │   ├── validate/                        # Custom validation logic
│   │   │   ├── ImageNameValidator.java
│   │   │   └── ImageNameValid.java
│   │   └── TechNexusHubApplication.java     # Main Spring Boot application entry point
│   ├── resources/                           # Configuration files
│   │   └── application.properties           # Application properties and settings
│   └── ProjectRelatedNotes/                 # Documentation and notes
│       ├── Assignment
│       ├── cartModule
│       ├── userControllerNotes
└── test/                                    # Test directory
    └── java/com/codeneeti/technexushub/
        └── TechNexusHubApplicationTests.java # Test cases for the application
```

---

## 🔄 How It Works

### 1. **Request Flow**
   - The user sends a request (e.g., viewing a product or placing an order).
   - The request is routed through a **Controller**, which handles business logic with the help of **Services** and interacts with **Repositories** for database operations.

### 2. **Business Logic**
   - Services handle the application's core logic.
   - Services fetch and process data using **Repositories** and return processed data to the **Controller**.

### 3. **Database Interaction**
   - Entities map to database tables.
   - Repositories use **Spring Data JPA** to interact with the database for CRUD operations.

### 4. **Error Handling**
   - Exceptions are managed globally through the `GlobalExceptionHandler`, ensuring consistent error responses across the application.

### 5. **Custom Features**
   - **Custom Validation:** Validators in the `validate` package enforce business rules (e.g., image name restrictions).  
   - **Data Transfer Objects:** DTOs streamline data exchange between layers while maintaining abstraction.

---

## 🛠️ Getting Started

1. **Clone the Repository:**  
   ```bash
   git clone <repository_url>
   cd TechNexusHub
   ```

2. **Build the Project:**  
   Use the Maven wrapper to build the project:  
   ```bash
   ./mvnw clean install
   ```

3. **Run the Application:**  
   Start the Spring Boot application:  
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application:**  
   Open a browser and navigate to `http://localhost:8080`.

---

## 🚀 Features and Future Enhancements
- **Current Features:**  
  - User management, product management, cart management, and order processing.
- **Future Enhancements:**  
  - Add payment gateway integration.
  - Enhance product search with advanced filters.
  - Improve UI for seamless user experience.

Stay tuned for more updates as we continue to enhance TechNexusHub!  

--- 
