package pagetest.paymentgatewaypage;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;
@Slf4j
public class AdministrationPage extends AbcCommonAbstractPage<AdministrationPage> {
    private By adminLink = By.xpath( "//a[@href='/payment-gateway-ui/app/administration']" );
    private By commandTab = By.xpath("//div[text() = 'Commands']");
    private By schedulerTab = By.xpath("//div[text() = 'Schedulers']");
    private By webhookTab = By.xpath("//div[text() = 'Webhooks']");
    private By webhookClient = By.xpath("//div[text() = 'Webhook Clients']");
    private By notificationEmails = By.xpath("//div[text() = 'Notification Emails']");

    public AdministrationPage audit_tab(){
        log.info( "Click on Administration Link" );
        wait_until(2);
        return click( adminLink );
    }
    public AdministrationPage command_tab(){
        log.info( "Click on Command Tab" );
        wait_until(1);
        return click( commandTab );
    }
    public AdministrationPage scheduler_tab(){
        log.info( "Click on Scheduler Tab" );
        wait_until(1);
        return click( schedulerTab );
    }
    public AdministrationPage webhook_tab(){
        log.info( "Click on Webhook Tab" );
        wait_until(1);
        return click( webhookTab);
    }
    public AdministrationPage webhook_client_tab(){
        log.info( "Click on Webhook Client Tab" );
        wait_until(1);
        return click( webhookClient );
    }
    public AdministrationPage runtime_tab(){
        log.info( "Click on Scheduler Tab" );
        wait_until(1);
        return click(notificationEmails);
    }
    public AdministrationPage administration_tab(){
        log.info( "Clicking on Administration Tab" );
        audit_tab();
        command_tab();
        scheduler_tab();
        webhook_tab();
        webhook_client_tab();
        runtime_tab();
        return me();
    }
}
