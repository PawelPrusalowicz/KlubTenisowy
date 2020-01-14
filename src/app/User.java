package app;

import java.sql.Date;
import java.sql.Time;

public class User {

    public enum UserType {
        Client, Worker, Admin;
    }

    private static User instance;

    int clubId, Id;
    UserType userType;

    private User(){}

    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
