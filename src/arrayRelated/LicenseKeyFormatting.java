/*
 * == Created Date ==
 * Jan 12, 2019
 * 
 * == Question - License Key Formatting ==
 * 
 *   
 * == Example == 
 *   
 * == Notes == 
 * LeetCode 482 (E)
 * Google OA Test Question 
 * 
 */

package arrayRelated;

public class LicenseKeyFormatting {
	public String formatKeyString(String S, int K) {
        // Replace all - 
        String S1 = S.replace("-","");
        
        StringBuilder sb = new StringBuilder(S1);
        int len = sb.length();
        
        // Inserting '-' from back at every K position
        for (int i = K; i < len; i += K) {
            sb.insert(len - i,'-');
            System.out.println(sb.toString().toUpperCase());
        }
        return sb.toString().toUpperCase(); 
	}
	
    /* K = 3
     * 
     * len = 8
     * 
     * 01234567
     * ABCDEFGH
     * ABCDE-FGH (Insert at len - 3 = 5) 
     * 
     * 012345678
     * ABCDE-FGH
     * 
     * AB-CDE-FGH (Insert at len - 6 = 2) 
     * 
     * 
     * */
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LicenseKeyFormatting testObj = new LicenseKeyFormatting();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.formatKeyString("A-Bcdef-gh", 3));
		
		testObj.formatKeyString("A", 1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
