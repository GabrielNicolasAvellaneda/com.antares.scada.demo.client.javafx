package com.antares.scada.demo.client.javafx.interfaces;

import javafx.beans.property.ObjectProperty;

import org.eclipse.scada.core.Variant;
import org.eclipse.scada.da.client.DataItemValue;

// An interface to abstract a wrapper for a dataitem

public interface IDataItem {

	public void setState(IDataItemValue value);
	public IDataItemValue getState();
	public ObjectProperty<IDataItemValue> stateProperty();
	public void write(Object value);
	
}
