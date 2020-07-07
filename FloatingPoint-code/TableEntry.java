// CSE 360 Group Project
// Developed by Tyler Walters, Paxton Ostler, Nevin Foster, Daniel Salas
// IDE: Eclipse

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class TableEntry extends StackPane 
{
	private Rectangle r = new Rectangle(55, 25);
	
	public TableEntry(Label l)
	{
		r.setStroke(Color.BLACK);
		r.setFill(Color.WHITE);
		getChildren().addAll(r, l);
	}
	
}
