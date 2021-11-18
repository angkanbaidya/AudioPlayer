import java.util.Scanner;
//Angkan Baidya
//112309655
// Recitation 01

public class Player {  /*** This main class is the driver and allows the playlist to work **/
    static String userInput;
    static boolean keepGoing;
    static Scanner keyboard;
    static SongLinkedList a = new SongLinkedList();

    public static void main(String[] args)
    {



    }


    private static void  runMainMenu() { /*** This method displays all the main menu options.***/

        System.out.println("\n*** SELECT A MENU OPTION ***");
        System.out.println("A) Add Song to Playlist");
        System.out.println("F) Go to next song");
        System.out.println("B) Go to previous song");
        System.out.println("R) Remove song from playlist ");
        System.out.println("L) Play song");
        System.out.println("C) Clear the playlist");
        System.out.println("S) Shuffle playlist");
        System.out.println("Z) Random Song");
        System.out.println("P) Print Playlist");
        System.out.println("T) Get size");
        System.out.println("Q) Quit");
        userInput = keyboard.nextLine();
    }

    private static  void processMainMenuInput() /*** If the user presses the letter for a specific option then the code will
     run the specific method for that option.***/
    {
        // 1)
        if (userInput.toUpperCase().equals("A"))
        {
            runAddtoPlaylist();
        }
        // 2)
        else if (userInput.toUpperCase().equals("R"))
        {
            runRemoveSong();
        }
        // Q or q
        else if (userInput.toUpperCase().equals("B"))
        {
            runPreviousSong();
        }

        else if (userInput.toUpperCase().equals("F")){
            runNextSong();
        }

        else if (userInput.toUpperCase().equals("T")){
            runGetSize();
        }

        else if (userInput.toUpperCase().equals("C")){

            runDeleteAll();

        }

       else if (userInput.toUpperCase().equals("S")){
           runShufflePlaylist();

        }

        else if (userInput.toUpperCase().equals("Z")){
            runRandomSong();
        }
        else if (userInput.toUpperCase().equals("Q"))
        {
            System.out.println("\nGoodbye\n");
            keepGoing = false;
        }

        else if( userInput.toUpperCase().equals("P"))
        {
            runPrintPlaylist();
        }

       else if( userInput.toUpperCase().equals("L"))
        {
            runPlaySong();
        }
        else
        {
            System.out.println("\nINVALID INPUT - Please enter a menu choice\n");
        }
    }

    private static void runAddtoPlaylist(){  /*** This method asks the user what the name of the song, who the artist is,
                                                etc and compiles all that data into a song object **/
        System.out.println("Enter Song title:");
        userInput = keyboard.nextLine();
        String title = userInput;
        System.out.println("Enter artist of the song:");
        String artist = keyboard.nextLine();
        System.out.println("Enter album:");
        String album = keyboard.nextLine();
        System.out.println("Enter length (in seconds)");
        int length = keyboard.nextInt();
        keyboard.nextLine();
        Song newsong  = new Song(title,artist,album,length);
        a.insertAfterCursor(newsong);
        System.out.println(title + " " + "by" + " " + artist + " " + "has been added to the playlist");
    }

    private static void runRemoveSong(){   /*** This method calls on upon the instance of SongLinkedList and the remove at
                                                cursor method. **/
        a.removeatCursor();

    }

    private static void runPreviousSong(){  /*** This method calls on upon the instance of SongLinkedList and the
                                                cursorBackwards method, which moves the cursor one node back **/
        a.cursorBackwards();
    }

    private static  void runNextSong(){     /*** This method calls on upon the instance of SongLinkedList and the
                                            cursorForwards method, which moves the cursor one node front **/
       a.cursorForwards();
    }


    private static int runGetSize(){       /*** This method gets the total size of the playlist by calling upon the
                                                getSize method and returns the total number of songs in the playlist.**/
       int total = a.getSize();
       return total;

    }

    private static void runPrintPlaylist(){      /*** This method calls upon the printPlaylist method and prints the
                                                        current playlist **/
        a.printPlaylist();



    }

    private static void runShufflePlaylist(){     /*** This method calls upon the shuffle method and shuffles the
                                                    playlist and tells the users the playlist has been shuffled **/
        a.shuffle();
        System.out.println("The playlist has been shuffled.");
    }

    private static void runRandomSong(){     /*** This method calls upon the random method and plays a random song.**/
        a.random();
    }   /*** This method plays a random song in the playlist **/

    private static void runDeleteAll(){   /*** This method calls upon the method that deletes the whole playlist **/
        a.deleteAll();
        int random = 5;
        int b = random+random+(random++)*2;
        System.out.println(b);
    }

    private static void runPlaySong(){      /*** This method asks the user what song they want to play and sends the name
                                                into the play method which then verifies the song and plays it. **/
        System.out.println("Enter a name of a song to play:");
        String song = keyboard.nextLine();
        a.play(song);

    }




}
