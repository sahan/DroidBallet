package com.lonepulse.droidballet.widget;

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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.lonepulse.droidballet.R;
import com.lonepulse.droidballet.listener.VerticalMotionEvent;
import com.lonepulse.droidballet.listener.VerticalMotionEvent.VERTICAL_DIRECTION;
import com.lonepulse.droidballet.listener.VerticalMotionListener;
import com.lonepulse.droidballet.registry.MotionViewRegistry;

/**
 * <p>An extension of {@link ListView} which listens to {@link VerticalMotionEvent}s 
 * by implementing {@link VerticalMotionListener} and performs the necessary UI updates 
 * for the respective callback methods.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionListView extends ListView implements MotionView, VerticalMotionListener {


	/**
	 * <p>The minimum scroll duration in milliseconds.
	 */
	private static final int DRAG_COEFFICIENT = 2;
	
	/**
	 * <p>Represents a force of resistivity whose vector opposes to that of the velocity.
	 * A default friction factor of <b>0.75</b> is used where a comfortable range for this 
	 * value lies between <b>0.0</b> and <b>1.0</b>. However, values beyond these bounds may 
	 * be employed to produce breakneck speeds or to slow down to a snail's pace.
	 */
	private float friction;
	
	/**
	 * <p>A flag which determines if motion scrolling is to be disabled momentarily.
	 */
	private boolean scrollDisabled = false;
	
	
	/**
	 * See {@link ListView#ListView(Context)}.
	 */
	public MotionListView(Context context) {
	
		super(context);
		
		initListeners();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet)}.
	 */
	public MotionListView(Context context, AttributeSet attrs) {
	
		super(context, attrs);

		initAttributes(context, attrs);
		initListeners();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet, int)}.
	 */
	public MotionListView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		
		initAttributes(context, attrs);
		initListeners();
	}

   /**
    * <p>Initializes the view with the custom attributes which were declared 
    * in the XML layout. This 
    * 
	* @param context
	* 			the {@link Context} in which this component is instantiated
	* 
	* @param attributeSet
	* 			the {@link AttributeSet} given in the layout declaration
	*/
	private void initAttributes(Context context, AttributeSet attributeSet) {
		
		TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MotionView);
		
		friction = typedArray.getFloat(R.styleable.MotionView_friction, 0.75f) * 1000;
			
		typedArray.recycle();
	}
	
	/**
	 * <p>Initializes the {@link OnTouchListener} of this {@link ListView} which momentarily 
	 * disables motion scrolling (by setting {@link #scrollDisabled} to <b>true</b>) while 
	 * the user is actively interacting with the list.
	 */
	private void initListeners() {
		
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN)
					scrollDisabled = true;

				else if (event.getAction() == MotionEvent.ACTION_UP)
					scrollDisabled = false;

				return false;
			}
		});
	}
	
	/**
	 * <p>Accessor for {@link #friction}.
	 *
	 * @return the {@link #friction}
	 * 
	 * @since 1.1.1
	 */
	public float getFriction() {
		
		return friction;
	}

	/**
	 * <p>Mutator for {@link #friction}.
	 *
	 * @param friction 
	 *			the value to set {@link #friction}
	 *
	 * @since 1.1.1
	 */
	public void setFriction(float friction) {
		
		this.friction = friction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register() {

		MotionViewRegistry.INSTANCE.register(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregister() {
		
		MotionViewRegistry.INSTANCE.unregister(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionUp(VerticalMotionEvent event) {

		if(!scrollDisabled) {
		
			int velocity = MotionListView.processVelocity(event.getDirection(), 
														  event.getFilteredOutput()[1], 
														  event.getSensorEvent().sensor.getMaximumRange());
			
			int scrollDistance = processScrollDistance(velocity);
			int scrollDuration = processScrollDuration(velocity);
			
			smoothScrollBy(scrollDistance, scrollDuration);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionDown(VerticalMotionEvent event) {
		
		if(!scrollDisabled) {
		
			int velocity = MotionListView.processVelocity(event.getDirection(), 
												          event.getFilteredOutput()[1], 
												          event.getSensorEvent().sensor.getMaximumRange());
			
			int scrollDistance = processScrollDistance(velocity);
			int scrollDuration = processScrollDuration(velocity);
			
			smoothScrollBy(scrollDistance, scrollDuration);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionRest(VerticalMotionEvent event) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int processScrollDistance(int yAxisReading) {
		
		return (int) (yAxisReading * Math.pow(yAxisReading, DRAG_COEFFICIENT));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int processScrollDuration(int yAxisReading) {

		return (int) (friction + Math.pow(yAxisReading, DRAG_COEFFICIENT));
	}
	
	/**
	 * <p>Takes the motion sensor reading on the Y-Axis and converts it to 
	 * a vector with a direction which is essentially the <b>velocity</b>.
	 *
	 * @param direction
	 *  		the {@link VERTICAL_DIRECTION} of the motion
	 *  
	 * @param sensorReading
	 * 			the sensor reading on the Y-Axis
	 *  
	 * @param maxSensorReading
	 * 			the maximum value which can be reached by a sensor reading 
	 *  
	 * @return the processed Y-Axis velocity
	 */
	private static int processVelocity(VERTICAL_DIRECTION direction, 
									   float sensorReading, 
									   float maxSensorReading) {
		
		switch (direction) {
		
			case UP: {
				
				return (int) (-1 * (maxSensorReading + sensorReading));
			}
				
			case DOWN: {
				
				return (int) sensorReading;
			}
				
			case NONE: default: { 
				
				return 0;
			}
		}
	}
}
