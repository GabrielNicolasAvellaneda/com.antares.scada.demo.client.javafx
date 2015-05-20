package com.antares.scada.demo.client.javafx.ui;

import javafx.beans.DefaultProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableMap;

@DefaultProperty("properties")
public class ScadaControlState {

	private final StringProperty name = new SimpleStringProperty();
	private final ObjectProperty<Object> value = new SimpleObjectProperty<Object>();
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
	
	public ObjectProperty<Object> valueProperty() {
		return this.value;
	}
	
	public Object getValue() {
		return this.value.get();
	}
	
	public void setValue(Object value) {
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
