package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreLateFeeDetails {
    String lateFeeCount;
    String lateFeeAmount;

    public StoreLateFeeDetails() {
    }

    public String toString() {
        return "Late Fee details are:->(lateFeeCount=" + this.getLateFeeCount() + ", lateFeeAmount=" + this.getLateFeeAmount() + ")";
    }
}
