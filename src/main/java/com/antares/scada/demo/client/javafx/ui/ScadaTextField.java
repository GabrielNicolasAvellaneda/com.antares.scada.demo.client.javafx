package com.antares.scada.demo.client.javafx.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import com.antares.scada.demo.client.javafx.interfaces.IValueInput;

public class ScadaTextField extends TextField implements IValueInput {

	private final ObjectProperty<Object> value = new SimpleObjectProperty<Object>();
	private final StringProperty type = new SimpleStringProperty("int");
	
	public ScadaTextField() {
		super();
		
		this.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				// Update the converted value.
				updateState();
			}
		});
		
	}
	
	private void updateState() {
		final String textValue = this.getText();
		this.setValue(convertTo(this.getType(), textValue));
	}

	
	@Override
	public void setValue(Object value) {
		this.value.set(value);
	}
	
	@Override
	public Object getValue() {
		//return convertTo(type,  this.value.get());
		return this.value.get();
	}
	
	private Object convertTo(String type, final String value) {
		type = type.toLowerCase();
		if (type.equals("int") || type.equals("integer")) {
			return Integer.parseInt((String) value);
		} else if (type.equals("bool") || type.equals("boolean")) {
			return Boolean.parseBoolean((String) value);
		} else if (type.equals("double")) {
			return Double.parseDouble(value);
		} else if (type.equals("float")) {
			return Float.parseFloat(value);
		}
		
		return value;
	}
	
	@Override
	public ObjectProperty<Object> valueProperty() {
		return this.value;
	}
		
	@Override
	public void setType(String type) {
		this.type.set(type);		
	}

	@Override
	public String getType() {
		return this.type.get();
	}
	
	@Override
	public StringProperty typeProperty() {
		return this.type;
	}
}
