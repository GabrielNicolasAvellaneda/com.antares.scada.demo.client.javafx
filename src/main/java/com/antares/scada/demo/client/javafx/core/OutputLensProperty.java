package com.antares.scada.demo.client.javafx.core;

import org.eclipse.scada.da.client.DataItemValue;

import javafx.beans.property.ObjectProperty;

public interface OutputLensProperty {

	public ObjectProperty<DataItemValue> outputLens ();
	
}
