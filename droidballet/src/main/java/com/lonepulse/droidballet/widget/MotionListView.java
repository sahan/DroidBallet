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
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.lonepulse.droidballet.listener.VerticalMotionEvent;
import com.lonepulse.droidballet.listener.VerticalMotionListener;
import com.lonepulse.droidballet.registry.MotionViewRegistry;

/**
 * <p>An extension of {@link ListView} which listens to {@link VerticalMotionEvent}s 
 * by implementing {@link VerticalMotionListener} and performs the necessary UI updates 
 * for the respective callback methods.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionListView extends ListView implements MotionView, VerticalMotionListener {

	
	/**
	 * <p>A flag which determines if motion scrolling is to be disabled momentarily.
	 */
	private boolean scrollDisabled = false;
	
	
	/**
	 * See {@link ListView#ListView(Context)}.
	 */
	public MotionListView(Context context) {
	
		super(context);
		init();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet)}.
	 */
	public MotionListView(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		init();
	}
	
	/**
	 * See {@link ListView#ListView(Context, AttributeSet, int)}.
	 */
	public MotionListView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		init();
	}
	
	/**
	 * <p>Initializes the {@link OnTouchListener} of this {@link ListView} which momentarily 
	 * disables motion scrolling (by setting {@link #scrollDisabled} to <b>true</b>) while 
	 * the user is actively interacting with the list. 
	 */
	private void init() {
		
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

		if(!scrollDisabled)
			smoothScrollBy(event.getScrollDistance(), event.getScrollDuration());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionDown(VerticalMotionEvent event) {
		
		if(!scrollDisabled)
			smoothScrollBy(event.getScrollDistance(), event.getScrollDuration());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMotionRest(VerticalMotionEvent event) {
	}
}
