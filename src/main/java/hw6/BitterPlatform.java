package hw6;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BitterPlatform {
    private static final Scanner input = new Scanner(System.in);

    private static Bitter bitter;
    private static User user;

    private BitterPlatform() {
        setup();
    }

    public static void main(String[] args) {
        new BitterPlatform().init();
    }

    private boolean setup() {
        File file = new File("bitter.ser");

        try(
                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream stream = new ObjectInputStream(fileStream)
        ) {
            try {
                bitter = (Bitter) stream.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println("Error. Bitter class could not be found.");
            }
            return true;
        } catch (IOException ex) {
            bitter = new Bitter();
            return false;
        }
    }

    private void init() {
        if(!setup()) {
            System.out.println("Hello, and welcome to Bitter, a more tasteful social network. No previous data found.");
        } else {
            System.out.println("Hello, and welcome to Bitter, a more tasteful social network. Bitter.ser loaded.");
        }

        openMenu(MenuType.LOGIN);
    }

    private void quit() {
        File file = new File("bitter.ser");

        try(
                FileOutputStream fileStream = new FileOutputStream(file);
                ObjectOutputStream stream = new ObjectOutputStream(fileStream)
        ) {
            stream.writeObject(bitter);
        } catch (IOException ex) {
            System.out.println("Error. Current program state could not be saved to Bitter.ser.");
        }

        System.exit(0);
    }

    private enum MenuType {
        LOGIN, USER
    }

    private void showMenu(final MenuType type) {
        switch(type) {
            case LOGIN:
                System.out.println("You are not logged in.");
                System.out.printf("Menu: \n\t%s \n\t%s \n\t%s\n", "L) Login", "S) Sign up", "Q) Quit"); break;
            case USER: System.out.printf("Menu: \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s\n",
                    "F) Follow", "U) Unfollow", "V) View Followers",
                    "S) See Following", "List All Users", "Logout", "Close Your Account"); break;
        }
    }

    private void openMenu(final MenuType type) {
        String option;
        char letter;

        showMenu(type);

        System.out.print("Please select an option: ");

        option = input.nextLine();
        letter = option.isEmpty() ? '0' : option.trim().toUpperCase().charAt(0);

        try {
            selectOption(letter, type);
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
        }
    }

    private void selectOption(final char option, final MenuType type) {
        String email, password, name;

        switch(type) {
            case LOGIN:
                switch(option) {
                    case 'L':
                        System.out.print("Please enter your email: ");
                        email = input.nextLine();

                        System.out.print("\nPlease enter your password: ");
                        password = input.nextLine();

                        this.login(email, password);
                        break;
                    case 'S':
                        System.out.print("Please enter your email: ");
                        email = input.nextLine();

                        System.out.print("\nPlease enter your name: ");
                        name = input.nextLine();

                        System.out.print("\nPlease enter your password: ");
                        password = input.nextLine();

                        this.signup(email, name, password);
                        break;
                    case 'Q':
                        System.out.print("Shutting down... Current program state saved to 'Bitter.ser'");

                        this.quit();
                        break;
                    default:
                        openMenu(MenuType.LOGIN);
                }

                break;
            case USER:
                switch(option) {
                    case 'F':
                        System.out.print("Please enter the email of the user you would like to follow: ");
                        email = input.nextLine();

                        this.follow(email);
                        break;
                    case 'U':
                        System.out.print("Please enter the email of the user you would like to unfollow: ");
                        email = input.nextLine();

                        this.unfollow(email);
                        break;
                    case 'V':
                        this.viewFollowers();
                        break;
                    case 'S':
                        this.viewFollowing();
                        break;
                    case 'A':
                        this.viewUsers();
                        break;
                    case 'L':
                        this.logout();
                        break;
                    case 'C':
                        this.closeAccount();
                        break;
                    default:
                        openMenu(MenuType.USER);
                }
                break;
        }
    }

    private void signup(final String email, final String name, final String password) {
        User newUser = new User(name, email);
        bitter.addUser(email, newUser, new Account(name, new Password(password)));

        user = newUser;

        System.out.println("Thanks for joining Bitter!");
        openMenu(MenuType.USER);
    }

    private void login(final String email, final String password) {
        Account account = bitter.getAccounts().getAccountInformation(email);

        if(!account.getPassword().equals(new Password(password))) {
            System.out.println("Incorrect password. Please try again");
            selectOption('L', MenuType.LOGIN);
        } else {
            user = bitter.getUsers().getUser(email);
        }

        System.out.println("You have logged in!");

        openMenu(MenuType.USER);
    }

    private void logout() {
        user = null;
        openMenu(MenuType.LOGIN);
    }

    private void follow(final String email) {
        Account account = bitter.getAccount(user.getEmail());
        User other = bitter.getUser(email);

        if(!account.isFollowing(other)) {
            account.addFollowing(other);
            bitter.getAccount(email).addFollower(user);
        } else {
            System.out.println("You are already following " + user.getName() + ".");
        }

        System.out.println("You are now following " + user.getName() + ".");
        openMenu(MenuType.USER);
    }

    private void unfollow(final String email) {
        Account account = bitter.getAccount(user.getEmail());
        User other = bitter.getUser(email);

        if(account.isFollowing(other)) {
            account.removeFollowing(other);
            bitter.getAccount(email).removeFollower(user);
        } else {
            System.out.println("You were not following " + user.getName() + ".");
        }

        System.out.println("You are no longer following " + user.getName() + ".");
        openMenu(MenuType.USER);
    }

    private void viewFollowers() {
        Account account = bitter.getAccount(user.getEmail());
        account.getFollowers().forEach(u -> System.out.println(u.getName()));

        openMenu(MenuType.USER);
    }

    private void viewFollowing() {
        Account account = bitter.getAccount(user.getEmail());
        account.getFollowing().forEach(u -> System.out.println(u.getName()));

        openMenu(MenuType.USER);
    }

    private void viewUsers() {
        bitter.getUsers().forEach((k, v) -> System.out.println(v));

        openMenu(MenuType.USER);
    }

    private void closeAccount() {
        bitter.removeUser(user.getEmail());
        openMenu(MenuType.LOGIN);
    }
}
