/*
 * == Created Date ==
 * Dec 19, 2018
 * 
 * == Question - Best Time to Buy and Sell Stock ==
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), 
 *   design an algorithm to find the maximum profit.
 *   
 * Note that you cannot sell a stock before you buy one. 
 *   
 * == Example == 
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * 
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * 			    Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * == Notes == 
 * LeetCode 121 - Easy
 * 
 */
package dynamicProgramming;

public class BuyStock {

	/* ----------------------< DP Solution >-------------------------
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		    
		// Lowest price up to i-th day
		int[] minPrice = new int[prices.length]; 
		minPrice[0] = prices[0];
		    
		// Max profit up to i-th day
	    // maxProfit[i] = max(maxProfit[i - 1], current price - minPrice[i])
	    int[] maxProfit = new int[prices.length]; 
	    maxProfit[0] = 0;
		    
	    for (int i = 1; i < prices.length; i++) {
	    		minPrice[i] = Math.min(minPrice[i - 1], prices[i]);
	    		maxProfit[i] = Math.max(maxProfit[i - 1], prices[i] - minPrice[i - 1]);
		}
		return maxProfit[prices.length - 1];
	}
	  
	/* ----------------------<  DP Solution, space optimization >-------------------------
	 * Time Complexity: O(n);
	 * Space Complexity: O(1);
	 * 
	 * In the first solution, we only care about the latest mininum price, and the latest max profix,
	 * we don't need two array to record them.
	 * 
	 * */
    public int maxProfitOptimization(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                res = Math.max(res, price - minPrice);
            }
        }
        return res;
    }
    
    // TODO: Solution using Max Subarray
    // source : https://www.youtube.com/watch?v=8pVhUpF1INw
    
}
