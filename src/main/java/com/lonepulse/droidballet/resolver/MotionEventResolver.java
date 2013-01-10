package com.lonepulse.droidballet.resolver;

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


import java.util.Set;

import android.hardware.SensorEvent;

import com.lonepulse.droidballet.detector.MotionDetector;
import com.lonepulse.droidballet.listener.MotionEvent;
import com.lonepulse.droidballet.listener.MotionListener;

/**
 * <p>The common contract which all <b>motion resolvers</b> should implement. A motion event resolver 
 * is created for each motion type and is responsible for generating a {@link MotionEvent} of it's 
 * associated type using the relevant {@link MotionDetector} and firing this event on a {@link Set} 
 * of {@link MotionListener}s akin to it's type.  
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface MotionEventResolver {
	
	/**
	 * <p>Accepts a {@link Set} of {@link MotionListener}s and filters them to discover 
     * those that are of the handled type. Takes the supplied {@link SensorEvent} and 
     * generates the relevant {@link MotionEvent} from the associated {@link MotionDetector}. 
     * Finally, this {@link MotionEvent} is fired on the filtered {@link MotionListener}s.
	 * 
	 * @param sensorEvent
	 * 			the {@link SensorEvent} generated from the motion sensor
	 * 
	 * @param motionListeners
	 * 			the set of {@link MotionListener}s which should be filtered for the 
	 * 			type associated with the motion event resolver
     *
	 * @since 1.0.0
	 */
	public abstract void resolve(SensorEvent sensorEvent, Set<MotionListener> motionListeners);
}
