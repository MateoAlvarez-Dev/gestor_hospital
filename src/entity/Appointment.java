package entity;

public class Appointment {
    private int id;
    private String date_appointment;
    private String time_appointment;
    private String reason;
    private int id_patient;
    private int id_doctor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(String date_appointment) {
        this.date_appointment = date_appointment;
    }

    public String getTime_appointment() {
        return time_appointment;
    }

    public void setTime_appointment(String time_appointment) {
        this.time_appointment = time_appointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date_appointment='" + date_appointment + '\'' +
                ", time_appointment='" + time_appointment + '\'' +
                ", reason='" + reason + '\'' +
                ", id_patient=" + id_patient +
                ", id_doctor=" + id_doctor +
                '}';
    }
}
