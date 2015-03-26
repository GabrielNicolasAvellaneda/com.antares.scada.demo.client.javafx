package com.antares.scada.demo.client.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import jfxtras.labs.scene.control.gauge.linear.SimpleMetroArcGauge;

import org.eclipse.scada.da.client.DataItemValue;

public class GaugeControl extends SimpleMetroArcGauge
{
    private final ObjectProperty<DataItemValue> input = new SimpleObjectProperty<> ();
    
    private final SimpleMetroArcGauge gauge = new SimpleMetroArcGauge();
    
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
        	System.out.println(newValue.getValue().asDouble());
            this.setValue ( newValue.getValue ().asDouble () );
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
