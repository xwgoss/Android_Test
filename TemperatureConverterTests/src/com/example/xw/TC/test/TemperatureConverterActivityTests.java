package com.example.xw.TC.test;

import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertRightAligned;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xw.TC.EditNumber;
import com.example.xw.TC.TemperatureConverter;
import com.example.xw.TC.TemperatureConverterActivity;

public class TemperatureConverterActivityTests extends
ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

	
	private TemperatureConverterActivity mActivity;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	private TextView mCelsius_label;
	private TextView mFahrenheit_label;

	public TemperatureConverterActivityTests() {
		this("TemperatureConverterActivityTests");
	}

	public TemperatureConverterActivityTests(String name) {
		super(TemperatureConverterActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		//温度转换器应该有两个输入域
		mCelsius=(EditNumber)mActivity.findViewById(com.example.xw.TC.R.id.celsius);
		mCelsius_label=(TextView)mActivity.findViewById(com.example.xw.TC.R.id.celsius_label);
		mFahrenheit=(EditNumber)mActivity.findViewById(com.example.xw.TC.R.id.fahrenheit);
		mFahrenheit_label=(TextView)mActivity.findViewById(com.example.xw.TC.R.id.fahrenheit_label);
	}
	
	public final void testPreconditions(){
		assertNotNull(mActivity);
	}
	
	public final void testHasInputFields(){
		assertNotNull(mCelsius);
		assertNotNull(mFahrenheit);
	}
	
	public final void testFieldShouldStartEmpty(){
		assertEquals("", mCelsius.getText().toString());
		assertEquals("",mFahrenheit.getText().toString());
	}
	
	public final void testFieldOnScreen(){
		final Window window=mActivity.getWindow();
		final View origin=window.getDecorView();
		assertOnScreen(origin, mCelsius);
		assertOnScreen(origin, mFahrenheit);
	}
	
	public final void testAlignment(){
		assertLeftAligned(mCelsius_label,mCelsius);
		assertLeftAligned(mFahrenheit_label, mFahrenheit);
		assertLeftAligned(mCelsius,mFahrenheit);
		assertRightAligned(mCelsius, mFahrenheit);
	}
	
	public final void testCelsiusInputFieldCoverEntiresScreen(){
		final int expected=LayoutParams.MATCH_PARENT;
		final LayoutParams lp=mCelsius.getLayoutParams();
		assertEquals("mCelsius layout width is not MATCH_PARENT",expected,lp.width );
	}
	public final void testFahrenheitInputFieldCoverEntiresScreen(){
		final int expected=LayoutParams.MATCH_PARENT;
		final LayoutParams lp=mFahrenheit.getLayoutParams();
		assertEquals("mFahrenheit layout width is not MATCH_PARENT",expected,lp.width );
	}
	
	public final void testFontSize(){
		final float expected=24.0f;
		assertEquals(expected, mCelsius_label.getTextSize());
		assertEquals(expected, mFahrenheit_label.getTextSize());
	}
	
	public final void testMargins(){
		LinearLayout.LayoutParams lp;
		final int expected=6;
		lp=(android.widget.LinearLayout.LayoutParams) mCelsius.getLayoutParams();
		assertEquals(expected, lp.leftMargin);
		assertEquals(expected, lp.rightMargin);
		lp=(android.widget.LinearLayout.LayoutParams) mFahrenheit.getLayoutParams();
		assertEquals(expected, lp.leftMargin);
		assertEquals(expected, lp.rightMargin);
	}
	
	public final void testJustification(){
		final int expected=Gravity.RIGHT|Gravity.CENTER_VERTICAL;
		int actual=mCelsius.getGravity();
		assertEquals(String.format("Expected 0x%02x but was 0x%02x",expected,actual),expected,actual);
		actual=mFahrenheit.getGravity();
		assertEquals(String.format("Expected 0x%2x but was 0x%02x",expected,actual),expected,actual);
		
	}
	
	public final void testVirtualKeyboardSpaceReserved(){
		final int expected=281;
		final int actual=mFahrenheit.getBottom();
		assertTrue("the acutal's value is "+actual,actual<=expected);
	}
	
	@UiThreadTest
	public final void testFahrenheiToCelsiusConversion(){
		mCelsius.clear();
		mFahrenheit.clear();
		final double f=32.f;
		mFahrenheit.requestFocus();
		mFahrenheit.setNumber(f);
		mCelsius.requestFocus();
		final double expected=TemperatureConverter.fahrenheiToCelsius(f);
		final double actualC=mCelsius.getNumber();
		assertEquals(expected, actualC);
	}
	
	public void testInputFilter() throws Throwable{
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mCelsius.requestFocus();
			}
		});
		final Double n=-1.234d;
		sendKeys("MINUS 1 PERIOD 2 PERIOD 3 PERIOD 4");
		Object nr=null;
		try{
			nr=mCelsius.getNumber();
		}catch(NumberFormatException e){
			nr=mFahrenheit.getNumber();
		}
		final String msg="-1.2.3.4 should be filtered to"+n+" but is "+nr;
		assertEquals(msg,n,nr);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
