package com.example.c195_assessment;

/**
 * Java class for Country
 *
 * @author Sheldon Handler
 */
public class Country {

    /**
     * ID of Country
     */
    private int countryId;

    /**
     * name of Country
     */
    private String country;

    /**
     * Default constructor.
     */
    public Country() {
    }

    /**
     * Parameterized constructor for Country.
     *
     * @param countryId ID of Country
     * @param country   name of Country
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Getter for countryId
     *
     * @return ID of Country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for countryId
     *
     * @param countryId ID of Country
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Getter for country
     *
     * @return name of Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for country
     *
     * @param country name of Country
     */
    public void setCountry(String country) {
        this.country = country;
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
        return country;
    }
}
