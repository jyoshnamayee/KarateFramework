package com.api.karate.automation;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;

public class ParallelRunnerTest {

    @Test
    public void executeKarateTests() {

        Results result = Runner.builder()
                .path("classpath:com/api/karate/automation/requests")
                .outputCucumberJson(true)
                .parallel(10);

        System.out.println("Total features => " + result.getFeaturesTotal());
        System.out.println("Total Scenarios => " + result.getScenariosTotal());
        System.out.println("Total passed scenarios => " + result.getScenariosPassed());

        generateCucumberReport(result.getReportDir());

        Assertions.assertEquals(0, result.getFailCount(), result.getErrorMessages());
    }

    public static void generateCucumberReport(String karateOutputPath) {

        Collection<File> jsonFiles = FileUtils.listFiles(
                new File(karateOutputPath),
                new String[]{"json"},
                true);

        List<String> jsonPaths = jsonFiles.stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());

        Configuration config = new Configuration(
                new File("target"),
                "Karate Parallel Execution");

        // Add classifications BEFORE generating report
        config.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        config.addClassifications("User", "api_testing");
        config.addClassifications("Operating System", System.getProperty("os.name"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();

        try {
            URL reportPath = Paths.get("target/cucumber-html-reports/overview-features.html")
                    .toUri()
                    .toURL();

            System.out.println("\nKarate Cucumber Report published at: " + reportPath);
        } catch (Exception e) {
            System.out.println("Report path error: " + e.getMessage());
        }
    }
}