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

import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.lonepulse.droidballet.listener.HorizontalMotionEvent.HORIZONTAL_DIRECTION;

/**
 * <p>This contract which specifies the services offered to a <b>Widget</i> or 
 * <i>View Group</i> which allows <b>horizontal scrolling</b>. Common examples 
 * of such components are {@link HorizontalScrollView}s and scrollable {@link TextView}s.</p>
 * 
 * <p>Components that wish to register for {@link HorizontalMotionEvent}s should 
 * implement this listener and handle the necessary <i>motion</i> in the provided 
 * callback methods.</p>
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface HorizontalMotionListener extends MotionListener {

	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link HORIZONTAL_DIRECTION#LEFT}. 
	 * 
	 * @param event
	 * 			the instance of {@link HorizontalMotionEvent} which can be used 
	 * 			to discover more information regarding the scroll
	 */
	public abstract void onMotionLeft(HorizontalMotionEvent event);
	
	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link HORIZONTAL_DIRECTION#RIGHT}. 
	 * 
	 * @param event
	 * 			the instance of {@link HorizontalMotionEvent} which can be used 
	 * 			to discover more information regarding the scroll 
	 */
	public abstract void onMotionRight(HorizontalMotionEvent event);
	
	/**
	 * <p>Invoked when the device orientation reaches a position which assumes 
	 * {@link HORIZONTAL_DIRECTION#NONE}. 
	 * 
	 * @param event
	 * 			the instance of {@link HorizontalMotionEvent} which can be used 
	 * 			to discover more information regarding the scroll 
	 */	
	public abstract void onMotionRest(HorizontalMotionEvent event);
}