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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

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
public class ScadaButton extends Button implements ISwitchInput, IOutputLens
{
	private final StringProperty switchInputId = new SimpleStringProperty ();
		
	private final ObjectProperty<IDataItem> switchInput = new SimpleObjectProperty<> ();
	
	private ObjectProperty<Object> inputSourceValue = new SimpleObjectProperty<> ();
		
	private final OutputLensBehaviour outputLensBehaviour = new OutputLensBehaviour(this, "scada-button", new ChangeListener<IDataItemValue>() {
		
		@Override
		public void changed(ObservableValue<? extends IDataItemValue> observable,
				IDataItemValue oldValue, IDataItemValue newValue) {
			
			// Do nothing.
		}
	});
		
    public ScadaButton() {
    	super();
    	
    	this.getStyleClass().add("scada-button"); // initial style
    	
    	// TODO: Place this in a behavior class.
    	
    	this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				write(getInputSourceValue());
				
			}
		});
    	
    	this.setOnTouchPressed(new EventHandler<TouchEvent>() {

			@Override
			public void handle(TouchEvent event) {
				// TODO Auto-generated method stub
				System.out.println("touch pressed");
			}
    		
    		
		});
    	
    	this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				for (ScadaControlState entry : getStates()) {
					System.out.println(entry.getName());
				}
				// TODO: Get the value from another property or control?
				write(getInputSourceValue());
			}
		});
    }
    
    // TODO: This functionality should be in another class?
    protected void write(Object value) {
    	IDataItem item = this.switchInput.getValue();
    	if (item != null) {
    		item.write(value);	
    	}
    }
    
    /**
     * OutputLens Properties
     */
    @Override
    public String getOutputLensId() {
    	return this.outputLensBehaviour.getOutputLensId();
    }
    
    @Override
    public void setOutputLensId(final String value) {
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
	public IDataItem getOutputLensItem() {
		return this.outputLensBehaviour.getOutputLensItem();
	}

	@Override
	public void setOutputLensItem(IDataItem item) {
		
		this.outputLensBehaviour.setOutputLensItem(item);
	}
	
    /**
     * SwitchInput property
     */
	
	/**
     * Map of states and properties
     * Should accept something like state['state0'] = Hasmap<String, String> and then apply that properties when the outputlens property changes.
     * @return
     */
    @Override
    public ObservableList<ScadaControlState> getStates() {
    	return this.outputLensBehaviour.getStates();
    }
	
	/**
     * SwitchInputId property.
     */
    @Override
	public String getSwitchInputId ()
    {
        return this.switchInputId.get ();
    }

    @Override
    public void setSwitchInputId ( final String value )
    {
        this.switchInputId.set ( value );
        // TODO: Invalidate the binding, set SwitchInputProperty to null. 
    }
    
    @Override
    public StringProperty switchInputIdProperty ()
    {
        return this.switchInputId;
    }
    
	@Override
    public ObjectProperty<IDataItem> switchInputProperty() {
    	return this.switchInput;
    }

	@Override
	public void setSwitchInput(IDataItem item) {
		this.switchInput.set(item);
	}

	@Override
	public IDataItem getSwitchInput() {
		return this.switchInput.get();
	}

	@Override
	public ObjectProperty<Object> inputSourceValueProperty() {
		return this.inputSourceValue;
	}

	@Override
	public void setInputSourceValue(Object value) {
		this.inputSourceValue.set(value);
	}

	@Override
	public Object getInputSourceValue() {
		return this.inputSourceValue.get();
	}

	@Override
	public String styleClassPrefixProperty() {
		return this.styleClassPrefixProperty();
	}

	
}