package com.antares.scada.demo.client.javafx.ui;

import org.controlsfx.control.PopOver;

public class ScadaKeypadPopOver extends PopOver {


	public ScadaKeypadPopOver() {
		
		super();
		
		this.setContentNode(new ScadaKeypad());
	}
	
}
