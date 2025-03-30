package Banking2;

import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int choice1 = 0;
        
        do {
            try {
                System.out.println("Welcome to the Banking System");
                System.out.println("1. Log In");
                System.out.println("2. Create New Account");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice1 = Integer.parseInt(sc.nextLine());
                
                if (choice1 == 1) {
                    bank.logIn();
                    if (!bank.isLoggedIn()) {
                        System.out.println("Invalid login. Please try again.");
                        continue;
                    }
                    int choice = 0;
                    do {
                        System.out.println("1. Get Account Details");
                        System.out.println("2. Check Balance");
                        System.out.println("3. Deposit");
                        System.out.println("4. Withdraw");
                        System.out.println("5. Transfer");
                        System.out.println("6. Get Transaction History");
                        System.out.println("7. Deactivate Account");
                        System.out.println("8. Reactivate Account");
                        System.out.println("9. Log Out");
                        
                        choice = Integer.parseInt(sc.nextLine());
                        
                        switch (choice) {
                            case 1:
                                bank.getAccountDetails();
                                break;
                            case 2:
                                bank.checkBalance();
                                break;
                            case 3:
                                bank.deposit();
                                break;
                            case 4:
                                bank.withdraw();
                                break;
                            case 5:
                                bank.transfer();
                                break;
                            case 6:
                                bank.getTransactionHistory();
                                break;
                            case 7:
                                bank.deActivateAccount();
                                break;
                            case 8:
                                bank.reactivateAccount();
                                break;
                            case 9:
                                bank.logOut();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 9);
                }
                else if (choice1 == 2) {
                    bank.createNewAccount();
                } else if (choice1 == 3) {
                    System.out.println("Exiting the Banking System. Goodbye!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
                
            } catch (Exception e) {
                System.out.println("An Error occurred: " + e.getMessage());
            }
        } while (choice1 != 3);
        
        sc.close();
    }
}

