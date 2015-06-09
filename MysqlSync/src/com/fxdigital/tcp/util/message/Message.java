package com.fxdigital.tcp.util.message;

import java.io.Serializable;

public interface Message extends Serializable {

	public abstract int getType();
	
	public abstract boolean isLast();
	
	public abstract String getMessage();
}
