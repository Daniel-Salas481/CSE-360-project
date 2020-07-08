import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControlPane extends VBox
{
	// open pane
	private TextField path = new TextField(), col = new TextField();
	private Button open = new Button("Open"), save = new Button("Save"), neww = new Button("Clear");
	
	// display pane
	public static  ChoiceBox<String> hv = new ChoiceBox<String>(), ord = new ChoiceBox<String>();
	private TextField t = new TextField();
	private Button cols = new Button("Columns:"), add = new Button("Add"), delete = new Button("Delete");
	
	// floats pane
	private GridPane fp = new GridPane();
	
	// stats pane
	private GridPane sp = new GridPane();
	private StackPane s1 = new StackPane(), s2 = new StackPane(), s3 = new StackPane(), s5 = new StackPane(), s6 = new StackPane(), s7 = new StackPane(), s8 = new StackPane(), s9 = new StackPane(), s10 = new StackPane(), s11 = new StackPane(), s12 = new StackPane(), s13 = new StackPane();
	
	// distribution pane
	private GridPane dsp = new GridPane();
	private StackPane s01 = new StackPane(), s02 = new StackPane(), s03 = new StackPane();
	private StackPane d1 = new StackPane(), d2 = new StackPane(), d3 = new StackPane(), d4 = new StackPane(), d5 = new StackPane(), d6 = new StackPane(), d7 = new StackPane(), d8 = new StackPane(), d9 = new StackPane(), d10 = new StackPane();
	private StackPane n1 = new StackPane(), n2 = new StackPane(), n3 = new StackPane(), n4 = new StackPane(), n5 = new StackPane(), n6 = new StackPane(), n7 = new StackPane(), n8 = new StackPane(), n9 = new StackPane(), n10 = new StackPane();
	private StackPane a1 = new StackPane(), a2 = new StackPane(), a3 = new StackPane(), a4 = new StackPane(), a5 = new StackPane(), a6 = new StackPane(), a7 = new StackPane(), a8 = new StackPane(), a9 = new StackPane(), a10 = new StackPane();	
	static int columns = 10;
	
	public ControlPane()
	{
		// create Open pane
		HBox op = new HBox();
		// set parameters
		op.setSpacing(10);
		// add components
		op.getChildren().add(neww);
		op.getChildren().add(path);
		op.getChildren().add(open);
		op.getChildren().add(save);
		// button events
		neww.setOnAction(new ButtonHandler());
		open.setOnAction(new ButtonHandler());
		save.setOnAction(new ButtonHandler());
		
		// create Display pane
		HBox dp = new HBox();		
		// set parameters
		dp.setSpacing(10);
		// set up the choice boxes
		hv.getItems().addAll("Horizontal", "Vertical");
		hv.setValue("Horizontal");
		ord.getItems().addAll("Ascend", "Descend");
		ord.setValue("Descend");
		// add components
		dp.getChildren().add(cols);
		dp.getChildren().add(col);
		dp.getChildren().add(hv);
		dp.getChildren().add(ord);
		dp.getChildren().add(t);
		dp.getChildren().add(add);
		dp.getChildren().add(delete);
		// button events
		add.setOnAction(new ButtonHandler());
		delete.setOnAction(new ButtonHandler());
		cols.setOnAction(new ButtonHandler());
		// choice box events
		hv.setOnAction(new ChoiceHandler());
		ord.setOnAction(new ChoiceHandler());
		// create default floats pane
		ScrollPane scroll = new ScrollPane();
		scroll.setPrefSize(500, 500);
		scroll.setContent(fp);
		// create default stats pane
		s1.getChildren().addAll(new TableEntry(new Label()), new Label("Mean"));
		s2.getChildren().addAll(new TableEntry(new Label()), new Label("Median"));
		s3.getChildren().addAll(new TableEntry(new Label()), new Label("Mode"));
		s13.getChildren().addAll(new TableEntry(new Label()), new Label("(count)"));
		// (blank)
		s5.getChildren().add(new TableEntry(new Label()));
		s6.getChildren().add(new TableEntry(new Label()));
		s7.getChildren().add(new TableEntry(new Label()));
		s8.getChildren().add(new TableEntry(new Label()));
		s9.getChildren().add(new TableEntry(new Label()));
		s10.getChildren().add(new TableEntry(new Label()));
		s11.getChildren().add(new TableEntry(new Label()));
		s12.getChildren().add(new TableEntry(new Label()));
		
		// set up table 
		sp.add(s1, 0, 0); 
		sp.add(s2, 1, 0);
		sp.add(s3, 2, 0); 
		sp.add(s13, 3, 0);
		sp.add(s5, 0, 1);
		sp.add(s6, 1, 1);
		sp.add(s7, 2, 1); 
		sp.add(s8, 2, 2); 
		sp.add(s9, 2, 3);
		sp.add(s10, 3, 1); 
		sp.add(s11, 3, 2); 
		sp.add(s12, 3, 3);
		
		
		// create default distribution pane
		s01.getChildren().addAll(new TableEntry(new Label()), new Label("Percentile"));
		s02.getChildren().addAll(new TableEntry(new Label()), new Label("Number"));
		s03.getChildren().addAll(new TableEntry(new Label()), new Label("Avg"));
		
		d1.getChildren().add(new TableEntry(new Label("90-100%"))); 
		d2.getChildren().add(new TableEntry(new Label("80-90%")));
		d3.getChildren().add(new TableEntry(new Label("70-80%")));
		d4.getChildren().add(new TableEntry(new Label("60-70%")));
		d5.getChildren().add(new TableEntry(new Label("50-60%")));
		d6.getChildren().add(new TableEntry(new Label("40-50%")));
		d7.getChildren().add(new TableEntry(new Label("30-40%")));
		d8.getChildren().add(new TableEntry(new Label("20-30%")));
		d9.getChildren().add(new TableEntry(new Label("10-20%")));
		d10.getChildren().add(new TableEntry(new Label("0-10%")));

		// numbers (blank)
		n1.getChildren().add(new TableEntry(new Label()));
		n2.getChildren().add(new TableEntry(new Label()));
		n3.getChildren().add(new TableEntry(new Label()));
		n4.getChildren().add(new TableEntry(new Label()));
		n5.getChildren().add(new TableEntry(new Label()));
		n6.getChildren().add(new TableEntry(new Label()));
		n7.getChildren().add(new TableEntry(new Label()));
		n8.getChildren().add(new TableEntry(new Label()));
		n9.getChildren().add(new TableEntry(new Label()));
		n10.getChildren().add(new TableEntry(new Label()));
		
		// averages (blank)
		a1.getChildren().add(new TableEntry(new Label()));
		a2.getChildren().add(new TableEntry(new Label()));
		a3.getChildren().add(new TableEntry(new Label()));
		a4.getChildren().add(new TableEntry(new Label()));
		a5.getChildren().add(new TableEntry(new Label()));
		a6.getChildren().add(new TableEntry(new Label()));
		a7.getChildren().add(new TableEntry(new Label()));
		a8.getChildren().add(new TableEntry(new Label()));
		a9.getChildren().add(new TableEntry(new Label()));
		a10.getChildren().add(new TableEntry(new Label()));
		
		// set up table 
		dsp.add(s01, 0, 0); dsp.add(s02, 1, 0); dsp.add(s03, 2, 0);
		dsp.add(d1, 0, 1); dsp.add(n1, 1, 1); dsp.add(a1, 2, 1);
		dsp.add(d2, 0, 2); dsp.add(n2, 1, 2); dsp.add(a2, 2, 2);
		dsp.add(d3, 0, 3); dsp.add(n3, 1, 3); dsp.add(a3, 2, 3);
		dsp.add(d4, 0, 4); dsp.add(n4, 1, 4); dsp.add(a4, 2, 4);
		dsp.add(d5, 0, 5); dsp.add(n5, 1, 5); dsp.add(a5, 2, 5);
		dsp.add(d6, 0, 6); dsp.add(n6, 1, 6); dsp.add(a6, 2, 6);
		dsp.add(d7, 0, 7); dsp.add(n7, 1, 7); dsp.add(a7, 2, 7);
		dsp.add(d8, 0, 8); dsp.add(n8, 1, 8); dsp.add(a8, 2, 8);
		dsp.add(d9, 0, 9); dsp.add(n9, 1, 9); dsp.add(a9, 2, 9);
		dsp.add(d10, 0, 10); dsp.add(n10, 1, 10); dsp.add(a10, 2, 10);
		
		// combine stats and distribution panes
		HBox SP = new HBox(10, sp, dsp);
		
		// put all panes together
		setAlignment(Pos.CENTER);
		setSpacing(10);
		getChildren().addAll(op, dp, scroll, SP);
		
	}
	
	protected void update()
	{
		changeFloats();
		changeStats();
		changeDistribution();
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> 
	{
		public void handle(ActionEvent event) 
		{
			if (event.getSource() == neww)
			{
				path.clear();
				FloatProgram.f = new Floats();
				update();
			}
			else if (event.getSource() == open)
			{
				if (path.getText() != null)
					FloatProgram.f.open(path.getText());
				update();
			}
			else if (event.getSource() == save)
			{
				if (path.getText() != null)
					FloatProgram.f.save(path.getText());
			} else if (event.getSource() == cols)
			{
				try {
					columns = Integer.parseInt(col.getText());
				} catch(NumberFormatException e)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR, col.getText() + " is NOT an Int", ButtonType.CLOSE);
					alert.showAndWait();
				}
				update();
			}
			else if (event.getSource() == add)
			{
				boolean check = true;
				if (t.getText() != null)
				{
					try
					{
						new NumberFormatExceptionHandlingTest().floatParsingMethod(t.getText());
					} catch (NumberFormatException e) {
						check = false;
						Alert alert = new Alert(Alert.AlertType.ERROR, t.getText() + " is NOT a Float or Int", ButtonType.CLOSE);
						alert.showAndWait();
					}
					if (check)
					{
						FloatProgram.f.add(Float.parseFloat(t.getText()));
					}
				}
				update();
			}
			else if (event.getSource() == delete)
			{
				boolean check = true;
				if (t.getText() != null)
				{
					try
					{
						new NumberFormatExceptionHandlingTest().floatParsingMethod(t.getText());
					} catch (NumberFormatException e) {
						check = false;
						Alert alert = new Alert(Alert.AlertType.ERROR, t.getText() + " is NOT a Float or Int", ButtonType.CLOSE);
						alert.showAndWait();
					}
					if (check)
					{
						FloatProgram.f.delete(Float.parseFloat(t.getText()));
					}
				}
				update();
			}
		}
	}
	private class ChoiceHandler implements EventHandler<ActionEvent> 
	{
		public void handle(ActionEvent event) 
		{
			update();
		}
	}
	public void changeFloats()
	{
		fp.getChildren().clear();
		if (ord.getValue() == "Ascend")
		{
			FloatProgram.f.ascend(0, FloatProgram.f.array.length - 1);
		}
		else
		{
			FloatProgram.f.descend(0, FloatProgram.f.array.length - 1);
		}
		
		int cols = columns;
		int rows = FloatProgram.f.array.length/columns;
		if (FloatProgram.f.array.length % columns != 0)
			rows++;
		
		if (hv.getValue() == "Horizontal")
		{
			int k = 0;
			for (int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					if (k < FloatProgram.f.array.length)
					{
						fp.add(new TableEntry(new Label(FloatProgram.f.array[k].toString())),j,i);
						k++;
					}
					else
					{
						Rectangle r = new Rectangle(55, 25);
						r.setStroke(Color.BLACK);
						r.setFill(Color.WHITE);
						fp.add(r,j,i);
					}
				}
			}
		}
		else if (hv.getValue() == "Vertical")
		{
			int k = 0;
			for(int j = 0; j < cols; j++)
			{
				for (int i = 0; i < rows; i++)
				{
					if (k < FloatProgram.f.array.length)
					{
						fp.add(new TableEntry(new Label(FloatProgram.f.array[k].toString())),j,i);
						k++;
					}
					else
					{
						Rectangle r = new Rectangle(55, 25);
						r.setStroke(Color.BLACK);
						r.setFill(Color.WHITE);
						fp.add(r,j,i);
					}
				}
			}
		}
	}
	public void changeStats()
	{
		sp.getChildren().clear();
		
		s1.getChildren().addAll(new TableEntry(new Label()), new Label("Mean"));
		s2.getChildren().addAll(new TableEntry(new Label()), new Label("Median"));
		s3.getChildren().addAll(new TableEntry(new Label()), new Label("Mode"));
		s13.getChildren().addAll(new TableEntry(new Label()), new Label("(count)"));
		s5.getChildren().add(new TableEntry(new Label()));
		s6.getChildren().add(new TableEntry(new Label()));
		s7.getChildren().add(new TableEntry(new Label()));
		s8.getChildren().add(new TableEntry(new Label()));
		s9.getChildren().add(new TableEntry(new Label()));
		s10.getChildren().add(new TableEntry(new Label()));
		s11.getChildren().add(new TableEntry(new Label()));
		s12.getChildren().add(new TableEntry(new Label()));

		if (FloatProgram.f.array.length != 0)
		{
			Float[][] modes = FloatProgram.f.modes();
			
			s5.getChildren().add(new Label(FloatProgram.f.mean().toString()));
			s6.getChildren().add(new Label(FloatProgram.f.median().toString()));
			if (modes[0][0] != null)
			{
				s7.getChildren().add(new Label(modes[0][0].toString()));
				s10.getChildren().add(new Label(modes[0][1].toString()));
			}
			if (modes[1][0] != null)
			{
				s8.getChildren().add(new Label(modes[1][0].toString()));
				s11.getChildren().add(new Label(modes[1][1].toString()));
			}
			if (modes[2][0] != null)
			{
				s9.getChildren().add(new Label(modes[2][0].toString()));
				s12.getChildren().add(new Label(modes[2][1].toString()));
			}
		}
		sp.add(s1, 0, 0); 
		sp.add(s2, 1, 0);
		sp.add(s3, 2, 0); 
		sp.add(s13, 3, 0);
		sp.add(s5, 0, 1);
		sp.add(s6, 1, 1);
		sp.add(s7, 2, 1); 
		sp.add(s8, 2, 2); 
		sp.add(s9, 2, 3);
		sp.add(s10, 3, 1); 
		sp.add(s11, 3, 2); 
		sp.add(s12, 3, 3);
	}
	public void changeDistribution()
	{
		dsp.getChildren().clear();
		
		s01.getChildren().addAll(new TableEntry(new Label()), new Label("Percentile"));
		s02.getChildren().addAll(new TableEntry(new Label()), new Label("Number"));
		s03.getChildren().addAll(new TableEntry(new Label()), new Label("Avg"));
		
		d1.getChildren().add(new TableEntry(new Label("90-100%"))); 
		d2.getChildren().add(new TableEntry(new Label("80-90%")));
		d3.getChildren().add(new TableEntry(new Label("70-80%")));
		d4.getChildren().add(new TableEntry(new Label("60-70%")));
		d5.getChildren().add(new TableEntry(new Label("50-60%")));
		d6.getChildren().add(new TableEntry(new Label("40-50%")));
		d7.getChildren().add(new TableEntry(new Label("30-40%")));
		d8.getChildren().add(new TableEntry(new Label("20-30%")));
		d9.getChildren().add(new TableEntry(new Label("10-20%")));
		d10.getChildren().add(new TableEntry(new Label("0-10%")));
		
		Floats[] distrs = FloatProgram.f.distribution();
		
		if (distrs != null) 
		{
			// numbers
			n1.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[9].array.length))));
			n2.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[8].array.length))));
			n3.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[7].array.length))));
			n4.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[6].array.length))));
			n5.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[5].array.length))));
			n6.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[4].array.length))));
			n7.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[3].array.length))));
			n8.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[2].array.length))));
			n9.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[1].array.length))));
			n10.getChildren().add(new TableEntry(new Label(Integer.toString(distrs[0].array.length))));
			
			// averages
			a1.getChildren().add(new TableEntry(new Label(Float.toString(distrs[9].mean()))));
			a2.getChildren().add(new TableEntry(new Label(Float.toString(distrs[8].mean()))));
			a3.getChildren().add(new TableEntry(new Label(Float.toString(distrs[7].mean()))));
			a4.getChildren().add(new TableEntry(new Label(Float.toString(distrs[6].mean()))));
			a5.getChildren().add(new TableEntry(new Label(Float.toString(distrs[5].mean()))));
			a6.getChildren().add(new TableEntry(new Label(Float.toString(distrs[4].mean()))));
			a7.getChildren().add(new TableEntry(new Label(Float.toString(distrs[3].mean()))));
			a8.getChildren().add(new TableEntry(new Label(Float.toString(distrs[2].mean()))));
			a9.getChildren().add(new TableEntry(new Label(Float.toString(distrs[1].mean()))));
			a10.getChildren().add(new TableEntry(new Label(Float.toString(distrs[0].mean()))));
		} else
		{
			// numbers
			n1.getChildren().add(new TableEntry(new Label()));
			n2.getChildren().add(new TableEntry(new Label()));
			n3.getChildren().add(new TableEntry(new Label()));
			n4.getChildren().add(new TableEntry(new Label()));
			n5.getChildren().add(new TableEntry(new Label()));
			n6.getChildren().add(new TableEntry(new Label()));
			n7.getChildren().add(new TableEntry(new Label()));
			n8.getChildren().add(new TableEntry(new Label()));
			n9.getChildren().add(new TableEntry(new Label()));
			n10.getChildren().add(new TableEntry(new Label()));
			
			// averages
			a1.getChildren().add(new TableEntry(new Label()));
			a2.getChildren().add(new TableEntry(new Label()));
			a3.getChildren().add(new TableEntry(new Label()));
			a4.getChildren().add(new TableEntry(new Label()));
			a5.getChildren().add(new TableEntry(new Label()));
			a6.getChildren().add(new TableEntry(new Label()));
			a7.getChildren().add(new TableEntry(new Label()));
			a8.getChildren().add(new TableEntry(new Label()));
			a9.getChildren().add(new TableEntry(new Label()));
			a10.getChildren().add(new TableEntry(new Label()));
		}
		dsp.add(s01, 0, 0); dsp.add(s02, 1, 0); dsp.add(s03, 2, 0);
		dsp.add(d1, 0, 1); dsp.add(n1, 1, 1); dsp.add(a1, 2, 1);
		dsp.add(d2, 0, 2); dsp.add(n2, 1, 2); dsp.add(a2, 2, 2);
		dsp.add(d3, 0, 3); dsp.add(n3, 1, 3); dsp.add(a3, 2, 3);
		dsp.add(d4, 0, 4); dsp.add(n4, 1, 4); dsp.add(a4, 2, 4);
		dsp.add(d5, 0, 5); dsp.add(n5, 1, 5); dsp.add(a5, 2, 5);
		dsp.add(d6, 0, 6); dsp.add(n6, 1, 6); dsp.add(a6, 2, 6);
		dsp.add(d7, 0, 7); dsp.add(n7, 1, 7); dsp.add(a7, 2, 7);
		dsp.add(d8, 0, 8); dsp.add(n8, 1, 8); dsp.add(a8, 2, 8);
		dsp.add(d9, 0, 9); dsp.add(n9, 1, 9); dsp.add(a9, 2, 9);
		dsp.add(d10, 0, 10); dsp.add(n10, 1, 10); dsp.add(a10, 2, 10);
	}
}

	
