package com.antares.scada.demo.client.javafx.interfaces;

import java.util.Calendar;

import org.eclipse.scada.core.NotConvertableException;
import org.eclipse.scada.core.NullValueException;

public interface IDataItemValue {

	public Boolean isAlarm();
	public Boolean isWarning();
	public Boolean isError();
	public Boolean isManual();
	public Boolean isBlocked();
	public Boolean isConnected();
	public Object getValue();
	public Calendar getTimestamp();
	public boolean coerceEquals(Object value);
	public boolean isBoolean();
	public boolean isNull();
	public boolean isString();
	public boolean isDouble();
	public boolean isLong();
	public boolean isInteger();
	public Double getValueAsDouble() throws NullValueException, NotConvertableException;
	public int  getValueAsInteger() throws NullValueException, NotConvertableException;
}
