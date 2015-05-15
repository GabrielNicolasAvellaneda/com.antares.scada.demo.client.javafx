package com.antares.scada.demo.client.javafx.interfaces;

import com.antares.scada.demo.client.javafx.interfaces.IDataItem;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public interface ISwitchInput {
	
	public String getSwitchInputId ();
    public void setSwitchInputId ( final String value );
    public StringProperty switchInputIdProperty();
    public ObjectProperty<Object> inputSourceValueProperty();
    public void setInputSourceValue(Object value);
    public Object getInputSourceValue();
    
    public ObjectProperty<IDataItem> switchInputProperty();
    public void setSwitchInput(final IDataItem item);
    public IDataItem getSwitchInput();
	
}
