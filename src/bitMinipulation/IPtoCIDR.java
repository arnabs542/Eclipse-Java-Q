/*
 * == Created Date ==
 * Jan 4, 2019
 * 
 * == Question - IP to CIDR ==
 * Given a start IP address ip and a number of ips we need to cover n, 
 *   return a representation of the range as a list (of smallest possible length) of CIDR blocks.
 * 
 * A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. 
 * For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.
 * 
 * == Example ==
 * 
 * Input: ip = "255.0.0.7", n = 10
 * 
 * Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * 
 * Explanation:
 * The initial ip address, when converted to binary, looks like this (spaces added for clarity):
 * 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
 * ie. just this one address.
 * 
 * The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * Eight addresses are with common prefix of 29 bits:
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * ...
 * ...
 * 11111111 00000000 00000000 00001111
 * 
 * The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
 * ie. just 11111111 00000000 00000000 00010000.
 * 
 * In total, the answer "255.0.0.7/32","255.0.0.8/29","255.0.0.16/32" specifies the range of 10 ips starting with the address 255.0.0.7 .
 * 
 * There were other representations, such as:
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * but our answer was the shortest possible.
 * 
 * Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
 * because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100 
 * that are outside the specified range.
 * 
 * == Notes == 
 * LeetCode 751* (E++)
 * 
 */

package bitMinipulation;

import java.util.ArrayList;
import java.util.List;

public class IPtoCIDR {
	
	/* ----------------------< Syntax I >-------------------------*/
    public List<String> ipToCidr(String ip, int n) {
        long start = ipToLong(ip);
        List<String> result = new ArrayList<>();
        while (n > 0) {
        		// lowestOneBit() 最低有效位
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                                33 - bitLength(n));
            result.add(longToIP(start) + "/" + mask);
            
            long numOfIpToCover = 1 << (32 - mask);
            start += numOfIpToCover;
            n -= numOfIpToCover;
        }
        return result;
    }
    
    private long ipToLong(String ip) {
        long result = 0;
        for (String x: ip.split("\\.")) {
        		result = 256 * result + Integer.valueOf(x);
        }
        return result;
    }
    
    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
            x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    
    private int bitLength(long x) { 
    		long bit = x;
        if (x == 0) {
        		return 1;
        }
        int result = 0;
        while (x > 0) {
            x >>= 1;
            result++;
        }
        System.out.println("bitLength of " + bit + ": " + result);
        return result;
    }
    
    /* ----------------------< Syntax II >-------------------------*/
    public List<String> ipToCidrII(String ip, int n) {
        long x = 0;
        String[] ips = ip.split("\\."); //获得一个ip地址每一部分
        for (int i = 0; i < ips.length; ++i) { //将整ip地址看为一个整体，求出整体的int表示
            x = Integer.parseInt(ips[i]) + x * 256;
        }
        
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            
    			// 求得该IP用32位二进制表示中从右往左首次出现1的位置 (-x才是x的补码，~x为反码),
        		// 即：二进制表示下的最低有效位的位数能表示的地址的数量
        		//
        		//temp如果为奇数，如果为奇数，则=1，该IP为第一个CIDR块
        		//如果偶数，则该IP用二进制表示下的最低有效位的位数能表示的地址的数量
            long step = x & -x;
            
            //如果大于range，则需要缩小范围
            while (step > n) {
                step /= 2;
            }
            //不大于需要的range，开始处理，step 表示包括此IP在内的step个IPs可以用一个ICDR来表示
            //接下来求出这些IPs所处的CIDR
            ans.add(longToIP(x, (int)step));
            
            //x加上以求出的地址块
            x += step;
            
            //range减去以表示的地址块
            n -= step;
        }//直到range<0
        return ans;
    }
    
    static String longToIP(long x, int step) {
        int[] ans = new int[4];
        
        //&255操作求出后8位十进制表示
        ans[0] = (int) (x & 255);
        
        //右移8位，即求下一个块
        x >>= 8;
        ans[1] = (int) (x & 255);
        x >>= 8;
        ans[2] = (int) (x & 255);
        x >>= 8;
        ans[3] = (int) x;
        
        int len = 33;
        
        //每一位就可以表示2个
        while (step > 0) {
            len--;
            step /= 2;
        }
        return  ans[3] + "." + ans[2] + "." + ans[1] + "." + ans[0] + "/" + len;
    }
    
	/* ----------------------< test stub >-------------------------*/
    
    public static void print(List<String> result) {
    		System.out.println();
    		for (String cidr : result) {
    			System.out.println(cidr);
    		}
    		System.out.println();
    }
	public static void main(String[] args) {
		
		IPtoCIDR testObj = new IPtoCIDR();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		// IP TO LONG
		String ip = "255.0.0.7";
		long longValueOfIp = 0;
        for (String str: ip.split("\\.")) {
        		longValueOfIp = 256 * longValueOfIp + Integer.valueOf(str);
        }
       
        System.out.println(longValueOfIp);
        System.out.println(Long.lowestOneBit(longValueOfIp));
        
        // LONG TO IP 
        System.out.println("- LONG TO IP  -");
        System.out.println(longValueOfIp >> 24); // 255
        System.out.println(longValueOfIp % 256); // 7
        
		ip = String.format("%s.%s.%s.%s",
				longValueOfIp >> 24, (longValueOfIp >> 16) % 256, (longValueOfIp >> 8) % 256, longValueOfIp % 256);
		
		System.out.println(ip);
		
		testObj.bitLength(4);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		print(testObj.ipToCidr(ip, 10));
		
		print(testObj.ipToCidrII(ip, 10));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		ip = "255.0.0.8";
		print(testObj.ipToCidr(ip, 12));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
}
