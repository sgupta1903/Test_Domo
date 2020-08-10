package datastore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
@AllArgsConstructor
public class StoreFreezeStatus {
    String subscriptionFreezeStatus;

    public StoreFreezeStatus(){

    }
}
