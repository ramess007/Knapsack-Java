//Imports java utility and io packages to access all the required methods and classes in the package
import java.util.*;
import java.io.*;


/**
   -This class formulates Knapsack algorithm which maximizes the value of a knapsack.
   -The class is called from DPKnapsack class to return a maximized solution. 
   -The name of the items,weight, and value are accessed from arraylist that is obtained 
    from DpKnapsack and the soution is added to another arraylist called solution.
*/

public class Knapsack
{
   
   //Declaration and initialization of variable and arraylists to be used in the class
   private int capacity;
   private List<String> items = new ArrayList<String>();
   private List<Integer> weight = new ArrayList<Integer>();
   private List<Integer> value = new ArrayList<Integer>();
   private List<String> solution= new ArrayList<String>();
  
  
  /**
     -This constructor accepts and initializes the variable capacity and arraylists of items, weight, and value.
     @param capacity the capacity of knapsack
     @param items the arraylist of items names
     @param weight the arraylist of items weights
     @param value the arraylist of items values
  */
  
   public Knapsack(int capacity,List<String> items,List<Integer> weight,List<Integer> value)
   {
      this.capacity = capacity;
      this.items = items;
      this.weight = weight;
      this.value = value;
   }
  
  
  /**
     -This methods construct a two dimensional array (table) which corresponds to the maximum profit obtained
      by applying a dynamic programming solution of this problem using knapsack algorithm.
     -It also construct another two dimensional array (keep table) where keep[i][j] is true if the ith item is 
      kept in table[i][j], false otherwise. (the value is true or false in each cell)
     -The method uses nested for loop to put values in each cells of table and keep. 
     -The method uses nested for loop to solve and add the optimal solution in the solution arraylist.
     @return an arraylist of optimal solution
  */
  
   public List<String> knapsack()
   {
      int[][] table = new int[items.size()][this.capacity+1];
      boolean [][] keep = new boolean [items.size()][this.capacity+1];
        
      for (int i = 1; i<items.size() ;i++)
      {
         for (int j=1; j<=this.capacity;j++)
         {
            if(weight.get(i)> j)
            {
               table[i][j]= table[i-1][j];
            }
            else
            {
               if (table[i-1][j]>value.get(i)+table[i-1][j-weight.get(i)])
               {
                  table[i][j]= table[i-1][j];
               }
               else
               {
                  table[i][j]= value.get(i)+table[i-1][j-weight.get(i)];
                  keep[i][j]= true;
               }
            }
         }
      }
   
      
//       int k = table[items.size()-1][this.capacity];   //initializes k to max weight
      int k = this.capacity;
      
         for (int j = items.size(); j>=0;j--)
         {
            if((keep[j][k]== true))
            {
               solution.add(items.get(j));
               k = k - value.get(j);
               break;
            }
         }
      
      return (solution);
   }
}
