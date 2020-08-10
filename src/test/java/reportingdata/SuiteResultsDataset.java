package reportingdata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SuiteResultsDataset {

    private Iterator<SuiteResults> itr;
    private List<SuiteResults> suiteResultsLst;

    @SuppressWarnings("unchecked")
    public void open(Object obj, Map<String,Object> map) {
        this.suiteResultsLst = (List<SuiteResults>) map.get("APP_CONTEXT_KEY_SUITERESULTSDATASET");
    }

    public Object next() {
        if (itr == null)
            itr = suiteResultsLst.iterator();
        if (itr.hasNext())
            return itr.next();
        return null;
    }

    public void close() {

    }
}
