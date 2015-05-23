package com.antares.scada.demo.client.javafx.core;

import java.util.Calendar;

import org.eclipse.scada.core.NotConvertableException;
import org.eclipse.scada.core.NullValueException;
import org.eclipse.scada.core.Variant;
import org.eclipse.scada.da.client.DataItemValue;

import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;

public class DataItemValueWrapper implements IDataItemValue {

	private DataItemValue dataItemValue;

	public DataItemValueWrapper(DataItemValue dataItemValue) {
		this.dataItemValue = dataItemValue;
	}
	
	@Override
	public boolean isInteger() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isInteger();
	}
	
	@Override
	public boolean isLong() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isLong();
	}
	
	@Override
	public boolean isDouble() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isDouble();
	}
	
	@Override
	public boolean isString() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isString();
	}
	
	@Override
	public boolean isNull() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isNull();
	}
	
	@Override
	public boolean isBoolean() {
		Variant variant = this.dataItemValue.getValue();
		
		return variant.isBoolean();
	}
	
	public boolean coerceEquals(Object other) {
		try {
			Object localValue = getValue();
			Variant otherVariant = Variant.valueOf(other);
						
			if (isBoolean()) {
				return localValue.equals(otherVariant.asBoolean());
			} else if (isInteger()) {
				return localValue.equals(otherVariant.asInteger());
			} else if (isDouble()) {
				return localValue.equals(otherVariant.asDouble());
			} else if (isString()) {
				return localValue.equals(otherVariant.asString());
			} 
		} catch (NullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotConvertableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Object getValue() {
		try {
			final Variant variant = dataItemValue.getValue();
			if (variant.isBoolean()) {
				return variant.asBoolean();
			} else if (variant.isDouble()) {
				return variant.asDouble();
			} else if (variant.isInteger()) {
				return variant.asInteger();
			} else if (variant.isLong()) {
				return variant.asLong();
			} else if (variant.isString()) {
				return variant.asString();
			} else if (variant.isNull()) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public Boolean isAlarm() {

		return dataItemValue.isAlarm();
	}

	@Override
	public Boolean isWarning() {

		return dataItemValue.isWarning();
	}

	@Override
	public Boolean isError() {

		return dataItemValue.isError();
	}

	@Override
	public Boolean isManual() {

		return dataItemValue.isManual();
	}

	@Override
	public Boolean isBlocked() {
		return dataItemValue.isBlocked();
	}

	@Override
	public Boolean isConnected() {
		return dataItemValue.isConnected();
	}

	@Override
	public Calendar getTimestamp() {
		return dataItemValue.getTimestamp();
	}

	@Override
	public Double getValueAsDouble() throws NullValueException, NotConvertableException {
		return dataItemValue.getValue().asDouble();
	}

	@Override
	public int getValueAsInteger() throws NullValueException,
			NotConvertableException {
		return dataItemValue.getValue().asInteger();
	}
	
}
