package com.example.c195_assessment;

/**
 * Java class for Customer
 *
 * @author Sheldon Handler
 */
public class Customer {

    /**
     * ID of Customer
     */
    private int customerId;

    /**
     * name of Customer
     */
    private String customerName, /**
     * address of Customer
     */
    address, /**
     * postalCode of Customer
     */
    postalCode, /**
     * phone of Customer
     */
    phone;

    /**
     * Division of Customer
     */
    private Division division;

    /**
     * Country of Customer
     */
    private Country country;

    /**
     * Default constructor for Customer
     */
    public Customer() {
    }

    /**
     * Parameterized constructor for Customer
     *
     * @param customerName name of Customer
     * @param address      address of Customer
     * @param postalCode   postal code of Customer
     * @param phone        phone number of Customer
     * @param division     Division for Customer
     */
    public Customer(String customerName, String address, String postalCode, String phone, Division division) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
    }

    /**
     * Parameterized constructor for Customer
     *
     * @param customerId   ID of Customer
     * @param customerName name of Customer
     * @param address      address of Customer
     * @param postalCode   postal code of Customer
     * @param phone        phone number of Customer
     * @param division     Division for Customer
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, Division division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
    }

    /**
     * Getter for customerId
     *
     * @return ID of Customer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customerId
     *
     * @param customerId ID of Customer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for customerName
     *
     * @return name of Customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for customerName
     *
     * @param customerName name of Customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for address
     *
     * @return address of Customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address
     *
     * @param address address of Customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for postalCode
     *
     * @return postal code of Customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for postalCode
     *
     * @param postalCode postal code of Customer
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for phone
     *
     * @return phone number of Customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for phone
     *
     * @param phone phone number of Customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for division
     *
     * @return Division of Customer
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Setter for division
     *
     * @param division Division of Customer
     */
    public void setDivision(Division division) {
        this.division = division;
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
        return getCustomerId() + " - " + getCustomerName();
    }
}
