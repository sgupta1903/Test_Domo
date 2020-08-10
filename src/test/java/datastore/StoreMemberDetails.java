package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreMemberDetails {
    String memberName;
    String memberAddress;
    String memberCity;
    String memberZipCode;
    String memberCountry;
    String memberEmail;
    String memberHomePhone;
    String memberWorkPhone;
    String memberEmergencyPhone;
    String memberCellPhone;
    String memberStatus;
    String memberSinceDate;
    String membershipType;

    public StoreMemberDetails(){

    }

   /* public StoreMemberDetails(String address, String city, String zipcode, String country, String name){
        memberAddress=address;
        memberCity=city;
        memberZipCode=zipcode;
        memberCountry=country;
        memberName=name;

    }

    public StoreMemberDetails(String workPHone, String cellPhone, String emergencyPhone, String SinceDate,String membershipType,String homePhone){
        memberWorkPhone=workPHone;
        memberCellPhone=cellPhone;
        memberEmergencyPhone=emergencyPhone;
        this.membershipType=membershipType;
        memberHomePhone=homePhone;
        memberSinceDate=SinceDate;
    }*/
}
