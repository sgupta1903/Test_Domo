package pagetest.unoapppage;
/*
Created By: Shilpi Gupta
Updated By:
Date: 09/19/2018
*/

import helper.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pagetest.businessapppage.AbcCommonAbstractPage;
import java.util.Map;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class UnoAppNotesTabPage extends AbcCommonAbstractPage<UnoAppNotesTabPage> {
    protected By notesTab = By.xpath("//div[contains(text(),'Notes')]");
    protected By blankNotesTab = By.xpath("//p[text() = 'There are no notes to display.']");
    protected String description = "//td[@data-abc-id = 'note.text']";
    protected By addButton = By.xpath("//i[@data-abc-id='animateSidebarIcon']");
    protected By codeInput = By.xpath("//div[@id='actionCode']//div[contains(@class,'fieldWrapper')]//input[@name='actionCode']");
    protected By notesTextArea = By.xpath("//div[@data-abc-id='textInput']//textarea");
    protected By notesCodeInTable = By.xpath("//td[@data-abc-id='actionCode.numericCode']");
    protected By notesDescriptionInTable = By.xpath("//td[@data-abc-id='note.text']");
    protected String noteSaveButton = "//button[@data-abc-id='noteFormSubmit']";
    protected By notesRecord = By.xpath("//tr[@data-abc-id='notesListRow']");
    protected By notesEditButton = By.xpath("//i[@data-abc-id='editButtonIcon']");


    public UnoAppNotesTabPage select_notes_tab() {
        log.info("Selecting notes tab");
        verify(presenceOfElementLocated(notesTab), 60, 2000);
        click(notesTab);
        return me();
    }
    public By get_member_notes_description(String memberDescription) {
        log.info("Verify personal information section is present");
        return By.xpath(String.format(description, memberDescription));
    }
    public UnoAppNotesTabPage verify_blank_notes_tab() {
        log.info("Verifying notes tab when no notes are present frequency");
        verify(presenceOfElementLocated(blankNotesTab), 60, 2000);
        click(blankNotesTab);
        return me();
    }

    public UnoAppNotesTabPage verify_primary_member_notes(Map<String,String> memberNotes) {
        log.info("Verifying notes tab when no notes are present frequency");
        verify(presenceOfElementLocated(get_member_notes_description(memberNotes.get(AppConstants.MEMBER_NOTES))));
        return me();
    }

    public UnoAppNotesTabPage click_add_button() {
        log.info("clicking on add button");
        click(addButton);
        return me();
    }

    public UnoAppNotesTabPage enter_code(String code) {
        log.info("entering the code");
        wait_until(2);
        enter(codeInput, code);
        enter_by_key();
        return me();
    }

    public UnoAppNotesTabPage enter_notes_text(String text) {
        log.info("entering the notes text");
        enter(notesTextArea, text);
        enter_by_key();
        return me();
    }

    public By click_save_button() {
        log.info("click on save button");
        return By.xpath(noteSaveButton);

    }

    public UnoAppNotesTabPage verify_notes_code(String notesCode) {
        log.info("verifying code in the table : " +notesCode);
        wait_until_page_loaded();
        verify_element_by_text(notesCodeInTable, notesCode);
        return me();
    }

    public UnoAppNotesTabPage verify_notes_description(String notesDescription) {
        log.info("verifying code in the table : " + notesDescription);
        verify_element_by_text(notesDescriptionInTable, notesDescription);
        return me();
    }
    public UnoAppNotesTabPage select_notes_record() {
        log.info("Selecting notes record from the table");
        click(notesRecord);
        return me();
    }
    public UnoAppNotesTabPage click_edit_button() {
        log.info("Click on edit button");
        wait_until_page_loaded();
        click(notesEditButton);
        return me();
    }


}
