package com.example.c195_assessment.dto;

import java.time.LocalDateTime;

/**
 * Java class for Appointment
 *
 * @author Sheldon Handler
 */
public class Appointment {

    /**
     * ID of Appointment
     */
    private int appointmentId, /**
     * ID of Customer for Appointment
     */
    customerId, /**
     * ID of User for Appointment
     */
    userId;

    /**
     * title of Appointment
     */
    private String title, /**
     * description of Appointment
     */
    description, /**
     * location of Appointment
     */
    location, /**
     * type of Appointment
     */
    type;

    /**
     * LocalDateTime for start of Appointment
     */
    private LocalDateTime start, /**
     * LocalDateTime for end of Appointment
     */
    end;

    /**
     * Contact of Appointment
     */
    private Contact contact;

    /**
     * Default constructor.
     */
    public Appointment() {
    }

    /**
     * Constructor with parameters.
     *
     * @param title       Title of appointment
     * @param description Description of appointment
     * @param location    Location of appointment
     * @param type        Type of appointment
     * @param start       LocalDateTime for start of appointment
     * @param end         LocalDateTime for end of appointment
     * @param customerId  ID of Customer who has this appointment
     * @param userId      ID of User who has this appointment
     * @param contact     Contact for Appointment
     */
    public Appointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, Contact contact) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contact = contact;
    }

    /**
     * Constructor with parameters.
     *
     * @param appointmentId ID of appointment
     * @param title         Title of appointment
     * @param description   Description of appointment
     * @param location      Location of appointment
     * @param type          Type of appointment
     * @param start         LocalDateTime for start of appointment
     * @param end           LocalDateTime for end of appointment
     * @param customerId    ID of Customer who has this appointment
     * @param userId        ID of User who has this appointment
     * @param contact       Contact for Appointment
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contact = contact;
    }

    /**
     * Getter for appointmentId
     *
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Setter for appointmentId
     *
     * @param appointmentId ID of Appointment
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter for title
     *
     * @return title of Appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title.
     *
     * @param title title of Appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description.
     *
     * @return description of Appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     *
     * @param description description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location.
     *
     * @return location of Appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location.
     *
     * @param location location of Appointment
     */
    public void setLocation(String location) {
        this.location = location;
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
     * Getter for start.
     *
     * @return start of Appointment
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for start.
     *
     * @param start start of Appointment
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for end.
     *
     * @return end of Appointment
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for end.
     *
     * @param end end of Appointment
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for customerId.
     *
     * @return ID of Customer for Appointment
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customerId.
     *
     * @param customerId ID of Customer for Appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for userId
     *
     * @return ID of User for Appointment
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for userId
     *
     * @param userId ID of User for Appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for contact
     *
     * @return Contact for Appointment
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Setter for contact
     *
     * @param contact Contact for Appointment
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
