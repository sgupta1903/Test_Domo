package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StorePaymentMethodDetails {
    String paymentType;
    String payorName;
    String routingNumber;
    String lastFour;
    String bankAccountType;
    String expirationDate;
    String creditCardType;
    String payorPGName;

    public StorePaymentMethodDetails() {
    }

    public String toString() {
        return "Payment Method Details are:->(paymentType=" + this.getPaymentType() + ", payorName=" + this.getPayorName() + ", routingNumber=" + this.getRoutingNumber() + ", lastFour=" + this.getLastFour() + ", bankAccountType=" + this.getBankAccountType() + ", expirationDate=" + this.getExpirationDate() + ",creditCardType=" + this.getCreditCardType() + ")";
    }
}

