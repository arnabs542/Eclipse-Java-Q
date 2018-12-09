/*
 * == Created Date ==
 * Dec 5, 2018
 * 
 * == Question - Ticket Resellers ==
 *   
 * == Notes == 
 * Airbnb Practice Coding Challenge 
 * 
 */

package heapRelated;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class TicketResellers {
	
	/*
     * Complete the 'maximumAmount' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. LONG_INTEGER k
     */

	/* ----------------------< Solution using Priority Queue >-------------------------
	 * 
	 * Time exceeds limitation
	 * 
	 */
    public static long maximumAmountUsingPriorityQueue(List<Integer> a, long k) {
    // Write your code here
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(a.size(), Collections.reverseOrder());
        long result = 0;
        for (int item : a) { //nlogn
            maxHeap.offer(item);
        }
        while (k > 0) { // k * logn
            int cur = maxHeap.poll();
            while (k > 0 && cur >= maxHeap.peek()) {
                result += cur;
                cur--;
                k--;
            }
            maxHeap.offer(cur);
        }
        return result;
    }

	/* ----------------------< Solution using extra space >-------------------------
	 * 
	 * O(n)
	 * 
	 */
    public static long maximumAmount(List<Integer> a, long k) {
    // Write your code here
        //         2   4   6   8   10
        //data 0 0 1 0 1 0 1 0 1 0 1 
        long[] data = new long[100000];
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        long res = 0;
        for (int i = 0; i < a.size(); i++) {
            data[a.get(i)]++;
            if (a.get(i) > max) {
                max = a.get(i);
                maxIndex = a.get(i);
            }
        }
        while (k > 0 && maxIndex >= 0) {
            long num = Math.min(k, data[maxIndex]);
            res += maxIndex * num;
            k -= num;
            data[maxIndex] = 0;
            data[maxIndex - 1] += num;  
            maxIndex--;
        }
        return res;
    }
    
    /* ----------------------< test stub >-------------------------*/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long k = Long.parseLong(bufferedReader.readLine().trim());

        long result = TicketResellers.maximumAmount(a, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
