package com.example.xw.TC.test;

import com.example.mock.RenamingMockContext;
import com.example.xw.TC.TemperatureConverter;
import com.example.xw.TC.TemperatureConverterApplication;

import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

public class TemperatureConverterApplicationTests extends ApplicationTestCase<TemperatureConverterApplication> {
	
	private TemperatureConverterApplication mApplication;
	public TemperatureConverterApplicationTests() {
		// TODO Auto-generated constructor stub
		this("TemperatureConverterApplicationTests");
	}
	public TemperatureConverterApplicationTests(String string) {
		// TODO Auto-generated constructor stub
		super(TemperatureConverterApplication.class);
		setName(string);
	}
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		final RenamingMockContext mockContext=new RenamingMockContext(getContext());
		createApplication();
		mApplication=getApplication();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public final void testPrecondtions(){
		assertNotNull(mApplication);
	}
	
	public final void testSetDecimalPlaces(){
		final int expected=3;
		mApplication.setDecimalPlaces(expected);
		assertEquals(expected, mApplication.getDecimalPlaces());
	}
	
	
	

}
