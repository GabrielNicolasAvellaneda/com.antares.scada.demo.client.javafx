package com.antares.scada.demo.client.javafx.ui;

import java.io.IOException;

import org.eclipse.scada.core.NotConvertableException;
import org.eclipse.scada.core.NullValueException;

import com.antares.scada.demo.client.javafx.interfaces.IDataItem;
import com.antares.scada.demo.client.javafx.interfaces.IDataItemValue;
import com.antares.scada.demo.client.javafx.interfaces.ISwitchInput;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ScadaKeypad extends Pane implements ISwitchInput {

	public final ScadaKeypadLogic keypad = new ScadaKeypadLogic();
	
	@FXML private ScadaTextField textField;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	@FXML private Button button6;
	@FXML private Button button7;
	@FXML private Button button8;
	@FXML private Button button9;
	@FXML private Button buttonIncrement;
	@FXML private Button buttonDecrement;
	@FXML private ScadaButton buttonEnter;
	@FXML private Button buttonClear;
	
	public ScadaKeypad () {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ScadaKeypad.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public IntegerProperty valueProperty() {
		return this.keypad.valueProperty();
	}
	
	public int getValue() {
		return valueProperty().get();
	}
	
	public void setValue(int value) {
		valueProperty().set(value);
	}
	
	@FXML
	private void button1Action() {
		this.keypad.appendDigit(1);
	}
	
	@FXML
	private void button2Action() {
		this.keypad.appendDigit(2);
	}
	
	@FXML
	private void button3Action() {
		this.keypad.appendDigit(3);
	}
	
	@FXML
	private void button4Action() {
		this.keypad.appendDigit(4);
	}
	
	@FXML
	private void button5Action() {
		this.keypad.appendDigit(5);
	}
	
	@FXML
	private void button6Action() {
		this.keypad.appendDigit(6);
	}
	
	@FXML
	private void button7Action() {
		this.keypad.appendDigit(7);
	}
	
	@FXML
	private void button8Action() {
		this.keypad.appendDigit(8);
	}
	
	@FXML
	private void button9Action() {
		this.keypad.appendDigit(9);
	}
	
	@FXML
	private void button0Action() {
		this.keypad.appendDigit(0);
	}
	
	@FXML
	private void buttonIncrement() {
		this.keypad.increment();
	}
	
	@FXML
	private void buttonDecrement() {
		this.keypad.decrement();
	}
	
	@FXML
	private void buttonClear() {
		this.keypad.clear();
	}

	@Override
	public String getSwitchInputId() {
		return this.buttonEnter.getSwitchInputId();
	}

	@Override
	public void setSwitchInputId(String value) {
		this.buttonEnter.setSwitchInputId(value);
	}

	@Override
	public StringProperty switchInputIdProperty() {
		return this.buttonEnter.switchInputIdProperty();
	}

	@Override
	public ObjectProperty<Object> inputSourceValueProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInputSourceValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getInputSourceValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectProperty<IDataItem> switchInputProperty() {
		return this.buttonEnter.switchInputProperty();
	}

	@Override
	public void setSwitchInput(IDataItem item) {
		this.buttonEnter.setSwitchInput(item);
		if (item != null) {
			item.stateProperty().addListener(new ChangeListener<IDataItemValue>() {

				@Override
				public void changed(
						ObservableValue<? extends IDataItemValue> observable,
						IDataItemValue oldValue, IDataItemValue newValue) {

					try {
						if (newValue.getValue() != null) {
							ScadaKeypad.this.keypad.setValue(newValue.getValueAsInteger());	
						}
					} catch (NullValueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotConvertableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public IDataItem getSwitchInput() {
		return this.buttonEnter.getSwitchInput();
	}

}
