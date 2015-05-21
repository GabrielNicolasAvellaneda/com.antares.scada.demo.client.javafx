package com.antares.scada.demo.client.javafx.interfaces;

import com.antares.scada.demo.client.javafx.ui.ScadaControlState;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public interface IOutputLens {
	
	String getOutputLensId ();
	void setOutputLensId(String value);
	StringProperty outputLensIdProperty();
	ObjectProperty<IDataItem> outputLensItemProperty();
	void setOutputLensItem(IDataItem value);
	IDataItem getOutputLensItem();
	ObservableList<ScadaControlState> getStates();
	String styleClassPrefixProperty();
}
