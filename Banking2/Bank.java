package Banking2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    ArrayList<Account> accounts = new ArrayList<>();
    private static int accountNumberCounter = 1000;
    Account currentLoginnedAccount = null;
   
    boolean isLoggedIn () {
        if (currentLoginnedAccount != null) {
            return true;
        }
        return false;
    }
    
    public void createNewAccount () {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        Account account = new Account();
        System.out.println("Provide the following details to create a new account:");
        System.out.println("Type \"Cancel\" to cancel the process at any time.");
        
        do {
            System.out.println("Enter your Full Name:");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            if (name.matches("[a-zA-Z\\s]+")) {
                account.setAccountHolderName(name);
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid name.");
            }
        } while (true);
        
        do {
            System.out.println("Enter your Gender");
            String gender = sc.nextLine();
            
            if (gender.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            
            if (gender.equalsIgnoreCase("male") || gender.charAt(0) == 'm') {
                account.setGender("Male");
                break;
            }
            else if (gender.equalsIgnoreCase("female") || gender.charAt(0) == 'f') {
                account.setGender("Female");
                break;
            }
            else {
                System.out.println("Invalid Gender, please enter \"Male\" or \"Female\".");
            }
        } while (true);
        
        do {
            System.out.println("Enter your Date of Birth (dd-mm-yyyy):");
            String dob = sc.nextLine();
            if (dob.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            if (dob.matches("\\d{2}-\\d{2}-\\d{4}")) {
                account.setDateOfBirth(dob);
                break;
            } else {
                System.out.println("Invalid date format. Please enter in dd-mm-yyyy format.");
            }
        } while (true);
        
        do {
            System.out.println("Enter your Address:");
            String address = sc.nextLine();
            if (address.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            account.setAddress(address);
            break;
        } while (true);
        
        do {
            System.out.println("Enter your Phone Number:");
            String phoneNumber = sc.nextLine();
            if (phoneNumber.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            if (phoneNumber.matches("\\d{10}")) {
                account.setPhoneNumber(phoneNumber);
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
            }
        } while (true);
        
        do {
            System.out.println("Enter your Email:");
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                account.setEmail(email);
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (true);
        
        do {
            System.out.println("Enter your Account Type (Savings/Current):");
            String accountType = sc.nextLine();
            if (accountType.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            if (accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("current")) {
                account.setAccountType(accountType);
                break;
            } else {
                System.out.println("Invalid account type. Please enter \"Savings\" or \"Current\".");
            }
        } while (true);
        
        do {
            System.out.println("Make your pin:");
            String pin = sc.nextLine();
            if (pin.equalsIgnoreCase("Cancel")) {
                System.out.println("Account creation cancelled.");
                return;
            }
            
            if (pin.matches("\\d{4}")) {
                account.setPin(Integer.parseInt(pin));
                break;
            } else {
                System.out.println("Invalid pin. Please enter a 4-digit number.");
            }
        } while (true);
        
        // Generate a random CVV
        account.setCvv(rd.nextInt(900) + 100);
        // Assign a unique account number
        account.setAccountNumber(String.valueOf(accountNumberCounter++));
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("CVV: " + account.getCvv());
        System.out.println("PIN: " + account.getPin());
        System.out.println("Remember these details for future reference.");
        
        account.setActive(true);
        accounts.add(account);
    }
    
    public void logIn () {
        currentLoginnedAccount = accountFinder();
        if (currentLoginnedAccount == null) {
            System.out.println("Login failed.");
            return;
        }
        System.out.println("Logged in successfully.");
    }
    
    public void logOut () {
        if (currentLoginnedAccount != null) {
            System.out.println("Logged out successfully.");
            currentLoginnedAccount = null;
        } else {
            System.out.println("No account is currently logged in.");
        }
    }
    
    Account accountFinder () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your account number:");
        String accountNumber = sc.nextLine();
        
        // Sort the accounts by account number for binary search
        
        int left = 0;
        int right = accounts.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = accounts.get(mid).getAccountNumber().compareTo(accountNumber);
            
            if (comparison == 0) {
                Account account = accounts.get(mid);
                System.out.println("Welcome " + account.getAccountHolderName() + "!");
                System.out.println("Before proceeding, please verify your pin.");
                if (!verify(account)) {
                    System.out.println("Pin verification failed.");
                    return null;
                }
                return accounts.get(mid); // Account found
            } else if (comparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        
        System.out.println("Account not found."); // Account not found
        return null;
    }
    
    private boolean verify(Account account) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pin:");
        int attempts = 0;
        while (attempts < 5) {
            String pin = sc.nextLine();
            
            if (pin.equalsIgnoreCase("Cancel")) {
                System.out.println("Operation cancelled.");
                return false;
            }
            
            if (pin.matches("\\d{4}")) {
                if (Integer.parseInt(pin) == account.getPin()) {
                    System.out.println("Pin verified successfully.");
                    break;
                } else {
                    System.out.println("Incorrect pin.");
                    System.out.println("Try again:");
                }
            } else {
                System.out.println("Invalid pin format. Please enter a 4-digit number.");
            }
            attempts++;
        }
        
        if (attempts == 5) {
            System.out.println("Too many attempts. Please try again later.");
            return false;
        }
        
        attempts = 0;
        
        while (attempts < 5) {
            System.out.println("Enter your CVV:");
            String cvv = sc.nextLine();
            
            if (cvv.equalsIgnoreCase("Cancel")) {
                System.out.println("Operation cancelled.");
                return false;
            }
            
            if (cvv.matches("\\d{3}")) {
                if (Integer.parseInt(cvv) == account.getCvv()) {
                    System.out.println("CVV verified successfully.");
                    return true;
                } else {
                    System.out.println("Incorrect CVV.");
                    System.out.println("Try again:");
                }
            } else {
                System.out.println("Invalid CVV format. Please enter a 3-digit number.");
            }
            attempts++;
        }
        
        if (attempts == 5) {
            System.out.println("Too many attempts. Please try again later.");
            return false;
        }
        return false; // Verification failed
    }
    
    private boolean verifyPin (Account account) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pin:");
        int attempts = 0;
        while (attempts < 5) {
            String pin = sc.nextLine();
            if (pin.equalsIgnoreCase("Cancel")) {
                System.out.println("Operation cancelled.");
                return false;
            }
            if (pin.matches("\\d{4}")) {
                if (Integer.parseInt(pin) == account.getPin()) {
                    System.out.println("Pin verified successfully.");
                    return true;
                } else {
                    System.out.println("Incorrect pin. Try again:");
                }
            } else {
                System.out.println("Invalid pin format. Please enter a 4-digit number.");
            }
            attempts++;
        }
        
        if (attempts == 5) {
            System.out.println("Too many attempts. Please try again later.");
            return false;
        }
        return false; // Verification failed
    }
    
    public void getAccountDetails () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        System.out.println("=========================================================");
        System.out.println("Account Holder Details:");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder Name: " + account.getAccountHolderName());
        System.out.println("Gender: " + account.getGender());
        System.out.println("Date of Birth: " + account.getDateOfBirth());
        System.out.println("Address: " + account.getAddress());
        System.out.println("Phone Number: " + account.getPhoneNumber());
        System.out.println("Email: " + account.getEmail());
        
        System.out.println("\nAccount Details:");
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("CVV: " + account.getCvv());
        System.out.println("PIN: " + account.getPin());
        System.out.println("Account Status: " + (account.isActive() ? "Active" : "Deactivated"));
        System.out.println("=========================================================");
    }
    
    public void checkBalance () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is deactivated. Please reactivate it to perform transactions.");
            return;
        }
        
        System.out.println("Balance: " + account.getBalance());
    }
    
    public void getTransactionHistory () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is deactivated. Please reactivate it to perform transactions.");
            return;
        }
        
        System.out.println("Transaction History:");
        System.out.println(account.getTransactionHistory());
        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions have been done with this account yet.");
        }
        System.out.println("Current Balance: " + account.getBalance());
    }
    
    public void deposit () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is deactivated. Please reactivate it to perform transactions.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        double amount;
        while (true) {
            System.out.println("Enter the amount to deposit:");
            String amountStr = sc.nextLine();
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        
        
        account.setBalance(account.getBalance() + amount);
        account.updateTransactionHistory("Deposited: " + amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }
    
    public void withdraw () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is deactivated. Please reactivate it to perform transactions.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        double amount;
        while (true) {
            System.out.println("Enter the amount to withdraw:");
            String amountStr = sc.nextLine();
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a positive number.");
                } else if (amount > account.getBalance()) {
                    System.out.println("Insufficient balance.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        
        
        
        
        
        account.setBalance(account.getBalance() - amount);
        account.updateTransactionHistory("Withdrawn: " + amount);
        System.out.println("Withdrawal successful. New balance: " + account.getBalance());
    }
    
    public void transfer () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is deactivated. Please reactivate it to perform transactions.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account number to transfer to:");
        String targetAccountNumber = sc.nextLine();
        Account targetAccount = null;
        
        int left = 0;
        int right = accounts.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = accounts.get(mid).getAccountNumber().compareTo(targetAccountNumber);
            
            if (comparison == 0) {
                targetAccount = accounts.get(mid);
                System.out.println("Enter the amount to transfer to " + targetAccount.getAccountHolderName() + ":");
                double amount;
                while (true) {
                    String amountStr = sc.nextLine();
                    try {
                        amount = Double.parseDouble(amountStr);
                        if (amount <= 0) {
                            System.out.println("Invalid amount. Please enter a positive number.");
                        } else if (amount > account.getBalance()) {
                            System.out.println("Insufficient balance.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
                
                
                System.out.println("Are you sure you want to transfer " + amount + " to " + targetAccount.getAccountHolderName() + "? (yes/no)");
                String confirmation = sc.nextLine();
                if (!(confirmation.equalsIgnoreCase("yes") || Character.toLowerCase(confirmation.charAt(0)) == 'y')) {
                    System.out.println("Transfer cancelled.");
                    return;
                }
                
                
                account.setBalance(account.getBalance() - amount);
                targetAccount.setBalance(targetAccount.getBalance() + amount);
                
                account.updateTransactionHistory("Transferred: " + amount + " to " + targetAccount.getAccountHolderName() + " (Account Number: " + targetAccount.getAccountNumber() + ")");
                targetAccount.updateTransactionHistory("Received: " + amount + " from " + account.getAccountHolderName() + " (Account Number: " + account.getAccountNumber() + ")");
                
                System.out.println("Transfer successful. New balance: " + account.getBalance());
                return; // Transfer completed
            } else if (comparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        
        System.out.println("Target account not found."); // Target account not found
    }
    
    public void deActivateAccount () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        
        if (!account.isActive()) {
            System.out.println("Your account is already deactivated.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to deactivate your account? (yes/no)");
        String confirmation = sc.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            account.setActive(false);
            System.out.println("Account deactivated successfully.");
        } else {
            System.out.println("Account deactivation cancelled.");
        }
    }
    
    public void reactivateAccount () {
        Account account = currentLoginnedAccount;
        boolean isPinVerified = verifyPin(account);
        if (account == null || !isPinVerified) {
            return;
        }
        if (account.isActive()) {
            System.out.println("Your account is already active.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to reactivate your account? (yes/no)");
        String confirmation = sc.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            account.setActive(true);
            System.out.println("Account reactivated successfully.");
        } else {
            System.out.println("Account reactivation cancelled.");
        }
    }
}
