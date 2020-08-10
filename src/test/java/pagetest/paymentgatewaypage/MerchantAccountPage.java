package pagetest.paymentgatewaypage;

import config.EnvProperty;
import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.util.Map;

import static helper.AppConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
@Slf4j
public class MerchantAccountPage extends AbcCommonAbstractPage<MerchantAccountPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.UNOAPP_INI);
    private By merchantPageTab = By.xpath("//div[text() = 'Merchants']");
    private By locationSearchBox = By.xpath("//div[@data-abc-id='searchMerchantsInput']//input[@name='searchMerchantsInput']");
    private By processorSpecificId = By.xpath("//*[@id='processorSpecificId-processors[0]']");
    private String merchatSaveButton = "//span[text()='Save']";
    private By saveBtn = By.xpath("//span[text()='Save']");
    private By selectProcessorType = By.xpath("//div[@data-abc-id = 'processor'] //div[contains(@class, 'fieldWrapper')]//input[@name='processors[0].id']");
    private By processorDropDownButton = By.xpath("//i[@data-abc-id='processorIcon']");
    private By selectAmex = By.xpath("//li[contains(@class ,'option')]//span[@data-abc-id='optionLabel'][contains(.,'AMEX')]");
    private String clickableLocList = "//td[text() = '%s']";
    private By addProcessorButton = By.xpath("//button[@data-abc-id='addProcessorButton']");
    public By get_location_to_select(String locationName) {
        return By.xpath(String.format(clickableLocList, locationName));
    }

    public MerchantAccountPage select_location(String locationName) {
        log.info("Selecting the location");
        click(get_location_to_select(locationName));
        wait_until(2);
        return me();
    }

    public MerchantAccountPage select_merchant_tab() {
        log.info("Click on Config Link");
        wait_until(2);
        return click(merchantPageTab);
    }

    public MerchantAccountPage config_merchant(String locationName) {
        log.info(" Search and select the location > " + locationName + " data set");
        verify(presenceOfElementLocated(locationSearchBox), 60, 2000);
        enter(locationSearchBox, locationName);
        wait_until(2);
        enter_by_key();
        return me();
    }

    public MerchantAccountPage processor_specific_id(String processorSpecificId) {
        log.info("Enter the processor specific id");
        wait_until(2);
        return enter(this.processorSpecificId, processorSpecificId);

    }

    public By click_on_save_btn() {
        log.info("Save button is clicked");
        wait_until(2);
        return By.xpath(merchatSaveButton);
    }
    public MerchantAccountPage save_merchant(){
        log.info("Save button is clicked");
        wait_until(2);
        return click(saveBtn);
    }
    public MerchantAccountPage create_merchant_account(Map<String, String> merchantData) {
        log.info("Creating Merchant for location.");
        select_merchant_tab();
        if (merchantData.containsKey(SECONDLOCATION)) {
            config_merchant(merchantData.get(SECONDLOCATION));
            select_location(merchantData.get(SECONDLOCATION));
        } else {
            config_merchant(merchantData.get(LOCATION_NAME));
            select_location(merchantData.get(LOCATION_NAME));
        }
        add_processor();
        select_processor_type(merchantData.get(REGIONS_BANK));
        wait_until(2);
        click_on_save_btn();
        return me();
    }

    public MerchantAccountPage select_processor_type(String dataSetName) {
        log.info("Select processor type.");
        wait_until(2);
        verify(presenceOfElementLocated(selectProcessorType), 60, 2000);
        enter(selectProcessorType, dataSetName);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public MerchantAccountPage select_processor_type_vantiv(String dataSetName) {
        log.info("Select processor for vantiv type.");
        wait_until(2);
        verify(presenceOfElementLocated(selectProcessorType), 60, 2000);
        enter(selectProcessorType, dataSetName);
        wait_until(2);
        press_key_down();
        enter_by_key();
        wait_until(3);
        return me();
    }

    public String get_location_name(String locationKey) {
        log.info("Enter Account Holder Name");
        return env.getConfigPropertyValue("LOCATION", locationKey);

    }

    public MerchantAccountPage add_processor() {
        log.info("Clicking on add processor button.");
        wait_until(3);
        return click(addProcessorButton);
    }

    public MerchantAccountPage create_merchant_vantiv_for_another_location(String locName) {
        log.info("Creating Merchant for 2nd location.");
        select_merchant_tab();
        config_merchant(get_location_name(locName));
        select_location(env.getConfigPropertyValue("LOCATION", locName));
        add_processor();
        select_processor_type_vantiv(env.getConfigPropertyValue("Processor", "processorVantivType"));
        wait_until(1);
        processor_specific_id(generate_random_number_by_range(5, 9999999));
        save_merchant();
        return me();
    }

    public MerchantAccountPage create_merchant_regions_bank_for_second_location(String locName) {
        log.info("Creating Merchant for 2nd location.");
        select_merchant_tab();
        config_merchant(get_location_name(locName));
        select_location(env.getConfigPropertyValue("LOCATION", locName));
        add_processor();
        select_processor_type_vantiv(env.getConfigPropertyValue("Processor", "processorTypeBank"));
        wait_until(1);
        processor_specific_id(generate_random_number_by_range(5, 9999999));
        click_on_save_btn();
        return me();
    }
    public MerchantAccountPage create_merchant_vantiv() {
        log.info("Creating Merchant for location.");
        select_merchant_tab();
        config_merchant(env.getConfigPropertyValue("LOCATION", "locationName"));
        select_location(env.getConfigPropertyValue("LOCATION", "locationName"));
        add_processor();
        select_processor_type_vantiv(env.getConfigPropertyValue("Processor", "processorVantivType"));
        wait_until(1);
        processor_specific_id(generate_random_number_by_range(5, 9999999));
        save_merchant();
        return me();
    }

    public MerchantAccountPage configure_merchant_amex() {
        log.info("Configure Merchant for Amex Processor.");
        select_merchant_tab();
        config_merchant(env.getConfigPropertyValue("LOCATION", "locationName"));
        select_location(env.getConfigPropertyValue("LOCATION", "locationName"));
        add_processor();
        wait_until(2);
        select_processor_type_vantiv(env.getConfigPropertyValue("Processor", "processorAmexType"));
        click_on_save_btn();
        return me();
    }

    public MerchantAccountPage create_merchant_for_vantiv_and_eft() {
        log.info("Configuring Merchant for VANTIV and EFT Processors.");
        select_merchant_tab();
        config_merchant(env.getConfigPropertyValue("LOCATION", "locationName"));
        select_location(env.getConfigPropertyValue("LOCATION", "locationName"));
        add_processor();
        select_processor_type_vantiv(env.getConfigPropertyValue("Processor", "processorVantivType"));
        processor_specific_id(generate_random_number_by_range(5, 9999999));
        wait_until(1);
        add_processor();
        select_processor_type(env.getConfigPropertyValue("Processor", "processorTypeBank"));
        click_on_save_btn();
        return me();

    }
}