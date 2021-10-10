package adinvinapp;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AdivinApp extends Application {

	int numRandom= (int) Math.floor(Math.random() * (100 - 1) + 1);
	IntegerProperty num = new SimpleIntegerProperty();
	int Intentos=0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TextField numTextField;
				
		Button ok = new Button("comprobar");
		ok.setOnAction(f -> okButton(f));
		
		Label clasificacionLabel = new Label();
		
		HBox numHbox = new HBox(clasificacionLabel = new Label("Introduce un numero del 1 al 100 "), numTextField = new TextField());
        numHbox.setAlignment(Pos.CENTER);

    	VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(numHbox,ok,clasificacionLabel);		
	
		
		Scene scene = new Scene(root, 640,480);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AdivinApp.fxml");
		primaryStage.show();
		
		
		Bindings.bindBidirectional(numTextField.textProperty(),num, new NumberStringConverter());
			
	}
	
	
	private void okButton(ActionEvent f) {
		
		if(num.getValue()==numRandom) {
			Alert Ganar = new Alert(AlertType.INFORMATION);
			Ganar.setTitle("AdivinApp.fxml");
			Ganar.setHeaderText("¡Has ganado!");
			Ganar.setContentText("Sólo has necesitado" + Intentos + " intentos");
			Ganar.setContentText("Vuelve a jugar y Hazlo mejor");
			Ganar.showAndWait();
			}
			else if(num.getValue()>numRandom) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AdivinApp.fxml");
			alert.setHeaderText("¡Has fallado!");
			alert.setContentText("El numero a adivinar es menor que "+ num.getValue());
			alert.setContentText("Vuelve a intentarlo.");
			Intentos++;
			alert.showAndWait();
			}
			
			else if(num.getValue()<numRandom) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp.fxml");
				alert.setHeaderText("¡Has fallado!");
				alert.setContentText("El numero a adivinar es mayor que "+ num.getValue());
				alert.setContentText("Vuelve a intentarlo.");
				Intentos++;
				alert.showAndWait();
				}
			else if(num.getValue()==0 || num.getValue()==null){
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("AdivinApp.fxml");
			error.setHeaderText("ERROR");
			error.setContentText("El número introducido no es válido.");
			error.showAndWait();
			}
	}
	
	public static void main(String[] args) {

		launch(args);

	}

}
