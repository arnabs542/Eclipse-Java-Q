/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - File System ==
 * There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizards and wizard as square of different of I and j. 
 * To find the min cost between 0 and 9.
 * 
 * == Example == 
 * wizard[0] list: 1, 4, 5
 * wizard[4] list: 9
 *   
 * wizard 0 to 9 min distance is (0-4) ^ 2 + (4-9) ^ 2 = 41 (wizard[0]->wizard[4]->wizard[9])]
 * 
 * == Notes == 
 * Airbnb Interview Problem
 * 
 */

package hashTableRelated;

import java.util.HashMap;
import java.util.Map;

public class FileSystemAirbnb {
	
    Map<String, Integer> map;
    Map<String, Runnable> callbackMap;
    
    public FileSystemAirbnb() {
        this.map = new HashMap<>();
        map.put("", 0);
        this.callbackMap = new HashMap<>();
    }
    
    public boolean create(String key, int value) {
        if (map.containsKey(key)) {
            return false;
        }
        String prefix = key.substring(0, key.lastIndexOf("/"));
        if (!map.containsKey(prefix)) {
            return false;
        }
        map.put(key, value);
        return true;
    }
    
    public boolean set(String key, int value) {
        if (!map.containsKey(key)) {
            return false;
        }
        map.put(key, value);
        String curt = key;
        while (curt.length() > 0) {
            if (callbackMap.containsKey(curt)) {
                callbackMap.get(curt).run();
            }
            curt = curt.substring(0, curt.lastIndexOf("/"));
        }
        return true;
    }
     
    public int get(String key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }
    
    public void watch(String path, String alert) {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println(alert);
            }
        };
        callbackMap.put(path, runnable);
    }
}
