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


import java.util.Map;

/**
 * <p>Declares the contract offered by a <a href="http://en.wikipedia.org/wiki/Smoothing#Smoothing_Algorithms">
 * Smoothing Filter</a> which will be used to smooth <b>sensor readings</b>.
 * 
 * <p><em>"Smoothing a data set involves creating an approximating function that 
 * attempts to capture important patterns in the data, while leaving out noise or other 
 * fine-scale structures or rapid phenomena" -  Wikipedia.</em></p>
 * 
 * <p>All implementations should extend {@link AbstractSmoothingFilter} instead of 
 * the {@link SmoothingFilter} contract. 
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface SmoothingFilter {

	/**
	 * <p>Takes an array of floating point values as input and filters 
	 * them using the implemented smoothing algorithm.
	 * 
	 * @param input
	 * 			an array of floating point values which need to be filtered
	 * 
	 * @param output
	 * 			the array used to populate the output values
	 * 
	 * @param args
	 * 			arguments to the filtering algorithm; individual algorithms 
	 * 			should define their arguments as static integer parameters 
	 * 			which will be referenced by those that invoke the algorithm
	 * 
	 * @return the filtered (<i>smoothed</i>) array of floats
	 * 
	 * @throws SmoothingFilterException
	 * 			when the algorithm is erroneous or it cannot execute due to 
	 * 			the supplied parameters 
	 */
	public float[] filter(final float[] input, final float[] output, final Map<Integer, ? extends Object> args) 
	throws SmoothingFilterException;

}