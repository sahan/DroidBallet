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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>An abstract implementation of a {@link SmoothingFilter} which handles the algorithm 
 * <i>security</i> and <i>execution</i>. 
 * 
 * <p>The execute an implementation, {@link AbstractSmoothingFilter#filter(float[], float[], Map)} 
 * should be invoked.
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractSmoothingFilter implements SmoothingFilter {

	
	/**
	 * <p>A {@link Map} which contains all the arguments used in the filter 
	 * and their associated types. 
	 */
	private Map<Integer, Class<?>> argTypes = new HashMap<Integer, Class<?>>();
	

	/**
	 * <p>The template method which must be used to register arguments (if any) for 
	 * the filter algorithm.
	 * 
	 * <p>A sample argument registration would be as follows:
	 * <pre>
	 * 	argTypes.put(0, Float.class);
	 *  defaultArgs.put(0, Float.valueOf(0.90f));
	 * </pre>
	 * </p>
	 * 
	 * @param argTypes
	 * 			the {@link Map} which registers parameter identifiers ({@link Integer}s) 
	 * 			along with their {@link Class} types.
	 */
	protected void registerParameters(Map<Integer, Class<?>> argTypes) {
	}
	
	/**
	 * <p>The template method provided for the actual algorithm implementation. 
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
	protected abstract float[] runAlgorithm(float[] input, float[] output, Map<Integer, ? extends Object> args) 
	throws SmoothingFilterException;
	
	/**
	 * <p>Validates the input and output arrays along with the arguments supplied 
	 * to the filter algorithm.
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
	 * @throws SmoothingFilterException
	 * 			when the algorithm is erroneous or it cannot execute due to 
	 * 			the supplied parameters 
	 */
	private void validate(float[] input, float[] output, Map<Integer, ? extends Object> args) 
	throws SmoothingFilterException {
		
		if(input == null)
			throw new SmoothingFilterException(getClass(), "Input array is null.", new NullPointerException());
		
		if(output == null) 
			throw new SmoothingFilterException(getClass(), "Output array is null.", new NullPointerException());
		
		if(input.length != output.length)
			throw new SmoothingFilterException(getClass(), 
					"Input array length does not match the Output array length.");
		
		if(args != null) {
			
			Set<Integer> keys = argTypes.keySet();
			
			for (Integer argId :keys) {
				
				if(!args.containsKey(argId)) {
					
						throw new SmoothingFilterException(getClass(), 
								"Arguments do not contain an identifier with value " + argId + ". ");
				}
				
				if(!(args.get(argId).getClass().equals(argTypes.get(argId)))) {
				
					StringBuilder builder = new StringBuilder()
					.append("Arguments with identifier ")
					.append(argId)
					.append(" is not of registered type ")
					.append(argTypes.get(argId).getName())
					.append(". ");
					
					throw new SmoothingFilterException(getClass(), builder.toString());
				}
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float[] filter(float[] input, float[] output, Map<Integer, ? extends Object> args) 
	throws SmoothingFilterException {

		try {
		
			registerParameters(this.argTypes);
			validate(input, output, args);
			
			return runAlgorithm(input, output, args);
		}
		catch (Exception e) {
			
			throw new SmoothingFilterException(getClass(), "", e);
		}
	}
}
