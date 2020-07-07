// CSE 360 Group Project
// Developed by Tyler Walters, Paxton Ostler, Nevin Foster, Daniel Salas
// IDE: Eclipse
import java.io.*;

public class Floats 
{
	public Float[] array; // array of the float values
	
	public Floats()
	{
		array = new Float[0];
	}
	public Float get(int i) // returns the float from specified index
	{
		return array[i];
	}
	public void open(String fileName)
	{
		try 
		{
			File file = new File(fileName); 
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line; 
			while ((line = reader.readLine()) != null)
			{
				add(Float.parseFloat(line));
			}
			reader.close(); //close the reader
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch (IOException e)
		{
			System.out.println("Error reading floats in file.");
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
	public void delete(int i) // deletes i, the index of the to be deleted float
	{
		Float[] old = array; // store old
		array = new Float[array.length - 1]; // create new with one less space
		for (int k = 0; k < i; k++)
		{
			array[k] = old[k]; // replace old until i
		}
		for (int k = i; k < array.length; k++)
		{
			array[k] = old[k + 1]; // skips index i and continues
		}
		
	}
	public void save(String fileName)
	{
		try 
		{
			FileWriter file = new FileWriter(fileName);
			BufferedWriter output = new BufferedWriter(file);
			for (int k = 0; k < array.length; k++)
			{
				output.write(Float.toString(array[k]) + '\n'); // write floats as strings each on new line
			}
			output.close(); // close writer
			
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
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
    	ascend(0, array.length - 1); // sort array first
    	int size = array.length - 1;
    	if (size != 0)
    		return array[size/2];
    	else
    		return null;
    }
    public Float[] modes() // returns array of top 3 modes of the floats
    {
    	// create a count of all the repetitions of floats
    	int[] count = new int[array.length];
    	for (int k = 0; k < array.length - 1; k++)
    	{
        	for (int i = 0; i < array.length; i++)
        	{
        		if (array[i] == array[k] && i != k)
        			count[k]++;
        	}
    	}
    	
    	Float[] modes = new Float[3]; 
    	// first mode
    	int c = count[0];
    	if (c != 0) // as long as the count isn't 0
    		modes[1] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c)
    		{
    			c = count[j];
    			modes[1] = array[j]; // update mode
    		}
    	}
    	// second mode
		c = count[0];
		if (c != 0) // as long as the count isn't 0
    		modes[2] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c && array[j] != modes[1]) 
    		{
    			c = count[j];
    			modes[2] = array[j]; // update mode
    		}
    	}
    	// third mode
    	c = count[0];
    	if (c != 0) // as long as the count isn't 0
    		modes[3] = array[0];
    	for (int j = 1; j < count.length; j++)
    	{
    		if (count[j] > c && array[j] != modes[1] && array[j] != modes[2])
    		{
    			c = count[j];
    			modes[3] = array[j]; // update mode
    		}
    	}
    	
    	return modes;
    }
    public Floats[] distribution()
    {
    	if (array.length > 0)
    	{
    		ascend(0, array.length - 1); // start with sorted array
        	Float range = array[array.length - 1] - array[0];
        	// percentiles
        	Float p90 = range * .9f;
        	Float p80 = range * .8f;
        	Float p70 = range * .7f;
        	Float p60 = range * .6f;
        	Float p50 = range * .5f;
        	Float p40 = range * .4f;
        	Float p30 = range * .3f;
        	Float p20 = range * .2f;
        	Float p10 = range * .1f;
        	
        	Floats f90 = new Floats();
        	Floats f80 = new Floats();
        	Floats f70 = new Floats();
        	Floats f60 = new Floats();
        	Floats f50 = new Floats();
        	Floats f40 = new Floats();
        	Floats f30 = new Floats();
        	Floats f20 = new Floats();
        	Floats f10 = new Floats();
        	
        	for (int i = 0; i < array.length; i++)
        	{
        		if (array[i] <= p90)
        			f90.add(array[i]);
        		if (array[i] <= p80)
        			f80.add(array[i]);
        		if (array[i] <= p70)
        			f70.add(array[i]);
        		if (array[i] <= p60)
        			f60.add(array[i]);
        		if (array[i] <= p50)
        			f50.add(array[i]);
        		if (array[i] <= p40)
        			f40.add(array[i]);
        		if (array[i] <= p30)
        			f30.add(array[i]);
        		if (array[i] <= p20)
        			f20.add(array[i]);
        		if (array[i] <= p10)
        			f10.add(array[i]);
        	}
        	Floats[] f = {f90, f80, f70, f60, f50, f50, f40, f30, f20, f10};
        	return f;
    	}
    	else
    		return null;
    	
    }
}