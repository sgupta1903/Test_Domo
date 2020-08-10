package data;

import java.util.HashMap;
import java.util.Map;

public class ManagePayorProfileData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String primaryPhoneNumber;
    private String mobilePhoneNumber;
    private String workPhoneNumber;
    private String extenstion;
    private String emergencyPhoneNumber;
    private String dateOfBirth;
    private String ssn;
    private String drivingLicense;
    private String driverLicenseState;
    private String agreementNumber;
    private String communicationText;
    private String communicationPhone;
    private String communicationLetter;
    private String communicationEmail;
    private String routingNumber;
    private String accountNumber;
    private String accountType;
    private String creditCardNumber;
    private String expiryDate;
    private String creditCardType;


    Map<String, String> payorProfileData = new HashMap<>();

    public ManagePayorProfileData(Map<String, String> payorProfileData) {
        this.payorProfileData = payorProfileData;
    }

    public void setFirstName() {
        if (payorProfileData.get("Buyer_First_Name").isEmpty() && payorProfileData.get("Draft_First_Name").isEmpty())
            this.firstName = payorProfileData.get("First_Name");
        else if (!payorProfileData.get("Buyer_First_Name").isEmpty())
            this.firstName = payorProfileData.get("Buyer_First_Name");
        else if (payorProfileData.get("Buyer_First_Name").isEmpty())
            this.firstName = payorProfileData.get("Draft_First_Name");

    }

    public String getFirstName() {
        setFirstName();
        return firstName;
    }

    public void setLastName() {
        if (payorProfileData.get("Buyer_Last_Name").isEmpty() && payorProfileData.get("Draft_Last_Name").isEmpty())
            this.lastName = payorProfileData.get("Last_Name");
        else if (!payorProfileData.get("Buyer_Last_Name").isEmpty())
            this.lastName = payorProfileData.get("Buyer_Last_Name");
        else if (payorProfileData.get("Buyer_Last_Name").isEmpty())
            this.lastName = payorProfileData.get("Draft_Last_Name");
    }

    public String getLastName() {
        setLastName();
        return lastName;
    }

    public void setMiddleName() {
        this.middleName = payorProfileData.get("Middile_Name");
    }

    public String getMiddleName() {
        setMiddleName();
        return middleName;
    }


    public void setEmail() {
        if (payorProfileData.get("Buyer_Email").isEmpty())
            this.email = payorProfileData.get("Email");
        else
            this.email = payorProfileData.get("Buyer_Email");
    }

    public String getEmail() {
        setEmail();
        return email;
    }

    public void setAddress1() {
        if (payorProfileData.get("Buyer_Address_1").isEmpty())
            this.address1 = payorProfileData.get("Address_1");
        else
            this.email = payorProfileData.get("Buyer_Address_1");
    }

    public String getAddress1() {
        setAddress1();
        return address1;
    }

    public void setAddress2() {
        if (payorProfileData.get("Buyer_Address_2").isEmpty())
            this.address2 = payorProfileData.get("Address_2");
        else
            this.address2 = payorProfileData.get("Buyer_Address_2");
    }

    public String getAddress2() {
        setAddress2();
        return address2;
    }

    public void setCity() {
        if (payorProfileData.get("Buyer_City").isEmpty())
            this.city = payorProfileData.get("City");
        else
            this.city = payorProfileData.get("Buyer_City");
    }

    public String getCity() {
        setCity();
        return city;
    }

    public void setState() {
        if (payorProfileData.get("Buyer_State").isEmpty())
            this.state = payorProfileData.get("State");
        else
            this.state = payorProfileData.get("Buyer_State");
    }

    public String getState() {
        setState();
        return state;
    }

    public void setZip() {
        if (payorProfileData.get("Buyer_Zip").isEmpty())
            this.zip = payorProfileData.get("Zip");
        else
            this.zip = payorProfileData.get("Buyer_Zip");
    }

    public String getZip() {
        setZip();
        return zip;
    }

    public void setCountry() {
        this.country = payorProfileData.get("Country");
    }

    public String getCountry() {
        setCountry();
        return country;
    }

    public void setWorkPhoneNumber() {
        if (payorProfileData.get("Buyer_Work_Phone").isEmpty())
            this.workPhoneNumber = payorProfileData.get("Work_Phone");
        else
            this.workPhoneNumber = payorProfileData.get("Buyer_Work_Phone");
    }

    public String getWorkPhoneNumber() {
        setWorkPhoneNumber();
        return workPhoneNumber;
    }

    public void setMobilePhoneNumber() {
        if (payorProfileData.get("Buyer_Cell_Phone").isEmpty())
            this.mobilePhoneNumber = payorProfileData.get("Mobile_Phone");
        else
            this.mobilePhoneNumber = payorProfileData.get("Buyer_Cell_Phone");

    }

    public String getMobilePhoneNumber() {
        setMobilePhoneNumber();
        return mobilePhoneNumber;
    }

    public void setEmergencyPhoneNumber() {
        if (payorProfileData.get("Buyer_Emergency_Phone").isEmpty())
            this.emergencyPhoneNumber = payorProfileData.get("Emergency_Phone");
        else
            this.emergencyPhoneNumber = payorProfileData.get("Buyer_Emergency_Phone");

    }

    public String getEmergencyPhoneNumber() {
        setEmergencyPhoneNumber();
        return emergencyPhoneNumber;
    }

    public void setPrimaryPhoneNumber() {
        if (payorProfileData.get("Buyer_Home_Phone").isEmpty())
            this.primaryPhoneNumber = payorProfileData.get("Home_Phone");
        else
            this.primaryPhoneNumber = payorProfileData.get("Buyer_Home_Phone");
    }

    public String getPrimaryPhoneNumber() {
        setPrimaryPhoneNumber();
        return primaryPhoneNumber;
    }

    public void setDateOfBirth() {
        this.dateOfBirth = payorProfileData.get("Buyer_Birth_Day");
    }

    public String getDateOfBirth() {
        setDateOfBirth();
        return dateOfBirth;
    }

    public void setSsn() {
        this.ssn = payorProfileData.get("SSN");
    }

    public String getSsn() {
        setSsn();
        return ssn;
    }

    public void setDrivingLicense() {
        if (payorProfileData.get("Buyer_Driver_License").isEmpty())
            this.drivingLicense = payorProfileData.get("Driving_License");
        else
            this.drivingLicense = payorProfileData.get("Buyer_Driver_License");
    }

    public String getDrivingLicense() {
        setDrivingLicense();
        return drivingLicense;
    }

    public void setDriverLicenseState() {
        this.driverLicenseState = payorProfileData.get("Buyer_Driver_License_State");
    }

    public String getDriverLicenseState() {
        setDriverLicenseState();
        return driverLicenseState;
    }

    public void setAgreementNumber() {
        this.agreementNumber = payorProfileData.get("Agreement_Number");
    }

    public String getAgreementNumber() {
        setAgreementNumber();
        return agreementNumber;
    }

    public void setCommunicationText() {
        this.communicationText = payorProfileData.get("Communication_Text");
    }

    public String getCommunicationText() {
        setCommunicationText();
        return communicationText;
    }

    public void setCommunication_Phone() {
        this.communicationPhone = payorProfileData.get("Communication_Phone");
    }

    public String getCommunicationPhone() {
        setCommunication_Phone();
        return communicationPhone;
    }

    public void setCommunicationLetter() {
        this.communicationLetter = payorProfileData.get("Communication_Letter");
    }

    public String getCommunicationLetter() {
        setCommunicationLetter();
        return communicationLetter;
    }

    public void setCommunicationEmail() {
        this.communicationEmail = payorProfileData.get("Communication_Email");
    }

    public String getCommunicationEmail() {
        setCommunicationEmail();
        return communicationEmail;
    }

    public void setRoutingNumber() {
        this.routingNumber = payorProfileData.get("Bank_Routing_Number");
    }

    public String getRoutingNumber() {
        setRoutingNumber();
        return routingNumber;
    }

    public void setAccountNumber() {
        this.accountNumber = payorProfileData.get("Bank_Account_Number");
    }

    public String getAccountNumber() {
        setAccountNumber();
        return accountNumber;
    }

    public void setAccountType() {
        this.accountType = payorProfileData.get("Bank_Account_Type");
    }

    public String getAccountType() {
        setAccountType();
        return accountType;
    }

    public void setCreditCardNumber() {
        this.creditCardNumber = payorProfileData.get("Credit_Card_Number");
    }

    public String getCreditCardNumber() {
        setCreditCardNumber();
        return creditCardNumber;
    }

    public void setExpiryDate() {
        this.expiryDate = payorProfileData.get("Credit_Card_Expiry");
    }

    public String getExpiryDate() {
        setExpiryDate();
        return expiryDate;
    }

    public void setCreditCardType() {
        this.creditCardType = payorProfileData.get("Credit_Card_Type");
    }

    public String getCreditCardType() {
        setCreditCardType();
        return creditCardType;
    }
}
