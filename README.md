# Banking System - README

## Overview
This is a simplified Java-based banking system that allows users to create new accounts, log in, perform various banking operations (such as checking balance, depositing, withdrawing, and transferring funds), and manage their account details. The system also supports account deactivation/reactivation and maintains transaction history.

The program consists of three main classes:
1. **`Bank`**: Manages all the accounts and provides methods for performing banking operations.
2. **`Account`**: Represents an individual bank account with attributes such as account number, balance, account holder name, and more.
3. **`Banking`**: The main class that serves as the entry point for the application and interacts with the user through a menu-driven interface.

---

## Features
### 1. Account Creation
- Users can create a new account by providing personal details such as name, gender, date of birth, address, phone number, email, and account type (Savings/Current).
- A unique account number and random CVV are generated for each account.
- Users set a 4-digit PIN during account creation.

### 2. Login and Logout
- Users can log in using their account number and verify their identity with a PIN and CVV.
- Once logged in, users can access various banking operations.
- Users can log out at any time.

### 3. Banking Operations
- **Get Account Details**: View all personal and account-related information.
- **Check Balance**: Check the current balance of the account.
- **Deposit**: Add funds to the account.
- **Withdraw**: Withdraw funds from the account (if sufficient balance is available).
- **Transfer**: Transfer funds to another account within the system.
- **Transaction History**: View a detailed history of all transactions performed on the account.

### 4. Account Management
- **Deactivate Account**: Temporarily deactivate the account.
- **Reactivate Account**: Reactivate a deactivated account.

### 5. Security
- Users must verify their identity using a 4-digit PIN and a 3-digit CVV before performing sensitive operations.
- Limited attempts are allowed for PIN and CVV verification to prevent brute-force attacks.

---

## How to Run the Program
### Prerequisites
- Java Development Kit (JDK) installed on your system.
- A text editor or IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).

### Steps
1. **Compile the Code**:
   - Save the files (`Bank.java`, `Account.java`, and `Banking.java`) in a directory named `Banking2`.
   - Open a terminal or command prompt and navigate to the directory containing the files.
   - Compile the code using the following command:
     ```bash
     javac Banking2/*.java
     ```

2. **Run the Program**:
   - Execute the program using the following command:
     ```bash
     java Banking2.Banking
     ```

3. **Follow the On-Screen Instructions**:
   - Use the menu options to interact with the banking system.
   - Follow the prompts to create an account, log in, and perform banking operations.

---

## Code Structure
### Classes and Their Responsibilities
#### 1. `Bank`
- Manages a list of accounts (`ArrayList<Account>`).
- Provides methods for creating new accounts, logging in, logging out, and performing banking operations.
- Implements security features such as PIN and CVV verification.

#### 2. `Account`
- Represents an individual bank account with attributes like account number, balance, account holder name, and more.
- Maintains transaction history using a `StringBuilder`.
- Provides getter and setter methods for accessing and modifying account details.

#### 3. `Banking`
- Serves as the entry point for the application.
- Displays a menu-driven interface to the user.
- Calls methods from the `Bank` class to perform operations based on user input.

---

## Key Methods in the Code
### In `Bank` Class
- **`createNewAccount()`**: Guides the user through the process of creating a new account.
- **`logIn()`**: Allows the user to log in using their account number and verifies their identity with PIN and CVV.
- **`accountFinder()`**: Searches for an account using binary search (assumes accounts are sorted by account number).
- **`verify()`**: Verifies the user's PIN and CVV.
- **`getAccountDetails()`**: Displays all details of the logged-in account.
- **`checkBalance()`**: Displays the current balance of the logged-in account.
- **`deposit()`**: Adds funds to the logged-in account.
- **`withdraw()`**: Withdraws funds from the logged-in account.
- **`transfer()`**: Transfers funds from the logged-in account to another account.
- **`deActivateAccount()`**: Deactivates the logged-in account.
- **`reactivateAccount()`**: Reactivates a deactivated account.

### In `Account` Class
- **`updateTransactionHistory()`**: Updates the transaction history with timestamps.
- **`isActive()`**: Checks if the account is active.
- **`setActive()`**: Activates or deactivates the account.

---

## Example Usage
### Creating a New Account
1. Select option `2` from the main menu.
2. Provide the required details when prompted.
3. Note down the generated account number, CVV, and PIN for future reference.

### Logging In
1. Select option `1` from the main menu.
2. Enter your account number.
3. Verify your identity by entering the correct PIN and CVV.

### Performing Operations
- After logging in, use the menu options to check balance, deposit, withdraw, transfer funds, view transaction history, or manage your account.

---

## Notes
- The program uses a simple in-memory storage mechanism (`ArrayList<Account>`) to store account data. This means all data will be lost when the program terminates.
- For a production-grade system, consider integrating a database for persistent storage.
- The binary search in `accountFinder()` assumes that accounts are sorted by account number. Ensure this assumption holds true if you modify the code.

---
