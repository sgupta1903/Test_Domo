package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StorePcsDetails {
    String subscriptionCancelStatus;

    public StorePcsDetails(){}
}
