package reporter;

import config.EnvProperty;
import org.apache.commons.lang3.StringUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import reportingdata.SuiteResults;
import reportingdata.SuiteResultsDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Reporter implements IReporter {

    EnvProperty envProperty;
    String outputDirectory;


    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
        String outputDirectory) {

        if (suites.size() > 0) {
            envProperty = (EnvProperty) suites.get(0).getAttribute( EnvProperty.class.getName());
        }

        this.outputDirectory = System.getProperty("reportOutputLocation");
        if (StringUtils.isBlank(this.outputDirectory)) {
            this.outputDirectory = getConfigParamValue("REPORTING", "reportOutputLocation");
        }

        if(StringUtils.isBlank(this.outputDirectory)) {
            this.outputDirectory = outputDirectory;
        }

        String pdfFormat = System.getProperty("pdf");
        if (StringUtils.isBlank(pdfFormat)) {
            pdfFormat = getConfigParamValue("REPORTING", "pdf");
        }

        if("yes".equalsIgnoreCase(pdfFormat)) {
            SuiteResultsDao dao = new SuiteResultsDao(suites);
          //  renderPDFReport(dao.getSuiteResultsLst());
        }
    }

    private String getConfigParamValue(String section, String param) {
        Map<String, String> configSection = this.envProperty.get(section.toUpperCase());
        if (configSection != null) {
            return configSection.get(param);
        }

        return "";
    }

   /* private void renderPDFReport(List<SuiteResults> suiteResultsLst) {

        try {
            HashMap<String,List<SuiteResults>> context = new HashMap<String, List<SuiteResults>>();
            context.put("APP_CONTEXT_KEY_SUITERESULTSDATASET", suiteResultsLst);
            EnvProperty config = new EngineConfig();
            config.setLogConfig("", Level.OFF);
            Platform.startup(config);
            final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
                .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            IReportEngine engine = FACTORY.createReportEngine(config);

            // Open the report design
            IReportRunnable design = engine.openReportDesign(this.getClass().getResourceAsStream("/reportdesign/testresults.rptdesign"));
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);

            task.setAppContext(context);

            DateFormat df = new SimpleDateFormat("MMddyy_HHmmss");

            // render the output in PDF format
            PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            PDF_OPTIONS.setOutputFileName(outputDirectory + "/" + "testresults_" + df.format(new Date()) + ".pdf");
            PDF_OPTIONS.setOutputFormat("pdf");

            task.setRenderOption(PDF_OPTIONS);
            task.run();
            task.close();
            engine.destroy();

        } catch(final Exception ex) {
            throw new RuntimeException("Unable to generate the report, " + ex.getMessage());
        } finally {
            Platform.shutdown();
        }
    }*/
}
