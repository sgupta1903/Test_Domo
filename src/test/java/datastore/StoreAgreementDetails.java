package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreAgreementDetails {
        String contactBeganDate;
        String nextDueDate;
        String pastDueAmount;
        String dueAmount;

    public StoreAgreementDetails() {

    }
    public String toString() {
        return "Agreement Details are :--->(contactBeganDate=" + this.getContactBeganDate() + ", nextDueDate=" + this.getNextDueDate() + ", pastDueAmount=" + this.getPastDueAmount() + ", dueAmount=" + this.getDueAmount() + ")";
    }
}
