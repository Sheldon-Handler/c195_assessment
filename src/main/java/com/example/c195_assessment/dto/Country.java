package com.example.c195_assessment.dto;

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
     */
    @Override
    public String toString() {
        return getCountry();
    }
}
