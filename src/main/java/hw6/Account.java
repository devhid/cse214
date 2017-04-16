package hw6;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Account} class is a {@code Serializable} object representing a
 * social media account with a name, password, followers and people they follow
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Account implements Serializable {
    // Set of followers and set of people they follow.
    private final Set<User> followers, following;

    // Name of the account.
    private final String name;
    // The account's password.
    private final Password password;

    /**
     * Initializes {@code name} and {@code password} to the parameters specified in the constructor.
     * Also initializes {@code followers} and {@code following} to a new {@code HashSet}.
     *
     * @param name          The name of the account.
     * @param password      The password of the account.
     */
    public Account(final String name, final Password password) {
        this.name = name;
        this.password = password;

        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    /**
     * Returns the name of this account.
     *
     * @return The name of the user who created this account.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the password for this account.
     *
     * @return The password the user created to access this account.
     */
    public Password getPassword() {
        return password;
    }

    /**
     * Adds a user to this account's following set.
     *
     * @param user The {@code User} this {@code Account} wants to follow.
     */
    public void addFollowing(final User user) {
        following.add(user);
    }

    /**
     * Removes a user from this account's following set.
     *
     * @param user The {@code User} this {@code Account} wants to unfollow.
     */
    public void removeFollowing(final User user) {
        following.remove(user);
    }

    /**
     * Adds a user to this account's follower set.
     *
     * @param user The {@code User} that wants to follow this {@code Account}.
     */
    public void addFollower(final User user) {
        followers.add(user);
    }

    /**
     * Removes a user from this account's follower set.
     *
     * @param user The {@code User} that wants to unfollow this {@code Account}.
     */
    public void removeFollower(final User user) {
        followers.remove(user);
    }

    /**
     * Checks if this account is following is the specified user.
     *
     * @param user The {@code User} that is being checked for in this {@code Account}'s set of followers.
     * @return {@code true} if this {@code Account} is following {@code user}, {@code false} if not.
     */
    public boolean isFollowing(final User user) {
        return following.contains(user);
    }

    /**
     * Returns this account's followers.
     *
     * @return A {@code User} set representing all the users that are following this {@code Account}.
     */
    public Set<User> getFollowers() {
        return this.followers;
    }

    /**
     * Returns the users that this account follows.
     *
     * @return A {@code User} set representing all the users this {@code Account} follows.
     */
    public Set<User> getFollowing() {
        return this.following;
    }

}
