package com.example.c195_assessment.dao;

import com.example.c195_assessment.JDBC;
import com.example.c195_assessment.dto.Appointment;
import com.example.c195_assessment.dto.Contact;
import com.example.c195_assessment.dto.NumberOfAppointments;
import com.example.c195_assessment.dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Data access object (DAO) class for Appointment
 *
 * @author Sheldon Handler
 */
public class AppointmentDAO {

    /**
     * ObservableList of Appointment from "appointments" table in database.
     */
    public static ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

    /**
     * ObservableList of Appointment from "appointments" table in database, sorted by Start.
     */
    public static ObservableList<Appointment> appointmentObservableListSortedByStart = FXCollections.observableArrayList();

    /**
     * ObservableList for number of Appointments with corresponding type and month
     */
    public static ObservableList<NumberOfAppointments> numberOfAppointmentsObservableList = FXCollections.observableArrayList();

    /**
     * Default constructor
     */
    public AppointmentDAO() {
    }

    /**
     * Sets appointmentsObservableList from each row in database table "appointments".
     *
     * @throws SQLException SQLException to throw if SQL query fails
     */
    public static void setAppointmentObservableList() throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM appointments;");

        ResultSet resultSet = preparedStatement.executeQuery();

        appointmentObservableList.clear();

        while (resultSet.next()) {


            LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();

            LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();

            Contact contact = ContactDAO.getContactFromId(resultSet.getInt("Contact_ID"));

            Appointment appointment = new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"), resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"), start, end, resultSet.getInt("Customer_ID"), resultSet.getInt("User_ID"), contact);

            appointmentObservableList.add(appointment);
        }

        preparedStatement.close();

        setNumberOfAppointmentsObservableList();
    }

    /**
     * Creates an ObservableList of class NumberOfAppointments
     *
     * @throws SQLException Exception to throw just in case
     */
    public static void setNumberOfAppointmentsObservableList() throws SQLException {
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT DISTINCT Type, MONTHNAME(Start) AS Month, Count(*) AS Number_Of_Appointments FROM appointments GROUP BY Type, MONTHNAME(Start);");

        ResultSet resultSet = preparedStatement.executeQuery();

        numberOfAppointmentsObservableList.clear();

        while (resultSet.next()) {
            NumberOfAppointments numberOfAppointments = new NumberOfAppointments(resultSet.getString("Type"), resultSet.getString("Month"), resultSet.getInt("Number_Of_Appointments"));
            numberOfAppointmentsObservableList.add(numberOfAppointments);
        }

        preparedStatement.close();
    }

    /**
     * Adds an entry into "appointments" table in database using appointment parameter.
     *
     * @param appointment Appointment to add
     * @throws SQLException SQLException thrown if SQL statement fails to execute
     */
    public static void addAppointment(Appointment appointment) throws SQLException {

        // preparing SQL statement
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

        // inserting parameters into prepared SQL statement
        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getDescription());
        preparedStatement.setString(3, appointment.getLocation());
        preparedStatement.setString(4, appointment.getType());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
        preparedStatement.setInt(7, appointment.getCustomerId());
        preparedStatement.setInt(8, appointment.getUserId());
        preparedStatement.setInt(9, appointment.getContact().getContactId());

        // executing SQL prepared statement
        preparedStatement.executeUpdate();
        preparedStatement.close();

        // updating ObservableList to match
        setAppointmentObservableList();
    }

    /**
     * Updates a row in database table "appointments" to match the newly updated appointment.
     *
     * @param appointment Appointment with updated information
     * @throws SQLException SQLException thrown if SQL statement fails to execute
     */
    public static void updateAppointment(Appointment appointment) throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?;");

        // setting SQL statement values
        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getDescription());
        preparedStatement.setString(3, appointment.getLocation());
        preparedStatement.setString(4, appointment.getType());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
        preparedStatement.setInt(7, appointment.getCustomerId());
        preparedStatement.setInt(8, appointment.getUserId());
        preparedStatement.setInt(9, appointment.getContact().getContactId());
        preparedStatement.setInt(10, appointment.getAppointmentId());

        // executing prepared statement
        preparedStatement.executeUpdate();
        preparedStatement.close();

        // updating ObservableList
        setAppointmentObservableList();
    }

    /**
     * Method to delete an entry from appointments table in database.
     *
     * @param appointment Appointment to delete from "appointments" table in database
     * @throws SQLException SQLException thrown if SQL statement fails to execute
     */
    public static void deleteAppointment(Appointment appointment) throws SQLException {

        // preparing SQL statement
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("DELETE FROM appointments WHERE Appointment_ID = ?;");

        // inserting parameters into SQL statement
        preparedStatement.setInt(1, appointment.getAppointmentId());

        // executing prepared statement
        preparedStatement.executeUpdate();
        preparedStatement.close();

        // updating ObservableList
        setAppointmentObservableList();
    }

    /**
     * Checks if there is an Appointment within 15 before current time and returns that Appointment if parameter user matches userId of found Appointment.
     *
     * @param user User to compare getUserId() with Appointment
     * @return Appointment that is within 15 minutes before LocalDateTime.now()
     * @throws SQLException Exception to throw if SQL query fails
     */
    public static Appointment notification(User user) throws SQLException {

        Appointment appointment;

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM client_schedule.appointments WHERE date_format(Start, '%Y-%m-%d') = curDate() AND Start >= curtime() AND User_ID = ? limit 1;");

        preparedStatement.setInt(1, user.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            LocalDateTime localDateTime = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
            long difference = ChronoUnit.MINUTES.between(localTime, localDateTime.toLocalTime());
            if (difference >= 1 && difference <= 15) {
                System.out.println("Upcoming appointment");
                appointment = new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"), resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"), resultSet.getTimestamp("Start").toLocalDateTime(), resultSet.getTimestamp("End").toLocalDateTime(), resultSet.getInt("Customer_ID"), resultSet.getInt("User_ID"), ContactDAO.getContactFromId(resultSet.getInt("Contact_ID")));
            } else {
                System.out.println("No upcoming appointment");
                appointment = null;
            }

        } else {
            System.out.println("No upcoming appointments");
            appointment = null;
        }

        preparedStatement.close();

        return appointment;
    }
}
