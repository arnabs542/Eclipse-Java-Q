/*
 * == Created Date ==
 * Dec 5, 2018
 * 
 * == Question - Final Discounted Price ==
 *   
 * == Notes == 
 * Airbnb Practice Coding Challenge 1
 * 
 */

package queueStackRelated;


import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class FinalDiscountedPrice {
	
    /*
     * Complete the 'finalPrice' function below.
     *
     * The function accepts INTEGER_ARRAY prices as parameter.
     */

    public static void finalPriceBruteForce(List<Integer> prices) {
    // Write your code here
        if (prices == null || prices.size() == 0) {
            System.out.println(0);
            return;
        }
        if (prices.size() == 1) {
            System.out.println(prices.get(0));
            System.out.println(0);
            return;
        }
        List<Integer> noDiscountItems = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < prices.size(); i++) {
            int first = i + 1;
            while (first < prices.size() && prices.get(first) > prices.get(i)) {
                first++;
            }
            if (first == prices.size()) {
                total += prices.get(i);
                noDiscountItems.add(i);
            } else {
                total += prices.get(i) - prices.get(first);             
            }
        }
        System.out.println(total);
        for (int item : noDiscountItems) {
            System.out.print(item + " ");
        }
    }
    
    public static void finalPrice(List<Integer> prices) {
    		
    }
    
    /* ----------------------< test stub >-------------------------*/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int pricesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> prices = IntStream.range(0, pricesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        finalPrice(prices);

        bufferedReader.close();
    }
}


