package com.lonepulse.droidballet.core;

/**
 * <p>This {@link HiggsFieldException} is thrown if there is a failure in 
 * initializing the {@link HiggsField#INSTANCE}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HiggsFieldUninitializedException extends HiggsFieldException {


	private static final long serialVersionUID = 2268659343072087845L;

	
	/**
	 * <p>See {@link HiggsFieldException#HiggsFieldException()}.
	 */
	public HiggsFieldUninitializedException() {}

	/**
	 * <p>See {@link HiggsFieldException#HiggsFieldException(String))}.
	 */
	public HiggsFieldUninitializedException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * <p>See {@link HiggsFieldException#HiggsFieldException(Throwable))}.
	 */
	public HiggsFieldUninitializedException(Throwable throwable) {

		super(throwable);
	}

	/**
	 * <p>See {@link HiggsFieldException#HiggsFieldException(String, Throwable))}.
	 */
	public HiggsFieldUninitializedException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
