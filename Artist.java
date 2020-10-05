/**
 *
 * @author Esther Cortez
 * @since 9/28/2020
 * @version 1.0
 */
package topmusicartists;

import static topmusicartists.TopMusicArtists.ps;

// node of an artist
class Artist {
    public String artistName;
    public Artist next;


    //constructor of Artist
    public Artist(String artistName) {
        this.artistName = artistName;      
        next = null;
        
    }
    
    //displays the artist and their info
    public void displayArtist() throws Exception{
        ps.println(artistName);
    }
    
}
//a linked list of Artist objects            
class artistPlaylist extends TopMusicArtists{
    
    private Artist first; //link to the first element
    private int linkedListSize; // will track size of linkedList
    
    //constructor, sets first to null
    public void artistPlaylist(){
    first= null;
    linkedListSize = 0;
    }
    
    //returns true if list is empty
    public boolean isEmpty(){
        return (first == null);
        
    }
    //adds a node to the linked list
    public void addNode(String artistName){
        Artist newLink = new Artist(artistName); // creates new link
        newLink.next = first; //changes the new link's next reference
        first = newLink; // references the new link
        linkedListSize++; //adds to count size of link
        
    }
    
    //deletes first link
    public Artist deletefirst(){
        Artist temp = first;
        first = first.next;
        return temp; //returns deleted link
        
    }
    
    //goes through linked list and displays each element
    public void displayList() throws Exception{
        Artist current = first;
        
        ps.println();
        while (current != null){
            current.displayArtist();
            current = current.next;
        }
        ps.println(" ");
        
    }
}

