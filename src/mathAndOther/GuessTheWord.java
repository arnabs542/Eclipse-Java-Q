/*
 * == Created Date ==
 * Jan 12, 2019
 * 
 * == Question - Guess The Word ==
 * 
 * == Notes == 
 * LeetCode 843(H)
 * 
 */

package mathAndOther;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessTheWord {
	
	 // This is the Master's API interface.
	 // You should not implement it, or speculate about its implementation
	 interface Master {
	     public int guess(String word);
	 }

	/* ----- < Method 1 - Pick Random word > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; ++i) {
            // randomly select a word from the current wordlist
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            int matchesNum = master.guess(guess);
            
            // narrow the range of the wordlist using the guess feedback
            List<String> newWordlist = new ArrayList<>();
            for (String word : wordlist) {
                if (match(guess, word) == matchesNum) {
                    newWordlist.add(word);
                }
            }
            wordlist = newWordlist.toArray(new String[newWordlist.size()]);
        }
    }

    public int match(String a, String b) {
        int matchesNum = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) == b.charAt(i)) {
                matchesNum++;
            }
        }
        return matchesNum;
    }
    
	/* ----- < Method 2 - minimax approach > -----
	 * Time Complexity: O(N^2);
	 * Space Complexity: O(N);
	 * 
	 * */
    public void findSecretWordII(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) {
        		// key: word; value: the frequency that it has zero mathes with other words in the wordList
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist) {
                for (String w2 : wordlist) {
                    if (match(w1, w2) == 0) {
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
                    }
                }
            }

            String minimax = "";
            int minZeroMatches = Integer.MAX_VALUE;
            
            // Find the word that has minimun zero matches with other words
            for (String word : wordlist) {
                if (count.getOrDefault(word, 0) < minZeroMatches) {
                    minZeroMatches = count.getOrDefault(word, 0);
                    minimax = word;
                }
            }
                
            // Use the word as guess word
            int matchesNum = master.guess(minimax);
            
            // Narrow the range of the wordList using the guess feedback
            List<String> newWordlist = new ArrayList<String>();
            for (String word : wordlist) {
                if (match(minimax, word) == matchesNum) {
                    newWordlist.add(word);
                }
            }
            wordlist = newWordlist.toArray(new String[0]);
        }
    }

}
