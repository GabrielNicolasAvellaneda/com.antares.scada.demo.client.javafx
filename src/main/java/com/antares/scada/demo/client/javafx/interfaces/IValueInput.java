package com.antares.scada.demo.client.javafx.interfaces;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public interface IValueInput {

	public void setValue(Object Value);
	public Object getValue();
	public ObjectProperty<Object> valueProperty();
	
	public void setType(final String type);
	public String getType();
	StringProperty typeProperty();	
}
