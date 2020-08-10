package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreSecondaryPaymentMethodDetails {
    String secondaryCreditCardNumber;
    String secondaryAccountHolder;
    String secondaryCreditCardType;
    String secondaryCreditCardExpirationDate;
    String secondaryBankAccountNumber;
    String secondaryRoutingNumber;

    public StoreSecondaryPaymentMethodDetails() {
    }

    public String toString() {
        return "Secondary Payment Method Details are:->(secondaryCreditCardNumber=" + this.getSecondaryCreditCardNumber() + ", secondaryAccountHolder=" + this.getSecondaryAccountHolder() + ", secondaryCreditCardType=" + this.getSecondaryCreditCardType() + ", secondaryCreditCardExpirationDate=" + this.getSecondaryCreditCardExpirationDate() + ", secondaryBankAccountNumber=" + this.getSecondaryBankAccountNumber() + ", secondaryRoutingNumber=" + this.getSecondaryRoutingNumber() + ")";
    }
}
