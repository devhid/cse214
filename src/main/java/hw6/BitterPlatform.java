package hw6;

import java.io.*;
import java.util.Scanner;

public class BitterPlatform {
    private static final Scanner input = new Scanner(System.in);

    private static Bitter bitter;
    private static User user;

    public static void main(String[] args) {
        new BitterPlatform().init();
    }

    private boolean setup() {
        File file = new File("bitter.ser");

        try(
                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream stream = new ObjectInputStream(fileStream)
        ) {
            try { bitter = (Bitter) stream.readObject(); }
            catch (ClassNotFoundException ex) { System.out.print(Lang.CLASS_NOT_FOUND); }
            return true;
        } catch (IOException ex) {
            bitter = new Bitter();
            return false;
        }
    }

    private void init() {
        if(!setup()) {
            System.out.printf(Lang.WELCOME_MSG, "No previous data found.");
        } else {
            System.out.printf(Lang.WELCOME_MSG, "File bitter.ser loaded.");
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
        } catch (IOException ex) { System.out.print(Lang.SAVE_FAIL); }

        System.exit(0);
    }

    private enum MenuType {
        LOGIN, USER
    }

    private void showMenu(final MenuType type) {
        switch(type) {
            case LOGIN:
                System.out.printf(Lang.LOGIN_MENU, "L) Login", "S) Sign up", "Q) Quit"); break;
            case USER: System.out.printf(Lang.USER_MENU, "F) Follow", "U) Unfollow", "V) View Followers",
                    "S) See Following", "A) List All Users", "L) Logout", "C) Close Your Account"); break;
        }
    }

    private void openMenu(final MenuType type) {
        String option;
        char letter;

        showMenu(type);

        System.out.print(Lang.INPUT_OPTION);

        option = input.nextLine();
        letter = option.isEmpty() ? '0' : option.trim().toUpperCase().charAt(0);

        try {
            selectOption(letter, type);
        } catch (IllegalArgumentException ex) {
            System.out.print(ex.getMessage());
            openMenu(type);
        }
    }

    private void selectOption(final char option, final MenuType type) {
        String email, password, name;
        Account account;

        switch(type) {
            case LOGIN:
                switch(option) {
                    case 'L':
                        System.out.print(Lang.INPUT_EMAIL);
                        email = input.nextLine();

                        System.out.print(Lang.INPUT_PASSWORD);
                        password = input.nextLine();

                        this.login(email, password);
                        break;
                    case 'S':
                        System.out.print(Lang.INPUT_EMAIL);
                        email = input.nextLine();

                        System.out.print(Lang.INPUT_NAME);
                        name = input.nextLine();

                        System.out.print(Lang.INPUT_PASSWORD);
                        password = input.nextLine();

                        this.signup(email, name, password);
                        break;
                    case 'Q':
                        System.out.print(Lang.SAVE_SUCCESS);

                        this.quit();
                        break;
                    default:
                        System.out.print(Lang.INVALID_OPTION);
                        openMenu(MenuType.LOGIN);
                }

                break;
            case USER:
                switch(option) {
                    case 'F':
                        System.out.printf(Lang.FOLLOW_TOGGLE, "follow");
                        email = input.nextLine();

                        this.follow(email);
                        break;
                    case 'U':
                        System.out.printf(Lang.FOLLOW_TOGGLE, "unfollow");
                        email = input.nextLine();

                        this.unfollow(email);
                        break;
                    case 'V':
                        account = bitter.getAccount(user.getEmail());

                        System.out.println("\nFollowers:");
                        System.out.printf("%-32s |%s\n", "Email", "Name");
                        System.out.print("---------------------------------------------------------\n");
                        account.getFollowers().forEach(follower -> System.out.printf("%-32s |%s\n", follower.getEmail(), follower.getName()));

                        openMenu(MenuType.USER);
                        break;
                    case 'S':
                        account = bitter.getAccount(user.getEmail());

                        System.out.println("\nFollowing:");
                        System.out.printf("%-32s |%s\n", "Email", "Name");
                        System.out.print("---------------------------------------------------------\n");
                        account.getFollowing().forEach(following -> System.out.printf("%-32s |%s\n", following.getEmail(), following.getName()));

                        openMenu(MenuType.USER);
                        break;
                    case 'A':
                        System.out.println("\nUsers:");
                        System.out.printf("%-32s |%s\n", "Email", "Name");
                        System.out.print("---------------------------------------------------------\n");
                        bitter.getUsers().forEach((e, u) -> System.out.printf("%-32s |%s\n", e, u.getName()));

                        openMenu(MenuType.USER);
                        break;
                    case 'L':
                        user = null;

                        openMenu(MenuType.LOGIN);
                        break;
                    case 'C':
                        System.out.printf(Lang.ACCOUNT_CLOSED, user.getName());

                        // Remove user from other people's sets of following/followers.
                        bitter.getAccounts().forEach((e, a) -> {
                            a.removeFollower(user);
                            a.removeFollowing(user);
                        });

                        // Remove user from database along with their set of following/followers.
                        account = bitter.getAccount(user.getEmail());

                        account.getFollowers().clear();
                        account.getFollowing().clear();
                        bitter.removeUser(user.getEmail());

                        user = null;
                        openMenu(MenuType.LOGIN);
                        break;
                    default:
                        System.out.print(Lang.INVALID_OPTION);
                        openMenu(MenuType.USER);
                }
        }
    }

    private void signup(final String email, final String name, final String password) {
        User newUser = new User(name, email);
        bitter.addUser(email, newUser, new Account(name, new Password(password)));

        user = newUser;

        System.out.print(Lang.SIGNED_UP_SUCCESS);
        openMenu(MenuType.USER);
    }

    private void login(final String email, final String password) {
        Account account = bitter.getAccounts().getAccountInformation(email);
        if(account == null) {
            System.out.print(Lang.USER_NOT_FOUND);
            openMenu(MenuType.LOGIN);
            return;
        }

        if(!account.getPassword().toString().equals(password)) {
            System.out.print(Lang.INCORRECT_PASSWORD);
            openMenu(MenuType.LOGIN);
        } else {
            user = bitter.getUsers().getUser(email);
        }

        openMenu(MenuType.USER);
    }

    private void follow(final String email) {
        if(bitter.getAccount(email) == null) {
            System.out.print(Lang.USER_NOT_FOUND);
        } else {
            Account account = bitter.getAccount(user.getEmail());
            User other = bitter.getUser(email);

            if(user.getEmail().equals(email)) {
                System.out.print(Lang.CANNOT_FOLLOW_SELF);
                openMenu(MenuType.USER);
                return;
            }

            if (!account.isFollowing(other)) {
                account.addFollowing(other);
                bitter.getAccount(email).addFollower(user);

                System.out.printf(Lang.FOLLOW_SUCCESS, other.getName());
            } else {
                System.out.printf(Lang.ALREADY_FOLLOWED, other.getName());
            }
        }
        openMenu(MenuType.USER);
    }

    private void unfollow(final String email) {
        if(bitter.getAccount(email) == null) {
            System.out.printf(Lang.USER_NOT_FOUND);
        } else {
            Account account = bitter.getAccount(user.getEmail());
            User other = bitter.getUser(email);

            if (account.isFollowing(other)) {
                account.removeFollowing(other);
                bitter.getAccount(email).removeFollower(user);
                System.out.printf(Lang.UNFOLLOW_SUCCESS, other.getName());
            } else {
                System.out.printf(Lang.NOT_FOLLOWING, other.getName());
            }

        }
        openMenu(MenuType.USER);
    }
}
