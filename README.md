
# GitHub API Service

## Overview

This project is a Spring Boot-based microservice that interacts with the GitHub API. It provides RESTful endpoints to retrieve information about GitHub repositories and branches.

## Features

- Retrieve repository details from GitHub.
- Fetch branch information for a given repository.
- Handles errors and exceptions gracefully.

## Technologies Used

- **Java 21**: The project is built using the latest Java version.
- **Spring Boot**: Simplifies the development of RESTful web services.
- **Maven**: For dependency management and build automation.
- **GitHub API**: The microservice interacts with GitHub's REST API.

## Getting Started

### Prerequisites

- **Java 21**: Ensure you have JDK 21 installed.
- **Maven**: Install Maven for building the project.

### Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/YaroslavMykhalkiv/GitHubApi.git
   cd GitHubApiService
   ```

2. **Build the project**:

   Navigate to the project directory and run:

   ```bash
   mvn clean install
   ```

3. **Run the application**:

   After building, you can run the application using:

   ```bash
   mvn spring-boot:run
   ```

### Configuration

The project does not require additional configuration by default. However, you can customize it by modifying the `application.properties` file located in `src/main/resources/`.

### Usage

Once the application is running, you can interact with it using the following endpoints:

- **Get Repository Information**:
  - **Endpoint**: `/api/github/{username}`
  - **Method**: `GET`
  - **Description**: Retrieves information about a specific repository.
  - Response:

    - 200 OK - Returns a list of repositories with branch details.
    - 404 Not Found - Returns an error message if the GitHub user is not found.
    
    Example Success Response:

```bash
[
    {
        "name": "example-repo",
        "ownerLogin": "example-user",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "abc123def456ghi789jkl012mno345pqrs678tuv"
            }
        ]
    }
]
```
Example Error Response:

```bash
{
    "status": 404,
    "message": "Probably not existing username - Not Found"
}
```

### Error Handling

Custom error handling is implemented using `GitHubExceptionHandler.java`. Errors are returned in a structured JSON format with appropriate HTTP status codes.


## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or new features.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- The GitHub API documentation for providing detailed guidance on API usage.
- The Spring community for their extensive documentation and support.
