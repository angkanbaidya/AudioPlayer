//Angkan Baidya
//112309655
// Recitation 01

import sun.audio.AudioPlayer;

import java.util.Random;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;
import java.io.File;

public class SongLinkedList { /*** This class implements a doubly linked list which acts as a playlist. It will reference
                                the head and tails and cursor. **/
    private SongNode head;
    private SongNode tail;
    private int length;
    private SongNode cursor;



    public SongLinkedList(){   /*** This method instantiates an empty linked list with no objects in it
                                    Everything is set to null **/
        this.head = null;
        this.tail = null;
        this.length = 0;
        this.cursor = null;

    }


    public boolean isEmpty(){ /*** This method checks if the list is empty and returns 0 if it is **/
        return length == 0;
    }
    public int getSize(){ /*** This method gets the size of the doubly linked list. It runs at O(1) **/
        if(length == 0){
            System.out.println("The playlist is empty.");
        }
        else {
            System.out.println("There is a total of" + " " + length + " " + "songs in your playlist.");
        }
        return length;

    }


    public void insertAfterCursor(Song newSong) throws IllegalArgumentException {
                                                            /*** This method inserts a new song of the users choice by
                                                                 adding it after the cursor. It makes sure that if a song
                                                                the user wants to add is null then it throws an illegal
                                                                argument exception. **/

        try {
            SongNode newNode = new SongNode(newSong);

            if (head == null) {
                head = newNode;
                cursor = newNode;
            } else {
                if (cursor.getNext() == null) {
                    newNode.setPrev(cursor);
                    cursor.setNext(newNode);
                } else {
                    newNode.setPrev(cursor);
                    cursor.getNext().setPrev(newNode);
                    newNode.setNext(cursor.getNext());
                    cursor.setNext(newNode);
                }

                cursor = newNode;
            }
            length++;
        }
        catch (Exception ex) {System.out.println("The selected song is null");}


    }

    public Song removeatCursor() {  /*** This method removes the current node referenced by the cursor. If removed the
                                        cursor will now reference the node after the previous node.**/

        length--;
        if(cursor == head)
        {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            Song song = cursor.getData();
            cursor = cursor.getNext();
            head = cursor;
            System.out.println(song.getName() + " " + "by" + " " +song.getArtist() + " " + "was removed from your playlist.");
            return song;
        }
        else if (cursor.getNext() == null){
            Song song = cursor.getData();
            cursor = cursor.getPrev();
            cursor.setNext(null);
            System.out.println(song.getName() + " " + "by" + " " +song.getArtist() + " " + "was removed from your playlist.");
            return song;
        }
        else
        {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            Song song = cursor.getData();
            cursor = cursor.getNext();
            System.out.println(song.getName() + " " + "by" + " " +song.getArtist() + " " + "was removed from your playlist.");
            return song;

        }
    }

    public void cursorForwards(){   /*** This method moves the cursor to the next node **/
        SongNode next = cursor.getNext();
        if (next != null) {
            cursor = next;
            System.out.println("Cursor moved to next song.");
        }
    }

    public void cursorBackwards(){ /*** This method moves the cursor to the previous node and makes sure that if the the
                                        head is the current node then it won't give an exception **/
        SongNode before = cursor.getPrev();
        if (before != null){
            cursor = before;
            System.out.println("Cursor moved to previous song.");
        }

        else{
            System.out.println("Already at the beginning of the playlist.");
        }
    }

    public Song random(){  /*** This method selects a random song and plays it. It generates a random number and makes
                                the cursor go to the random node by the random number and plays the song in that node. **/


        int j = 0;
        Random rand = new Random();
        int i = rand.nextInt(length);
        SongNode current = head;
        Song testsong = current.getData();

        while(i != j) {
            current = current.getNext();
            j++;
            testsong = current.getData();

        }
        System.out.println("Playing a random song");
        play(testsong.getName());
        return testsong;

    }

    public Song removeRandomSong()  /*** This method removes a random song from the playlist. It calls upon the random
                                        method and removes the song at that random number generated by rand.nextInt.
                                        It does error checking to make sure that if the current cursor is pointing to
                                        the head, then there are no exceptions. **/
    {
        Random rand = new Random();
        int i = rand.nextInt(length);
        int j = 0;
        SongNode current = head;

        while(i != j) {
            current = current.getNext();
            j++;
        }

        length--;
        if(current == head) {
            Song song = head.getData();
            head = head.getNext();
            return song;

        }
        else if (current.getNext() == null){
            Song song = current.getData();
            current.getPrev().setNext(null);
            return song;
        }
        else
        {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            Song song = cursor.getData();
            return song;
        }

    }

    public void shuffle()  /*** This method shuffles the playlist. This method calls upon the remove random method and adds
                                those randomly removed songs to a brand new temporary doubly linked list. Once added the
                                cursor is also copied thus shuffling the playlist. **/
    {
        Song orgCursor = cursor.getData();
        SongLinkedList temp = new SongLinkedList();
        while(head != null)
        {
            Song removed = removeRandomSong();
            temp.insertAfterCursor(removed);
        }

        head = temp.head;
        length = temp.length;
        SongNode current = head;
      while(current != null)
      {
          if(current.getData() == orgCursor) {
               cursor = current;
               break;
           }
       }

    }

    public void play(String name)throws IllegalArgumentException{  /*** This method plays a song specified by the user.
                                                                It throws an error if the user plays a song that is not
                                                                in the playlist. It finds the files that are in the
                                                                directory. **/
        try {

            AudioInputStream AIS = AudioSystem.getAudioInputStream(

                    new File(name + ".wav"));

            Clip c = AudioSystem.getClip();

            c.open(AIS);
            c.start();
            String artist = findData(name);
            System.out.println(name + " " + "by" + " " + artist + " " + "is now playing.");


        }


        catch (Exception ex) {System.out.println("The selected song could not be found.");}
    }

    public String findData(String name){       /*** This method finds the artist of a specific song based on the name of
                                                    the song the user inputs. This helps the playlist show which artist
                                                    each song is by when it is being played. **/
        SongNode current = head;
        int i = 0;
        String artist = "";
        while(i < length ) {
            if (current.getData().getName().equals(name) ){
                artist = current.getData().getArtist();
                return artist;
            }
            else{
            current = current.getNext();
            i++;
        }}
    return artist;
    }

    public void printPlaylist(){      /*** This method prints the playlist in a neat tabular format **/
        SongNode current = head;
        if(head == null) {
            System.out.println("Playlist:");
            System.out.printf( "%-25s%-25s%25s%25s", "Song", "Artist", "Album", "Length (s)");
            System.out.print("\n");
            System.out.println("---------------------------------------------------------------------------------------------------------");

        }
        else{
        System.out.printf( "%-25s%-25s%25s%25s", "Song", "Artist", "Album", "Length (s)");
        System.out.print("\n");
            System.out.println("---------------------------------------------------------------------------------------------------------");
        while(current != null) {
            if (current == cursor)
                System.out.printf("%n%-25s%-25s%25s%25s",current.getData().getName(),current.getData().getArtist(),current.getData().getAlbum(),current.getData().getLength() +  "<- ");
            else
                System.out.printf("%n%-25s%-25s%25s%25s",current.getData().getName(),current.getData().getArtist(),current.getData().getAlbum(),current.getData().getLength());

            current = current.getNext();
        }}
    }

    public void deleteAll(){   /*** This method completely clears the playlist by setting the head to null **/
        this.head = null;
        length = 0;
        System.out.println("The playlist has been cleared.");

    }
}