package testcase.datamigrationtest;
/*
Created By: Monika Phoughat mphoughat@bhavnacorp.com
Updated By:
Data Factory to Execute Test Execution
Date: 9/29/2019
*/

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Factory;
@Slf4j
public class DataMigrationFactoryExecutorTest {
    @Factory
    public Object[] createFactoryInstances() {
        log.info("Creating a instance to provide Data Migrated Org- Migrated 4 Orgs");
        return new Object[]{
                new DataValidationTest("METRICS"),
                new DataValidationTest("SCHALLER"),
                new DataValidationTest("PURCELL"),
                new DataValidationTest("PAGELAND")
        };
    }


}