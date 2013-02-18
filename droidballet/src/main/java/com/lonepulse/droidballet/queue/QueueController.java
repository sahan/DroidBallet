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



/**
 * <p>This contract specifies the services offered on a queue which 
 * buffers {@link MotionEventResolutionJob}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface QueueController {

	/**
	 * <p>Adds a {@link MotionEventResolutionJob} to an FIFO queue.
	 * 
	 * @param merj
	 * 			the {@link MotionEventResolutionJob} to add 
	 * 			to the queue
	 * 
	 * @since 1.1.0
	 */
	public void enqueue(MotionEventResolutionJob merj);
	
	/**
	 * <p>Removes a {@link MotionEventResolutionJob} from an FIFO queue.
	 * 
	 * @return the {@link MotionEventResolutionJob} which is next in 
	 * 		   sequence to be processed
	 * 
	 * @since 1.1.0
	 */
	public MotionEventResolutionJob dequeue();
	
	/**
	 * <p>Initiates the consumption and execution of {@link MotionEventResolutionJob}s 
	 * which are enqueued.
	 * 
	 * @since 1.1.0
	 */
	public void startConsuming();

	/**
	 * <p>Terminates the consumption and execution of {@link MotionEventResolutionJob}s 
	 * which are enqueued.
	 * 
	 * @since 1.1.0
	 */
	public void stopConsuming();
}
