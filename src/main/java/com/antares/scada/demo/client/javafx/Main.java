package com.antares.scada.demo.client.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.*;

import java.util.HashMap;

import com.antares.scada.demo.client.javafx.core.ConnectionManager;
import com.sun.javafx.fxml.BeanAdapter;

public class Main extends Application
{
    public static ConnectionManager connectionManager = new ConnectionManager ();

    @Override
    public void start ( final Stage primaryStage ) throws Exception
    {
    	final Parent root = FXMLLoader.load ( getClass ().getResource ( "view/Window2.fxml" ) );
    	final Scene scene = new Scene ( root, 1024, 768 ); 
    	scene.getStylesheets().add( getClass().getResource("view/styles.css").toExternalForm());
        primaryStage.setTitle ( "JavaFX Eclipse Scada Client" );
        primaryStage.setScene ( scene );
        
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
        // TODO: Get connection parameters from config file.
    	
    	launch ( args );
    }

}
