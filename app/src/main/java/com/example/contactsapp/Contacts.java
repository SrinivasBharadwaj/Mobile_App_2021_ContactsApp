package com.example.contactsapp;

import java.io.Serializable;

public class Contacts implements Serializable {

    int contactID;
    String contactName, contactEmail, contactPhoneNumber, contactPhoneType;

    public Contacts(int contactID, String contactName, String contactEmail, String contactPhoneNumber, String contactPhoneType) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactPhoneType = contactPhoneType;
    }

    public Contacts() {

    }


    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactPhoneType() {
        return contactPhoneType;
    }

    public void setContactPhoneType(String contactPhoneType) {
        this.contactPhoneType = contactPhoneType;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contactID=" + contactID +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                ", contactPhoneType='" + contactPhoneType + '\'' +
                '}';
    }
}
