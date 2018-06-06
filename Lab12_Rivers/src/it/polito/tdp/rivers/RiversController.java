/**
 * Sample Skeleton for 'Rivers.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    

    @FXML
    void doInizializza(ActionEvent event) {
    	try {
    		if(this.boxRiver.getValue()==null)
    			txtResult.appendText("Selezionare un fiume!\n");
    		else {
    			River river = this.boxRiver.getValue();
    			this.btnSimula.setDisable(false);
    			this.txtK.setDisable(false);
    			RiverData rd = model.getDataFromRiver(river);
    			this.txtStartDate.setDisable(false);
    			this.txtStartDate.setText(rd.getDataInizioMisurazione().toString());
    			this.txtEndDate.setDisable(false);
    			this.txtEndDate.setText(rd.getDataFineMisurazione().toString());
    			this.txtNumMeasurements.setDisable(false);
    			this.txtNumMeasurements.setText(""+rd.getNumeroMisurazioni());
    			this.txtFMed.setDisable(false);
    			this.txtFMed.setText(String.format("%.2f", rd.getFlowMedio()));
    		}
    	}
    	catch(RuntimeException e) {
    		txtResult.appendText("C'è un problema di connessione al database!\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	try {
    		Integer.parseInt(this.txtK.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un numero!\n");
    		return;
    	}
    	try {
    		if(this.boxRiver.getValue()==null)
    			txtResult.appendText("Selezionare un fiume!\n");
    		else {
    			River river = this.boxRiver.getValue();
    			double k = Integer.parseInt(this.txtK.getText());
    			if(k<=0) {
    				txtResult.appendText("Inserire un numero positivo!\n");
    				return;
    			}
    			txtResult.appendText(model.simula(k, river).toString());
    		}
    	}
    	catch(RuntimeException e) {
    		txtResult.appendText("C'è un problema di connessione al database!\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }

    
	public void setModel(Model model) {
		this.model=model;
		this.boxRiver.getItems().setAll(model.getRivers());
	}
}
