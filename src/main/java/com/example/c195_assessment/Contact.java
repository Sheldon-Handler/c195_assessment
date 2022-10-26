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
     */
    @Override
    public String toString() {
        return getContactName() + " - " + getEmail();
    }
}
