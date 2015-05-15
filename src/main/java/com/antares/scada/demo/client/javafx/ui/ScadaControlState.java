package com.antares.scada.demo.client.javafx.ui;

import java.util.HashMap;

import javafx.beans.DefaultProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

@DefaultProperty("properties")
public class ScadaControlState {

	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty value = new SimpleIntegerProperty();
	//private final ObservableMap<String, String> properties = FXCollections.observableMap(new HashMap<String, String>());
	private final MapProperty<String, String> properties = new SimpleMapProperty<String, String>();
	
	public StringProperty nameProperty() {
		return this.name;
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public void setName(final String value) {
		this.name.set(value);
	}
	
	public IntegerProperty valueProperty() {
		return this.value;
	}
	
	public int getValue() {
		return this.value.get();
	}
	
	public void setValue(int value) {
		this.value.set(value);
	}
	
	public ObservableMap<String, String> getProperties() {
		return this.properties.get();
	}
	
	public void setProperties(ObservableMap<String, String> value) {
		this.properties.set(value);
	}

	public MapProperty<String, String> propertiesProperty() {
		return this.properties;
	}
}
