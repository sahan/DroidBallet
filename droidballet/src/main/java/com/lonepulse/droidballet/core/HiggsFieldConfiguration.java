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

import android.app.Application;
import android.content.Context;

/**
 * <p>Contains all the information and tools which can be used to establish 
 * a {@link HiggsField}.</p>
 * 
 * <p>Use {@link HiggsFieldConfiguration.Builder} to create an instance.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HiggsFieldConfiguration {

	/**
	 * <p>A <b>builder</b> which can be used to create a custom 
	 * {@link HiggsFieldConfiguration}.</p>
	 * 
	 * @version 1.0.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Builder {
		
		
		/**
		 * <p>The {@link Application} context which is used to obtain <b>system 
		 * services</b> such as {@link Context#SENSOR_SERVICE}.
		 */
		private Application application;
		
		
		/**
		 * <p>Takes a mandatory parameter - any extension of {@link Application} 
		 * (which provides a global context).
		 * 
		 * @param application
		 * 			the {@link Application} instance or an extension; <b>cannot be null</b>
		 */
		public <T extends Application> Builder(T application) {
			
			this.application = application;
		}
		
		/**
		 * <p>Creates an instance of {@link HiggsFieldConfiguration} to reflect the 
		 * properties set in this instance of {@link HiggsFieldConfiguration.Builder}.
		 * 
		 * @return the configured instance of {@link HiggsFieldConfiguration}
		 */
		public HiggsFieldConfiguration build() {
			
			return new HiggsFieldConfiguration(this);
		}
	}
	
	
	/**
	 * <p>The {@link Application} context which is used to obtain <b>system 
	 * services</b> such as {@link Context#SENSOR_SERVICE}.
	 * 
	 * @since 1.0.0
	 */
	private final Application application;
	
	
	/**
	 * <p>Accessor for {@link #application}.
	 * 
	 * @return {@link #application}
	 */
	public Application getApplication() {
		
		return application;
	}
	
	/**
	 * <p>Creates an instance of {@link HiggsFieldConfiguration} using the supplied 
	 * {@link HiggsFieldConfiguration.Builder}.
	 * 
	 * @param builder
	 * 			a {@link Builder} which can be used to create a {@link HiggsFieldConfiguration}
	 */
	private HiggsFieldConfiguration(HiggsFieldConfiguration.Builder builder) {
		
		this.application = builder.application;
	}
}
