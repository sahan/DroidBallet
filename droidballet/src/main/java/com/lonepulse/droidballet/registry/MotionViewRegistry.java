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

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

import android.hardware.SensorEvent;

import com.lonepulse.droidballet.detector.MotionDetector;
import com.lonepulse.droidballet.listener.MotionEvent;
import com.lonepulse.droidballet.listener.MotionListener;
import com.lonepulse.droidballet.queue.EventQueue;
import com.lonepulse.droidballet.queue.MotionEventResolutionJob;
import com.lonepulse.droidballet.resolver.HorizontalMotionEventResolver;
import com.lonepulse.droidballet.resolver.MotionEventResolver;
import com.lonepulse.droidballet.resolver.VerticalMotionEventResolver;
import com.lonepulse.droidballet.widget.MotionView;

/**
 * <p>A basic implementation of {@link MotionListenerRegistry} which maintains 
 * all motion widgets and view groups.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum MotionViewRegistry implements MotionListenerRegistry, MotionEventResolverRegistry {

	/**
	 * <p>The {@link MotionViewRegistry} singleton.
	 * 
	 * @since 1.0.0
	 */
	INSTANCE;

	
	/**
	 * <p>Maintains the {@link Set} of {@link MotionListener}s. 
	 */
	private volatile Set<MotionListener> motionListeners; 
	{
		motionListeners = new HashSet<MotionListener>();
	}
	
	/**
	 * <p>Maintains the {@link Set} of {@link MotionEventResolver}s.
	 */
	private volatile Set<MotionEventResolver> motionEventResolvers; 
	{
		
		motionEventResolvers = new HashSet<MotionEventResolver>();
		
		motionEventResolvers.add(new VerticalMotionEventResolver());
		motionEventResolvers.add(new HorizontalMotionEventResolver());
	}
	
	/**
	 * <p>This is a {@link ReferenceQueue} with {@link PhantomReference}s 
	 * of all registered {@link MotionListener}s. 
     *
	 * TODO complete this reference queue implementation
	 */
	@SuppressWarnings("unused")
	private ReferenceQueue<MotionListener> referenceQueue; 
	{
		referenceQueue = new ReferenceQueue<MotionListener>();	
	}

	
	/**
	 * <p>Initializes the {@link #motionListeners} and the set of {@link MotionEventResolver}s. 
	 */
	private MotionViewRegistry() {
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>This service is {@code synchronized} over {@link MotionViewRegistry#INSTANCE}. 
	 */
	@Override
	public synchronized <T extends MotionListener> void register(T motionListener) {
		
		motionListeners.add(motionListener);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>This service is {@code synchronized} over {@link MotionViewRegistry#INSTANCE}.
	 */
	@Override
	public synchronized <T extends MotionListener> void unregister(T motionListener) {
		
		motionListeners.remove(motionListener);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>This service is {@code synchronized} over {@link MotionViewRegistry#INSTANCE}.
	 */
	@Override
	public synchronized <T extends MotionEventResolver> void register(T motionEventResolver) {
		
		motionEventResolvers.add(motionEventResolver);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>This service is {@code synchronized} over {@link MotionViewRegistry#INSTANCE}.
	 */
	@Override
	public synchronized <T extends MotionEventResolver> void unregister(T motionEventResolver) {
		
		motionEventResolvers.remove(motionEventResolver);
	}

	/**
	 * <p>Takes the {@link SensorEvent} which was fired from the motion 
	 * sensor and runs it through the {@link MotionDetector}s to generate 
	 * the associated {@link MotionEvent}s. Theses events are then fired 
	 * on the {@link MotionView}s which are registered under the respective 
	 * {@link MotionEvent}s.
	 * 
	 * @param sensorEvent
	 * 			the {@link SensorEvent} which was fired from the <b>motion sensor</b>
	 * 
	 * @since 1.0.0
	 */
	public void notify(SensorEvent sensorEvent) {

		for (MotionEventResolver motionEventResolver : motionEventResolvers) {

			MotionEventResolutionJob.Builder builder 
				= new MotionEventResolutionJob.Builder(sensorEvent, motionListeners, motionEventResolver);
			
			EventQueue.INSTANCE.enqueue(builder.build());
		}
	}
}
