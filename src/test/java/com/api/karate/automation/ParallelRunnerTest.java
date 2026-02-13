package com.api.karate.automation;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.Runner.Builder;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.Configuration;

public class ParallelRunnerTest {
	@Test
	public void executeKarateTests() {
//		Builder aRunner = new Builder();
//		aRunner.path("classpath:com/api/karate/automation/requests");
//		Results result = aRunner.parallel(5);
	
//		Assertions.assertEquals(0,result.getFailCount(), "There are some failed scenarios ");
//		
		Builder arunner = new Builder();
//
//        Results result = arunner
//
//		 Results result = Runner.builder()
		Results result = arunner
	                .path("classpath:com/api/karate/automation/requests")
	                .outputCucumberJson(true)
	                .parallel(10);
		System.out.println("Total features => " + result.getFeaturesTotal() );
		System.out.println("Total Scenarios => " + result.getScenariosTotal() );
		System.out.println("Total passed scenarios => " + result.getScenariosPassed() );
		
		  // generateCucumberReport(result.getReportDir());
		   Assertions.assertEquals(0, result.getFailCount(), result.getErrorMessages());
	    }

	   private static void generateCucumberReport(String karateOutputPath) {

	        File reportOutputDirectory = new File("target/cucumber-html-reports");

	        Collection<File> jsonFileCollection = FileUtils.listFiles(reportOutputDirectory, new String[] { "json" }, true);
	        
	        List<String> jsonFiles = new ArrayList<>();
	        jsonFiles.add(karateOutputPath + "/cucumber.json");
	        //jsonFileCollection.forEach(file -> jsonFiles.add(file.getAbsolutePath()));


	        Configuration config = new Configuration(reportOutputDirectory, "Karate Parallel Execution");
	        config.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
	        config.addCustomJsFiles(Collections.singletonList("src/test/java/com/api/karate/automation/CustomReportUI.js"));
	        config.addClassifications("User", "api_testing");
	        config.addClassifications("Operating System", System.getProperty("os.name"));

	        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
	        reportBuilder.generateReports();
	        try {
	            URL reportPath = Paths.get("target", "karate-reports/cucumber-html-reports/overview-features.html").toUri()
	                    .toURL();
	            System.out.println(
	                    "\nKarate Cucumber Report published: " + reportPath.getProtocol() + "://" + reportPath.getPath());
	        } catch (MalformedURLException badReportPath) {
	            System.out.println(badReportPath.getMessage());
	        }
	    }

	 
	}
