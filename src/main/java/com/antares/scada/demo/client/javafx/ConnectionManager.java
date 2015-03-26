package com.antares.scada.demo.client.javafx;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.scada.core.client.AutoReconnectController;
import org.eclipse.scada.da.client.Connection;

public class ConnectionManager
{
    private final Set<Connection> connections = new HashSet<> ();

    private final Set<AutoReconnectController> controllers = new HashSet<> ();

    public void addConnection ( final Connection connection, final AutoReconnectController controller )
    {
        this.connections.add ( connection );
        this.controllers.add ( controller );
    }

    public void removeConnection ( final Connection connection, final AutoReconnectController controller )
    {
        this.connections.remove ( connection );
        this.controllers.remove ( controller );
    }

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
