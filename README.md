# Quantity Measurement Application (Backend)

A robust Spring Boot REST API for measuring and converting physical quantities across various units. This backend supports complex arithmetic operations, precision-safe comparisons, and persistent operation history with JWT-based security.

## 🚀 Features

- **Unit Conversion**: Seamlessly convert between units within the same category (Length, Volume, Weight, Temperature).
- **Arithmetic Operations**: Perform Addition, Subtraction, and Division across different units (e.g., adding 1 Feet and 12 Inches).
- **Precision-Safe Comparison**: Compare quantities with a tolerance for floating-point inaccuracies.
- **Operation History**: Track every operation performed, categorized by user (requires authentication).
- **Security**: Stateless authentication using JWT (JSON Web Tokens).
- **Error Tracking**: Specialized endpoints to retrieve operations that resulted in errors.

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.2.5
- **Language**: Java 17
- **Security**: Spring Security with JWT (io.jsonwebtoken)
- **Database**: H2 (In-memory, for development)
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Utilities**: Lombok

## 🛣️ API Endpoints

### Authentication (Public)
- `POST /auth/register`: Create a new user account.
- `POST /auth/login`: Authenticate and receive a JWT token.

### Measurements (Public)
- `POST /api/v1/quantities/convert`: Convert a value to a target unit.
- `POST /api/v1/quantities/compare`: Compare two values (returns boolean).
- `POST /api/v1/quantities/add`: Add two values and return the result in a target unit.
- `POST /api/v1/quantities/subtract`: Subtract two values and return the result in a target unit.
- `POST /api/v1/quantities/divide`: Divide two values and return the result in a target unit.

### History & Analytics (Protected - Requires JWT)
- `GET /api/v1/quantities/history/type/{type}`: View history for a specific category (LENGTH, VOLUME, etc.).
- `GET /api/v1/quantities/history/operation/{operation}`: View history for a specific operation (ADD, CONVERT, etc.).
- `GET /api/v1/quantities/history/errored`: View all failed operations.
- `GET /api/v1/quantities/count/{operation}`: Get the total count of a specific operation.

## 📋 Supported Units

| Category | Units Supported |
| :--- | :--- |
| **Length** | INCH, FEET, YARD, MILE, CENTIMETER, METER, KILOMETER |
| **Volume** | LITRE, GALLON, MILLILITRE |
| **Weight** | GRAM, KILOGRAM, TONNE |
| **Temperature** | CELSIUS, FAHRENHEIT, KELVIN |

## ⚙️ Setup & Installation

1. **Clone the repository**:
   ```bash
   git clone <your-repo-url>
   ```

2. **Navigate to the project directory**:
   ```bash
   cd QuantityMeasurementApp
   ```

3. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

The server will start at `http://localhost:8080`.

## 🗄️ Database Console

The H2 database console is enabled for easy debugging:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:quantitydb`
- **User**: `root`
- **Password**: (leave blank)

## 🔐 Security Configuration

The application uses a stateless JWT security model.
1. Register/Login to get the token.
2. Include the token in the header of protected requests:
   `Authorization: Bearer <your_jwt_token>`

CORS is configured to allow all origins (`*`) for frontend integration.
