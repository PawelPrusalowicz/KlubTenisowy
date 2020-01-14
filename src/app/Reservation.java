package app;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Reservation {

    private static Reservation instance;

    int agencyId, serviceTypeId, serviceRealisationId, trainerId, courtsNumber;
    String serviceTypeName;
    Date date;
    Time timeFrom, timeTo;


    private Reservation(){}

    public static Reservation getInstance(){
        if(instance == null){
            instance = new Reservation();
        }
        return instance;
    }

    public String info() {
        return "AgencyId: " + agencyId +
                " serviceTypeId: " + serviceTypeId +
                " serviceTypeName: " + serviceTypeName +
                " serviceRealisationId: " + serviceRealisationId +
                " courtsNumber: " + courtsNumber +
                " trainerId: " + trainerId +
                " Date: " + date +
                " from: " + timeFrom +
                " to: " + timeTo;
    }

    public void saveReservation(){

        if (serviceTypeName.equals("Pojedyńcza gra") || serviceTypeName.equals("Lekcja z trenerem")){

            String workerId = Integer.toString(trainerId);
            if( workerId.equals("0")){
                workerId = "null";
            }

            String sqlInsertServiceRealization = "Insert into realizacje_uslug (TERMIN_OD, TERMIN_DO, ID_USLUGI, ID_PRACOWNIKA) " +
                    "Values (TO_TIMESTAMP('" + date + " " + timeFrom + "', 'YYYY-MM-DD HH24:MI:SS')," +
                    "TO_TIMESTAMP('" + date + " " + timeFrom + "', 'YYYY-MM-DD HH24:MI:SS')," +
                    serviceTypeId + "," + workerId + ")";

            String selectServiceRealizationId = "Select id_realizacji_uslugi from realizacje_uslug where " +
                    "TERMIN_OD = " + "TO_TIMESTAMP('" + date + " " + timeFrom + "', 'YYYY-MM-DD HH24:MI:SS')," +
                    "TERMIN_DO = " + "TO_TIMESTAMP('" + date + " " + timeTo + "', 'YYYY-MM-DD HH24:MI:SS')," +
                    "ID_USLUGI = " + serviceTypeId + ", id_pracownika =" + workerId;

            int serviceRealizationId;
                    String sqlInsertCourtReservations = "Insert into rezerwacje_kortow (ID_REALIZACJI_USLUGI, ID_KORTU, TERMIN_OD, TERMIN_DO) " +
                            "Values ";


            System.out.println(sqlInsertServiceRealization);



        }



        //na końcu
        String sqlInsertClientServiceRealization;

    }



    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public int getServiceRealisationId() {
        return serviceRealisationId;
    }

    public void setServiceRealisationId(int serviceRealisationId) {
        this.serviceRealisationId = serviceRealisationId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getCourtsNumber() {
        return courtsNumber;
    }

    public void setCourtsNumber(int courtsNumber) {
        this.courtsNumber = courtsNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }
}


