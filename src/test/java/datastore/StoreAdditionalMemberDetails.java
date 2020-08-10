package datastore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreAdditionalMemberDetails {
    // String membershipType;
    String drivingLicense;
    String ssn;
    String dateOfBirth;
    String employer;
    String occupation;

    public StoreAdditionalMemberDetails() {

    }
}


