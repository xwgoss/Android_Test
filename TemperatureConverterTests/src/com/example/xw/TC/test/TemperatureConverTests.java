/**
 * 
 */
package com.example.xw.TC.test;

import java.util.HashMap;

import com.example.xw.TC.InvalidTemperatureException;
import com.example.xw.TC.TemperatureConverter;

import junit.framework.TestCase;

/**
 * @author xwgoss
 *
 */
public class TemperatureConverTests extends TestCase {
	
	private static final HashMap<Double,Double> conversionTableDouble=new HashMap<Double,Double>();
	static{
		//initialize(c,f) pairs
		conversionTableDouble.put(0.0, 32.0);
		conversionTableDouble.put(100.0, 212.0);
		conversionTableDouble.put(-1.0,30.20 );
		conversionTableDouble.put(-100.0,-148.0);
		conversionTableDouble.put(32.0, 89.60);
		conversionTableDouble.put(-40.0, -40.0);
		conversionTableDouble.put(-273.0, -459.40);
	}

	/**
	 * @param name
	 */
	public TemperatureConverTests(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.example.xw.TC.TemperatureConverter#fahrenheiToCelsius(double)}.
	 */
	public final void testFahrenheiToCelsius() {
		for(double c:conversionTableDouble.keySet()){
			final double f=conversionTableDouble.get(c);
			final double ca=TemperatureConverter.fahrenheiToCelsius(f);
			final double delta=Math.abs(ca-c);	
			final String msg=""+f+"F=>"+c+"C but i qs "+ca+"(delta"+delta+")";
			assertTrue(msg,delta<0.0001);
		}
	}
	
	/**
	 * Test method for {@link com.example.xw.TC.TemperatureConverter#celsiusToFahrenhei(double)}.
	 */
	public final void testCelsiusToFahrenhei() {
		for(double c:conversionTableDouble.keySet()){
			final double f=conversionTableDouble.get(c);
			final double fa=TemperatureConverter.celsiusToFahrenhei(c);
			final double delta=Math.abs(fa-f);	
			final String msg=""+c+"C=>"+f+"F but is "+fa+"(delta"+delta+")";
			assertTrue(msg,delta<0.0001);
		}
	}
	
	public final void testExceptionForLessThanAbsoluteZeroF(){
		try{
			TemperatureConverter.fahrenheiToCelsius(TemperatureConverter.ABSOLUTE_ZERO_F-1);
			fail();
		}
		catch(InvalidTemperatureException e){
			
		}
	}
	
	public final void testExceptionForLessThanAbsoluteZeroC(){
		try{
			TemperatureConverter.celsiusToFahrenhei(TemperatureConverter.ABSOLUTE_ZERO_C-1);
			fail();
		}
		catch(InvalidTemperatureException e){
			
		}
	}

}
