package com.example.c195_assessment;

/**
 * Java class for Contact.
 *
 * @author Sheldon Handler
 */
public class Contact {

    /**
     * ID of Contact
     */
    private int contactId;

    /**
     * name of Contact
     */
    private String contactName,
    /**
     * email address of Contact
     */
    email;

    /**
     * Default constructor.
     */
    public Contact() {
    }

    /**
     * Parameterized constructor for Contact.
     *
     * @param contactName name of Contact
     * @param email       email address of Contact
     */
    public Contact(String contactName, String email) {
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Parameterized constructor for Contact.
     *
     * @param contactId   ID of Contact
     * @param contactName name of Contact
     * @param email       email address of Contact
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Getter for contactId
     *
     * @return ID of Contact
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Setter for contactId
     *
     * @param contactId ID of Contact
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Getter for contactName
     *
     * @return name of Contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for contactName
     *
     * @param contactName name of Contact
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter for email
     *
     * @return email address of Contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email email address of Contact
     */
    public void setEmail(String email) {
        this.email = email;
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
        return getContactName() + " - " + getEmail();
    }
}
