# File Hider Application

Welcome to the **File Hider** project! This Java console application provides a simple way to hide and manage files securely. It uses core Java concepts and JDBC for database integration to enhance file privacy and organization.

## Features

- **File Hiding:** Move files to a designated hidden directory to keep them out of plain sight.
- **Database Integration:** Store and manage file metadata in a MySQL database.
- **Console Interface:** Interact with the application through the command line.

## Getting Started

Follow these steps to set up and run the File Hider application:

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Database
- JDBC Driver for MySQL

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/file-hider.git

  ## Usage

- **To hide a file:**  
  Run the application and provide the full path of the file when prompted. The file will be moved to the `hidden_files` directory, and its metadata will be saved in the database.

- **To unhide a file:**  
  Run the application and specify the name of the file you want to restore. The file will be retrieved from the `hidden_files` directory and returned to its original location
  
## Contributing

- Feel free to fork the repository, make improvements, and submit pull requests. If you have suggestions or encounter issues, please open an issue to discuss them.

## License

- This project does not have a formal license. You are free to use, modify, and distribute the code as you see fit.

## Acknowledgements

- [MySQL JDBC Driver](https://dev.mysql.com/downloads/connector/j/)
- [Java Documentation](https://docs.oracle.com/javase/8/docs/)




   




