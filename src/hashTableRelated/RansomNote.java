/*
 * == Created Date ==
 * September 19, 2018
 * 
 * == Question - Ransom Note (easy) ==
 * Given the words in the magazine and the words in the ransom note, 
 * print Yes if he can replicate his ransom note exactly using whole words from the magazine; otherwise, print No.
 *    
 * == Input Format ==
 * The first line contains two space-separated integers, m and n, the numbers of words in the magazine and the note. 
 * The second line contains m space-separated strings, each magazine[i] 
 * The third line contains n space-separated strings, each note[i]
 * 
 * == Example ==
 * 
 * Sample Input 0
 * 
 * 6 4
 * give me one grand today night
 * give one grand today
 * 
 * Sample Output 0 Yes
 *   
 * == Notes == 
 *  
 * 
 */

package hashTableRelated;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {
	
    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magazineDic = new HashMap<>();
        for (String s : magazine) {
            if (magazineDic.containsKey(s)) {
                int count = magazineDic.get(s);
                magazineDic.put(s, ++count);
            } else {
                magazineDic.put(s, 1);
            }
        }
        for (String word : note) {
            if (!magazineDic.containsKey(word)) {
                System.out.print("No");
                return;
            } else {
                if (magazineDic.get(word) == 1) {
                    magazineDic.remove(word);
                } else {
                    int count = magazineDic.get(word);
                    magazineDic.put(word, --count);
                }             
            }
        }
        System.out.print("Yes");
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];
        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }

}
