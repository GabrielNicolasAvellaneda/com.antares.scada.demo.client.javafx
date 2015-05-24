package com.antares.scada.demo.client.javafx.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Control;
import javafx.scene.layout.Region;

import com.antares.scada.demo.client.javafx.interfaces.IDataItem;
import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;
import com.antares.scada.demo.client.javafx.interfaces.IOutputLens;
import com.antares.scada.demo.client.javafx.ui.ScadaControlState;
import com.sun.javafx.fxml.BeanAdapter;

public class OutputLensBehaviour implements IOutputLens {

	private final StringProperty outputLensId = new SimpleStringProperty();
	private final ObjectProperty<IDataItem> outputLensItem = new SimpleObjectProperty<> ();
	private ObservableList<ScadaControlState> states = FXCollections.observableArrayList();
	private Region control = null;
	private String styleClassPrefix = "scada-button";
	private List<String> defaultStyles = FXCollections.observableArrayList();
	private ChangeListener<IDataItemValue> stateChangeHandler = null; 
	
	public OutputLensBehaviour(Region control, final String styleClassPrefix, ChangeListener<IDataItemValue> stateChangeHandler) {
		this.control = control;
		this.styleClassPrefix = styleClassPrefix;
		this.stateChangeHandler = stateChangeHandler;
		
		copyControlDefaultStyles();

		initialize();
	}
	
	private void copyControlDefaultStyles() {
		
		for (final String style : this.control.getStyleClass()) {
			this.defaultStyles.add(style);	
		}
	}

	private void initialize() {
		this.control.getStyleClass().add(this.styleClassPrefix); // initial style
	}
	
	@Override
	public String getOutputLensId() {
		return outputLensId.get();
	}

	@Override
	public void setOutputLensId(String value) {
		this.outputLensId.set(value);
	}
	
	@Override
	public StringProperty outputLensIdProperty() {
		return this.outputLensId;
	}

	@Override
	public ObjectProperty<IDataItem> outputLensItemProperty() {
		return this.outputLensItem;
	}

	@Override
	public void setOutputLensItem(IDataItem value) {
		this.outputLensItem.set(value);
		// Now listen for changes in the state property.
		if (value == null) {
			return;
		}
		
		updateState(value.getState());
		
		value.stateProperty().addListener(new ChangeListener<IDataItemValue>() {

			@Override
			public void changed(
					ObservableValue<? extends IDataItemValue> observable,
					IDataItemValue oldValue, IDataItemValue newValue) {

				handleStateChange(observable, oldValue, newValue);
				
				updateState(newValue);
			}
		});
	}
	
	private void handleStateChange(ObservableValue<? extends IDataItemValue> observable, IDataItemValue oldValue, IDataItemValue newValue) {
		if (this.stateChangeHandler != null) {
        	this.stateChangeHandler.changed(observable, oldValue, newValue);
        }
	}
	
	protected void updateState ( final IDataItemValue newValue )
    {
		// TODO: Maintain styles classes that were specified in the fxml.
		// TODO: Only first remove special classes assigned by state.
		updateStylesForAttributes(newValue);
		updateStateProperties(newValue);
    }
	
	private void updateStylesForAttributes(final IDataItemValue newValue) {
		
		List<String> styleClasses = createStylesForAttributes(newValue);
		//List<String> oldStyleClass = this.control.getStyleClass();
		
		//this.control.getStyleClass().removeAll(getCustomStyles());
		this.control.getStyleClass().clear();
		this.control.getStyleClass().addAll(this.defaultStyles);
        this.control.getStyleClass().addAll(styleClasses);
        this.control.applyCss();
	}
	
    private List<String> createStylesForAttributes (IDataItemValue value) {
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
    
    /**
     * Create class styles like scada-button-alarm
     */
    private String createStyleClass(String name) {
    	return String.format("%s-%s", this.styleClassPrefix, name);
    }
    
    @Override
    public String styleClassPrefixProperty() {
    	return this.styleClassPrefix;
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
	
	private void updateStateProperties(IDataItemValue newValue) {

		final ScadaControlState state = getScadaControlState(newValue);
		if (state != null) {
			applyStateProperties(state.getProperties());	
		}
	}
	
	private void applyStateProperties(ObservableMap<String, String> properties) {
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			BeanAdapter adapter = new BeanAdapter(this.control);
			if (entry.getKey() == "styleClass") {
				//List<String> styles = (List<String>) adapter.get(entry.getKey());
				// TODO: Detect type, try to split, addall()
				this.control.getStyleClass().add(entry.getValue());
				
			} else {
				adapter.put(entry.getKey(), entry.getValue());	
			}
		}
	}

	private ScadaControlState getScadaControlState(IDataItemValue newValue) {
		
		if (newValue.getValue() == null) {
			return null;
		}
		
		for (ScadaControlState state : this.getStates()) {
			if (newValue.coerceEquals(state.getValue())) {
				return state;
			}
		}
		
		return null;
	}

	@Override
	public ObservableList<ScadaControlState> getStates() {
		return this.states;
	}

	@Override
	public IDataItem getOutputLensItem() {
		return this.outputLensItem.get();
	}
}
