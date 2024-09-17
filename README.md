# Online Flight and Bus Ticket Sales System

## Project Overview

The **Online Flight and Bus Ticket Sales System** is a sophisticated platform designed to streamline the management and purchase of travel tickets. It provides a seamless experience for users to search for trips, book tickets, and manage their accounts. Administrators benefit from powerful tools for trip management, sales tracking, and notification handling.

## Completed Functionalities

### 1. User Management
- **Registration & Login**: Users can register and log in to access the system.
- **Email Notifications**: Automatic email notifications are sent upon user registration.
- **Role Management**: Administrators can assign and modify user roles.

### 2. Admin Functionality
- **Trip Management**: Admins can add new trips or cancel existing ones.
- **Sales Statistics**: Admins can view comprehensive statistics on ticket sales and revenue.

### 3. Trip Search and Booking
- **Search Functionality**: Users can search for trips based on city, transportation type (plane or bus), or date.
- **Ticket Purchase Limits**:
    - **Individual Users**: Maximum of 5 tickets per trip.
    - **Male Passengers**: Up to 2 male passengers per order.
    - **Corporate Users**: Maximum of 40 tickets per trip.

### 4. Notification System
- **Transactional Notifications**: Successful transactions trigger SMS and email notifications with ticket details.

### 5. Data Access
- **User Privacy**: Users have access only to their own information.

## Technical Implementations

### 1. Notification Services
- **Unified Service**: Handles SMS, email, and push notifications using the Strategy Pattern.
- **Database Operations**: Integrated with database operations, optional third-party service integrations.

### 2. Password Security
- **SHA-512 Hashing**: Passwords are hashed using the SHA-512 algorithm before storage.

### 3. Architecture
- **Microservices**: Built with a microservice architecture for enhanced modularity and scalability.
- **Performance Optimization**: Implemented to ensure efficient and smooth system performance.

### 4. System Assumptions
- **User Types**: Supports both individual and corporate users.
- **Notification Handling**: SMS, email, and push notifications are managed asynchronously.
- **Capacity Limits**:
    - **Plane**: 189 passengers.
    - **Bus**: 45 passengers.
- **Payment Methods**: Supports credit cards and bank transfers (EFT).
- **Payment Operations**: Synchronous processing.

## Technologies Used

- **Java 11**: The primary programming language used.
- **Spring Boot**: Framework utilized for application development.
- **JUnit 5**: Framework employed for unit testing.
- **RabbitMQ**: For managing asynchronous communication.
- **PostgreSQL**: Database for storing user data, ticket information, and trip details.
- **MongoDB**: Used for storing email information.
- **Redis**: Caching and data storage solution.
- **JWT (JSON Web Token)**: For user authentication and authorization.

## Additional Notes

- **Scalability**: Designed to accommodate high volumes of simultaneous users and transactions.
- **Error Handling**: Incorporates robust error handling and logging mechanisms.
- **API Documentation**: Swagger is used for API documentation to streamline development and integration.

---

**Contributions** and **Feedback**: Contributions are welcome! Please submit pull requests or open issues for feedback or improvements.

**License**: This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

