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

import com.lonepulse.droidballet.listener.MotionEvent;

/**
 * <p>The common contract implemented by all motion detectors.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface MotionDetector<T extends MotionEvent> {
	
	/**
	 * <p>Takes an instance of a {@link SensorEvent} and generates the 
	 * repective {@link MotionEvent} associated with this {@link MotionDetector}.
	 * 
	 * @param sensorEvent
	 * 			the {@link SensorEvent} aquired by a {@link Sensor}.
	 * 
	 * @return the generated {@link MotionEvent}, or <b>{@code null}</b> if a 
	 * 			motion event of the current detector type cannot be created using 
	 * 			the {@link SensorEvent}. 
	 * 
	 * @throws MotionDetectorException
	 * 			if there is a failure in detecting motion using the {@link SensorEvent} 
	 * 			produced by the sensor
	 * <br><br>
	 * @since 1.0.0
	 */
	public <U extends SensorEvent> T getMotionEvent(U sensorEvent)
	throws MotionDetectorException;
}
