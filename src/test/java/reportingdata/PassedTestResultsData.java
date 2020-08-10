package reportingdata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PassedTestResultsData {
    private Iterator<TestResults> itr;
    private List<TestResults> testResultsLst;

    @SuppressWarnings("unchecked")
    public void open(Object obj, Map<String,Object> map) {
        this.testResultsLst = (List<TestResults>) map.get("passedTests");
    }

    public Object next() {
        if (itr == null)
            itr = testResultsLst.iterator();
        if (itr.hasNext())
            return itr.next();

        return null;
    }

    public void close() {

    }
}
