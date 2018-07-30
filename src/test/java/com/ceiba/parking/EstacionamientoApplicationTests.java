package com.ceiba.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EstacionamientoApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testPruebaDosMasDos() {
		int a = 2, b = 2;
		int sumaEsperada = 4;
		
		assertEquals(sumaEsperada, a+b);
	}

}
