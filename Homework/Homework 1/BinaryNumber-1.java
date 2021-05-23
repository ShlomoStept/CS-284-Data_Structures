/* 	Shlomo Stept
 * "I pledge my honor that I have abided by the Stevens Honor System"
 *  
 *  CS284: Lecture A; Recitation A
 *  
 *  Homework 1: Binary Number
 */

package binaryNumber;

import java.util.Arrays;
import java.lang.*;
import java.math.*;


class BinaryNumber {

//---------------------------------------------------------------------------------------------------------------------
    // Data Fields
//---------------------------------------------------------------------------------------------------------------------
    private int[] data;
    private int length;


//---------------------------------------------------------------------------------------------------------------------
    //Constructors
//---------------------------------------------------------------------------------------------------------------------

            //  --> Constructor # 1
    //-------------------------------------------------------------------------------------
    /**
     * An Integer Constructor for creating a binary number of specified legth filled with zeros
     * @param length The length of the BinaryNumber
     */
    public BinaryNumber(int length)
    {
        // --> (1) make sure the length is not zero or a negative number
        if(length < 1)
            throw new IllegalArgumentException("The Binary Number's Length must be greater than 0");

        // --> (2) Set up the data fields
        data = new int[length];
        this.length = length;

        // --> (3) populate data[] with zeros
        for( int i =0; i< length; i++)
        {
            data[i]= 0;
        }

    }
//-----------------------------------------------------------------------------------------------------------------

            //  --> Constructor # 2
    //-------------------------------------------------------------------------------------
    /**
     * A Constructor for creating a binary number of specified Input String
     *      --> This is done by:
     *      --> (1) creating an array of an equal size as the string
     *      --> (2) taking each character out one at a time converting their Char value to Integer value
     *              and saving in the appropriate place
     * @param str The Binary Number in String Form
     */
     public BinaryNumber(String str)
    {

        // --> (0) Check to make sure a proper binary number was entered: only 0's or 1's
        for( int i =0; i< str.length(); i++)
        {
            if(str.charAt(i) != '0' && str.charAt(i) != '1')
            { throw new IllegalArgumentException("The Binary Number must be composed of only 0's or 1's"); };
        }

        // --> (1) Set up the data fields
        data = new int[str.length()];
        this.length = str.length();

        // --> (2) Create the data[] by transforming each char in the string to its numerical equivalent
        for( int i =0; i< str.length() ; i++)
        {
            data[i]= Character.getNumericValue( str.charAt(i) );
        }

    }

//---------------------------------------------------------------------------------------------------------------------
    // Methods
//---------------------------------------------------------------------------------------------------------------------

        //  --> 1. getLength
    //-------------------------------------------------------------------------------------
    /**
     * getLength is a method used to obtain the length of a binary number
     * @return The Length of binaryNumber
     */
    public int getLength()
    {
        return this.length;
    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 2. getDigit
    //-------------------------------------------------------------------------------------
    /**
     * getDigit is a method used to obtain the digit located at the specified index
     * @param index The index of digit to return
     * @return The digit located at the index entered in the ()
     */
    public int getDigit(int index)
    {
        // --> (1) Make sure the Index is in the appropriate range: not less than zero, or greater than the length of the data[]
        if (index < 0 || index > this.length)
        {
            throw new ArrayIndexOutOfBoundsException("The Index entered is Out of Bounds");
        }

        return this.data[index];
    }



//-----------------------------------------------------------------------------------------------------------------
        //  3. getInnerArray
    //-------------------------------------------------------------------------------------
    /**
     * getInnerArray is a method used to obtain the Array of the Binary Number
     * @return The array of the binaryNumber
     */
    public int[] getInnerArray()
    {
        return this.data;
    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 5. bwor
    //-------------------------------------------------------------------------------------
    /**
     * bwor is a method used to obtain the BitWise-OR of two Binary Numbers
     * @param bn1 The First BinaryNumber instance to use in the BitWise-OR operation
     * @param bn2 The second BinaryNumber instance to use in the BitWise-OR operation
     * @return The BitWise-OR of the two binaryNumbers passed into the method
     */
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2)
    {
        // --> (1) Make sure the binary numbers are of the same length
        if(bn1.length != bn2.length)
        {
            throw new IllegalArgumentException(" The Binary Numbers are not the same length");
        }

        // --> (2) Calculate the BitWise-OR, By:
        //        (A) creating a new instance of BinaryNumber and,
        //        (B) going one index at a time, and adding a 1 to the new BinaryNum if bn1 OR bn2 contains a 1 in that Index
        BinaryNumber bitwiseOr = new BinaryNumber(bn1.length);

        for( int i = 0 ; i < bn1.length ; i++)
        {
            if ( bn1.data[i] == 1 || bn2.data[i] == 1)
            {
                bitwiseOr.data[i] = 1;
            }
            else
            {
                bitwiseOr.data[i] = 0;
            }
        }


        return bitwiseOr.data;
    }



//-----------------------------------------------------------------------------------------------------------------
       //  --> 6. bwand
    //-------------------------------------------------------------------------------------
    /**
     * bwand is a method used to obtain the BitWise-AND of two Binary Numbers
     * @param bn1 The First BinaryNumber instance to use in the BitWise-AND operation
     * @param bn2 The second BinaryNumber instance to use in the BitWise-AND operation
     * @return The BitWise-AND of the two binaryNumbers passed into the method
     */
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2)
    {
        // --> (1) Make sure the binary numbers are of the same length
        if(bn1.length != bn2.length)
        {
            throw new IllegalArgumentException(" The Binary Numbers are not the same length");
        }

        // --> (2) Calculate the BitWise-AND, By:
        //        (A) creating a new instance of BinaryNumber  and,
        //        (B) going one index at a time, and adding a 1 to the new BinaryNum if bn1 AND bn2 contains a 1 in that Index
        BinaryNumber bitwiseAnd = new BinaryNumber(bn1.length);

        for( int i = 0 ; i < bn1.length ; i++)
        {
            if ( bn1.data[i] == 1 && bn2.data[i] == 1)
            {
                bitwiseAnd.data[i] = 1;
            }
            else
            {
                bitwiseAnd.data[i] = 0;
            }
        }

        return bitwiseAnd.data;
    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 7. bitShift
    //-------------------------------------------------------------------------------------
    /**
     * bitShift is a method used to shift the bits of a Binary Number to the Left or Right by th user specified amount
     * @param direction The direction of the Bit-Shift
     * @param amount The amount of spaces to Shift the bits
     */
    public void bitShift(int direction, int amount)
    {
        // --> (1) Make sure direction argument is valid: is a -1 or a 1, and
        if( direction != -1 && direction != 1)
        { throw new IllegalArgumentException("The Direction argument is not vaild -> It must be a 1 or a -1 "); }

        // --> (2)Make sure that the amount is a number greater than 0
        if(amount<0)
        { throw new IllegalArgumentException("The Amount argument is not valid -> it cannot be Negative "); }


        // --> (2A) shift Right operation ( ---> )
        if (direction == 1)
        {
            BinaryNumber temp = new BinaryNumber(this.length - amount);

            for( int i =0; i < (this.length - amount); i++ )
            {
                temp.data[i] = data[i];
            }

            data= temp.data;
            length -= amount;
        }

        // --> (2B) shift Left operation ( <--- )
        if (direction == -1)
        {
            BinaryNumber temp = new BinaryNumber(this.length + amount);

            for( int i =0; i < this.length ; i++ )
            {
                temp.data[i] = data[i];
            }

            for( int i = this.length; i < (this.length + amount); i++ )
            {
                temp.data[i] = 0;
            }

            data= temp.data;
            length += amount;
        }

    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 8. add
    //-------------------------------------------------------------------------------------
    /**
     * add is a method used to Add Two (2) Binary Numbers together
     * @param aBinaryNumber The Binary Number that you want to add
     */
    /* -> This is done by:
        --> (1) making the two arrays the same length, by adding zeros to the beginning of the shorter length Binary number
        --> (2) Going bit by bit to calculate bitwise addition and caring any value greater than one, so:
        -->(A) a, 0 + 0 = 0; and a zero(0) carry
         -->(B) a, 0 + 1 = 1: and a zero(0) carry
        -->(C) a, 1 + 1 = 0; and a one(1) carry
                so if the next bit pair was (1+0) we add the carry to get 1+0+1 = 0 with a one carry

    // NOTES: The Binary Number that you want to add (but not keep) is entered in the argument, But
    //           The BinaryNumber you set = to this method changes to the result of this addition
    */
    public void add(BinaryNumber aBinaryNumber)
    {
        // --> (1) Make the size of the two arrays in the addition equivalent
            int dataSizeDif = Math.abs( this.length - aBinaryNumber.length);

            if (aBinaryNumber.length > this.length)
            {
                this.prepend(dataSizeDif);
            }

            else if ( this.length > aBinaryNumber.length)
            {
                aBinaryNumber.prepend(dataSizeDif);
            }


        // --> (2) Add the two arrays one bit at a time going left to right ( <---- )
        int[] temp = new int[aBinaryNumber.length + 1];

        for( int carry =0, i = (this.length - 1 ) ; i >= 0; i-- )
        {
            /*System.out*/
            int total = carry + data[i] + aBinaryNumber.data[i];

            switch(total)
            {
                case 0:
                    temp[i+1] = 0;
                    carry = 0 ;
                    break;

                case 1:
                    temp[i+1] =1;
                    carry = 0 ;
                    break;

                case 2:
                    temp[i+1] =0;
                    carry = 1;
                    break;

                case 3:
                    temp[i+1] =1;
                    carry =1;
                    break;
            }

            if (i == 0 && carry == 1)
            {
                temp[i] = carry;
            }

        }

        // --> (3) Update length and data[]
        data = temp;
        length = temp.length;
    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 9. toString
    //-------------------------------------------------------------------------------------
    /**
     * toString is a method that returns the binary Number stored in data[] as a string
     * @return The string form of the value stored in BinaryNumber
     */
    @Override
    public String toString() {

        String binaryNumber="";
        for(int i: data)
        {
        	binaryNumber += i;
        }

        return "{BinaryNumber = " + binaryNumber + "}" ;
    }




//-----------------------------------------------------------------------------------------------------------------
        //  --> 10. toDecimal
    //-------------------------------------------------------------------------------------
    /**
     * toDecimal is a method that converts and returns the decimal value of the Binary Number stored in BinaryNumber
     * @return The Decimal value of the Binary Number
     */
    public int toDecimal()
    {
        int decimalValue = 0;
        for( int j=0, i = this.length ; i > 0; i--, j++)
        {
            int base2 = (int) Math.pow(2,j);
            decimalValue +=  base2 * data[i-1];
        }

        return decimalValue;
    }



//-----------------------------------------------------------------------------------------------------------------
        //  --> 11. prepend (bonus) 
    // -------------------------------------------------------------------------------------
    /**
     * prepend is a method that adds additional zeros at the begging of the data[] array, based on the user specified amount
     * @param amount The amount of zeros to add to the begging of the data[]/Binary Number array
     */
    public void prepend(int amount)
    {
        // --> (0) Check to make sure a proper amount was entered: Must not be negative
        if(amount < 0)
        { throw new ArrayIndexOutOfBoundsException(" The Entered amount is out of Bounds -> it must be a positive number");}

        int[] temp = new int[this.length + amount];

        for (int i = 0; i < amount ; i++)
        {
            temp[i] = 0;
        }

        for( int i = 0; i < this.length  ; i++)
        {
            temp[ amount + i ] = data[i];
        }

        length += amount;
        data = temp;


    }


    //---------------------------------------------------------------------------------------------------------------------
    //  ::   MAIN   ::
    //---------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        

    }
}