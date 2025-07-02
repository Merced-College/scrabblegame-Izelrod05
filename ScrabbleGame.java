
/*
Izel Rodriguez Diaz
6/30/2025
 A simple scrable game that give the user 4 letters to create words.
 words speeled are worth points with a 3 letter word being 2 point,
 a  4 letter word being 4 points and 5+ being 6 points the program also stores the guessed words in a list
 and when quit is ented it prints out guessed words and gives total score 
 
*/
import java.io.*;
import java.util.*;

public class ScrabbleGame {
    //private varibles
    private static ArrayList<Word> wordList = new ArrayList<>();
    private static List<String> guessedList = new ArrayList<>();
    private static int totalScore =0;
    private static final Random rand = new Random();
    private static final Scanner scnr = new Scanner(System.in);

    
    public static void main(String[] args ){
        loadWordsFromFile("CollinsScrabbleWords_2019.txt");//loads txt file in order to late on compare answers
        Collections.sort(wordList);

         while(true){
          List<Character> randomLetters  = generateRandomLetters(4);
          System.out.println("Your Letters are: " + randomLetters);

         System.out.println("Enter a word using the given letters (or 'exit' to quit): ");
         String userInput = scnr.nextLine().toLowerCase();

        //exit fn after user inputs exit
         if(userInput.equals("exit")){
            System.out.println("Thanks for playing!");
             System.out.println("You guessed the following valid words:");
                for (String word : guessedList) {
                    System.out.println(" - " + word);
                }
                System.out.println("Final Score: " + totalScore + " points");
                break;
          }
            
            if(isValidWord(userInput,randomLetters)){
               Word userWord = new Word(userInput);
               int index = Collections.binarySearch(wordList, userWord);

                if(index >= 0){
                    if(!guessedList.contains(userInput)){
                        guessedList.add(userInput);//stores each unique guess wether points are given or not
                        int earnedPoints = calculateScore(userInput);
                        totalScore += earnedPoints;
                        System.out.println("valid word +" + earnedPoints + " points.");
                    }else{

                        System.out.println("You already guessed that word");
                    }
                    //show stats 
                    System.out.println("Words guessed: " + guessedList);
                    System.out.println("Total Score: " + totalScore + " points");
                }
                else{
                    System.out.println("Not a valid scribble word or Word uses invalid letters");
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
                    wordList.add(new Word(wordStr));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found.");
            System.exit(1);
        }
    }

    //Random letter gen
    public static List<Character> generateRandomLetters(int numLetters){
        List<Character> letters = new ArrayList<>();
         for (int i = 0; i < numLetters; i++) {
            char letter = (char) ('a' + rand.nextInt(26));
            letters.add(letter);
    }
    return letters;
    }

   // check word with given letters
    public static boolean isValidWord(String word, List<Character> availableLetters){
        List<Character> tempLetters = new ArrayList<>(availableLetters);
        for(char c : word.toCharArray()){
            if(!tempLetters.remove((Character) c )){
              return false;
            }
        }
        return true;
    }

    public static int calculateScore(String word){

        int len = word.length();
        if(len == 3) return 2;
        else if(len == 4) return 4;
        else if (len >= 5) return 6;
        else return 1;
    }
}
