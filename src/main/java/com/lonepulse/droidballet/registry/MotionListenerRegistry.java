package com.lonepulse.droidballet.registry;

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
import android.view.ViewGroup;

import com.lonepulse.droidballet.core.HiggsFieldConfiguration;
import com.lonepulse.droidballet.listener.MotionEvent;
import com.lonepulse.droidballet.listener.MotionListener;

/**
  * <p>This contract specifies how {@link MotionListener}s can register 
 * and unregister themselves on a central registry.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface MotionListenerRegistry {
	
	
	/**
	 * <p>Registers a {@link View} with the {@link HiggsFieldConfiguration} so that it may 
	 * receive callbacks on the desired {@link MotionEvent}s.</p>
	 * 
	 * @param motionListener
	 * 			the {@link MotionListener} (Widget or {@link ViewGroup}) to be registered
	 * 
	 * @since 1.0.0
	 */
	public abstract <T extends MotionListener> void register(T motionListener);
	
	/**
	 * <p>Unregisters a {@link View} which has be registered to receive 
	 * motion callbacks.</p> 
	 * 
	 * <p>If the specified view has not being registered, a waring is logged 
	 * and the error is silently ignored.
	 * 
	 * @param motionListener
	 * 			the {@link MotionListener} (Widget or {@link ViewGroup}) to be unregistered
	 * 
	 * @since 1.0.0
	 */
	public abstract <T extends MotionListener> void unregister(T motionListener);
}
