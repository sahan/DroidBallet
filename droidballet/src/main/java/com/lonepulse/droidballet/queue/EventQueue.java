package com.lonepulse.droidballet.queue;

/*
 * #%L
 * DroidBallet Library
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


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.util.Log;

/**
 * <p>The implementation of {@link QueueController} which handles the 
 * queuing and dequeuing of {@link MotionEventResolutionJob}s and their 
 * subsequent execution.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum EventQueue implements QueueController {
	
	
	/**
	 * <p>The universal instance of the {@link QueueController} which 
	 * can be used to enqueue and dequeue {@link MotionEventResolutionJob}s.
	 * 
	 * @since 1.1.0
	 */
	INSTANCE;

	
	/**
	 * <p>A reentrant lock which must be acquired before consuming 
	 * {@link MotionEventResolutionJob}s. 
	 */
	public static final Lock CONSUMER_LOCK = new ReentrantLock(true);
	
	/**
	 * <p>A condition which signals if new {@link MotionEventResolutionJob}s 
	 * have been enqueued on an already empty queue.
	 */
	public static final Condition ENQUEUED = CONSUMER_LOCK.newCondition();
	
	/**
	 * <p>A flag which determines if the {@link MotionEventResolutionJob}s 
	 * continue to be dequeued and consumed.
	 */
	private static volatile AtomicBoolean consume;
	
	/**
	 * <p>A {@link Runnable} which dequeues {@link MotionEventResolutionJob}s 
	 * from the {@link #queue} and executes them asynchronously.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	private static final class CONSUMER implements Runnable {
		
		@Override
		public void run() {
			
			CONSUMER_LOCK.lock();
			
			try {
			
				while(consume.get()) {
					
					MotionEventResolutionJob merj = EventQueue.INSTANCE.dequeue();
					
					if(merj != null) {
					
						merj.getMotionEventResolver()
								.resolve(merj.getSensorEvent(), merj.getMotionListeners());
					}
					else {
						
						try {
							
							ENQUEUED.await();
						} 
						catch (InterruptedException e) {
						
							Log.w(getClass().getSimpleName(), e);
						}
					}
				}
			}
			finally {
				
				CONSUMER_LOCK.unlock();
			}
		}
	}
	
	/**
	 * <p>The instance of {@link ConcurrentLinkedQueue} which will be used to enqueue  
	 * {@link MotionEventResolutionJob}s for subsequent dequeuing and execution on the 
	 * {@link #CONSUMER_EXECUTOR} via the current instance of {@link #CONSUMER_EXECUTOR}.</p>
	 * 
	 * <p>Thread-safety is deferred to the {@link ConcurrentLinkedQueue}'s inherent locking 
	 * mechanisms. Hence manual synchronization on {@link QueueController} services is to 
	 * be neglected.</p> 
	 */
	private static final Queue<MotionEventResolutionJob> queue;
	
	static
	{
		queue = new ConcurrentLinkedQueue<MotionEventResolutionJob>();
		consume = new AtomicBoolean(false);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enqueue(MotionEventResolutionJob merj) {
		
		queue.offer(merj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MotionEventResolutionJob dequeue() {
		
		return queue.poll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startConsuming() {

		if(!consume.get()) {

			consume.set(true);
			new Thread(new CONSUMER()).start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopConsuming() {

		if(consume.get()) {

			consume.set(false);
		}
	}
}
