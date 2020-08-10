package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreServiceFeeDetails {
    String serviceFeeCount;
    String serviceFeeAmount;

    public StoreServiceFeeDetails() {
    }

    public String toString() {
        return "Service fee  details are:->(serviceFeeCount=" + this.getServiceFeeCount() + ", serviceFeeAmount=" + this.getServiceFeeAmount() + ")";
    }
}
