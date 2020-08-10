package sdk.tax;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class CreateTaxData
{
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String taxCode;
    @JsonProperty
    private String isOverriden;
    @JsonProperty
    private String suggestedTaxRate;
    @JsonProperty
    private String itemCategoryId;
    @JsonProperty
    private String empId;

    public String getEmpId(){return  empId;}

    public void setEmpId(String empId){this.empId=empId;}

    public String getLocationId()
    {
        return locationId;
    }

    public void setLocationId( String locationId )
    {
        this.locationId = locationId;
    }

    public String getTaxCode()
    {
        return taxCode;
    }

    public void setTaxCode( String taxCode )
    {
        this.taxCode = taxCode;
    }

    public String getIsOverriden()
    {
        return isOverriden;
    }

    public void setIsOverriden (String isOverriden){this.isOverriden=isOverriden;}

    public void setFeeMode( String isOverriden )
    {
        this.isOverriden = isOverriden;
    }

    public String getSuggestedTaxRate()
    {
        return suggestedTaxRate;
    }

    public void setSuggestedTaxRate( String suggestedTaxRate )
    {
        this.suggestedTaxRate = suggestedTaxRate;
    }

    public String getItemCategoryId()
    {
        return itemCategoryId;
    }

    public void setItemCategoryId( String itemCategoryId )
    {
        this.itemCategoryId = itemCategoryId;
    }

    public CreateTaxData()
    {
    }
}
