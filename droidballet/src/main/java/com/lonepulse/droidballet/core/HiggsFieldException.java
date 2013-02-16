package com.lonepulse.droidballet.core;

import com.lonepulse.droidballet.DroidBalletRuntimeException;

/**
 * <p>This runtime exception is thrown due to an unrecoverable failure in 
 * the {@link HiggsField}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HiggsFieldException extends DroidBalletRuntimeException {


	private static final long serialVersionUID = -7733562133899460260L;

	
	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException()}.
	 */
	public HiggsFieldException() {}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(String)}.
	 */
	public HiggsFieldException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(Throwable)}.
	 */
	public HiggsFieldException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(String, Throwable)}.
	 */
	public HiggsFieldException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
