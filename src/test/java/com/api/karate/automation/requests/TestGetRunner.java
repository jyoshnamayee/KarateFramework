package com.api.karate.automation.requests;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;;

public class TestGetRunner {
	
	@Test
	public Karate runTest() {
        return Karate.run("postRequest","getRequest").relativeTo(getClass());
	}

}
