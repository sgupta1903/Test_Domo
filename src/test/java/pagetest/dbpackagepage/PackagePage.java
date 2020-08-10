package pagetest.dbpackagepage;
import helper.DataBaseHandler;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackagePage extends AbcCommonAbstractPage<PackagePage> {
    protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private static int count = 0;
    private BigDecimal marginTargetAmount = null;


    public List<Map<String, Object>> get_db_automation_test_data(DataBaseHandler dataBaseHandler) {
      return dataBaseHandler.get_multiple_rows("SELECT convert_timezone('US/Eastern',test_date) as test_date_eastern, * FROM public.automated_testing\n" +
                "where test_date like '" + get_current_date("YYYY-MM-dd") + " %'" +
                " order by 1 desc, 2 desc limit 144;");
    }

    public PackagePage verify_automation_test_data_count_greater_than_zero(List<Map<String, Object>> automationTestResult) {
        if (automationTestResult.size() == 0) {
            logger.info("No Tests executed today : " + get_current_date("YYYY-MM-dd"));
            throw new RuntimeException(" All tests skipped.");
        }
        return me();
    }

    public Map<String,Object> get_test_map(List<Map<String, Object>> automationTestResult, String testName) {
        Map<String, Object> testMap = new HashMap<>();
        for (int i = 0; i < automationTestResult.size(); i++) {
            testMap = automationTestResult.get(i);
            if (testName.equals(testMap.get("test_name"))) {
                break;
            } else {
                testMap=null;
            }
        }
        return testMap;
    }

    public PackagePage verify_test_pass_flag(List<Map<String, Object>> automationTestResult, String testName) {
        logger.info("...... Method: verifyTestPassFlag() execution starts: ......");
        Boolean flag = false;
        int sourceCount, targetCount, marginPercent = 15, marginTargetCount = 2147483647;
        BigDecimal sourceAmount, targetAmount;
        Map<String, Object> testMap = get_test_map(automationTestResult,testName);


        testMap = fix_count_format(testMap);
        testMap = fix_amount_format(testMap);

                sourceCount = (int) (testMap.get("source_count"));
                targetCount = (int) (testMap.get("target_count"));
                sourceAmount = (BigDecimal) (testMap.get("source_amount"));
                targetAmount = (BigDecimal) (testMap.get("target_amount"));
        if(sourceCount > targetCount) {
            marginTargetCount = get_count_margin(sourceCount, marginPercent);
        }
        if(sourceAmount.compareTo(targetAmount) == 1) {
            marginTargetAmount = get_amount_margin(sourceAmount, marginPercent);
        }

                if ("true".equals(String.valueOf(testMap.get("test_pass_flag"))) && (testMap.get("source_count").equals(testMap.get("target_count"))) && sourceAmount.compareTo(targetAmount) == 0){ //|| (testMap.get("test_pass_flag") == "false" && (int) testMap.get("target_count") >= marginTargetCount)) {
                    flag = true;
                    logger.info("Source Count, Target Count, Source Amount and Target Amount matched exactly.");
                }

                else if(("false".equals(String.valueOf(testMap.get("test_pass_flag")))) &&
                        (targetCount >= marginTargetCount && targetCount <= sourceCount)
                        && ((targetAmount.compareTo(marginTargetAmount) == 0 || sourceAmount.compareTo(targetAmount) == 1 || sourceAmount.compareTo(targetAmount) == 0)
                        &&  (targetAmount.compareTo(marginTargetAmount) == 1 || sourceAmount.compareTo(targetAmount) == 1 || sourceAmount.compareTo(targetAmount) == 0))) {
                    flag = true;
                    logger.info("Source Count and Target Count matched within the difference of " + marginPercent + "%.");
                }

                else
                    {
                    flag = false;
                    logger.info("Source count - " + sourceCount);
                    logger.info("Target count - " + targetCount);
                    logger.info("Source amount - " + sourceAmount);
                    logger.info("Target amount - " + targetAmount);
                    logger.info("Target is not matched with Source.\n");
                }

                logger.info("...... Method: verifyTestPassFlag() >> executed successfully .......");
                Assert.assertTrue(flag);
        return me();
    }

    public PackagePage verify_test_critical_pass_flag(List<Map<String, Object>> automationTestResult, String testName) {
        logger.info("..... Method: verifyTestCriticalPassFlag() execution starts: ......");
        Boolean flag = false;
        int targetCount = 0, targetPrimaryKeyDistinctCount = 0;
        Map<String, Object> testMap = get_test_map(automationTestResult,testName);

        testMap = fix_count_format(testMap);
        testMap = fix_amount_format(testMap);

        targetCount = (int) (testMap.get("target_count"));
        targetPrimaryKeyDistinctCount = (int) (testMap.get("target_primary_key_distinct_count"));
        if ("true".equals(String.valueOf(testMap.get("test_critical_pass_flag"))) && targetCount == targetPrimaryKeyDistinctCount){
            flag = true;
            logger.info("Target Count and Target Primary Key Distinct Count matched exactly.");
        }
        else {
            flag = false;
            logger.info("Test Critical Pass Flag Failed: \n");
            logger.info("Value of Test Critical Pass Flag for the test " + testName + "is : " + testMap.get("test_critical_pass_flag"));
        }

        Assert.assertTrue(flag);
        return me();
    }


    public int get_count_margin(int source, int marginPercent) {
        logger.info("...... Method: getCountMargin() execution starts ......");
        int margin = ((source * marginPercent) / 100);
        margin = source - margin;
        return margin;
    }

    public BigDecimal get_amount_margin(BigDecimal source, int marginPercent) {
        logger.info("...... Method: getAmountMargin() execution starts: ......");
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal marginPercentBD = new BigDecimal(marginPercent);
        BigDecimal margin = (source.multiply(marginPercentBD)).divide(hundred);
        margin = source.subtract(margin);
        logger.info("My Source amount is : " + source);
        logger.info("New acceptable target Amount is: " + margin);
        return margin;
    }


    public Map<String, Object> fix_count_format(Map<String, Object> testMap) {
        logger.info("...... Method: fixCountFormat() execution starts: ......");
        if ((("0".equals(testMap.get("source_count"))) && ("null".equals(testMap.get("target_count")) && ("null".equals(testMap.get("target_primary_key_distinct_count"))))) ||
                (("null".equals(testMap.get("source_count"))) && ("0".equals(testMap.get("target_count")) && ("0".equals(testMap.get("target_primary_key_distinct_count")))))) {

            testMap.put("source_count",testMap.get("target_count"));
        }
        return testMap;
    }

    public Map<String, Object> fix_amount_format(Map<String, Object> testMap) {
        logger.info("...... Method: fixAmountFormat() execution starts: ......");
        if ((("0.0000".equals(testMap.get("source_amount").toString())) && ("null".equals(String.valueOf(testMap.get("target_amount"))))) ||
                (("null".equals(String.valueOf(testMap.get("source_amount").toString()))) && ("0.0000".equals(testMap.get("target_amount").toString())))) {
            testMap.put("target_amount",testMap.get("source_amount"));
            marginTargetAmount = (BigDecimal) testMap.get("source_amount");
        }
        return testMap;
    }
}
