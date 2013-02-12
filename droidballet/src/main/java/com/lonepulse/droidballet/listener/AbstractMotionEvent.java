package com.lonepulse.droidballet.listener;

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

import android.hardware.SensorEvent;

import com.lonepulse.droidballet.filter.SmoothingFilter;

/**
 * <p>An abstract implementation of {@link MotionEvent} which manages the 
 * common properties related to each {@link MotionEvent}.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractMotionEvent implements MotionEvent {


	/**
	 * <p>The original {@link SensorEvent} whose information was used 
	 * to generate this {@link MotionEvent}.
	 * 
	 * @since 1.1.0
	 */
	private SensorEvent sensorEvent;

	/**
	 * <p>The sensor output which has been processed through a 
	 * {@link SmoothingFilter}.
	 * 
	 * @since 1.1.1
	 */
	private float[] filteredOutput;
	
	
	/**
	 * <p>Default constructor which initializes the {@link AbstractMotionEvent}.
	 * 
	 * @param sensorEvent
	 * 			See {@link #getSensorEvent()}
	 * 
	 * @param filteredOutput
	 * 			See {@link #filteredOutput}
	 * 
	 * @since 1.1.1
	 */
	public AbstractMotionEvent(SensorEvent sensorEvent, float[] filteredOutput) {
		
		this.sensorEvent = sensorEvent;
		this.filteredOutput = filteredOutput;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SensorEvent getSensorEvent() {
		
		return this.sensorEvent;
	}
	
	@Override
	public float[] getFilteredOutput() {
		
		return this.filteredOutput;
	}
}
