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
	 * <p>The instance of {@link HIGGS_FIELD_STATE} which indicates the <b>current</b> 
	 * state of the {@link HiggsField#INSTANCE}.</p>
	 * 
	 * <p>Do not mutate directly - always use {@link #setState(HIGGS_FIELD_STATE)}.</p>
	 */
	private volatile HIGGS_FIELD_STATE state;

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
	public void initialize(final HiggsFieldConfiguration config) {
		
		this.config = config;

		Context context = this.config.getApplication();
		
		this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //TODO take action if null
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activate() {
		
		setState(HIGGS_FIELD_STATE.ACTIVE);
		
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
		EventQueue.INSTANCE.startConsuming();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivate() {
		
		setState(HIGGS_FIELD_STATE.INACTIVE);
		
		sensorManager.unregisterListener(this);
		EventQueue.INSTANCE.stopConsuming();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		
		if(getState().equals(HIGGS_FIELD_STATE.INACTIVE)) return;

		MotionViewRegistry.INSTANCE.notify(sensorEvent);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {} 
}
