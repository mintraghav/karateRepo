package feature;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.util.Asserts;
import org.apache.logging.log4j.core.util.Assert;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.*;
import com.intuit.karate.junit5.Karate.Test;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ApiRunnerTest 
{
	@Test
	public void testParallel()
	{
		//System.setProperty("karate.env", "demo"); // ensure reset if other tests (e.g. mock) had set env in CI
        Results results = Runner.parallel(getClass(),2);
         
        generateReport(results.getReportDir());
        //assertTrue(results.getErrorMessages(), results.getFailCount() == 0); 
	}
	
	@Karate.Test
    Karate testSample() {
		return Karate.run("pagesTest").relativeTo(getClass());
    }
    
    @Karate.Test
    Karate testTags() {
        return Karate.run("usersTest").tags("@second").relativeTo(getClass());
    }

//    @Karate.Test
//    Karate testFullPath() {
//        return Karate.run("classpath:karate/tags.feature").tags("@first");
//    }
    public static void generateReport(String karateOutputPath) {        
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        final List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();        
    }

}
