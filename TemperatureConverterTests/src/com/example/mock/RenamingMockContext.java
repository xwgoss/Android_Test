package com.example.mock;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;
import android.util.Log;

public class RenamingMockContext extends RenamingDelegatingContext {
	private static final String PREFIX="test.";

	public RenamingMockContext(Context context) {
		super(new DelegatedMockContext(context), PREFIX);
		// TODO Auto-generated constructor stub
	}


	
	private static class DelegatedMockContext extends MockContext{
		private Context mDelegatedContext;
		public DelegatedMockContext(Context content){
			mDelegatedContext=content;
		}
		@Override
		public String getPackageName() {
			// TODO Auto-generated method stub
			return mDelegatedContext.getPackageName();
		}
		@Override
		public SharedPreferences getSharedPreferences(String name, int mode) {
			// TODO Auto-generated method stub
			return mDelegatedContext.getSharedPreferences(PREFIX+name, mode);
		}
		
		
	}

}
