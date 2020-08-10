package sdk.generatestatement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )

public class CreateGenerateStatementData
{
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String accountId;

    public String getLocationId()
    {
        return locationId;
    }

    public void setLocationId( String locationId )
    {
        this.locationId = locationId;
    }

    public void setAccountId( String accountId )
    {
        this.accountId = accountId;
    }

    public CreateGenerateStatementData()
    {
    }
}
