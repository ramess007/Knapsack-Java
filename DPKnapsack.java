//Imports java utility and io packages to access all the required methods and classes in the package
import java.util.*;
import java.io.*;


/**
   -This class implements dynamic programming solutio to the 0-1 knapsack problem by calling and 
    initilizing Knapsack class. 
   -It consist of several methods to calculate and return optimalWeight and  optimalNumber of solution
    and check if the solution consists of certain items and also return the items of optimal solution.
   -The class uses arraylist data structure to store item names, weights, values, and optimal solution
    and to pass the value to Knapsack class.
   -The class uses goThroughFile and solutionSet methods to go through and process the text file and 
    process the solution arraylist.
*/

public class DPKnapsack
{
  //Declaration and initialization of variables and arraylists to be used in the class.
   private int capacity;
   private String itemFile;
   private int totalWeight;
   private int num;
   private String print;
   
   private List<String> items = new ArrayList<String>();
   private List<Integer> weight = new ArrayList<Integer>();
   private List<Integer> value = new ArrayList<Integer>();
   private List<String> solutions= new ArrayList<String>();
   
   
   /**
      -This constructor accepts and initializes the default capacity and the name of data file as argument.
      @param capacity capacity of knapsack
      @param itemFile text file formatted with the name of items, their value, and weight
   */
   
   public DPKnapsack(int capacity, String itemFile)
   {
      this.capacity = capacity;
      this.itemFile = itemFile;
   }
   
   
   /**
      -This method sets the capacity of the knapsack
      @param capacity capacity of the knapsack
   */
   
   public void setCapacity(int capacity)
   {
      this.capacity = capacity;
   }
   
   
   /**
      -This method go through the text file recieved as an argument in the constructor. 
      -It uses try and catch to for handling exception.
      -It uses while loop to go through the text file and add the name of the items in 
       items arraylist, weight of items in weight arraylist, and value of items in value arraylist.
   */
   
   public void goThroughFile()
   {
      items.clear();
      weight.clear();
      value.clear();
      
      try
      {
         Scanner in = new Scanner(new File(itemFile));
         items.add("");
         weight.add(0);
         value.add(0);
         while (in.hasNext())
         {
            items.add(in.next());
            weight.add(Integer.parseInt(in.next()));
            value.add(Integer.parseInt(in.next()));
         }
         
      }
      
      catch (Exception e)
      {
         System.out.println("Exception Occured!!");
      }
   }
   
   
   /**
      -This method is used to go through the optimal solution obtained from the knapsack class.
      -This method is used to create a new Knapsack object passing integer-capacity and arraylists-items, weight,
       and value in the constructor of Knapsack.
      -For loop is used to go throught the items in the solution arraylist which is obtained by invoking knapsack
       method of Knapsack Object.
      -It processes the solution arraylist to calculate total weight, number and print name of items in optimal solution.
   */
   
   private void solutionSet()
   {
      goThroughFile();
      Knapsack sack = new Knapsack(capacity,items,weight,value);
      solutions = sack.knapsack();
      totalWeight=0;
      num = 0;
      print = "";
      for (String sol: solutions)
      {
         this.totalWeight += weight.get(items.indexOf(sol));
         this.num++;
         this.print = sol+", "+ this.print;
      }
   }
   
   
   /**
      -This method returns the overall weight of the items in the optimal solution subset
      -It calls solutionSet method to calculate the totalWeight
      @return total weight of items in the optimal solution
   */
   
   public int optimalWeight()
   {
      solutionSet();
      return totalWeight;
   }
   
   
   /**
      -This method returns the number of items in optimal solution subset
      -It calls solutionSet method to calculate the num
      @return number of items in optimal solution 
   */
   
   public int optimalNumber()
   {
      solutionSet();
      return num;
   }
   
   
   /**
      -This method returns true if the item is in the optimal solution subset, false otherwise
      -This method uses for loop to go through the solution and check if the item is present in
       the solution
      @param item the name of the item checked if it is in optimal solution
      @return true if the item is in the solution, false otherwise
   */
   
   public boolean contains(String item)
   {
      solutionSet();
      for (String sol: solutions)
      {
         if(sol.equals(item)) 
            return true;
      }
      return false;
   }
   
   
   /**
      -This method returns a string representing the optimal solution subset.
      -It calls solutionSet method to add all the names of items to print variable
      @return formatted output of names of the items in optimal solution
   */
   
   public String solution()
   {
      solutionSet();
      print ="[ "+print+"]";
      return "Solution includes: " + print.replace(", ]"," ]\n");
   }
   
   
   /**
      -This method returns the overall weight of the items in the optimal solution subset
      -It calls setCapacity of set the capacity to maxWeight
      -It calls solutionSet method to calculate the totalWeight
      @param maxWeight the new capacity of the knapsack
      @return total weight of items in the optimal solution
   */
   
   public int optimalWeight(int maxWeight)
   {
      this.setCapacity(maxWeight);
      solutionSet();
      return totalWeight;
   }
   
   
   /**
      -This method returns the number of items in optimal solution subset
      -It calls setCapacity of set the capacity to maxWeight
      -It calls solutionSet method to calculate the num
      @param maxWeight the new capacity of the knapsack
      @return number of items in optimal solution 
   */
   public int optimalNumber(int maxWeight)
   {
      this.setCapacity(maxWeight);
      solutionSet();
      return num;
   }
   
   
   /**
      -This method returns true if the item is in the optimal solution subset, false otherwise
      -It calls setCapacity of set the capacity to maxWeight
      -This method uses for loop to go through the solution and check if the item is present in
       the solution
      @param item the name of the item checked if it is in optimal solution
      @param maxWeight the new capacity of the knapsack
      @return true if the item is in the solution, false otherwise
   */
   public boolean contains(String item, int maxWeight)
   {
      this.setCapacity(maxWeight);
      solutionSet();
      for (String sol: solutions)
      {
         if(sol.equals(item)) 
            return true;
      }
      return false;
   
   }
   
   
   /**
      -This method returns a string representing the optimal solution subset.
      -It calls setCapacity of set the capacity to maxWeight
      -It calls solutionSet method to add all the names of items to print variable
      @param maxWeight the new capacity of the knapsack
      @return formatted output of names of the items in optimal solution
   */
   public String solution(int maxWeight)
   {
      this.setCapacity(maxWeight);
      solutionSet();
      print ="[ "+print+"]";
      return "Solution includes: " + print.replace(", ]"," ]\n");
   }
}


   
   public void check()
   {
      for (int k = 0; k < noOfCourses;k++)
      {
         for (int j = 0; j < noOfCourses;j++)
         {
            if (matrixInfo[k][j]) 
            {
               System.out.print("T ");
            }
            else
            {
               System.out.print("F ");
            }
                         
         }
         System.out.println();
      }
   }