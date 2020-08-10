package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreClubDetails {
    private String address;
    private String city;
    private String zipCode;
    private String phone;
    private String clubNumber;
    private String clubName;
    private String agreementNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClubNumber() {
        return clubNumber;
    }

    public void setClubNumber(String clubNumber) {
        this.clubNumber = clubNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public StoreClubDetails() { }

    public String toString() {
        return "Club Details are:->(address=" + this.getAddress() + ", city=" + this.getCity() + ", ZipCode=" + this.getZipCode() + ", Phone=" + this.getPhone() + ", ClubNumber=" +this.getClubNumber()+ ", ClubName=" +this.getClubName()+ ",AgreementNumber=" +this.getAgreementNumber()+")";
    }
}
