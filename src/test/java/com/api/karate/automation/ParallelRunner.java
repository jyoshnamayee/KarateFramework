package com.api.karate.automation;


import org.junit.jupiter.api.Test;

import com.intuit.karate.Runner;
import com.intuit.karate.Runner.Builder;

public class ParallelRunner {
	@Test
	public void executeKarateTests() {
		Builder aRunner = new Builder();
		aRunner.path("classpath:com/api/karate/automation/requests");
		aRunner.parallel(5);
	
		
	}
}
