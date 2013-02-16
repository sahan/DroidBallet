package com.lonepulse.droidballet.detector;

import android.hardware.SensorEvent;

import com.lonepulse.droidballet.DroidBalletException;

/**
 * <p>This {@link DroidBalletException} is thrown when a motion detection 
 * operation failed for a particular sensor event.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MotionDetectorException extends DroidBalletException {


	private static final long serialVersionUID = 1793819813446546812L;
	
	
	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 * 
	 * @param motionDetectorType
	 * 			the {@link Class} type of the {@link MotionDetector} implementation
	 * 
	 * @param sensorEvent
	 * 			the {@link SensorEvent} for which motion detection failed
	 * 
	 * @param rootCause
	 * 			the {@link Exception} which was the root cause
	 */
	public MotionDetectorException(Class<? extends MotionDetector<?>> motionDetectorType, 
								   SensorEvent sensorEvent,
								   Throwable rootCause) {
		
		this("Motion detection failed on " + motionDetectorType + 
			 " with sensor values " + sensorEvent.values + ". ", rootCause);
	}
	
	/**
	 * <p>See {@link DroidBalletException#DroidBalletException()}.
	 */
	public MotionDetectorException() {}

	/**
	 * <p>See {@link DroidBalletException#DroidBalletException(String)}.
	 */
	public MotionDetectorException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * <p>See {@link DroidBalletException#DroidBalletException(Throwable)}.
	 */
	public MotionDetectorException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * <p>See {@link DroidBalletException#DroidBalletException(String, Throwable)}.
	 */
	public MotionDetectorException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
