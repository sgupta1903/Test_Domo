package testcase.pushpulltest;

import helper.DataBaseHandler;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagetest.pushpullPage.pushpullPage;
import ui.AbstractAutoUITest;

@Slf4j
public class DataSetTest extends AbstractAutoUITest {

    @AfterMethod()
    public void backToDatasets() {
        log.info("Reaching to QA dataset page");
        getPage(pushpullPage.class)
                .back_to_dataset();
    }

    @BeforeClass(description = "Testing of Before steps")
    public void reachToDatasetsPage() {
        log.info("Reaching to dataset page");
        getPage(pushpullPage.class)
                //.close_popup()
                .click_buzz_link()
                .click_data_tab()
                .click_dataset_link();
    }

    @DataProvider(name = "domo-dataset-provider")
    public Object[][] dataProviderMethod() throws InterruptedException {
        return new Object[][]{
                {"staging_delinquent_snapshot", "Select count(*) from fact.delinquent_snapshot"},
                {"staging_drill_across_invoice_payment_goal_trans", "Select count(*) from domo.drill_across_invoice_payment_goal_trans"},
                // {"staging_drill_across_subscription_goal_trans", "Select count(*) from fact.goal_and_subscription_drill_across"},
                {"staging_drill_across_subscription_goal_trans", "SELECT count(*) from domo.drill_across_subscription_goal_trans"},
                {"staging_fact_member_snapshot_daily", "Select count(*) from fact.member_snapshot_daily"},
                {"staging_dim_subscription", "Select count(*) from dim.subscription"},
                {"staging_dim_member", "Select count(*) from dim.member"},
                {"staging_dim_invoice", "Select count(*) from dim.invoice"},
                {"staging_dim_payment", "Select count(*) from dim.payment"},
                {"staging_dim_location", "Select count(*) from dim.location"},
                {"staging_dim_item", "Select count(*) from dim.item"},
                {"staging_dim_organization", "Select count(*) from dim.organization"},
                {"staging_dim_delinquent", "Select count(*) from dim.delinquent"},
                {"staging_dim_invoice_number", "Select count(*) from dim.invoice_number"}
        };
    }

    @Test(dataProvider = "domo-dataset-provider")
    public void datasetTest(String datasetName, String datasetQuery) {
        log.info("clicking on" + datasetName + "data tab");
        getPage(pushpullPage.class)
                .search_dataset(datasetName)
                .click_dataset(datasetName)
                .click_options_dropdown()
                .click_run_now_option()
                .verify_run_successfully();

        DataBaseHandler dataBaseHandler = getDBClient("RegShiftDB");
        dataBaseHandler.verify_query_result(datasetQuery, getPage(pushpullPage.class).get_rows_count(datasetName));

    }
}