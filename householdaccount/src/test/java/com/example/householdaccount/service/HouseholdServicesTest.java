package com.example.householdaccount.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HouseholdServicesTest {
	
	@Autowired
	HouseholdServices householdServices;
	
	@Test
	void testSample() {
		System.out.println("test Start");
		int result = householdServices.sample();
		System.out.println("result:"+result);
		
		assertEquals(5,result);
	}

}
