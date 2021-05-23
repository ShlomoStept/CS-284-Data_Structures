/*
 * 	Shlomo Stept
 * 	CS284 Lecture A Recitation A
 * 
 * I pledge my honor that I have abided by the Stevens Honor System. 
 * 
 * Homework 2: Complexity
*/

package Homework2;

public class Complexity {
	
	
//---------------------------------------------------------------------------------------------------------------------
    // Methods and Questions:
//---------------------------------------------------------------------------------------------------------------------
	
	
			//  Q1 --> Method # 1
				//  -->  A method that has a time complexity of O(n^2) 
    //-------------------------------------------------------------------------------------
	
	public static void method1(int n)
	{
		for(int i =0; i < n ; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.println("Iteration (Outerloop, Innerloop): -> (" + i + " " + j + ")");
			}
		}
	
	}
	
	
	
//-----------------------------------------------------------------------------------------------------------------
	
			//  Q2 --> Method # 2
				//  -->  A method that has a time complexity of O(n^3) 
    //-------------------------------------------------------------------------------------
	
	// A method that has a time complexity of O(n^3) 
	public static void method2(int n)
	{
		for(int i =0; i< n; i++)
		{
			for(int j = 0; j< n; j++ )
			{
				for(int k = 0 ; k < n; k++)
				{
					System.out.println("Iteration (Outerloop, Middle-Loop, Innerloop): -> (" + i + " " + j + " " + k + ")");
			
				}
			}
		}
	
	}
	
	
//-----------------------------------------------------------------------------------------------------------------
	
			// Q3 --> Method # 3
				//  -->  // A method that has a time complexity of O(log n)
	//-------------------------------------------------------------------------------------
	
	
	// A method that has a time complexity of O(log n)
	public static void method3(int n)
	{
		for( int i =1; i < n; i *= 2)
		{
			System.out.println("Iteration (outerloop): -> (" + i + ")");
		}
	}
	
	
	
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------
	
		//  Q4 -->  bSearch 
	//-------------------------------------------------------------------------------------	
	/*
	  
	 
	   			//  4-A
					// 	 --> Chart for length = 32
			//----------------------------------------------------------------------
			 
	  Iteration |  Start  | End     	
	------------|---------|-------
		1		|    0	  |  31  
		2		|    16	  |  31
		3		|    24	  |  31
		4		|    28	  |  31
		5		|    30	  |  31
		6		|    31	  |  31
		7		|    32	  |  31
		
		
	
		  		 
				//  4-B 
					// 	 --> Chart for length = 64
			//----------------------------------------------------------------------
		
		Iteration |  Start  | End     	
	------------|---------|-------
		1		|    0	  |  63
		2		|    32	  |  63
		3		|    48	  |  63
		4		|    56	  |  63
		5		|    60	  |  63
		6		|    62	  |  63
		7		|    63	  |  63
		8		|    64	  |  63
	
	*/
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------
			
	
		// Q5 : bSearch Con't
			//  -->   What is the relation between size n of a and the number of iterations?
	//------------------------------------------------------------------------------------------
	/*
	 * 		The relationship is Logarithmic  --->  T(n) = (log(base 2))^n ) + 2    --->   O (log n)
	 */
	
	
//-----------------------------------------------------------------------------------------------------------------
	
	
		// Q6 : bSearch Con't
			//  -->   What is the Time Complexity of bSearch?
	//-------------------------------------------------------------------------------------
	/*
	 * 		1. T(n) = (log(base 2))^n ) + 2
	 * 		
	 * 			-- i.e 	T(n) = [ ( ln(n) ) / ( ln(2) ) ] + 2  
	 * 
	 * 		2.  Thus we have O(log n)
	*/
	
	

	
//-----------------------------------------------------------------------------------------------------------------
	
		
	//  Q7 --> Method # 4
			//  -->  A method that has a time complexity of O( n log(n) ) 
//-------------------------------------------------------------------------------------

public static void method4(int n)
{
	for(int i =0; i< n; i++)
	{
		for(int j = 1; j < n; j *= 2)
		{
			System.out.println("Iteration (outerloop): -> (" + i + " " + j + ")");
		}
	}
	
}


//-----------------------------------------------------------------------------------------------------------------


	//  Q8 --> Method # 5
			//  -->  A method that has a time complexity of O( log( log(n) )  ) 
//-------------------------------------------------------------------------------------

public static void method5(int n)
{
	for(int i = 1; i< n; i*=2 )
	{
		for(int j = 1; j < n; j *= 2)
		{
			System.out.println("Iteration (outerloop, Innerloop): -> (" + i + " " + j + ")");
		}
	}
	
}
	
	
//-----------------------------------------------------------------------------------------------------------------


	//  Q9 :: Extra Credit  --> Method # 6
			//  -->  A method that has a time complexity of O( 2^n ) 
//----------------------------------------------s---------------------------------------


	//  ::::: calculates the fibonocci number  (not really but its close) :::::::
	

public static int method6(int n)
{
	if( n <= 1)
	{ 
		return n;
	}	
	else 
	{
		return method6(n - 1)+ method6(n - 2);
	}
}
	
	
	
	
//---------------------------------------------------------------------------------------------------------------------
//  ::   MAIN   ::
//---------------------------------------------------------------------------------------------------------------------
	
	
	public static void main(String[] args)
	{
	
	}

}




