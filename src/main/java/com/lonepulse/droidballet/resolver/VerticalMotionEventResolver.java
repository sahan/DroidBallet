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

import com.lonepulse.droidballet.detector.VerticalMotionDetector;
import com.lonepulse.droidballet.listener.VerticalMotionEvent;
import com.lonepulse.droidballet.listener.VerticalMotionListener;

/**
 * <p>An implementation of {@link AbstractMotionEventResolver} which 
 * resolves {@link VerticalMotionEvent}s for components which have 
 * implemented {@link VerticalMotionListener}.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class VerticalMotionEventResolver extends AbstractMotionEventResolver<VerticalMotionListener, 
																			 VerticalMotionEvent, 
																			 VerticalMotionDetector> {

	/**
	 * <p>Instantiates a {@link VerticalMotionEventResolver}. 
	 */
	public VerticalMotionEventResolver() {
		
		super(VerticalMotionListener.class, VerticalMotionDetector.newInstance());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fireMotionEvent(VerticalMotionEvent motionEvent, 
								   Set<VerticalMotionListener> filteredMotionListeners) {
		
		switch (motionEvent.getDirection()) {
		
			case DOWN:
				
				for (VerticalMotionListener verticalMotionListener : filteredMotionListeners)
					verticalMotionListener.onMotionDown(motionEvent);
				
				break;
				
			case UP:
				
				for (VerticalMotionListener verticalMotionListener : filteredMotionListeners)
					verticalMotionListener.onMotionUp(motionEvent);
				
				break;
				
			case NONE:
				
				for (VerticalMotionListener verticalMotionListener : filteredMotionListeners)
					verticalMotionListener.onMotionRest(motionEvent);
				
				break;
		}
	}
}
