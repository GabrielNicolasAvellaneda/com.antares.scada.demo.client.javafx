package com.antares.scada.demo.client.javafx.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.scada.core.Variant;

import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.gauge.linear.SimpleMetroArcGauge;

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
public class ScadaGauge extends Pane implements IOutputLens
{
	// TODO: There is a bug in the scada gauge that when you extend the class you lose the styles.
	private static final String DEFAULT_STYLE_CLASS = "scada-gauge";
	private final OutputLensBehaviour outputLensBehaviour = new OutputLensBehaviour(this, "scada-gauge", new ChangeListener<IDataItemValue>() {
		
		@Override
		public void changed(ObservableValue<? extends IDataItemValue> observable,
				IDataItemValue oldValue, IDataItemValue newValue) {
			if (newValue.getValue() != null) {
				try {
					if (newValue.isDouble() || newValue.isBoolean() || newValue.isInteger()) {
						Double value = (Double)newValue.getValueAsDouble();
						ScadaGauge.this.gauge.setValue(value);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	});

	private SimpleMetroArcGauge gauge;
		
    public ScadaGauge() {
    	super();
    	
    	this.gauge = new SimpleMetroArcGauge();
    	this.getChildren().add(gauge);
    }
    
    public double getMaxValue() {
    	return this.gauge.getMaxValue();
    }
    
    public void setMaxValue(Double value) {
    	this.gauge.setMaxValue(value);
    }
    
    public DoubleProperty maxValueProperty() {
    	return this.maxValueProperty();
    }
    
    public double getMinValue() {
    	return this.gauge.getMinValue();
    }
    
    public void setMinValue(Double value) {
    	this.gauge.setMinValue(value);
    }
    
   public DoubleProperty minValueProperty() {
    	return this.gauge.minValueProperty();
    }
    
    /**
     * OutputLensItem property
     */
    @Override
	public void setOutputLensItem(IDataItem value) {
    	this.outputLensBehaviour.setOutputLensItem(value);
    }
    
    /**
     * Map of states and properties
     * Should accept something like state['state0'] = Hasmap<String, String> and then apply that properties when the outputlens property changes.
     * @return
     */
    public ObservableList<ScadaControlState> getStates() {
    	return this.outputLensBehaviour.getStates();
    }
    
    /**
     * OutputLens Properties
     */
    public String getOutputLensId() {
    	return this.outputLensBehaviour.getOutputLensId();
    }
    
    public void setOutputLensId(final String value) {
    	this.outputLensBehaviour.setOutputLensId(value);
    }
    
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
	public String styleClassPrefixProperty() {
		return this.outputLensBehaviour.styleClassPrefixProperty();
	}
    
}