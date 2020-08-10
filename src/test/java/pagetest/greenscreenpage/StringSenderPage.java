package pagetest.greenscreenpage;

import config.EnvProperty;
import helper.AppConstants;
import pagetest.businessapppage.AbcCommonAbstractPage;

import java.io.IOException;

public class StringSenderPage extends AbcCommonAbstractPage<StringSenderPage> {
    EnvProperty env = EnvProperty.getInstance(AppConstants.DATA_MIGRATION_INI);

    public StringSenderPage send_string() throws IOException
    {
        String ahkPath = env.getConfigPropertyValue("APP_PARAMS", "AHK PATH");
        String scriptPath = System.getProperty("user.dir") + env.getConfigPropertyValue("APP_PARAMS", "SCRIPT PATH");
        get_run_time_instance().exec(new String[] { ahkPath, scriptPath,System.getProperty("user.dir") + env.getConfigPropertyValue("APP_PARAMS", "DM_INI_PATH")} );
        return me();
    }

     public Runtime get_run_time_instance()
     {
     return Runtime.getRuntime();
     }

}