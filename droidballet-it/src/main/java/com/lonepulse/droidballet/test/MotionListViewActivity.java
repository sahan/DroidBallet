package com.lonepulse.droidballet.test;

/*
 * #%L
 * DroidBallet Integration Tests
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lonepulse.droidballet.core.HiggsField;
import com.lonepulse.droidballet.widget.LinearMotionListView;

/**
 * <b>This activity makes use of DroidBallet's {@link LinearMotionListView}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionListViewActivity extends Activity {

	
	/**
	 * <p>The instance of the {@link MotionListView}. 
	 */
	private LinearMotionListView linearMotionListView;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_motion_list_view);
		
		linearMotionListView = ((LinearMotionListView)findViewById(android.R.id.list));
		
		linearMotionListView.setAdapter(
			new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, 
				getResources().getStringArray(R.array.sample_list_values)));		

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onResume() {
	
		super.onStart();
		
		HiggsField.INSTANCE.activate();
		linearMotionListView.register();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause() {
	
		super.onPause();
		
		HiggsField.INSTANCE.deactivate();
		linearMotionListView.unregister();
	}
}
