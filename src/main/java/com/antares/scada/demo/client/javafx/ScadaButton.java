package com.antares.scada.demo.client.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.eclipse.scada.core.Variant;
import org.eclipse.scada.da.client.DataItemValue;
import javafx.scene.control.Button;

public class ScadaButton extends Button
{
    private final ObjectProperty<Item> input = new SimpleObjectProperty<> ();
    
    public ScadaButton() {
    	super();
    	
    	/*inputProperty().addListener ( new ChangeListener<DataItemValue> () {

            @Override
            public void changed ( final ObservableValue<? extends DataItemValue> observable, final DataItemValue oldValue, final DataItemValue newValue )
            {
                updateState ( newValue );
            }});*/
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

        }
        catch ( final Exception e )
        {
        	e.printStackTrace ();
        }
    }
    
    public Item getInput ()
    {
        return this.input.get ();
    }

    public void setInput ( final Item input )
    {
        this.input.set ( input );
    }
    
    public void write(Variant value) {
    	Item item = this.inputProperty().getValue();
    	item.write(value);
    }

    public ObjectProperty<Item> inputProperty ()
    {
        return this.input;
    }

}
