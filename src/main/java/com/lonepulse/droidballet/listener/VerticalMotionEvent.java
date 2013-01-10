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
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>Holds information about a single <i>vertical</i> scroll event which 
 * has been fired on a Widget or {@link ViewGroup}.</i></p> 
 *
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class VerticalMotionEvent extends AbstractMotionEvent {
	

	/**
	 * <p>Specifies the direction of a {@link VerticalMotionEvent} with respect to 
	 * the <b>sensor coordinates</b>.</p>
	 * 
	 * <p>More information on the sensor coordinates can be found 
	 * <a href="http://developer.android.com/guide/topics/sensors/sensors_overview.html#sensors-coords">here</a>.</p>
	 * 
	 * @version 1.0.0
	 * 
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static enum VERTICAL_DIRECTION {
		
		/**
		 * <p>The device orientation is such that there is no deduced 
		 * direction of scrolling; i.e. scrolling has stopped and the 
		 * widget or {@link ViewGroup} remains stagnant.</p>
		 * 
		 * <p>This state is assumed when the device is tilted to an angle 
		 * which lies within <i>resting range</i>. This range is created 
		 * by adding a predetermined magnitude to either side of the medial 
		 * position of the <i>sensor range</i>.</p>
		 * 
		 * <p>For example, if the medial position is {@code 4.5} with a 
		 * resting magnitude of {@code 1}, then the <i>resting range</i> 
		 * would extend from {@code 3.5} to {@code 5.5}. <i>All values 
		 * are in terms of sensor output</i>.</p>
		 * 
		 * <p>{@link MotionEvent}s of this direction are mapped to 
		 * {@link VerticalMotionListener#onMotionRest(VerticalMotionEvent)}</p>.
		 * 
		 * @since 1.0.0
		 */
		NONE,
		
		/**
		 * <p>The device orientation is such that the deduced direction 
		 * of scrolling is towards the <b>zenith</b> with respect to the 
		 * sensor coordinates.</p>
		 * 
		 * </p>This direction is assumed when the device is tilted to an 
		 * angle which lies between the <i>sensor range <b>lower-bound</b></i> 
		 * and the <i>resting range <b>lower-bound</b></i>.
		 * 
		 * <p>{@link MotionEvent}s of this direction are mapped to 
		 * {@link VerticalMotionListener#onMotionUp(VerticalMotionEvent)}</p>.
		 * 
		 * @since 1.0.0
		 */
		UP,
		
		/**
		 * <p>The device orientation is such that the deduced direction 
		 * of scrolling is towards the <b>nadir</b> with respect to the 
		 * sensor coordinates.</p>
		 * 
		 * </p>This direction is assumed when the device is tilted to an 
		 * angle which lies between the <i>resting range <b>upper-bound</b></i> 
		 * and the <i>sensor range <b>upper-bound</b></i>.
		 * 
		 * <p>{@link MotionEvent}s of this direction are mapped to 
		 * {@link VerticalMotionListener#onMotionDown(VerticalMotionEvent)}</p>.
		 * 
		 * @since 1.0.0
		 */
		DOWN;
	}

	
	/**
	 * <p>Specifies the direction of the scroll movement along the Y-Axis of 
	 * the sensor coordinate system.
	 * 
	 * @since 1.0.0
	 */
	private VERTICAL_DIRECTION direction;
	
	/**
	 * <p>An <i>indication</i> of the distance which a {@link View} should 
	 * <b>scroll</b> in response to this {@link VerticalMotionEvent}.
	 * 
	 * <p><b>Note</b> that this value is a <i>vector</i> whose <i>direction</i> 
	 * is determined by the <i>sign</i> on the number.</p>
	 * 
	 * <ul>
	 * 		<li>Positive (+X): {@link VERTICAL_DIRECTION#UP}
	 *  	<li>Negative (-X): {@link VERTICAL_DIRECTION#DOWN}
	 *  	<li>Signless  (0): {@link VERTICAL_DIRECTION#NONE}
	 * </ul>
	 *  
	 * @since 1.0.0
	 */
	private int scrollDistance;

	/**
	 * <p>An <i>indication</i> of the time span which should be used for the 
	 * {@link View} to <b>scroll</b> the length of {@link #scrollDistance} 
	 * in reponse to this {@link VerticalMotionEvent}.
	 * 
	 * @since 1.0.0
	 */	
	private int scrollDuration;

	
	/**
	 * <p>Default constructor which initializes the {@link AbstractMotionEvent}.
	 * 
	 * @param sensorEvent
	 * 			See {@link AbstractMotionEvent#getSensorEvent()}
	 * 
	 * @param direction
	 * 			the {@link #direction} of the vertical motion
	 * 
	 * @param scrollDistance
	 * 			the {@link #scrollDistance} of the vertical motion
	 * 
	 * @param scrollDuration
	 * 			the {@link #scrollDuration} of the vertical motion
	 */
	public VerticalMotionEvent(SensorEvent sensorEvent, VERTICAL_DIRECTION direction, int scrollDistance, int scrollDuration) {
		
		super(sensorEvent);
		
		this.direction = direction;
		this.scrollDistance = scrollDistance;
		this.scrollDuration = scrollDuration;
	}
	
	/**
	 * <p>Accessor for {@link #direction}.
	 * 
	 * @return {@link #direction}
	 */
	public VERTICAL_DIRECTION getDirection() {
		
		return direction;
	}
	
	/**
	 * <p>Accessor for {@link #scrollDistance}.
	 * 
	 * @return {@link #scrollDistance}
	 */
	public int getScrollDistance() {
		
		return scrollDistance;
	}

	/**
	 * <p>Accessor for {@link #scrollDuration}.
	 * 
	 * @return {@link #scrollDuration}
	 */
	public int getScrollDuration() {
		
		return scrollDuration;
	}
}