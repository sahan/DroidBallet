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

import com.lonepulse.droidballet.filter.LowPassFilter;
import com.lonepulse.droidballet.filter.SmoothingFilter;
import com.lonepulse.droidballet.filter.SmoothingFilterException;
import com.lonepulse.droidballet.listener.VerticalMotionEvent;
import com.lonepulse.droidballet.listener.VerticalMotionEvent.VERTICAL_DIRECTION;

/**
 * <p>A concrete implementation of {@link MotionDetector} which detects <b>vertical
 * motion</b>.
 * 
 * @version 1.1.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class VerticalMotionDetector implements MotionDetector<VerticalMotionEvent> {
	

	/**
	 * <p>The instance of the {@link SmoothingFilter} which is used to smooth out
	 * the values from the motion sensor.
	 */
	private SmoothingFilter smoothingFilter;

	/**
	 * <p>Constructor visibility restricted to prevent direct instantiation.
	 * 
	 * <p>Use the factory method {@code HorizontalMotionDetector.newInstance();}
	 * 
	 * @param smoothingFilter
	 *            the {@link #smoothingFilter} to use
	 */
	private VerticalMotionDetector(SmoothingFilter smoothingFilter) {

		this.smoothingFilter = smoothingFilter;
	}

	/**
	 * <p>Creates a new instance of a {@link VerticalMotionDetector} using 
	 * an instance of {@link LowPassFilter} as the {@link SmoothingFilter} of
	 * choice.
	 * 
	 * @return a new instance of {@link VerticalMotionDetector}
	 * 
	 * @since 1.1.0
	 */
	public static final VerticalMotionDetector newInstance() {

		return new VerticalMotionDetector(new LowPassFilter());
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
	public <U extends SensorEvent> VerticalMotionEvent getMotionEvent(final U sensorEvent)
	throws MotionDetectorException {

		try {
			
			Sensor sensor = sensorEvent.sensor;
	
			if (sensor.getType() != Sensor.TYPE_ACCELEROMETER) { 
	
				return null;
			}
	
			final float[] input = sensorEvent.values;
			final float[] output = new float[input.length];
	
			try {
	
				smoothingFilter.filter(input, output, null);
			} 
			catch (SmoothingFilterException sfe) {
	
				Log.w(getClass().getName(), 
					  "Failed to execute " + smoothingFilter.getClass().getName() + " on " + input);
			}
	
			float max = sensorEvent.sensor.getMaximumRange();
			float mid = max / 2.0f;
	
			float midRangeHigh = mid + 1.0f;
			float midRangeLow = mid - 1.0f;
	
			VERTICAL_DIRECTION direction = processVerticalDirection(output, midRangeHigh, midRangeLow);
			
			return new VerticalMotionEvent(sensorEvent, direction, output);
		}
		catch (Exception e) {

			throw new MotionDetectorException(getClass(), sensorEvent, e);
		}
	}

	/**
	 * <p>Determines the {@link VERTICAL_DIRECTION} of the motion and trims the 
	 * sensor reading on the Y-Axis to within the bounds handled by the the 
	 * motion detector.
	 *  
	 * @param output
	 * 			the smoothed sensor values
	 * 
	 * @param midRangeHigh
	 * 			the upperbound of the mid-range which correlates with {@link VERTICAL_DIRECTION#NONE}
	 * 
	 * @param midRangeLow
	 * 			the lowerbound of the mid-range which correlates with {@link VERTICAL_DIRECTION#NONE}
	 * 
	 * @return the {@link VERTICAL_DIRECTION} of the motion
	 */
	private VERTICAL_DIRECTION processVerticalDirection(float[] output, float midRangeHigh, float midRangeLow) {

		output[1] = (output[1] < 0)? 0.0f : output[1];
		
		if (output[1] < midRangeLow) {

			output[1] *= -1;
			return VERTICAL_DIRECTION.UP;
		} 
		else if (output[1] > midRangeHigh) {

			return VERTICAL_DIRECTION.DOWN;
		} 
		else {

			return VERTICAL_DIRECTION.NONE;
		}
	}
}

