package sdk.fee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class CreateFeeData
{
    @JsonProperty
    private String feeId;
    @JsonProperty
    private String accountId;
    @JsonProperty
    private String feeMode;
    @JsonProperty
    private String feeType;
    @JsonProperty
    private String feeTransactionType;
    @JsonProperty
    private String feeValueType;
    @JsonProperty
    private BigDecimal feeValue;

    public String getFeeId()
    {
        return feeId;
    }

    public void setFeeId( String feeId )
    {
        this.feeId = feeId;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId( String accountId )
    {
        this.accountId = accountId;
    }

    public String getFeeMode()
    {
        return feeMode;
    }

    public void setFeeMode( String feeMode )
    {
        this.feeMode = feeMode;
    }

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType( String feeType )
    {
        this.feeType = feeType;
    }

    public String getFeeTransactionType()
    {
        return feeTransactionType;
    }

    public void setFeeTransactionType( String feeTransactionType )
    {
        this.feeTransactionType = feeTransactionType;
    }

    public String getFeeValueType()
    {
        return feeValueType;
    }

    public void setFeeValueType( String feeValueType )
    {
        this.feeValueType = feeValueType;
    }

    public BigDecimal getFeeValue()
    {
        return feeValue;
    }

    public void setFeeValue( BigDecimal feeValue )
    {
        this.feeValue = feeValue;
    }

    public CreateFeeData()
    {
    }
}
