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

import com.antares.scada.demo.client.javafx.interfaces.IDataItem;
import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;
import com.antares.scada.demo.client.javafx.interfaces.IOutputLens;
import com.antares.scada.demo.client.javafx.interfaces.ISwitchInput;
import com.antares.scada.demo.client.javafx.interfaces.IVariant;
import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.collections.ObservableMapWrapper;
import com.sun.javafx.fxml.BeanAdapter;

@DefaultProperty("states")
public class ScadaLabel extends Label implements ISwitchInput, IOutputLens
{
	private final String styleClassPrefix = "scada-label";
	
	private final StringProperty switchInputId = new SimpleStringProperty ();
	private final StringProperty outputLensId = new SimpleStringProperty();
		
	private ObservableList<ScadaControlState> states = FXCollections.observableArrayList();
	private final ObjectProperty<IDataItem> outputLens = new SimpleObjectProperty<> ();
	private final ObjectProperty<IDataItem> switchInput = new SimpleObjectProperty<> ();

	private ObjectProperty<Object> inputSourceValue = new SimpleObjectProperty<> ();
		
    public ScadaLabel() {
    	super();
    	
    	this.getStyleClass().add("scada-label"); // initial style
    	
    	// Set the context to the item subscribed with the itemmanager.
    	
    	/*getOutputLensItem().getState().addListener ( new ChangeListener<IDataItemValue> () {

            @Override
            public void changed ( final ObservableValue<? extends IDataItemValue> observable, final IDataItemValue oldValue, final IDataItemValue newValue )
            {
                updateState ( newValue );
            }});
    	*/
    	// TODO: Place this in a behavior class.
    	
    }
    
    // TODO: This functionality should be in another class?
    private void write(Object value) {
    	IDataItem item = this.switchInput.getValue();
    	if (item != null) {
    		item.write(value);	
    	}
    }
    
    public String styleClassPrefixProperty() {
    	return this.styleClassPrefix;
    }
    
    /**
     * Create class styles like scada-button-alarm
     */
    private String createStyleClass(String name) {
    	return String.format("%s-%s", this.styleClassPrefix, name);
    }
    
    private List<String> getCustomStyles() {
    	
    	final List<String> list = new ArrayList<String>();
    	list.add(createStyleClass("error"));
    	list.add(createStyleClass("alarm"));
    	list.add(createStyleClass("warning"));
    	list.add(createStyleClass("manual"));
    	list.add(createStyleClass("blocked"));
    	list.add(createStyleClass("connected"));
    	
    	return list;
    }
    	
    private List<String> createStyles (IDataItemValue value) {
    	List<String> styles = new ArrayList<>();
    	
    	if (value.isError () ) {
    		styles.add(createStyleClass("error"));
        }
    	else if (value.isAlarm()) {
        	styles.add(createStyleClass("alarm"));
        }
    	else if (value.isWarning()) {
    		styles.add(createStyleClass("warning"));
    	}
    	
    	if (value.isManual()) {
    		styles.add(createStyleClass("manual"));
    	}
    	
    	if (value.isBlocked()) {
    		styles.add(createStyleClass("blocked"));
    	}
    	
    	if (value.isConnected()) {
    		styles.add(createStyleClass("connected"));
    	}
    	
    	return styles;
    }
    
	protected void updateState ( final IDataItemValue newValue )
    {
        // TODO: Maintain styles classes that were specified in the fxml.
		// TODO: Only first remove special classes assigned by state.
		updateStyles(newValue);
        updateStateProperties(newValue);
        if (newValue.getValue() != null) {
        	this.setText(newValue.getValue().toString());	
        }
    }
	
	private void updateStateProperties(IDataItemValue newValue) {

		final ScadaControlState state = getScadaControlState(newValue);
		if (state != null) {
			applyStateProperties(state.getProperties());	
		}
	}
	
	private void applyStateProperties(ObservableMap<String, String> properties) {
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			BeanAdapter adapter = new BeanAdapter(this);
			//final Class<?> type = adapter.getType(entry.getKey());
			//if type.
			//"".split(regex)
			if (entry.getKey() == "styleClass") {
				//List<String> styles = (List<String>) adapter.get(entry.getKey());
				// TODO: Detect type, try to split, addall()
				this.getStyleClass().add(entry.getValue());
			
			} else {
				adapter.put(entry.getKey(), entry.getValue());	
			}
			
			
		}
	}

	private ScadaControlState getScadaControlState(IDataItemValue newValue) {

		for (ScadaControlState state : this.getStates()) {
			if (newValue.getValue() != null && newValue.getValue().equals(state.getValue())) {
				return state;
			}
		}
		
		return null;
	}

	private void updateStyles(final IDataItemValue newValue) {
		
		List<String> styleClasses = createStyles(newValue);
		List<String> oldStyleClass = this.getStyleClass();
		this.getStyleClass().removeAll(getCustomStyles());
        this.getStyleClass().addAll(styleClasses);
	}
    
      /**
     * OutputLensItem property
     */
    public void setOutputLensItem(IDataItem value) {
    	this.outputLens.set(value);
    }
    
    public ObjectProperty<IDataItem> getOutputLensItemProperty() {
    	return this.outputLens;
    }
   
    /**
     * Map of states and properties
     * Should accept something like state['state0'] = Hasmap<String, String> and then apply that properties when the outputlens property changes.
     * @return
     */
    public ObservableList<ScadaControlState> getStates() {
    	return this.states;
    }
    
    /**
     * OutputLens Properties
     */
    public String getOutputLensId() {
    	return this.outputLensId.get();
    }
    
    public void setOutputLensId(final String value) {
    	this.outputLensId.set(value);
    }
    
    public StringProperty outputLensIdProperty() {
    	return this.outputLensId;
    }
    
	@Override
	public ObjectProperty<IDataItem> outputLensProperty() {
		return this.outputLens;
	}
	
	@Override
	public IDataItem getOutputLens() {
		return this.outputLens.get();
	}

	@Override
	public void setOutputLens(IDataItem item) {

		// TODO: Verificar porqué es ejecutado en el momento la carga del fxml.
		
		this.outputLens.set(item);
		// Now listen for changes in the state property.
		if (item == null) {
			return;
		}
		
		item.stateProperty().addListener(new ChangeListener<IDataItemValue>() {

			@Override
			public void changed(
					ObservableValue<? extends IDataItemValue> observable,
					IDataItemValue oldValue, IDataItemValue newValue) {

				updateState(newValue);
				
				System.out.println("ScadaLabel > Value Changed to " + newValue.getValue());
				
			}
		});
	}
	
    /**
     * SwitchInput property
     */
	
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
}