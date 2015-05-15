package com.antares.scada.demo.client.javafx.interfaces;

import com.antares.scada.demo.client.javafx.ui.ScadaControlState;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

public interface IOutputLens {
	
	public String getOutputLensId ();
	public void setOutputLensId(String value);
	public ObjectProperty<IDataItem> outputLensProperty();
	public void setOutputLens(IDataItem value);
	public IDataItem getOutputLens();
	public ObservableList<ScadaControlState> getStates();
	//public void setStates(ObservableList<ScadaControlState> value);
	//public ListProperty statesProperty();
}
