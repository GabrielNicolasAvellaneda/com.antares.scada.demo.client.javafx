/*
 * This file is part of the openSCADA project
 * 
 * Copyright (C) 2013 Jens Reimann (ctron@dentrassi.de)
 *
 * openSCADA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * only, as published by the Free Software Foundation.
 *
 * openSCADA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License version 3 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with openSCADA. If not, see
 * <http://opensource.org/licenses/lgpl-3.0.html> for a copy of the LGPLv3 License.
 */

package com.antares.scada.demo.client.javafx.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import org.eclipse.scada.core.ConnectionInformation;
import org.eclipse.scada.core.client.AutoReconnectController;
import org.eclipse.scada.core.client.ConnectionState;
import org.eclipse.scada.core.client.ConnectionStateListener;
import org.eclipse.scada.da.client.ItemManagerImpl;
import org.eclipse.scada.da.client.ngp.ConnectionImpl;

import com.antares.scada.demo.client.javafx.Main;

/** An EclipseScada DA Connection wrapper that keep track of items registered for listening for changes.
 * 
 * @author developer
 *
 */
public class Connection
{
    private String connectionString;

    private ConnectionImpl connection;

    private final ObservableMap<String, Item> items;

    private ItemManagerImpl itemManager;

    private AutoReconnectController controller;

    public Connection ()
    {
    	this.items = FXCollections.observableMap(new HashMap<String, Item>());
    	this.items.addListener(new MapChangeListener<String, Item>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends Item> change) {
				
				    if ( change.wasAdded () )
                    {
                        final Item item = change.getValueAdded();
                        itemAdded ( item );
                                            }
                    if ( change.wasRemoved () )
                    {
                        final Item item = change.getValueRemoved(); 
                        itemRemoved ( item );
                    }
                }
				
			});        
    }

    protected void itemAdded ( final Item item )
    {
        if ( this.connection != null )
        {
            item.register ( this.connection, this.itemManager );
        }
    }

    protected void itemRemoved ( final Item item )
    {
        if ( this.connection != null )
        {
            item.unregister ();
        }
    }

    /** Sets the Connection String that represents the connection. It will recreate the internal connection object and register items to be listened for changes.
     * 
     * @param connectionString - The connection string that represent this connection object.
     * @throws Exception
     */
    public void setConnectionString ( final String connectionString ) throws Exception
    {
        disposeConnection ();

        this.connectionString = connectionString;

        createConnection ( connectionString );
    }

    private void createConnection ( final String connectionString ) throws Exception
    {

        this.connection = new ConnectionImpl ( ConnectionInformation.fromURI ( this.connectionString ) );
        connection.addConnectionStateListener(new ConnectionStateListener() {
			
			@Override
			public void stateChange(org.eclipse.scada.core.client.Connection connection, ConnectionState state,
					Throwable e) {
				System.out.println(state);
				if ( e != null )
					e.printStackTrace();
			}
		});
        this.controller = new AutoReconnectController ( this.connection, 10 * 1000 );
        this.controller.connect ();
        this.itemManager = new ItemManagerImpl ( this.connection );

        // TODO: Change this, look for another way to register this connection with the connection manager.
        // Maybe use a DI framework.?
        Main.connectionManager.addConnection ( this.connection, this.controller );

        // realize items
        for (final Map.Entry<String, Item> entry : this.items.entrySet() )
        {
        	final Item item = entry.getValue();
        	item.register ( this.connection, this.itemManager );
        }
    }

    private void disposeConnection ()
    {
        if ( this.connection == null )
        {
            return;
        }

        for ( final Map.Entry<String, Item> entry : this.items.entrySet() )
        {
            final Item item = entry.getValue();
        	item.unregister ();
        }

        Main.connectionManager.removeConnection ( this.connection, this.controller );

        this.itemManager = null;
        this.controller.dispose ( true );
        this.controller = null;
        this.connection.dispose ();
        this.connection = null;
    }

    /**
     * Returns the Connection String for the internal connection object. 
     * @return String - The configured connection string.
     */
    public String getConnectionString ()
    {
        return this.connectionString;
    }

    /**
     * Returns the list of items that were registerd for this connection for listening for changes.
     * @return
     */
    public Map<String, Item> getItems ()
    {
        return this.items;
    }

    @Override
    protected void finalize () throws Throwable
    {
        disposeConnection ();
        super.finalize ();
    }
}
