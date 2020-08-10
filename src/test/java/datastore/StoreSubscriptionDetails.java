package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreSubscriptionDetails {
    String subscriptionType;
    String subscriptionBeganDate;
    String subscriptionDueAmount;
    String subscriptionNextDueDate;
    String subscriptionExpirationDate;
    String subscriptionItem;
    String subscriptionCancelStatus;
    String subscriptionFreezeStatus;

    public StoreSubscriptionDetails() { }

    /*public String toString() {
        return "Subscription Details are :--->(subscriptionType=" + this.getSubscriptionType() + ", subscriptionBeganDate="
                + this.getSubscriptionBeganDate() + ", subscriptionDueAmount=" + this.getSubscriptionDueAmount() + ", subscriptionNextDueDate="
                + this.getSubscriptionNextDueDate() + ",subscriptionExpirationDate=" + this.getSubscriptionExpirationDate() + ",subscriptionItem="
                + this.getSubscriptionItem() + ",subscriptionCancelStatus=" + this.getSubscriptionCancelStatus()
                + ",subscriptionFreezeStatus=" + this.getSubscriptionFreezeStatus() + ")";
    }*/
}
