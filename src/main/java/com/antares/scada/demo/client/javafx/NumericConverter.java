package com.antares.scada.demo.client.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.eclipse.scada.da.client.DataItemValue;

public class NumericConverter extends AbstractValueConverter
{
    private final DoubleProperty value = new SimpleDoubleProperty ( this, "value" );

    public void setValue ( final double value )
    {
        this.value.set ( value );
    }

    public double getValue ()
    {
        return this.value.get ();
    }

    public DoubleProperty valueProperty ()
    {
        return this.value;
    }

    @Override
    protected void update ( final DataItemValue value )
    {
        super.update ( value );

        this.value.setValue ( value.getValue ().asDouble ( null ) );
    }
}
