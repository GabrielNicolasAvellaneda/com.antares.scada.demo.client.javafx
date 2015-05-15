package com.antares.scada.demo.client.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import com.antares.scada.demo.client.javafx.ui.ScadaButton;
import com.antares.scada.demo.client.javafx.ui.WindowControllerBase;

public class WindowController extends WindowControllerBase {

	public WindowController() throws Exception {
		
		super();
	}
	
	@FXML
	private Label myLabel;
	
	@FXML
	private ScadaButton myButton;
	
	private IntegerProperty counter;
	
	public IntegerProperty counterProperty() {
		return counter;
	}
	
	public int getCounter() {
		return this.counter.get();
	}
	
	public void setCounter(int value) {
		this.counter.set(value);
	}
	
	@FXML
    private void handleStartButtonAction(ActionEvent event)
    {
        setCounter(getCounter() - 1);
        System.out.println(getCounter());
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
	}
	
}
