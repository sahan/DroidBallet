package com.lonepulse.droidballet.core;

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

/**
 * <p>Identifies the <b>type</b> of a {@link Sensor}.</p> 
 * 
 * </p>This enum reflects the integer constants used in {@link Sensor} 
 * and is comparable with these constants (wrapped in an {@link Integer}).</p>  
 * 
 * TODO use this SensorType enum?
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum SensorType {
	
	
	/**
	 * <p>Specifies an <b>accelerometer</b> whose {@link SensorType#getType()} 
	 * will return {@link Sensor#TYPE_ACCELEROMETER}.
	 */
	ACCELEROMETER(Sensor.TYPE_ACCELEROMETER),
	
	/**
	 * <p>Specifies a <b>gyroscope</b> whose {@link SensorType#getType()} 
	 * will return {@link Sensor#TYPE_GYROSCOPE}.
	 */
	GYROSCOPE(Sensor.TYPE_GYROSCOPE);
	
	
	
	/**
	 * <p>Reflects the integer constant used in {@link Sensor}. 
	 */
	private int type;

	
	/**
	 * <p>Identifies the type as defined in {@link Sensor}.
	 * 
	 * @param type
	 */
	private SensorType(int type) {
		
		this.type = type;
	}
	
	
	/**
	 * <p>Accessor for {@link #type} which reflects the integer 
	 * constants used in {@link Sensor}.
	 * 
	 * @return {@link #type}
	 */
	public int getType() {
		
		return type;
	}
}
