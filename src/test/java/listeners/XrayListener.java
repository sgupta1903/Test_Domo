package listeners;

import org.testng.ITestNGListener;
import org.testng.ITestNGListenerFactory;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.internal.ClassHelper;
import org.testng.reporters.XMLReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XrayListener
{
    public static class MyListenerFactory implements ITestNGListener,ITestNGListenerFactory
    {

        @Override
        public ITestNGListener createListener(Class<? extends ITestNGListener> listenerClass) {
            System.err.println("Printing " + listenerClass.getName());
            ITestNGListener instance =  ClassHelper.newInstance(listenerClass);
            if (instance instanceof XMLReporter ) {

                    String current = Paths.get("test-output") + File.separator + "new" + File.separator;
                    ((XMLReporter) instance).getConfig().setOutputDirectory(current);
                    ((XMLReporter) instance).getConfig().setGenerateTestResultAttributes(true);
            }
            return instance;
        }
    }

    public static class MyReporter extends XMLReporter {
    }
}
