package com.antares.scada.demo.client.javafx.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScadaKeypadLogic {

	private IntegerProperty value = new SimpleIntegerProperty(0);
	private IntegerProperty minimum = new SimpleIntegerProperty(0);
	private IntegerProperty maximum = new SimpleIntegerProperty(Integer.MAX_VALUE);
	
	ScadaKeypadLogic() {
	
	}
	
	public void appendDigit(int digit) {
		setValue(getValue() * 10 + digit);
	}
	
	public IntegerProperty maximumProperty () {
		return this.maximum;
	}
	
	public void setMaximum(int maximum) {
		this.maximum.set(maximum);
		if (getValue() > maximum) {
			setToMaximum();
		}
	}
	
	private void setToMaximum() {
		setValue(getMaximum());
	}
	
	private void setToMinimum() {
		setValue(getMinimum());
	}
	
	public int getMaximum() {
		return this.maximum.get();
	}
	
	public IntegerProperty minimumProperty() {
		return this.minimum;
	}
	
	public void setMinimum(int minimum) {
		this.minimum.set(minimum);
		if (getValue() < minimum) {
			setToMinimum();
		}
	}
	
	public int getMinimum() {
		return this.minimum.get();
	}
	
	public IntegerProperty valueProperty() {
		return this.value;
	}
	
	public int getValue() {
		return this.value.get();
	}
	
	public void setValue(int value) {
		if (value > getMaximum()) {
			setToMaximum();
		} else if (value < getMinimum()) {
			setToMinimum();
		} else {
			this.value.set(value);	
		}
	}
	
	public void increment() {
		int value = getValue();
		setValue(value + 1);
	}
	
	public void decrement() {
		int value = getValue();
		setValue(value - 1);
	}
	
	public void clear() {
		setToMinimum();
	}
	
}
