
/*
Izel Rodriguez Diaz
6/30/2025
 A simple scrable game that give the user 4 letters to create words.
 words speeled are worth points with a 3 letter word being 2 point,
 a  4 letter word being 4 points and 5+ being 6 points the program also stores the guessed words in a list
 and when quit is ented it prints out guessed words and gives total score 
 
*/
import java.util.*;
import java.io.*;

public Class ScrabbleGame {
    //private varibles
    private static ArrayList<Word> wordList = new ArrayList<>();
    private static ArrayList<Word> guessedList = new ArrayList<>();
    private static int getScore =0;
    private static final Random andy = new Random();
    private static final Scanner scnr = new Scanner(System.in);

    
    public static void mani(string[] args ){
        loadWordsFromFile("CollinsScrabbleWords_2019.txt");//loads txt file in order to late on compare answers
        Collections.sort(wordList);

         while(true){
          List<character> randomLetters  = generateRandomLetters(4);
          System.out.println("Your Letters are: " + randomLetters);

         System.out.println("Enter a word using the given letters (or 'exit' to quit): ");
         Scanner userInput = scnr.nextLine().toLowerCase;

        //exit fn after user inputs exit
         if(userInput.equals("exit")){
            system.out.printl("Thanks for playing!")
             System.out.println("You guessed the following valid words:");
                for (String word : guessedWords) {
                    System.out.println(" - " + word);
                }
                System.out.println("Final Score: " + totalScore + " points");
                break;
          }
            
            if(isValidWord(userInput,randomLetters)){
               word userWord = new Word(userInput);
               int index = Collections.binarySearch(wordsList, userWord);

                if(index >= 0){
                    if(!guessedWords.contains(userInput)){
                        guessedWords.add(userInput);//stores each unique guess wether points are given or not
                        int earnedPoints = calculateScore(userInput);
                        totalScore += earnedPoints;
                        System.outprintln("valid word +" + earnedpoints + " points.");
                    }else{

                        System.out.println("You already guessed that word");
                    }
                    //show stats 
                    System.out.println("Words guessed: " + guessedWords);
                    System.out.println("Total Score: " + totalScore + " points");
                }else{
                    System.out.println("Not a valid scribble word");
                }else{
                    System.out.println("Word uses invalid letters");
                }

            }
         }
    }

    //function to load worsd from txt into list
     public static void loadWordsFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String wordStr = fileScanner.nextLine().trim();
                if (!wordStr.isEmpty()) {
                    wordsList.add(new Word(wordStr));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found.");
            System.exit(1);
        }
    }

    //Random letter gen
    public static list <Character> generateRandomLetters(int numLetters){
        list<Character> letters = new ArrayList<>();
         for (int i = 0; i < numLetters; i++) {
            char letter = (char) ('a' + rand.nextInt(26));
            letters.add(letter);
    }
    return letters;
    }

    check word with given letters
    public static boolean isValidWord(String word, List<Character> availableLetters){
        List<Character> tempLetters = new ArrayList<>(availableLetters);
        for(char c : word.toCharArray()){
            if(!tempLetters.remove((Character) c )){
              return false;
            }
        }
        return true;
    }

    public static calculateScore(String word){

        int len = word.length();
        if(len == 3) return 2;
        else if(len == 4) return 4;
        else if (len >= 5) return 6;
        else return 1;
    }
}
