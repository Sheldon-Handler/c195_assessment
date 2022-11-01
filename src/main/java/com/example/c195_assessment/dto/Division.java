package com.example.c195_assessment.dto;

/**
 * Java class for Division
 *
 * @author Sheldon Handler
 */
public class Division {

    /**
     * ID of Division
     */
    private int divisionId, /**
     * ID of Country for Division
     */
    countryId;

    /**
     * name of Division
     */
    private String division;

    /**
     * Default constructor
     */
    public Division() {
    }

    /**
     * Parameterized constructor for Division.
     *
     * @param divisionId ID of Division
     * @param division   name of Division
     * @param countryId  ID of Country for Division
     */
    public Division(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Getter for divisionId
     *
     * @return ID of division
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Setter for divisionId
     *
     * @param divisionId ID of division
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Getter for division
     *
     * @return name of Division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter for division
     *
     * @param division name of Division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Getter for countryId
     *
     * @return ID of Country for Division
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for countryId
     *
     * @param countryId ID of Country for Division
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getDivision();
    }
}
