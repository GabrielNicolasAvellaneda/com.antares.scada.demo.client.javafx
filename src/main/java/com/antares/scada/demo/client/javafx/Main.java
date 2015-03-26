package com.antares.scada.demo.client.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.*;

public class Main extends Application
{

    public static ConnectionManager connectionManager = new ConnectionManager ();

    private AnchorPane rootLayout = null;
    
    @Override
    public void start ( final Stage primaryStage ) throws Exception
    {
    	final Parent root = FXMLLoader.load ( getClass ().getResource ( "view/Main.fxml" ) );

        primaryStage.setTitle ( "JavaFX Eclipse Scada Demo Client" );
        primaryStage.setScene ( new Scene ( root, 640, 480 ) );

        primaryStage.show ();
    }

    @Override
    public void stop () throws Exception
    {
        connectionManager.dispose ();
        super.stop ();
    }

    public static void main ( final String[] args )
    {
        launch ( args );
    }

}
