package com.api.karate.automation.requests;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;;

public class ManualRunner {
	
	@Test
	public Karate runTest() {
        return Karate.run("dataDriven").relativeTo(getClass());
	}

}
