package com.lonepulse.droidballet.widget;

/*
 * #%L
 * DroidBallet
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

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.lonepulse.droidballet.listener.VerticalMotionEvent;
import com.lonepulse.droidballet.listener.VerticalMotionListener;
import com.lonepulse.droidballet.listener.VerticalMotionEvent.VERTICAL_DIRECTION;
import com.lonepulse.droidballet.registry.MotionViewRegistry;

/**
 * <p>An extension of {@link ListView} which listens to {@link VerticalMotionEvent}s 
 * by implementing {@link VerticalMotionListener} and performs the necessary UI updates 
 * for the respective callback methods.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionListView extends ListView implements MotionView, VerticalMotionListener {


	/**
	 * <p>The minimum scroll duration in milliseconds.
	 */
	private static final int DURATION = 1000;
	
	/**
	 * <p>A factor which represents the relative speed of the motion. 
	 */
	private static final float FRICTION = 2.0f;
	
	/**
	 * <p>A flag which determines if motion scrolling is to be disabled momentarily.
	 */
	private boolean scrollDisabled = false;
	
	
	/**
	 * See {@link ListView#ListView(Context)}.
	 */
	public MotionListView(Context context) {
	
		super(context);
		init();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet)}.
	 */
	public MotionListView(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		init();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet, int)}.
	 */
	public MotionListView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		init();
	}
	
	/**
	 * <p>Initializes the {@link OnTouchListener} of this {@link ListView} which momentarily 
	 * disables motion scrolling (by setting {@link #scrollDisabled} to <b>true</b>) while 
	 * the user is actively interacting with the list. 
	 */
	private void init() {
		
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN)
					scrollDisabled = true;

				else if (event.getAction() == MotionEvent.ACTION_UP)
					scrollDisabled = false;

				return false;
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register() {

		MotionViewRegistry.INSTANCE.register(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregister() {
		
		MotionViewRegistry.INSTANCE.unregister(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionUp(VerticalMotionEvent event) {

		if(!scrollDisabled) {
		
			int yAxisReading = MotionListView.processYAxisReading(event.getDirection(), 
																  event.getFilteredOutput()[1], 
																  event.getSensorEvent().sensor.getMaximumRange());
			
			int scrollDistance = processScrollDistance(yAxisReading);
			int scrollDuration = processScrollDuration(yAxisReading);
			
			smoothScrollBy(scrollDistance, scrollDuration);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionDown(VerticalMotionEvent event) {
		
		if(!scrollDisabled) {
		
			int yAxisReading = MotionListView.processYAxisReading(event.getDirection(), 
												                  event.getFilteredOutput()[1], 
												                  event.getSensorEvent().sensor.getMaximumRange());
			
			int scrollDistance = processScrollDistance(yAxisReading);
			int scrollDuration = processScrollDuration(yAxisReading);
			
			smoothScrollBy(scrollDistance, scrollDuration);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionRest(VerticalMotionEvent event) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int processScrollDistance(int yAxisReading) {
		
		return (int) (yAxisReading * Math.pow(yAxisReading, FRICTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int processScrollDuration(int yAxisReading) {

		return (int) (DURATION + Math.pow(yAxisReading, FRICTION));
	}
	
	/**
	 * <p>Takes the motion sensor reading on the Y-Axis and 
	 * converts it to a vector with a direction. This reading 
	 * is specific to {@link MotionListView}s.
	 *
	 * @param direction
	 *  		the {@link VERTICAL_DIRECTION} of the motion
	 *  
	 * @param sensorReading
	 * 			the sensor reading on the Y-Axis
	 *  
	 * @param maxSensorReading
	 * 			the maximum value which can be reached by a 
	 * 			sensor reading 
	 *  
	 * @return the processed Y-Axis sensor reading
	 */
	private static int processYAxisReading(VERTICAL_DIRECTION direction, 
										   float sensorReading, 
										   float maxSensorReading) {
		
		switch (direction) {
		
			case UP:
				return (int) (-1 * (maxSensorReading + sensorReading));
				
			case DOWN:
				return (int) sensorReading;
				
			case NONE: default: return 0;
		}
	}
}
