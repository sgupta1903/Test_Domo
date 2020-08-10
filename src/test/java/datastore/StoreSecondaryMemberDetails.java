package datastore;

import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreSecondaryMemberDetails {
    String secondoryMemberName;

    public StoreSecondaryMemberDetails() {
    }
}
