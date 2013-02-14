package com.lonepulse.droidballet.queue;

/*
 * #%L
 * DroidBallet Library
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
import android.view.MotionEvent;

import com.lonepulse.droidballet.listener.MotionListener;
import com.lonepulse.droidballet.resolver.MotionEventResolver;

/**
 * <p>Represents a motion event which is to be translated to a motion 
 * event and despatched to targeted motion listeners. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionEventResolutionJob {

	
	/**
	 * <p>This <i>builder</i> is to be used for creating a custom 
	 * {@link MotionEventResolutionJob}.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Builder {
		
		
		/**
		 * <p>See {@link MotionEventResolutionJob#sensorEvent}.
		 */
		private SensorEvent sensorEvent;
		
		/**
		 * <p>See {@link MotionEventResolutionJob#motionListeners}.
		 */
		private Set<MotionListener> motionListeners;
		
		/**
		 * <p>See {@link MotionEventResolutionJob#motionEventResolver}.
		 */
		private MotionEventResolver motionEventResolver;
		
		
		/**
		 * <p>Creates an instance of {@link MotionEventResolutionJob} to reflect the 
		 * properties set in this instance of {@link MotionEventResolutionJob.Builder}.
		 * 
		 * @param sensorEvent
		 * 			see {@link MotionEventResolutionJob#sensorEvent}
		 * 
		 * @param motionListeners
		 * 			see {@link MotionEventResolutionJob#motionListeners}
		 * 
		 * @param motionEventResolver
		 * 			see {@link MotionEventResolutionJob#motionEventResolver}
		 * 
		 * @since 1.1.0
		 */
		public Builder(SensorEvent sensorEvent, 
					   Set<MotionListener> motionListeners, 
					   MotionEventResolver motionEventResolver) {
			
			this.sensorEvent = sensorEvent;
			this.motionListeners = motionListeners;
			this.motionEventResolver = motionEventResolver;
		}

		/**
		 * <p>Mutator for {@link #sensorEvent}.
		 *
		 * @param sensorEvent 
		 *			sets {@link #sensorEvent}
		 */
		public void setSensorEvent(SensorEvent sensorEvent) {
			
			this.sensorEvent = sensorEvent;
		}

		/**
		 * <p>Mutator for {@link #motionListeners}.
		 *
		 * @param motionListeners 
		 *			sets {@link #motionListeners}
		 */
		public void setMotionListeners(Set<MotionListener> motionListeners) {
			
			this.motionListeners = motionListeners;
		}

		/**
		 * <p>Mutator for {@link #motionEventResolver}.
		 *
		 * @param motionEventResolver 
		 *			sets {@link #motionEventResolver}
		 */
		public void setMotionEventResolver(MotionEventResolver motionEventResolver) {
			
			this.motionEventResolver = motionEventResolver;
		}
		
		/**
		 * <p>Creates an instance of {@link MotionEventResolutionJob} to reflect the 
		 * properties set in this instance of {@link MotionEventResolutionJob.Builder}.
		 * 
		 * @return the configured instance of {@link MotionEventResolutionJob}
		 * 
		 * @since 1.1.0
		 */
		public MotionEventResolutionJob build() {
			
			return new MotionEventResolutionJob(this);
		}
	}

	
	/**
	 * <p>The motion {@link SensorEvent} which is to be processed 
	 * and translated to an action on a {@link MotionListener}.
	 */
	private final SensorEvent sensorEvent;
	
	/**
	 * <p>The set of {@link MotionListener}s on which the motion 
	 * event is despatched.  
	 */
	private final Set<MotionListener> motionListeners;
	
	/**
	 * <p>The {@link MotionEventResolver} which is to be used for 
	 * translating the {@link #sensorEvent} to a {@link MotionEvent} 
	 * on the set of {@link #motionListeners}
	 */
	private final MotionEventResolver motionEventResolver;

	
	/**
	 * <p>Accessor for {@link #sensorEvent}.
	 *
	 * @return the {@link #sensorEvent}
	 * 
	 * @since 1.1.0
	 */
	public SensorEvent getSensorEvent() {
		
		return sensorEvent;
	}

	/**
	 * <p>Accessor for {@link #motionListeners}.
	 *
	 * @return the {@link #motionListeners}
	 * 
	 * @since 1.1.0
	 */
	public Set<MotionListener> getMotionListeners() {
		
		return motionListeners;
	}

	/**
	 * <p>Accessor for {@link #motionEventResolver}.
	 *
	 * @return the {@link #motionEventResolver}
	 * 
	 * @since 1.1.0
	 */
	public MotionEventResolver getMotionEventResolver() {
		
		return motionEventResolver;
	}
	
	/**
	 * <p>Creates an instance of {@link MotionEventResolutionJob} using the 
	 * supplied {@link MotionEventResolutionJob.Builder}.
	 * 
	 * @param builder
	 * 			a {@link MotionEventResolutionJob.Builder} which can be used 
	 * 			to create a {@link MotionEventResolutionJob}
	 * 
	 * @since 1.1.0
	 */
	public MotionEventResolutionJob(MotionEventResolutionJob.Builder builder) {
	
		this.sensorEvent = builder.sensorEvent;
		this.motionListeners = builder.motionListeners;
		this.motionEventResolver = builder.motionEventResolver;
	}
}
