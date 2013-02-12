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
 * <p>A realization of a {@link SmoothingFilter} which implements the 
 * <a href="http://en.wikipedia.org/wiki/Low-pass_filter#Algorithmic_implementation">
 * Low Pass Filter</a> algorithm.</p>
 * 
 * <p><font color="#424242"><pre>
 * function lowpass(real[0..n] x, real dt, real RC)
 *  
 * var real[0..n] y
 * var real α := dt / (RC + dt)
 *  
 * y[0] := x[0]
 *  
 * for i from 1 to n
 *     y[i] := α * x[i] + (1-α) * y[i-1]
 *  
 * return y
 * 
 * <table border="1">
 * <tr><td>dt: Time Interval</td</tr>
 * <tr><td>RC: Time Constant</tr></td>
 * </table>
 * </pre></font></p>
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class LowPassFilter extends AbstractSmoothingFilter {

	
	/**
	 * <p>The ALPHA constant over which the input values are smoothened. 
	 * This should be <b>between 0 and 1</b>. This value is derived from 
	 * the equation <font color="#2F2F2F">{@code α := dt / (RC + dt)}</font>.  
	 */
	public static final int ALPHA = 0;
	
	/**
	 * <p>The default value of ALPHA which is used in the algorithm. 
	 */
	private static final float DEFAULT_APLPHA = 0.90f;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerParameters(Map<Integer, Class<?>> argTypes) {

		argTypes.put(ALPHA, Float.class);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>The arguments {@link Map} can be <b>null</b>, in which case the 
	 * default value for ALPHA will be used. If arguments are passed, an 
	 * ALPHA value <b>should</b> be included.
	 * 
	 * <p>The ALPHA can be set my passing a {@link Float} referenced via 
	 * {@link LowPassFilter#ALPHA}. The default value used is 
	 * <b>{@value #DEFAULT_APLPHA}</b>.
	 */
	@Override
	protected float[] runAlgorithm(float[] input, float[] output, Map<Integer, ? extends Object> args) 
	throws SmoothingFilterException {
		
		float alpha = DEFAULT_APLPHA;
		
		if(args != null)
			alpha = (Float) args.get(ALPHA); 
			
		for (int i = 0; i < input.length; i++)
			output[i] = output[i] + alpha * (input[i] - output[i]);

		return output;
	}
}
