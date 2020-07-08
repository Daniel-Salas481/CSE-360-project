// CSE 360 Group Project
// Developed by Tyler Walters, Paxton Ostler, Nevin Foster, Daniel Salas
// IDE: Eclipse
import java.io.*;
import java.text.DecimalFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Floats 
{
	public Float[] array; // array of the float values
	
	public Floats()
	{
		array = new Float[0];
	}
	public void open(String fileName)
	{
		// check to see if the file is a text file
		if(!fileName.endsWith(".txt"))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR, "The file \""+ fileName +"\" is not a text file.", ButtonType.CLOSE);
			alert.showAndWait();
			return; // no need to open.
		}
		try 
		{
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line; 
			while ((line = reader.readLine()) != null)
			{
				try
				{
					add(Float.parseFloat(line));			    
				}
				catch (NumberFormatException e)
				{
			        // not a float, skip
			    }
			}
			reader.close(); //close the reader
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			Alert alert = new Alert(Alert.AlertType.ERROR, "The Text file \""+ fileName +"\" could not be found.", ButtonType.CLOSE);
			alert.showAndWait();
		}
		catch (IOException e)
		{
			System.out.println("Error reading floats in file.");
			Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to read the file.", ButtonType.CLOSE);
			alert.showAndWait();
		}
		
		
	}
	public void add(Float f) // adds the new float f to the array
	{
		Float[] old = array; // store old
		array = new Float[array.length + 1]; // create new with one more space
		//copy old array to new one
		for (int k = 0; k < old.length; k++)
		{
			array[k] = old[k];
		}
		array[array.length - 1] = f;
	}
	public void delete(Float f) // deletes i, the index of the to be deleted float
	{
		int index = -1;
		for (int k = 0; k < array.length; k++)
		{
			if (Math.abs(array[k] - f) < .0000000001f)
			{
				index = k;
				break;
			}
		}
		if (index != -1)
		{
			Float[] old = array; // store old
			array = new Float[array.length - 1]; // create new with one less space
			for (int k = 0; k < index; k++)
			{
				array[k] = old[k]; // replace old until i
			}
			for (int k = index; k < array.length; k++)
			{
				array[k] = old[k + 1]; // skips index i and continues
			}
		} else
		{
			/*
			 * The index could not be found, display an error
			 */
			Alert alert = new Alert(Alert.AlertType.ERROR, "The number \""+ f +"\" could not be found.", ButtonType.CLOSE);
			alert.showAndWait();
		}
	}
	public void save(String fileName)
	{
		// check to see if the file is a text file
		if(!fileName.endsWith(".txt"))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR, "The file \""+ fileName +"\" is not a text file.", ButtonType.CLOSE);
			alert.showAndWait();
			return; // no need to open.
		}
		try 
		{
			File f = new File(fileName);
			if(f.exists())
			{
				Alert alert = new Alert(Alert.AlertType.WARNING, "The file \""+ fileName +"\" is being overwritten.", ButtonType.OK);
				alert.showAndWait();
			}
			
			FileWriter file = new FileWriter(fileName);
			BufferedWriter output = new BufferedWriter(file);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(6);
			df.setMinimumFractionDigits(6);
			df.setMinimumIntegerDigits(7);
			
			if (ControlPane.ord.getValue() == "Ascend")
			{
				FloatProgram.f.ascend(0, FloatProgram.f.array.length - 1);
			}
			else
			{
				FloatProgram.f.descend(0, FloatProgram.f.array.length - 1);
			}
			
			if (ControlPane.hv.getValue() == "Horizontal")
			{
				for (int k = 0; k < array.length; k++)
				{
					output.write(df.format(array[k]));
					if ((k + 1) % ControlPane.columns == 0 )
						output.write("\n");
					else 
						output.write(" | ");
						
				}
			} else
			{
				int rows = FloatProgram.f.array.length/ControlPane.columns;
				if (FloatProgram.f.array.length % ControlPane.columns != 0)
					rows++;
				int index = 0;
				int count = 0;
				while(count < rows)
				{
					output.write(df.format(array[index]));
					
					index = (index + rows);
					if(index >= array.length)
					{
						count++;
						index = count;
						output.write("\n");
					} else 
						output.write(" | ");
				}
			}
			output.close(); // close writer
			
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to write the output file.", ButtonType.CLOSE);
			alert.showAndWait();
		}
	}
    public void ascend(int low, int high) // used Quicksort algorithm
    { 
        if (low < high) 
        {           
          	float pivot = array[high];  
            int i = (low - 1);
            for (int j = low; j < high; j++) 
            { 
                if (array[j] < pivot) 
                { 
                	//increment
                    i++; 
                    //swap
                    float temp = array[i]; 
                    array[i] = array[j]; 
                    array[j] = temp; 
                } 
            } 
            //swap
            float temp = array[i + 1]; 
            array[i + 1] = array[high]; 
            array[high] = temp; 
            //partition
            int pi = i + 1;
            //recursion
            ascend(low, pi - 1); 
            ascend(pi + 1, high); 
        } 
    }
    public void descend(int low, int high) // modified above Quicksort algorithm
    { 
        if (low < high) 
        {           
          	float pivot = array[high];  
            int i = (low - 1);
            for (int j = low; j < high; j++) 
            { 
                if (array[j] > pivot) 
                { 
                	//increment
                    i++; 
                    //swap
                    float temp = array[i]; 
                    array[i] = array[j]; 
                    array[j] = temp; 
                } 
            } 
            //swap
            float temp = array[i + 1]; 
            array[i + 1] = array[high]; 
            array[high] = temp; 
            //partition
            int pi = i + 1;
            //recursion
            descend(low, pi - 1); 
            descend(pi + 1, high); 
        } 
    }
    public Float mean() // returns mean of floats
    {
    	int size = array.length;
    	Float sum = 0f;
    	for (int k = 0; k < size; k++)
    		sum += array[k];
    	return sum/size; 
    }
    public Float median() // returns the median of the floats
    {
    	Floats temp = new Floats();
		temp.array = this.array;
		temp.ascend(0, temp.array.length - 1); // easier to start with sorted array
		
		int size = temp.array.length - 1;
    	if (size != 0)
		{
			return temp.array[size/2];
		}
    	else if (temp.array.length == 1)
		{
			return temp.array[0];
		}
    	else
		{
			return null;
		}
    }
    public Float[][] modes() // returns array of top 3 modes of the floats
    {
    	// create a count of all the repetitions of floats
    	int[] count = new int[array.length];
    	for (int k = 0; k < array.length; k++)
    	{
        	for (int i = 0; i < array.length; i++)
        	{
        		if (Math.abs(array[i] - array[k]) < .00000001f && i != k)
        			count[k]++;
        	}
    	}
    	
    	Float[][] modes = new Float[3][2]; 
    	// first mode
    	int c = count[0];
    	if (c != 0) // as long as the count isn't 0
    		modes[0][0] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c)
    		{
    			c = count[j];
    			modes[0][0] = array[j]; // update mode
    			modes[0][1] = (float) c;
    		}
    	}
    	// second mode
		c = count[0];
		if (c != 0) // as long as the count isn't 0
    		modes[1][0] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c && Math.abs(array[j] - modes[0][0]) > .00000001f) 
    		{
    			c = count[j];
    			modes[1][0] = array[j]; // update mode
    			modes[1][1] = (float) c;
    		}
    	}
    	// third mode
    	c = count[0];
    	if (c != 0) // as long as the count isn't 0
    		modes[2][0] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c && Math.abs(array[j] - modes[0][0]) > .00000001f && Math.abs(array[j] - modes[1][0]) > .00000001f)
    		{
    			c = count[j];
    			modes[2][0] = array[j]; // update mode
    			modes[2][1] = (float) c;
    		}
    	}
    	
    	return modes;
    }
    public Floats[] distribution()
    {
    	if (array.length > 0)
    	{
    		Floats temp = new Floats();
    		temp.array = this.array;
    		temp.ascend(0, temp.array.length - 1); // easier to start with sorted array
        	// indexs
    		int[] indexs = new int[]
    				{
    				(int) (((0.10f) * (temp.array.length + 1)) - 1),
    				(int) (((0.20f) * (temp.array.length + 1)) - 1),
    				(int) (((0.30f) * (temp.array.length + 1)) - 1),
    				(int) (((0.40f) * (temp.array.length + 1)) - 1),
    				(int) (((0.50f) * (temp.array.length + 1)) - 1),
    				(int) (((0.60f) * (temp.array.length + 1)) - 1),
    				(int) (((0.70f) * (temp.array.length + 1)) - 1),
    				(int) (((0.80f) * (temp.array.length + 1)) - 1),
    				(int) (((0.90f) * (temp.array.length + 1)) - 1)
    				};
        	
        	Floats[] f = new Floats[10];
        	for(int x = 0; x < 10; x++)
        		f[x] = new Floats();
        	
        	for (int i = 0; i < temp.array.length; i++)
        	{
        		for (int j = 0; ; j++)
            	{
        			if (j == indexs.length || i <= indexs[j])
        			{
        				f[j].add(temp.array[i]);
        				break;
        			}
            	}
        	}
        	return f;
    	}
    	else
    		return null;
    	
    }
}