// CSE 360 Group Project
// Developed by Tyler Walters, Paxton Ostler, Nevin Foster, Daniel Salas
// IDE: Eclipse

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FloatProgram extends Application
{
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	public static Floats f = new Floats();
	
	public void start(Stage stage)
	{
		// create entire display
		ControlPane rootPane = new ControlPane();
		rootPane.setPadding(new Insets(10,10,10,10));
		// create scene
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		// set and display stage parameters
		stage.setTitle("Floats Program");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

}
