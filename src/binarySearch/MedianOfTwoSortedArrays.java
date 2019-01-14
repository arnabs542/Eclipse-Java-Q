/*
 * == Created Date ==
 * Dec 16, 2018
 * 
 * == Question - Median of Two Sorted Arrays ==
 * 
 * == Notes == 
 * LeetCode 2
 * 
 */

package binarySearch;

public class MedianOfTwoSortedArrays {
	
    /* ----------------------< Brute force Solution >-------------------------
     * Time Complexity: O(m + n), m and n are the len of nums1 and nums2
     * Space Complexity: O(1)
     * 
     * */
	
    public double findMedianSortedArraysBF(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int size = nums1.length + nums2.length;
        int midCount = size % 2 == 0 ? size / 2 : size / 2 + 1;
        while (midCount >= 1) {
            if (i >= nums1.length) {
                return midInOneArr(nums2, j, midCount, size);
            }
            if (j >= nums2.length) {                
            		return midInOneArr(nums1, i, midCount, size);
            }
            if (midCount == 1) {
                if (size % 2 == 0) {
                    return midInTwoArr(nums1, i, nums2, j);
                } else {
                    return nums1[i] <= nums2[j] ? nums1[i] : nums2[j];
                }
            }
            if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                j++;
            }
            midCount--;
        }
        return - 1;
    }
    
    private double midInOneArr(int[] arr, int a, int midCount, int size) {
        while (midCount > 1) {
            a++;
            midCount--;
        }
        if (size % 2 == 0) {
        		return (arr[a] + arr[a + 1]) / 2.0;
        } else {
    			return arr[a];
        }
    }
    
    private double midInTwoArr(int[] nums1, int i, int[] nums2, int j) {
        if (nums1[i] <= nums2[j]) {
            return (nums1[i] + smallest(nums1, i + 1, nums2, j)) / 2.0;
        } else {
            return (nums2[j] + smallest(nums2, j + 1, nums1, i)) / 2.0;
        }
    }
    
    private double smallest(int[] arr1, int a, int[] arr2, int b) {
        if (a >= arr1.length) {
            return arr2[b];
        }
        if (b >= arr2.length) {
            return arr1[a];
        }
        return arr1[a] <= arr2[b] ? arr1[a] : arr2[b];
    }
    
    /* ----------------------< Binary Search Solution >-------------------------
     * Time Complexity: O( log( min(m,n) ) ), m and n are the len of nums1 and nums2
     * Space Complexity: O(1)
     * 
     * */
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    		return 0;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MedianOfTwoSortedArrays testObj = new MedianOfTwoSortedArrays();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		double res1 = testObj.findMedianSortedArraysBF(new int[] {1, 3}, new int[] {2});
		System.out.println(res1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		double res2 = testObj.findMedianSortedArraysBF(new int[] {}, new int[] {2, 3});
		System.out.println(res2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		double res3 = testObj.findMedianSortedArraysBF(new int[] {1, 3, 4}, new int[] {2, 3});
		System.out.println(res3);
		
	}
}
