package datastore;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreRenewalDetails {
    String renewalFlag;
    String renewalType;
    String renewalDate;
    String renewalAmount;
    String noOfPayments;

    public StoreRenewalDetails() { }

    public String toString() {
        return "Renwal Details are:->(renewalFlag=" + this.getRenewalFlag() + ", renewalType=" + this.getRenewalType() + ", renewalDate=" + this.getRenewalDate() + ", renewalAmount=" + this.getRenewalAmount() + ", noOfPayments=" + this.getNoOfPayments() + ")";
    }
}
