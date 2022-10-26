package com.example.c195_assessment;

/**
 * Java class for number of Appointments report
 *
 * @author Sheldon Handler
 */
public class NumberOfAppointments {

    /**
     * type of Appointment
     */
    private String type;

    /**
     * month of Appointment
     */
    private String month;

    /**
     * number of Appointment with corresponding type and month
     */
    private int numberOfAppointments;

    /**
     * Default constructor.
     */
    public NumberOfAppointments() {
    }

    /**
     * Parameterized constructor.
     *
     * @param type                 String type of Appointment
     * @param month                month of Appointment
     * @param numberOfAppointments int number of Appointment with corresponding type and Month
     */
    public NumberOfAppointments(String type, String month, int numberOfAppointments) {
        this.type = type;
        this.month = month;
        this.numberOfAppointments = numberOfAppointments;
    }

    /**
     * Getter for type.
     *
     * @return type of Appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type.
     *
     * @param type type of Appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for month.
     *
     * @return month of Appointment for number of Appointment with corresponding type
     */
    public String getMonth() {
        return month;
    }

    /**
     * Setter for month.
     *
     * @param month month of Appointment for number of Appointment with corresponding type
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Getter for numberOfAppointments.
     *
     * @return number of Appointment with corresponding type and month
     */
    public int getNumberOfAppointments() {
        return numberOfAppointments;
    }

    /**
     * Setter for numberOfAppointments.
     *
     * @param numberOfAppointments number of Appointment with corresponding type and month
     */
    public void setNumberOfAppointments(int numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }
}
