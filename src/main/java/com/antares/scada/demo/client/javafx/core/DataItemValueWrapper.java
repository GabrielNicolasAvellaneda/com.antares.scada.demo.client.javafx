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

	public Object getValue() {
		try {
			final Variant variant = dataItemValue.getValue();
			if (variant.isBoolean()) {
				return variant.asBoolean();
			} else if (variant.isDouble()) {
				return variant.asDouble();
			} else if (variant.isInteger()) {
				return variant.asInteger();
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
}