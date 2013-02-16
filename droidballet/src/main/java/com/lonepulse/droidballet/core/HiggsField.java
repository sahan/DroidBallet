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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.ViewGroup;

import com.lonepulse.droidballet.queue.EventQueue;
import com.lonepulse.droidballet.registry.MotionViewRegistry;

/**
 * <p>"<i>The Higgs-Field is a possibly discovered, ubiquitous quantum field 
 * supposed to be responsible for giving elementary particles their masses.</i>"</p>
 * 
 * <p>This enum is responsible for maintaining a single instance of itself which 
 * can be used by Widgets  and {@link ViewGroup}s to register themselves for changes 
 * in device orientation and respond to the associated motion.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum HiggsField implements HiggsMechanism {
	
	
	/**
	 * <p>The {@link HiggsField} singleton.
	 * 
	 * @since 1.0.0
	 */
	INSTANCE;

	
	/**
	 * <p>An {@link ExecutorService} modeled using {@link Executors#newSingleThreadExecutor()} 
	 * which will be used to execute worker threads that run <i>PRODUCERS</i>.</p>
	 * 
	 * <p>See {@link #onSensorChanged(SensorEvent)};</p>
	 */
	private static final ExecutorService PRODUCER_EXECUTOR = Executors.newSingleThreadExecutor();
	
	/**
	 * <p>A flag which determines if the HiggsField has already been 
	 * initialized via {@link #initialize(HiggsFieldConfiguration)}.
	 */
	private volatile AtomicBoolean initialized = new AtomicBoolean(false);
	
	/**
	 * <p>The instance of {@link HIGGS_FIELD_STATE} which indicates the <b>current</b> 
	 * state of the {@link HiggsField#INSTANCE}.</p>
	 * 
	 * <p>Do not mutate directly - always use {@link #setState(HIGGS_FIELD_STATE)}.</p>
	 */
	private volatile HIGGS_FIELD_STATE state = HIGGS_FIELD_STATE.INACTIVE;

	/**
	 * <p>The <i>configuration</i> which was used to activate the {@link HiggsField#INSTANCE}. 
	 * This is available only after activation via {@link #activate(HiggsFieldConfiguration)} 
	 * has succeeded.
	 */
	private HiggsFieldConfiguration config;
	
	/**
	 * <p>The system {@link SensorManager} which can be used to manage all the 
	 * <b>motion sensors</b>.
	 */
	private SensorManager sensorManager;
	
	/**
	 * <p>The <i>default</i> instance of the <b>Accelerometer</b> found on the device.
	 */
	private Sensor accelerometer;
	
	
	/**
	 * <p>Accessor for {@link #state}.
	 * 
	 * @return {@link #state}
	 */
	public synchronized HIGGS_FIELD_STATE getState() {
		
		return state;
	}

	/**
	 * <p>Updates the {@link HIGGS_FIELD_STATE} of the {@link HiggsField}.
	 * 
	 * @param state
	 * 			the {@link HIGGS_FIELD_STATE} to update {@link HiggsField#state}
	 */
	private synchronized void setState(HIGGS_FIELD_STATE state) {
		
		this.state = state;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void initialize(final HiggsFieldConfiguration config) {
		
		if(!initialized.get()) {
		
			this.config = config;
	
			Context context = this.config.getApplication();
			
			this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
			this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			
			initialized.set(true);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void activate() {
		
		if(!initialized.get())
			throw new HiggsFieldUninitializedException();
		
		if(getState().equals(HIGGS_FIELD_STATE.INACTIVE)) {
			
			setState(HIGGS_FIELD_STATE.ACTIVE);
			
			sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			EventQueue.INSTANCE.startConsuming();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void deactivate() {
		
		if(!initialized.get())
			throw new HiggsFieldUninitializedException();
		
		if(getState().equals(HIGGS_FIELD_STATE.ACTIVE)) {
		
			setState(HIGGS_FIELD_STATE.INACTIVE);
			
			sensorManager.unregisterListener(this);
			EventQueue.INSTANCE.stopConsuming();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSensorChanged(final SensorEvent sensorEvent) {
		
		if(getState().equals(HIGGS_FIELD_STATE.INACTIVE)) return;
		
		Runnable producer = new Runnable() {
					
			@Override
			public void run() {
						
				MotionViewRegistry.INSTANCE.notify(sensorEvent);
			}
		};
				
		PRODUCER_EXECUTOR.execute(producer);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {} 
}
