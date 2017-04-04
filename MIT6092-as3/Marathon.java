/*
 * Assignment 3: Marathon
 * Name: Arti
 * Created date: 04 Apr 2017
 * Last updted: 04 Apr 2017
 */
class Marathon {
    public static void main (String[] arguments) {
        String[] names = {
            "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
            "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
            "Aaron", "Kate"
        };

        int[] times = {
            341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
            343, 317, 265
        };

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + times[i]);
        }
        
        int minIndex = GetMinIndex(times);
        
        if(minIndex != -1)
        {
        	System.out.println("Fastest runner is " + names[minIndex] + ": " + times[minIndex]);
        }
        
        int secondMinIndex = GetSecondMinIndex(times);
        
        if(secondMinIndex != -1)
        {
        	System.out.println("Second fastest runner is " + names[secondMinIndex] + ": " + times[secondMinIndex]);
        }
        
        int[] firstTwoMinIndices = GetFirstTwoMinIndices(times);
        System.out.println("Fastest runners are:");
        
        for(int i = 0; i < firstTwoMinIndices.length; i++)
        {
        	if(firstTwoMinIndices[i] != -1)
        	{
        		System.out.println(i+1 + ". " + names[firstTwoMinIndices[i]] + ": " + times[firstTwoMinIndices[i]]);
        	}
        }
        
    }
    
    /*
     * Method to get indices of first two minimum values
     * int[] times: array of integer values to be compared
     */
    static int[] GetFirstTwoMinIndices(int[] times)
    {
    	int[] firstTwoMinIndices = new int[2];
    	
    	try 
    	{
    		int minValIndex = -1;
        	int secondMinValIndex = -1;
    		Integer minVal = null;
    		Integer secondMinVal = null;
    		
    		for (int i = 0; i < times.length; i++) 
    		{   
    			if(minVal == null || minVal > times[i])
                {
    				if(minVal != null)
    				{
    					secondMinVal = minVal;
    					secondMinValIndex = minValIndex;
    				}
    				
                	minVal = times[i];
                	minValIndex = i;
                }
    			else if((secondMinVal == null || secondMinVal > times[i]) && (minValIndex != i))
    			{
    				secondMinVal = times[i];
    				secondMinValIndex = i;
    			}
            }
    		
    		firstTwoMinIndices[0] = minValIndex;
    		firstTwoMinIndices[1] = secondMinValIndex;
    	}
		catch(Exception ex)
		{
			System.out.println("Error has occurred while getting min indices !!! \nDetails: " + ex.getMessage());
		}
    	
    	return firstTwoMinIndices;
    }
    
    /*
     * Method to get index of minimum value
     * int[] times: array of integer values
     */
    static int GetMinIndex(int[] times)
    {
    	int minValIndex = -1;
    	
    	try 
    	{
    		Integer minVal = null;
    		
    		for (int i = 0; i < times.length; i++) 
    		{   
    			if(minVal == null || minVal > times[i])
                {
                	minVal = times[i];
                	minValIndex = i;
                }
            }
    	}
		catch(Exception ex)
		{
			System.out.println("Error has occurred while getting min index !!! \nDetails: " + ex.getMessage());
		}
    	
    	return minValIndex;
    }
    
    /*
     * Method to get index of second minimum value
     * int[] times: array of integer values
     */
    static int GetSecondMinIndex(int[] times)
    {
    	int secondMinValIndex = -1;
    	
    	try 
    	{
    		int minValIndex  = GetMinIndex(times);
    		Integer secondMinVal = null;
    		
    		for (int i = 0; i < times.length; i++) 
    		{   
    			if((i != minValIndex) && (secondMinVal == null || secondMinVal > times[i]))
                {
    				secondMinVal = times[i];
    				secondMinValIndex = i;
                }
            }
    	}
		catch(Exception ex)
		{
			System.out.println("Error has occurred while getting second min index !!! \nDetails: " + ex.getMessage());
		}
    	
    	return secondMinValIndex;
    }
} 
