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

/**
 * <p>An abstract implementation of {@link MotionEvent} which manages the 
 * common properties related to each {@link MotionEvent}.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractMotionEvent implements MotionEvent {


	/**
	 * <p>The original {@link SensorEvent} whose information was used 
	 * to generate this {@link MotionEvent}.
	 * 
	 * @since 1.0.0
	 */
	private SensorEvent sensorEvent;

	
	/**
	 * <p>Default constructor which initialzes the {@link AbstractMotionEvent}.
	 * 
	 * @param sensorEvent
	 * 			See {@link #getSensorEvent()}
	 */
	public AbstractMotionEvent(SensorEvent sensorEvent) {
		
		this.sensorEvent = sensorEvent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SensorEvent getSensorEvent() {
		
		return this.sensorEvent;
	}
}
