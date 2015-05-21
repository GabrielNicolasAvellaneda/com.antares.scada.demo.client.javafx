package com.antares.scada.demo.client.javafx.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.antares.scada.demo.client.javafx.core.Connection;
import com.antares.scada.demo.client.javafx.core.Item;
import com.antares.scada.demo.client.javafx.interfaces.IOutputLens;
import com.antares.scada.demo.client.javafx.interfaces.ISwitchInput;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WindowControllerBase implements Initializable {

	protected final Connection connection;
	
	public final BooleanProperty BOOLEAN_TRUE = new SimpleBooleanProperty(true);
	public final BooleanProperty BOOLEAN_FALSE = new SimpleBooleanProperty(false);
	
	
	public Boolean getBooleanTrue() {
		return this.BOOLEAN_TRUE.get();
	}
	
	public Boolean getBooleanFalse() {
		return this.BOOLEAN_FALSE.get();
	}
	
	public WindowControllerBase() throws Exception {
		// Look for another way to create the connection globally.
		this.connection = new Connection();
		this.connection.setConnectionString("da:ngp://admin:admin12@localhost:2101");
	}
	
	protected Item getOrRegisterItem(String itemId) {
		if (!this.connection.getItems().containsKey(itemId)) {
			final Item item = new Item();
			item.setId(itemId);	
			this.connection.getItems().put(item.getId(), item);	
		}
		
		return this.connection.getItems().get(itemId);
	}
	
	@FXML
	protected Pane root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createAndBindItems(root);
	}
	
	protected void createAndBindItems(Pane pane) {
		ObservableList<Node> childs = pane.getChildren();
		for (Node child : childs) {
			if (child instanceof ISwitchInput) {
				// TODO: Set the Item to the control
				final ISwitchInput switchInput = (ISwitchInput)child;
				final String itemId = switchInput.getSwitchInputId();
				if (itemId != null) {
					final Item item = this.getOrRegisterItem(itemId);
					switchInput.setSwitchInput(item);
				}
			}
			
			if (child instanceof IOutputLens) {
				final IOutputLens outputLens = (IOutputLens)child;
				final String itemId = outputLens.getOutputLensId();
				if (itemId != null) {
					final Item item = getOrRegisterItem(itemId);
					outputLens.setOutputLensItem(item);
				}
			}
			
			if (child instanceof Pane) {
				createAndBindItems((Pane)child);
			}
		}
	}
	
}