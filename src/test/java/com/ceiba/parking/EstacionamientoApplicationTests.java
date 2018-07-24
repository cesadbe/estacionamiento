package com.ceiba.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
