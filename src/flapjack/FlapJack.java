package flapjack;
// Flapjacks.java
// Solution to the "Stacks of flapjacks" problem
// Given a list of integers, find a way to reverse subranges of the list so 
// that the list ends up being sorted in ascending order.

import java.util.Scanner;
import java.io.*;

class FlapJack {
	/**
	 * Using an existing scanner, use it to fill an array of integers.
	 * Returns the number of values found.
	 */
	static int scanIntegerArray( int[] array, Scanner scan )
	{
		int i = 0;
		while( scan.hasNextInt())
			array[i++] = scan.nextInt();
		return i;
	}
	
	
	// returns the index of th1e largest element in a range of 
	// array values. Assumes that last >= first.
	static int findLargest( int[] array, int first, int last )
	{
		int index = first;
		int max = array[index];
		
		for( int i = first+1; i <= last; i++)
		{
			if( array[i] > max) {
				max = array[i];
				index = i;
			}
		}
	
		return index;
	}
	
	// reverse array elements within a given range
	// Assumes that last >= first.
	static void reverse( int[] array, int first, int last )
	{
		int mid = (last-first) / 2 + 1;
		int temp;
		
		for( int i = 0; i < mid; i++ )  {			
			temp = array[last-i];
			array[last-i] = array[first+i];
			array[first+i] = temp;
		}
	}

	// prints integer array in space-delimited format
	static void print( int[] array, int size )
	{
		for( int i = 0; i < size; i++ )
			System.out.print( array[i] + " " );
		
		System.out.println();
	}
	
	/**
	 * Sorts an array of integers by repeatedly reversing 
	 * subranges within the array. Prints the flip sequence. 
	 * Implementation note:
	 * Flip position notation requires adjusting because
	 * the problem description assumes the first value in the
	 * array has the highest position number.
	 */	
	public static void  sortFlapJacks( int[] array, int size )
	{	
		for(int i = size-1; i > 0; i--)
		{
			int j = findLargest(array, 0, i );
			int flipPosition;
			
			if( j != i )
			{
				if( j != 0 ) {
					reverse( array, 0, j );
					flipPosition = size-j;
					System.out.print( flipPosition + " " );
				}
				
				reverse( array, 0, i );
				flipPosition = size-i;
				System.out.print( flipPosition + " " );
			}
		}
		System.out.println( 0 );
	}
	
	
	public static void main(String[] args) 
		throws FileNotFoundException
	{
		final int MAX_INPUTS = 30;
		int[] array = new int[MAX_INPUTS];
		
		Scanner filescan = new Scanner( System.in );
		
		while( filescan.hasNextLine() )
		{
			String inputLine = filescan.nextLine(); 
			Scanner scan = new Scanner( inputLine );			
			
			// scan a string with a sequence of integers into an array
			int count = scanIntegerArray( array, scan );	
			
			// Store the sequence of flips for later printing. Might be 
			// larger than the number of input values. Beware of possible
			// blank line in input file.
			if( count > 0 ) 
			{
				// print the original array
				print( array, count );			
				// do the flipping now
				sortFlapJacks( array, count );				
			}
		}
	}
}