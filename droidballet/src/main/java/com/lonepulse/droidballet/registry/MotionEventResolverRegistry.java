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


import android.view.ViewGroup;

import com.lonepulse.droidballet.listener.MotionListener;
import com.lonepulse.droidballet.resolver.MotionEventResolver;

/**
 * <p>This contract specifies how {@link MotionEventResolver}s can register 
 * and unregister themselves on a central registry.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface MotionEventResolverRegistry {

	/**
	 * <p>Registers a {@link MotionEventResolver} on a central registry.</p>
	 * 
	 * @param motionEventResolver
	 * 			the {@link MotionListener} (Widget or {@link ViewGroup}) to be registered
	 * 
	 * @since 1.0.0
	 */
	public abstract <T extends MotionEventResolver> void register(T motionEventResolver);
	
	/**
	 * <p>Unregisters a {@link MotionEventResolver} on a central registry.</p> 
	 * 
	 * <p>If the specified {@link MotionEventResolver} has not being registered, 
	 * a waring is logged and the error is silently ignored.
	 * 
	 * @param motionEventResolver
	 * 			the {@link MotionEventResolver} to be unregistered
	 * 
	 * @since 1.0.0
	 */
	public abstract <T extends MotionEventResolver> void unregister(T motionEventResolver);
}
