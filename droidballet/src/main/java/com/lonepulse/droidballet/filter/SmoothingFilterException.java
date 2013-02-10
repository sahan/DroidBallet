package com.lonepulse.droidballet.filter;

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

import com.lonepulse.droidballet.DroidBalletException;

/**
 * <p>This exception is thrown due to a recoverable error when executing 
 * a {@link SmoothingFilter}.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class SmoothingFilterException extends DroidBalletException {


	private static final long serialVersionUID = 9036432522478867132L;

	
	/**
	 * <p>Prints a default message which indicates the implementation of {@link SmoothingFilter} 
	 * whose execution failed.
	 * 
	 * @param smoothingFilterClass
	 * 			the {@link Class} of the {@link SmoothingFilter} implementation.
	 * 
	 * @param info
	 * 			additional information regarding the exception
	 * 
	 * @param cause
	 * 			the root cause of the exception
	 */
	public SmoothingFilterException(Class<? extends SmoothingFilter> smoothingFilterClass, String info, Throwable cause) {
		
		this("Failed to execute an instance of the smoothing filter " + smoothingFilterClass.getName() + 
				". " + info, cause);
	}
	
	/**
	 * <p>Prints a default message which indicates the implementation of {@link SmoothingFilter} 
	 * whose execution failed.
	 * 
	 * @param smoothingFilterClass
	 * 			the {@link Class} of the {@link SmoothingFilter} implementation.
	 * 
	 * @param info
	 * 			additional information regarding the exception
	 */
	public SmoothingFilterException(Class<? extends SmoothingFilter> smoothingFilterClass, String info) {
		
		this("Failed to execute an instance of the smoothing filter " + smoothingFilterClass.getName() + 
			 ". " + info);
	}
	
	/**
	 * See {@link DroidBalletException#DroidBalletException()}. 
	 */
	public SmoothingFilterException() {
		
		this("Failed to execute the smoothing filter. " + 
			 "Please check your algorithm and the supplied parameters.");
	}

	/**
	 * See {@link DroidBalletException#DroidBalletException(String))}.
	 */
	public SmoothingFilterException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link DroidBalletException#DroidBalletException(Throwable))}.
	 */
	public SmoothingFilterException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link DroidBalletException#DroidBalletException(String, Throwable))}.
	 */
	public SmoothingFilterException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
