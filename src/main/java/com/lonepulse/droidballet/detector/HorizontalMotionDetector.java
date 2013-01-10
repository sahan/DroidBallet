package com.lonepulse.droidballet.detector;

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

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.View;

import com.lonepulse.droidballet.filter.LowPassFilter;
import com.lonepulse.droidballet.filter.SmoothingFilter;
import com.lonepulse.droidballet.filter.SmoothingFilterException;
import com.lonepulse.droidballet.listener.HorizontalMotionEvent;
import com.lonepulse.droidballet.listener.HorizontalMotionEvent.HORIZONTAL_DIRECTION;

/**
 * <p>A concrete implementation of {@link MotionDetector} which detects <b>horizontal
 * motion</b>.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HorizontalMotionDetector implements MotionDetector<HorizontalMotionEvent> {
	
	
	/**
	 * <p>The minimum scroll duration in milliseconds.
	 */
	private static final int MINIMUM_DURATION = 1000;
	
	/**
	 * <p>A factor which represents the relative speed of the motion. 
	 */
	private static final float FRICTION = 2.0f;

	/**
	 * <p>The instance of the {@link SmoothingFilter} which is used to smooth 
	 * out the values from the motion sensor.
	 */
	private SmoothingFilter smoothingFilter;

	/**
	 * <p>Constructor visibility restricted to prevent direct instantiation.</p> 
	 * 
	 * <p>Use the factory method {@code HorizontalMotionDetector.newInstance();}
	 * 
	 * @param smoothingFilter
	 *            the {@link #smoothingFilter} to use
	 */
	private HorizontalMotionDetector(SmoothingFilter smoothingFilter) {
		
		this.smoothingFilter = smoothingFilter;
	}

	/**
	 * <p>Creates a new instance of a {@link HorizontalMotionDetector} using 
	 * an instance of {@link LowPassFilter} as the {@link SmoothingFilter} of
	 * choice.
	 * 
	 * @return a new instance of {@link HorizontalMotionDetector}
	 */
	public static final HorizontalMotionDetector newInstance() {

		return new HorizontalMotionDetector(new LowPassFilter());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>The current implementation handles only {@link Sensor}s of type
	 * {@link Sensor#TYPE_ACCELEROMETER}.</p>
	 * 
	 * <p>Future implementations may support {@link Sensor#TYPE_GYROSCOPE}.</p>
	 */
	@Override
	public <U extends SensorEvent> HorizontalMotionEvent getMotionEvent(final U sensorEvent) {

		Sensor sensor = sensorEvent.sensor;

		if (sensor.getType() != Sensor.TYPE_ACCELEROMETER) { 

			return null;
		}

		final float[] input = sensorEvent.values;
		final float[] output = new float[input.length];

		try {

			smoothingFilter.filter(input, output, null);
		} 
		catch (SmoothingFilterException sfe) { //TODO use an alternative filter

			Log.w(getClass().getName(), 
				  "Failed to execute " + smoothingFilter.getClass().getName() + " on " + input);
		}

		float max = sensorEvent.sensor.getMaximumRange();
		float mid = max / 2.0f;

		float midRangeHigh = mid + 1.0f;
		float midRangeLow = mid - 1.0f;

		HORIZONTAL_DIRECTION direction = processHorizontalDirection(output, midRangeHigh, midRangeLow);
		
		int yAxisReading = processXAxisReading(direction, output[0], max);
		
		int scrollDistance = processScrollDistance(yAxisReading);
		int scrollDuration = processScrollDuration(yAxisReading);

		return new HorizontalMotionEvent(sensorEvent, direction, scrollDistance, scrollDuration);
	}

	/**
	 * <p>Determines the {@link HORIZONTAL_DIRECTION} of the motion and trims 
	 * the sensor reading on the X-Axis to within the bounds handled by the the 
	 * motion detector.
	 *  
	 * @param output
	 * 			the smoothed sensor values
	 * 
	 * @param midRangeHigh
	 * 			the upper-bound of the mid-range which correlates with {@link HORIZONTAL_DIRECTION#NONE}
	 * 
	 * @param midRangeLow
	 * 			the lower-bound of the mid-range which correlates with {@link HORIZONTAL_DIRECTION#NONE}
	 * 
	 * @return the {@link HORIZONTAL_DIRECTION} of the motion
	 */
	private HORIZONTAL_DIRECTION processHorizontalDirection(float[] output, float midRangeHigh, float midRangeLow) {

		output[1] = (output[1] < 0)? 0.0f : output[1];
		
		if (output[1] < midRangeLow) {

			output[1] *= -1;
			return HORIZONTAL_DIRECTION.RIGHT;
		} 
		else if (output[1] > midRangeHigh) {

			return HORIZONTAL_DIRECTION.LEFT;
		} 
		else {

			return HORIZONTAL_DIRECTION.NONE;
		}
	}
	
	/**
	 * <p>Takes the motion sensor reading on the X-Axis and converts it 
	 * to a vector with a direction.
	 *
	 * @param direction
	 *  		the {@link HORIZONTAL_DIRECTION} of the motion
	 *  
	 * @param sensorReading
	 * 			the sensor reading on the X-Axis
	 *  
	 * @param maxSensorReading
	 * 			the maximum value which can be reached by a sensor reading 
	 *  
	 * @return the processed X-Axis sensor reading
	 */
	private int processXAxisReading(HORIZONTAL_DIRECTION direction, float sensorReading, float maxSensorReading) {
		
		switch (direction) {
		
			case LEFT:
				return (int) (-1 * (maxSensorReading + sensorReading));
				
			case RIGHT:
				return (int) sensorReading;
				
			case NONE: default: 
				return 0;
		}
	}
	
	/**
	 * <p>Determines the distance which the {@link View} or <i>view group</i> should scroll 
	 * in response to the horizontal motion.
	 *
	 * @param yAxisReading
	 *  		the processed sensor reading on the yAxis
	 * 
	 * @return the scroll distance
	 */
	private int processScrollDistance(int yAxisReading) {
		
		return (int) (yAxisReading * Math.pow(yAxisReading, FRICTION));
	}
	
	/**
	 * <p>Determines the duration which the {@link View} or <i>view group</i> should scroll 
	 * in response to the horizontal motion.
	 *
	 * @param yAxisReading
	 *  		the processed sensor reading on the yAxis
	 * 
	 * @return the scroll duration
	 */
	private int processScrollDuration(int yAxisReading) {
		
		return (int) (MINIMUM_DURATION + Math.pow(yAxisReading, FRICTION));
	}
}
