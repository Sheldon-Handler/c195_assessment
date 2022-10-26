package com.example.c195_assessment;

/**
 * Java class for User
 *
 * @author Sheldon Handler
 */
public class User {

    /**
     * ID of User
     */
    private int userId;

    /**
     * name of User
     */
    private String userName,
    /**
     * password of User
     */
    password;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parameterized constructor for User.
     *
     * @param userId   ID of User
     * @param userName name of User
     * @param password password of User
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter for userId
     *
     * @return ID of User
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for userId
     *
     * @param userId ID of User
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for userName
     *
     * @return userName of User
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for userName
     *
     * @param userName userName of User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for password
     *
     * @return password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     *
     * @param password password of User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    @Override
    public String toString() {
        return getUserName();
    }
}
