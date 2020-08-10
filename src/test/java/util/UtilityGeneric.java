package util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.EnvProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.slf4j.LoggerFactory;
import reporter.LogToReporterAppender;

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static helper.AppConstants.*;


@Slf4j
public class UtilityGeneric {
    public static final int RANDOM_UPPER_LIMIT = 30;
    private static final org.slf4j.Logger APPLICATION_LOGGER = org.slf4j.LoggerFactory.getLogger(UtilityGeneric.class);
    static String sectionName;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void getMessage(String message, String expectedMessage, String actualMessage) {
        Assert.assertEquals(message, expectedMessage, actualMessage);
    }

    public static String getDate(int numberOfDays) {
        APPLICATION_LOGGER.info("Fetching required date with Time");
        String requiredDateWithTime =
                ZonedDateTime.now(ZoneOffset.UTC).plusDays(numberOfDays).plusMinutes(1).plusSeconds(RANDOM_UPPER_LIMIT).format(DateTimeFormatter.ofPattern(MM_DD_YYYY_WITH_SLASH));
        return requiredDateWithTime;

    }

    public static String getFormatedDate(String date) {
        APPLICATION_LOGGER.info("Fetching required date with Time");
        LocalDate ld = LocalDate.parse(date);
        String newDate = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ld);
        return newDate;
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));

        }

        return sb.toString();
    }

    public static LogToReporterAppender registerReporterAppender(String pattern) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("logger name");
        rootLogger.setLevel(Level.INFO);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(lc);
        encoder.setPattern(pattern);
        encoder.setOutputPatternAsHeader(true);
        encoder.start();

        LogToReporterAppender appender = new LogToReporterAppender<>();
        appender.setContext(lc);
        appender.setEncoder(encoder);
        appender.start();

        rootLogger.addAppender(appender);
        return appender;
    }

    public static void set_section_name(String section) {
        sectionName = section;
    }

    public static String
    get_section_name() {
        try {
            if (sectionName != null)
                return sectionName;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ("Section name is null");
    }

    public static void clear_ini_file(String fileName) {
        APPLICATION_LOGGER.info("Clearing all the data from the ini file > " + fileName);
        EnvProperty envProperty = EnvProperty.getInstance(fileName);
        envProperty.clearIniFile();
    }

    public static String convertObjectIntoString(Object key) {
        return (String) key;
    }

    public static String convertObjectIntoJsonString(Object obj) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


    /* This method will accept filepath of type text
    and will return list of all the lines present in the
    text file
   */
    public static List<String> read_text_file_and_return_list(String FilePath) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(FilePath),
                    StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /* This method will accept n number of string
    and will return a list of those strings
     */
    public static List<String> returnListOfString(String... stringName) {
        List<String> list = new ArrayList<>();

        for (String currentString : stringName) {
            list.add(currentString);
        }
        return list;
    }

    /*
    This method reads text file and
    write data into ini file
     */
    public static void convert_xml_formated_text_file_into_ini(String textFilePath, EnvProperty iniFile, List<String> testCaseIds) {

        Iterator<String> testCaseItr = testCaseIds.iterator();

        while (testCaseItr.hasNext()) {
            String currentTestCaseId = testCaseItr.next();
            List<String> mylines = read_text_file_and_return_list(textFilePath);
            Iterator<String> itr = mylines.iterator();
            while (itr.hasNext()) {
                String currentText = itr.next();
                if (!currentText.isEmpty() && currentText.contains(currentTestCaseId)) {
                    String[] itrTag = currentText.split("<|>");
                    if (itrTag[1].contains(currentTestCaseId)) {
                        String sectionName = (itrTag[1].split("_"))[0];
                        String nextTag = itr.next();
                        if (nextTag.contains("Not")) {
                            iniFile.writeIniFile(sectionName, "status", "Pass");
                            iniFile.writeIniFile(sectionName, "Key", "Data Not Found");
                            itr.next();
                        } else if (itrTag[1].contains(currentTestCaseId) && !(itrTag[1].split("_")[0]).contains("/") && !nextTag.split("<|>")[1].contains("/")) {
                            iniFile.writeIniFile(sectionName, "status", "Pass");
                            while (!nextTag.contains(currentTestCaseId)) {
                                String currentKey = (nextTag.split("<|>"))[1].trim();
                                String value = (nextTag.split("<|>"))[2].trim();
                                iniFile.writeIniFile(sectionName, currentKey, value);
                                nextTag = itr.next();
                            }
                        } else if (itrTag[1].contains(currentTestCaseId) && (nextTag.split("<|>")[1]).contains("/")) {
                            iniFile.writeIniFile(sectionName, "status", "Fail");
                        }
                    } else
                        log.info("Test case id ->" + currentTestCaseId + "not present in the file");
                }
            }
        }
    }

    /* This method will check if file exist in resources
    folder or not for given time frame
    At the time of method call,
    timeframe is entered in minutes
 */
    public static void check_file_existence_in_resources(String fileName, int timeFrame) {
        boolean exists = false;
        long startTime = System.currentTimeMillis();
        int time = timeFrame * SIXTY_THOUSAND;

        while (String.valueOf(exists).contains(FALSE) && System.currentTimeMillis() - startTime < time) {
            File fileCheck = new File(System.getProperty("user.dir") + SRC_TO_RESOURCE_PATH + fileName);
            exists = fileCheck.exists();
        }
        if (String.valueOf(exists).contains(TRUE)) {
            log.info("File with name -> " + fileName + " exists in resources folder");
        } else {
            log.info("File with name -> " + fileName + " does exists in resources folder");
            Assert.fail();
        }
    }

    public static String get_converted_date(String acutalFormattedDate) {
        String actualDate = null;
        String finalExpectedDate = null;
        int length = acutalFormattedDate.length();
        if (length == 5) {
            actualDate = "0" + acutalFormattedDate;
            String month = actualDate.substring(0, 2);
            String day = actualDate.substring(2, 4);
            String year = "20" + actualDate.substring(4, 6);
            finalExpectedDate = month + "/" + day + "/" + year;
        } else if (length == 6) {
            String month = acutalFormattedDate.substring(0, 2);
            String day = acutalFormattedDate.substring(2, 4);
            String year = "20" + acutalFormattedDate.substring(4, 6);
            finalExpectedDate = month + "/" + day + "/" + year;
        } else {
            finalExpectedDate = acutalFormattedDate;
        }
        return finalExpectedDate;
    }
}

