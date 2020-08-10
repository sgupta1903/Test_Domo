package pagetest.obcUIPage;

import datastore.StoreAdditionalMemberDetails;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;
import static helper.AppConstants.*;
import static util.UtilityGeneric.convertObjectIntoJsonString;

public class AccountSummaryUpdatePage extends AbcCommonAbstractPage<AccountSummaryUpdatePage> {

    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    public  StoreAdditionalMemberDetails storeAdditionalMemberDetails= new StoreAdditionalMemberDetails();

    private By accountSummaryUpdateLink = By.xpath("//a[contains(text(),'Account Summary Update')]");
    private By drivingLicense=By.xpath("//input[@id='f_driver']");
    private By ssn=By.xpath("//input[@id='f_hostSSN']");
    private By birthDate=By.xpath("//input[@id='f_dob']");
    private By employer=By.xpath("//input[@id='f_employer']");
    private By occupation=By.xpath("//input[@id='f_occupation']");

    public StoreAdditionalMemberDetails store_additional_information(){
        click(accountSummaryUpdateLink);
        String drivingLicenseObc=find_element_value(drivingLicense).replaceAll("[^0-9]", "");
        String ssnObc=find_element_value(ssn).replaceAll("[^0-9]", "");
        String birthDateObc=find_element_value(birthDate);
        String employerObc=find_element_value(employer).trim();
        String occupationObc=find_element_value(occupation).trim();

        storeAdditionalMemberDetails.setDrivingLicense(drivingLicenseObc);
        storeAdditionalMemberDetails.setSsn(ssnObc);
        storeAdditionalMemberDetails.setDateOfBirth(birthDateObc);
        storeAdditionalMemberDetails.setEmployer(employerObc);
        storeAdditionalMemberDetails.setOccupation(occupationObc);

        logger.info(BLUE_UNDERLINED+"************Additional member details are mentioned below***************"+ANSI_RESET);
        logger.info(String.valueOf(storeAdditionalMemberDetails));
        logger.info(convertObjectIntoJsonString(storeAdditionalMemberDetails));
        return storeAdditionalMemberDetails;
    }
}
