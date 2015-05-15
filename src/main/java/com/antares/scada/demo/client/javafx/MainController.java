package com.antares.scada.demo.client.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import org.eclipse.scada.core.Variant;

import com.antares.scada.demo.client.javafx.ui.ScadaButton;


public class MainController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void test(ActionEvent event)
    {
        ScadaButton button = (ScadaButton)event.getSource();
        Variant value = Variant.valueOf(17);
        
        //button.write(value);
    }
	
}
