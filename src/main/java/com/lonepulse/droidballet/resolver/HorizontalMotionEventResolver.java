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

import com.lonepulse.droidballet.detector.HorizontalMotionDetector;
import com.lonepulse.droidballet.listener.HorizontalMotionEvent;
import com.lonepulse.droidballet.listener.HorizontalMotionListener;

/**
 * <p>An implementation of {@link AbstractMotionEventResolver} which 
 * resolves {@link HorizontalMotionEvent}s for components which have 
 * implemented {@link HorizontalMotionListener}.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HorizontalMotionEventResolver extends AbstractMotionEventResolver<HorizontalMotionListener, 
																			   HorizontalMotionEvent, 
																			   HorizontalMotionDetector> {

	/**
	 * <p>Instantiates a {@link HorizontalMotionEventResolver}. 
	 */
	public HorizontalMotionEventResolver() {
		
		super(HorizontalMotionListener.class, HorizontalMotionDetector.newInstance());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fireMotionEvent(HorizontalMotionEvent motionEvent, 
								   Set<HorizontalMotionListener> filteredMotionListeners) {
		
		switch (motionEvent.getDirection()) {
		
			case LEFT:
				
				for (HorizontalMotionListener horizontalMotionListener : filteredMotionListeners)
					 horizontalMotionListener.onMotionLeft(motionEvent);
				
				break;
				
			case RIGHT:
				
				for (HorizontalMotionListener horizontalMotionListener : filteredMotionListeners)
					 horizontalMotionListener.onMotionRight(motionEvent);
				
				break;
				
			case NONE:
				
				for (HorizontalMotionListener horizontalMotionListener : filteredMotionListeners)
					 horizontalMotionListener.onMotionRest(motionEvent);
				
				break;
		}
	}
}
