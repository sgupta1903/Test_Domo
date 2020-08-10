package pagetest.paymentgatewaypage;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;
@Slf4j
public class ProcessorPage extends AbcCommonAbstractPage<ProcessorPage> {
    private By processorTab = By.xpath( "//div[text() = 'Processors']" );

    public ProcessorPage click_processor_tab(){
        log.info( "Clicking on Processor Tab" );
        return click( processorTab );
    }
    public ProcessorPage view_processor(){
        log.info("Showing all processor for selected company.");
        click_processor_tab();
        wait_until(2);
        return me();
    }
}
