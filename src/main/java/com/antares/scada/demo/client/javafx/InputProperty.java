package com.antares.scada.demo.client.javafx;

import javafx.beans.property.ObjectProperty;
import com.antares.scada.demo.client.javafx.Item;

public interface InputProperty {

	public ObjectProperty<Item> inputProperty ();
	
}
