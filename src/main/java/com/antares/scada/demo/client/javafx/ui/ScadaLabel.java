package com.antares.scada.demo.client.javafx.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.scada.core.Variant;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.antares.scada.demo.client.javafx.core.OutputLensBehaviour;
import com.antares.scada.demo.client.javafx.interfaces.IDataItem;
import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;
import com.antares.scada.demo.client.javafx.interfaces.IOutputLens;
import com.antares.scada.demo.client.javafx.interfaces.ISwitchInput;
import com.antares.scada.demo.client.javafx.interfaces.IVariant;
import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.collections.ObservableMapWrapper;
import com.sun.javafx.fxml.BeanAdapter;

@DefaultProperty("states")
public class ScadaLabel extends Label implements IOutputLens
{
	
	private final OutputLensBehaviour outputLensBehaviour = new OutputLensBehaviour(this, "scada-label", new ChangeListener<IDataItemValue>() {

		@Override
		public void changed(
				ObservableValue<? extends IDataItemValue> observable,
				IDataItemValue oldValue, IDataItemValue newValue) {
			// TODO Auto-generated method stub
			Object value = newValue.getValue();
			if (value != null) {
				ScadaLabel.this.setText(value.toString());	
			}
			
			
		}
	});
	
    public ScadaLabel() {
    	super();
   }
    
    public String styleClassPrefixProperty() {
    	return this.outputLensBehaviour.styleClassPrefixProperty();
    }
    
	protected void updateStateInstance ( final IDataItemValue newValue )
    {
		if (newValue.getValue() != null) {
        	this.setText(newValue.getValue().toString());	
        }
   }

	@Override
	public String getOutputLensId() {
		return this.outputLensBehaviour.getOutputLensId();
	}

	@Override
	public void setOutputLensId(String value) {
		this.outputLensBehaviour.setOutputLensId(value);
	}

	@Override
	public StringProperty outputLensIdProperty() {
		return this.outputLensBehaviour.outputLensIdProperty();
	}

	@Override
	public ObjectProperty<IDataItem> outputLensItemProperty() {
		return this.outputLensBehaviour.outputLensItemProperty();
	}

	@Override
	public void setOutputLensItem(IDataItem value) {
		this.outputLensBehaviour.setOutputLensItem(value);
	}

	@Override
	public IDataItem getOutputLensItem() {
		return this.outputLensBehaviour.getOutputLensItem();
	}

	@Override
	public ObservableList<ScadaControlState> getStates() {
		return this.outputLensBehaviour.getStates();
	}
	

    
}