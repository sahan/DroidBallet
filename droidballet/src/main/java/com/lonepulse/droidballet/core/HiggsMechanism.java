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

import android.app.Activity;
import android.hardware.SensorEventListener;

/**
 * <p>Describes the services offered by a typical 
 * <a href="http://en.wikipedia.org/wiki/Higgs_field">Higgs Field</a>.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface HiggsMechanism extends SensorEventListener {

	/**
	 * <p>An indicator of the <i>state</i> of the {@link HiggsField#INSTANCE}.
	 * 
	 * @version 1.0.0
	 * 
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public enum HIGGS_FIELD_STATE { 
		
		/**
		 * <p>The {@link HiggsField#INSTANCE} has been enabled via 
		 * {@link HiggsField#activate()}.
		 * 
		 * @since 1.0.0
		 */
		ACTIVE,
		
		/**
		 * <p>The {@link HiggsField#INSTANCE} has been disabled via 
		 * {@link HiggsField#deactivate()}.
		 * 
		 * @since 1.0.0
		 */
		INACTIVE;
	}

	
	/**
	 * <p>Initializes the {@link HiggsField} using the given {@link HiggsFieldConfiguration} 
	 * and renders it usable.
	 * 
	 * @param config
	 * 			the {@link HiggsFieldConfiguration} with which this 
	 * 			{@link HiggsField} is generated
	 * 
	 * @since 1.0.0
	 */
	public abstract void initialize(final HiggsFieldConfiguration config);
	
	/**
	 * <p>Activates the {@link HiggsField} and registers itself as the listener 
	 * for the motion sensors.</p>
	 * 
	 * <p>This should be invoked in the {@link Activity}'s {@code onResume()} 
	 * lifecycle callback method to <b>disable</b> all <i>motion views</i>.</p>
	 * 
	 * @since 1.0.0
	 */
	public abstract void activate();
	
	/**
	 * <p>Deactivates the {@link HiggsField} and unregisters itself as the listener 
	 * for the motion sensors.</p>
	 * 
	 * <p>This should be invoked in the {@link Activity}'s {@code onPause()} 
	 * lifecycle callback method to <b>disable</b> all <i>motion views</i>.</p>
	 * 
	 * @since 1.0.0
	 */
	public abstract void deactivate();
}
