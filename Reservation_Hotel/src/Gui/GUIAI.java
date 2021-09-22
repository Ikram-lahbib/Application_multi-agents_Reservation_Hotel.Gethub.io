package Gui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIAI extends Application {

	@Override
	public void start(Stage ps) throws Exception {
		GridPane grid = new GridPane();
		 grid.setAlignment(Pos.CENTER);
		 grid.setHgap(10);
		 grid.setVgap(10);
		 grid.setPadding(new Insets(25, 25, 25, 25));
		 Text scenetitle = new Text("Welcome");
		 scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		 grid.add(scenetitle, 0, 0, 2, 1);

		 Label pays = new Label("Pays:");
		 grid.add(pays, 0, 1);

		 TextField userTextField = new TextField();
		 grid.add(userTextField, 1, 1);

		 Label ville = new Label("Ville:");
		 grid.add(ville, 0, 2);

		 TextField villeTextField = new TextField();
		 grid.add(villeTextField, 1, 2);
		 
		 Label chambre = new Label("Chambre:");
		 grid.add(chambre, 0, 3);
		 
		 ListView<String> list = new ListView<String>();
		 ObservableList<String> items =FXCollections.observableArrayList ("Single", "Double");
		 list.setItems(items);
		 list.setPrefWidth(100);
		 list.setPrefHeight(50);
		 grid.add(list, 1, 3);
		 
		 Label nb = new Label("Nombre des Personne:");
		 grid.add(nb, 0, 4);
		 
		 TextField nbTextField = new TextField();
		 grid.add(nbTextField, 1, 4);
		
		 
		 Label dated = new Label("Date début:");
		 grid.add(dated, 0, 5);
		 DatePicker datePicker = new DatePicker();
	     datePicker.setValue(LocalDate.of(2020, 1, 6));
	     datePicker.setShowWeekNumbers(true);
		 grid.add(datePicker, 1, 5);
		 
		 Label datef = new Label("Date fin:");
		 grid.add(datef, 0, 6);
		 DatePicker datePickerf = new DatePicker();
	     datePickerf.setValue(LocalDate.of(2020, 1, 6));
	     datePickerf.setShowWeekNumbers(true);
		 grid.add(datePickerf, 1, 6);
		 
		 Button btn = new Button("Envoyer");
		 HBox hbBtn = new HBox(10);
		 hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		 hbBtn.getChildren().add(btn);
		 grid.add(hbBtn, 1, 7);
		 Scene scene = new Scene(grid, 500, 500);
		 ps.setScene(scene);
		 ps.setTitle("JavaFX Welcome");
	     ps.show();
		
		
		
	}

}
