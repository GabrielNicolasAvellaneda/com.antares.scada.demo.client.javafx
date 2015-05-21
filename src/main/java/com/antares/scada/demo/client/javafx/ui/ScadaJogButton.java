package com.antares.scada.demo.client.javafx.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ScadaJogButton extends ScadaButton {
	private final ObjectProperty<Object> onPressValue = new SimpleObjectProperty<Object>();
	
	public ScadaJogButton() {
		super();
		
		this.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				Object value = onPressValue.get();
				if (value != null) {
					write(value);	
				}
			}
		});
	}
	
	public ObjectProperty<Object> pressValueProperty() {
		return this.onPressValue;
	}
	
	public void setPressValue(Object value) {
		this.onPressValue.set(value);
	}
	
	public Object getPressValue() {
		return this.onPressValue.get();
	}
	
}
