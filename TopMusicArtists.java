/**
 *
 * @author Esther Cortez
 * @since 9/28/2020
 * @version 1.0
 */
package topmusicartists;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;


public class TopMusicArtists {

    public static PrintStream ps;
    
    
    
    public static void main(String[] args) throws Exception{
        
        ps = new PrintStream ("results.txt"); // sending all output to a file named results
    
        String [][] fullChart = musicChart();
        String [][] artistAndCount = artistTimes(fullChart); 
        /* takes the array made from csv file puts it in another method called 
        ArtistTimes 
        */
                                                            
        int artistIndex = artistTimes2(fullChart); 
        /*takes the logical length of the array which has the 
        artist name and number of times they appear in the top 200
        */
  
        sortArray(artistAndCount, artistIndex);    
        /* takes both the array made up of the artist names and number of times
        they show up in the top 200 as well as the length of that array
        */        
        
        //creates linked list called name
        artistPlaylist name = new artistPlaylist();
        
        //takes the sorted array and puts it into a linked list
        for(int i = artistIndex-1; i>= 0; i --){
            String artistName = sortArray(artistAndCount, artistIndex)[i][0];
            
            name.addNode(artistName); // adds each artist as a node to the linked list
            
        }
        
        name.displayList(); // displays the linked list of arttists
    }
  
    
    //populates a 2D array from the csv file
    public static String[][] musicChart() throws Exception{
        
        ps = new PrintStream ("results.txt"); // sending all output to a file named results
        Scanner sc = new Scanner (new File("regional-global-daily-latest-copy.txt"));
        //takes in the csv file
        
        int columns = 5;
        int rows = 200;
        
        
        String [][] myList = new String [rows][columns]; //creates a string with 5 columns, 200 rows
            
            for(int i = 0; i<myList.length; i++){   
                String line = sc.nextLine();
                String[] values = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
                /*gets rid of unecessary quotes etc 
                */
                
                
                for(int j = 0; j<myList[i].length; j++){
                
                    myList[i][j] = values[j];
                    ps.print(myList[i][j] + " "); //prints out to csv file to another file
                }
                
                
            ps.println(); 
            }
            
            ps.println();
            
            return myList; //method returns the csv file but in an organized array
    
    }
  
    /*this method takes in the array made from the csv file and counts how many
    times each artist shows up in the charts
    */
    public static String [][] artistTimes(String [][] array)throws Exception{ 
        int columns = 2;
        int rows = 200;
        int i, j, k;
        String [][] artistCount = new String[rows][columns];
        
        boolean duplicate = false;
        int numOfTimes = 1;
        int artistIndex = 0;
        
        for(j = 0; j< array.length; j++){      
            for(i = 1; i< array.length; i++){   
                
                if(array[j][2].equals(array[i][2])){
                    /*if the artist has another song in the top 200 charts, add
                    1 to the count;
                    */
                    numOfTimes++;      
                } 
            }
            
            for(k = 0; k< array.length; k++){   
                if(array[j][2].equals(artistCount[k][0])){ 
                    /*if the artists' name are the same, mark it as a duplicate.
                    */
                    duplicate = true;
                }     
            }
            
            if(duplicate == false){
                artistCount[artistIndex][0] = array[j][2];
                artistCount[artistIndex][1] = Integer.toString(numOfTimes);
                artistIndex++; //counts the number of entries in the non duplicated array
            }
                
                duplicate = false;
                numOfTimes = 0;   
                
        }  
        for(j = 0; j< artistIndex; j++){
                ps.println(artistCount[j][0]+ " appeared a total of " + artistCount[j][1] + " times.");
        }// prints to a file
        
        
        return artistCount;
            
    }
    public static int artistTimes2(String [][] array)throws Exception{ 
        int columns = 2;
        int rows = 200;
        int i, j, k;
        String [][] artistCount = new String[rows][columns];
        
        boolean duplicate = false;
        int numOfTimes = 1;
        int artistIndex = 0;
        
        for(j = 0; j< array.length; j++){
            //String artistName = array [j][2];       
            for(i = 1; i< array.length; i++){   
                
                if(array[j][2].equals(array[i][2])){
                    numOfTimes++;      
                } 
            }
            
            for(k = 0; k< array.length; k++){   
                if(array[j][2].equals(artistCount[k][0])){ //if the names are the same, mark it as a duplicate.
                    duplicate = true;
                }     
            }
            
            if(duplicate == false){
                artistCount[artistIndex][0] = array[j][2];
                artistCount[artistIndex][1] = Integer.toString(numOfTimes);
                artistIndex++;
            }
                
                duplicate = false;
                numOfTimes = 0;   
                
        }  
        return artistIndex; // returns the logical length in the non duplicated array
    }
    
    /*takes in array with artist names and count as well as logical length of that array,
    and sorts that array alphabetically
    */
    public static String [][] sortArray(String [][] array, int count)throws Exception{
        int i, j;
        
        String [][]temp = new String [1][2];
        boolean switched = true;
        
        for(i=0; i< count-1; i++){
            switched = false;
            
            for(j=0; j< count-i-1; j++){
                if(array[j][0].compareToIgnoreCase(array[j+1][0])>0){
                    switched = true;
                
                    temp[0][0] = array[j][0];
                    temp[0][1] = array[j][1];
                    
                    array[j][0] = array [j+1][0];
                    array[j][1] = array [j+1][1];
                    
                    array[j+1][0] = temp[0][0];
                    array[j+1][1] = temp[0][1];
                }
            }
        }
        
        return array;
    }

}

            

    

