package com.antares.scada.demo.client.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import jfxtras.labs.scene.control.gauge.linear.SimpleMetroArcGauge;

import org.eclipse.scada.core.Variant;
import org.eclipse.scada.da.client.DataItemValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GaugeControl extends StackPane
{
    private final ObjectProperty<DataItemValue> input = new SimpleObjectProperty<> ();
    
    private static final String DEFAULT_STYLE_CLASS = "openscada-gauge-control";

    public GaugeControl() {
    	super();
    	
    	inputProperty().addListener ( new ChangeListener<DataItemValue> () {

            @Override
            public void changed ( final ObservableValue<? extends DataItemValue> observable, final DataItemValue oldValue, final DataItemValue newValue )
            {
                updateState ( newValue );
            }});
    }
    	
	protected void updateState ( final DataItemValue newValue )
    {
        try
        {
            /*if ( newValue.isError () )
            {
                this.gauge.setGlowColor ( Color.MAGENTA );
                this.gauge.setGlowOn ( true );
            }
            else if ( newValue.isAlarm () )
            {
                this.gauge.setGlowColor ( Color.RED );
                this.gauge.setGlowOn ( true );
            }
            else if ( newValue.isWarning () )
            {
                this.gauge.setGlowColor ( Color.ORANGE );
                this.gauge.setGlowOn ( true );
            }
            else if ( newValue.isManual () )
            {
                this.gauge.setGlowColor ( Color.TURQUOISE );
                this.gauge.setGlowOn ( true );
            }*/
        	SimpleMetroArcGauge gauge = (SimpleMetroArcGauge)this.getChildren().get(0);
        	if (gauge != null) {
        		if (newValue != null) {
	            	final Variant value = newValue.getValue ();
	            	if (!value.isNull()) {
	            		gauge.setValue (value.asDouble ());		
	            	}
	            }
        	}

        }
        catch ( final Exception e )
        {
        	e.printStackTrace ();
        }
    }
    
    public DataItemValue getInput ()
    {
        return this.input.get ();
    }

    public void setInput ( final DataItemValue input )
    {
        this.input.set ( input );
    }

    public ObjectProperty<DataItemValue> inputProperty ()
    {
        return this.input;
    }

}
