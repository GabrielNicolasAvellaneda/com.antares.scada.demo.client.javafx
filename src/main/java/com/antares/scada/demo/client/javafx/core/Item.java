package com.antares.scada.demo.client.javafx.core;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.eclipse.scada.core.Variant;
import org.eclipse.scada.da.client.Connection;
import org.eclipse.scada.da.client.DataItem;
import org.eclipse.scada.da.client.DataItemValue;
import org.eclipse.scada.da.client.ItemManager;

import com.antares.scada.demo.client.javafx.interfaces.IDataItem;
import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;
import com.antares.scada.demo.client.javafx.interfaces.IVariant;

public class Item implements IDataItem
{
    private final StringProperty id = new SimpleStringProperty ( this, "id" );

    private final ObjectProperty<IDataItemValue> state = new SimpleObjectProperty<> ( this, "state" );

    private ItemManager itemManager;

    private DataItem item;

    private final Observer observer;

    private Connection connection;

    public Item ()
    {
    	this.id.addListener ( new ChangeListener<String> () {

            @Override
            public void changed ( final ObservableValue<? extends String> observable, final String oldValue, final String newValue )
            {
                update ();
            }
        } );
        this.observer = new Observer () {

        	// Called when a value change is detected from the ItemManager
            @Override
            public void update ( final Observable o, final Object arg )
            {
                final DataItemValue value = (DataItemValue)arg;
                final DataItemValueWrapper wrapper = new DataItemValueWrapper(value);
            	updateState ( wrapper );
            }
        };
        setState ( new DataItemValueWrapper(DataItemValue.DISCONNECTED) );
    }

    protected void updateState ( final IDataItemValue value )
    {
        Platform.runLater ( new Runnable () {
            @Override
            public void run ()
            {
                try
                {
                    setState ( value );
                }
                catch ( final Exception e )
                {
                    e.printStackTrace ();
                }
            }
        } );
    }

    public void setState ( final IDataItemValue value )
    {
        this.state.set ( value );
    }

    public IDataItemValue getState ()
    {
        return this.state.get ();
    }

    public ObjectProperty<IDataItemValue> stateProperty ()
    {
        return this.state;
    }

    public String getId ()
    {
        return this.id.get ();
    }

    public void setId ( final String id )
    {
        this.id.set ( id );
    }

    public StringProperty idProperty ()
    {
        return this.id;
    }

    public void register ( final org.eclipse.scada.da.client.Connection connection, final ItemManager itemManager )
    {
        this.connection = connection;
        this.itemManager = itemManager;
        update ();
    }

    public void unregister ()
    {
        this.connection = null;
        this.itemManager = null;
        update ();
    }

    @Override
    public void write ( final Object value )
    {
        final String id = this.id.get ();
        if ( this.connection != null && id != null )
        {
        	Variant variant = Variant.valueOf(value);
        	this.connection.startWrite(id, variant, null, null);
        }
    }
    

    private void update ()
    {
        dispose ();

        final String id = this.id.get ();
        if ( id != null && this.itemManager != null )
        {
            this.item = new DataItem ( id, this.itemManager );
            this.item.addObserver ( this.observer );
        }
    }

    private void dispose ()
    {
        if ( this.item != null )
        {
            this.item.unregister ();
            this.item = null;
            updateState ( new DataItemValueWrapper(DataItemValue.DISCONNECTED) );
        }
    }

	
}
