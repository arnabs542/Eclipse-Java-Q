/*
 * == Created Date ==
 * Dec 16, 2018
 * 
 * == Question - Valid Number==
 *      
 * == Notes == 
 * LeetCode 65, Hard
 * 
 */

package arrayRelated;

public class ValidNumber {

    /*
    
    SPC::=' '
    NUM::='0'|'1'|'2'|...|'9'
    DOT::='.'
    NUMERIC::=
    
    (SPC*)
    ['+'|'-'] (NUM+) [DOT(NUM+)] [ ('E'|'e') (['+'|'-'] (NUM+)) ] 
    (SPC*)
    
    clarification:
    1. What are the possible characters in the string?
    2. What is the requirement for space?
    
    3. What is the requirement for sign? +10e-2
    
       - It should be the first char before E or the first char after E
       - invalid cases:
         + not the first char -> (seenDot || seenNumber) && !seenE
         + duplicated signs before E (e.g. ++10e) -> !seenE && seenSignBeforeE
         + duplicated signs after E (e.g. 6e--1) -> seenSignAfterE
         
    4. What is the requirement for dot?
    5. What is the requirement for e/E?
    
    */
    
    public boolean isNumber(String s) {
        String str = s.trim();
        boolean seenNumber = false;
        boolean seenSignBeforeE = false;
        boolean seenE = false;
        boolean seenDot = false;
        boolean seenNumberAfterE = false;
        boolean seenSignAfterE = false;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                
                // case: sign is not the first char before E or the first char after E
                if ((seenDot || seenNumber) && !seenE || seenNumberAfterE) {
                    return false;
                }
                
                // case: duplicated signs after E or duplicated signs before E
                if (seenSignAfterE || (!seenE && seenSignBeforeE)) {
                    return false;
                }
                
                if (seenE) {
                    seenSignAfterE = true;
                } else {
                    seenSignBeforeE = true;
                }
            } else if (c >= '0' && c <= '9') {
                seenNumber = true;
                if (seenE) {
                    seenNumberAfterE = true;
                }
            } else if (c == 'e' || c == 'E') {
                if (seenE) {
                    return false;
                }
                if (!seenNumber) {
                    return false;
                }
                seenE = true;
            } else if (c == '.') {
                if (seenE || seenDot) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }
        
        if (seenE && !seenNumberAfterE) { // "0e"
            return false;
        }
        return seenNumber;
    }
	
}
