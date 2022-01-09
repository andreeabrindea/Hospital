package patient;

import java.io.Serializable;

public class Patient implements Identifiable<Integer>, Serializable  {
    private int ID;
    private String clientName;
    private String clientAddress;
    private String phoneNumber;
    private String date;
    private String problemDescription;
    private boolean hasapp;
    private int age;
    private String app_date;



    public Patient() {
        this.ID = 0;
        this.clientName = "";
        this.clientAddress = "";
        this.phoneNumber = "";
        this.date = "";
        this.problemDescription = "";
        this.hasapp = false;
        this.age = 0;
        this.app_date = "";
    }

    public Patient(int ID, String clientName, String clientAddress, String phoneNumber, String date, String problemDescription, boolean hasapp, int age, String app_date){
        this.ID = ID;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.problemDescription = problemDescription;
        this.hasapp = hasapp;
        this.age = age;
        this.app_date = app_date;
    }
    public String getApp_date() {
        return app_date;
    }

    public void setApp_date(String app_date) {
        this.app_date = app_date;
    }

    public boolean hasApp() {
        return hasapp;
    }

    public void setIfApp(boolean hasapp) {
        this.hasapp = hasapp;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer id){
        this.ID = id;
    }

    public String getClientName(){
        return this.clientName;
    }

    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setOwnerAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age){
        this.age = age;
    }

    @Override
    public String toString(){
        String s = "";
        s = s + "ID: " + this.ID + ", ";
        s = s + "The client " + this.clientName + ", ";
        s = s + "the client address is " + this.clientAddress;
        s = s + "his/her phone number is " + this.phoneNumber + ", ";
        s = s + "and he/she has the following problem " + this.problemDescription + ". ";
        if(this.hasapp == false)
            s = s + "\t The client doesn't have an appointment ";
        else {
            s = s + "\t The client have an appointment on " + this.date + ", ";}
        s = s + "and he/she has the age: " + this.age + ". ";
        return s;
    }
}