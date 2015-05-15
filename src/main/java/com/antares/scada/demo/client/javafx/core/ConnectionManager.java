package com.antares.scada.demo.client.javafx.core;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.scada.core.client.AutoReconnectController;
import org.eclipse.scada.da.client.Connection;

/**
 * Manage EclipseScada DA Connections and AutoReconnect controllers. 
 * @author Gabriel Nicolas Avellaneda <avellaneda.gabriel@gmail.com>
 *
 */
public class ConnectionManager
{
    private final Set<Connection> connections = new HashSet<> ();

    private final Set<AutoReconnectController> controllers = new HashSet<> ();

    /**
     * Add a connection to the list of registered connection.
     * @param connection an EclipseSCADA DA connection.
     * @param controller an AutoReconnectionController that will be associated with the connection.
     */
    public void addConnection ( final Connection connection, final AutoReconnectController controller )
    {
        this.connections.add ( connection );
        this.controllers.add ( controller );
    }

    /**
     * Remove a connection that were registered by @see addConnection
     * @param connection
     * @param controller
     */
    public void removeConnection ( final Connection connection, final AutoReconnectController controller )
    {
        this.connections.remove ( connection );
        this.controllers.remove ( controller );
    }

    /**
     * Clean-up connections and controllers.
     */
    public void dispose ()
    {
        for ( final AutoReconnectController controller : this.controllers )
        {
            controller.dispose ( false );
        }
        this.controllers.clear ();
        for ( final Connection connection : this.connections )
        {
            connection.dispose ();
        }
        this.connections.clear ();
    }
}
