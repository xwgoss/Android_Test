/**
 * 
 */
package com.example.xw.TC.test;

import com.example.xw.TC.EditNumber;

import android.test.AndroidTestCase;

/**
 * @author xwgoss
 *
 */
public class EditNumberTest extends AndroidTestCase {

	private EditNumber mEditNumber;

	public EditNumberTest(){
		this("EditNumberTest");
	}
	/**
	 * @param name
	 */
	public EditNumberTest(String name) {
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		mEditNumber=new EditNumber(mContext);
		mEditNumber.setFocusable(true);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.example.xw.TC.EditNumber#EditNumber(android.content.Context)}.
	 */
	public final void testEditNumberContext() {
		assertNotNull(mEditNumber);
	}

	/**
	 * Test method for {@link com.example.xw.TC.EditNumber#clear()}.
	 */
	public final void testClear() {
		final String value="123.45";
		mEditNumber.setText(value);
		mEditNumber.clear();
		String expectedString="";
		String actualString=mEditNumber.getText().toString();
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test method for {@link com.example.xw.TC.EditNumber#getNumber()}.
	 */
	public final void testGetNumber() {
		mEditNumber.setText("123.45");
		final double expectedString=123.45;
		final double actualString=mEditNumber.getNumber();
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test method for {@link com.example.xw.TC.EditNumber#setNumber(double)}.
	 */
	public final void testSetNumber() {
		mEditNumber.setNumber(123.45);
		final String expectedString="123.45";
		final String actualString=mEditNumber.getText().toString();
		assertEquals(expectedString, actualString);
	}

}
