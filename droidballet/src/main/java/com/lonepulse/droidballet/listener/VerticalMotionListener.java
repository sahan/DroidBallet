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

import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import com.lonepulse.droidballet.listener.VerticalMotionEvent.VERTICAL_DIRECTION;

/**
 * <p>This contract which specifies the services offered to a <b>Widget</i> or 
 * <i>View Group</i> which allows <b>vertical scrolling</b>. Common examples of 
 * such components are {@link ListView}s and {@link ScrollView}s.</p>
 * 
 * <p>Components that wish to register for {@link VerticalMotionEvent}s should 
 * implement this listener and handle the necessary <i>motion</i> in the provided 
 * callback methods.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface VerticalMotionListener extends MotionListener {

	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link VERTICAL_DIRECTION#UP}. 
	 * 
	 * @param event
	 * 			the instance of {@link VerticalMotionEvent} which can be used to discover 
	 * 			more information regarding the scroll
	 * 
	 * @since 1.1.0
	 */
	public abstract void onMotionUp(VerticalMotionEvent event);
	
	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link VERTICAL_DIRECTION#DOWN}. 
	 * 
	 * @param event
	 * 			the instance of {@link VerticalMotionEvent} which can be used to discover 
	 * 			more information regarding the scroll
	 * 
	 * @since 1.1.0
	 */
	public abstract void onMotionDown(VerticalMotionEvent event);
	
	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link VERTICAL_DIRECTION#NONE}. 
	 * 
	 * @param event
	 * 			the instance of {@link VerticalMotionEvent} which can be used to discover 
	 * 			more information regarding the scroll
	 * 
	 * @since 1.1.0
	 */	
	public abstract void onMotionRest(VerticalMotionEvent event);
	
	/**
	 * <p>Determines the distance which the {@link View} or <i>view group</i> should scroll 
	 * in response to the vertical motion.
	 *
	 * @param yAxisReading
	 *  		the processed sensor reading on the yAxis
	 * 
	 * @return the scroll distance
	 * 
	 * @since 1.1.0
	 */
	public abstract int processScrollDistance(int yAxisReading);
	
	/**
	 * <p>Determines the duration which the {@link View} or <i>view group</i> should scroll 
	 * in response to the vertical motion.
	 *
	 * @param yAxisReading
	 *  		the processed sensor reading on the yAxis
	 * 
	 * @return the scroll duration
	 * 
	 * @since 1.1.0
	 */
	public abstract int processScrollDuration(int yAxisReading);
}