/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - File System ==
 * 
 * Write a file system class, which has two functions: create, and get
 * 
 * create("/a",1)
 * get("/a") //get 1
 * create("/a/b",2)
 * get("/a/b") //get 2
 * create("/c/d",1) //Error, because "/c" is not existed
 * get("/c") //Error, because "/c" is not existed
 * 
 * == Assumptions need to clarify with the interviewee:
 * - should we store the root path "/" ?
 * - are the format of input paths are valid ?
 * - what's the return types
 * 
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
	
    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;
    
    public FileSystemAirbnb() {
        this.pathMap = new HashMap<>();
        pathMap.put("", 0);
        
        this.callbackMap = new HashMap<>();
    }
    
    public boolean create(String key, int value) {
        if (pathMap.containsKey(key)) {
            return false;
        }
        String prefix = key.substring(0, key.lastIndexOf("/"));
        System.out.println("prefix: " + prefix);
        if (!pathMap.containsKey(prefix)) {
            return false;
        }
        pathMap.put(key, value);
        return true;
    }
    
    public boolean set(String key, int value) {
        if (!pathMap.containsKey(key)) {
            return false;
        }
        pathMap.put(key, value);
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
        if (!pathMap.containsKey(key)) {
            return -1; // throw new java.util.NoSuchElementException();
        }
        return pathMap.get(key);
    }
    
    public void watch(String path, String alert) {
        Runnable runnable = new Runnable() {
	        public void run() {
	            	System.out.println(alert);
	        }
        };
        callbackMap.put(path, runnable);
    }
    
    /*
     * 
     *   Map<String, Integer> pathMap;
  Map<String, Runnable> callbackMap;
  
  
  public Solution() {
    this.pathMap = new HashMap<>();
    pathMap.put("", 0);
    this.callbackMap = new HashMap<>();
  }

  public boolean create(String key, int value) {
    if (pathMap.containsKey(key)) {
      return false;
    }
    int lastSlashIdx = key.lastIndexOf("/");
    String prefix = key.substring(0, lastSlashIdx);
    if (!pathMap.containsKey(prefix)) {
      return false;
    }
    pathMap.put(key, value);
    return true;
  }
    
  public Integer get_value(String path) {
    if (!pathMap.containsKey(path)) {
      return null;
    }
    return pathMap.get(path);
  }
        
  
  public boolean set_value(String path, int value) {
    if (!pathMap.containsKey(path)) {
      return false;
    }
    pathMap.put(path, value);
    
    String allPaths = path;
    while (allPaths.length() > 0) {
      if (callbackMap.containsKey(allPaths)) {
          callbackMap.get(allPaths).run();
      }
      allPaths = allPaths.substring(0, allPaths.lastIndexOf("/"));
    }
    return true;
  }

  public void watch(String path, String message) {
    Runnable runnable = new Runnable() {
      public void run() {
        System.out.println(message);
      }
    };
    callbackMap.put(path, runnable);
  }
    
  public static void main(String[] args) {
    Solution zooKeeper = new Solution();
    System.out.println(zooKeeper.create("/app1", 0));
    System.out.println(zooKeeper.create("/app1", 0));
    
    System.out.println(zooKeeper.create("/app2/p1", 1));
    System.out.println(zooKeeper.create("/app1/p1", 1));
    
    System.out.println(zooKeeper.get_value("/app1"));
    System.out.println(zooKeeper.get_value("/app2"));
    System.out.println(zooKeeper.get_value("/app1/p1"));
    
    System.out.println(zooKeeper.set_value("/app1", 3));
    System.out.println(zooKeeper.get_value("/app1"));
    
    System.out.println(zooKeeper.set_value("/app2", 3));
    
    zooKeeper.watch("/app1", "callback from app1");
    System.out.println(zooKeeper.set_value("/app1", 4));
    
    zooKeeper.watch("/app1/p1", "callback from p1");
    System.out.println(zooKeeper.set_value("/app1/p1", 5));
    
  }
     * */
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		FileSystemAirbnb testObj = new FileSystemAirbnb();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
        
		testObj.create("/a", 1);
        System.out.println(testObj.get("/a"));
        
        testObj.create("/a/b", 2);
        System.out.println(testObj.get("/a/b"));
        
        testObj.create("/a/b/c", 3);
        System.out.println(testObj.get("/a/b/c"));
        
        testObj.create("/c/d", 4);
        System.out.println(testObj.get("/c"));
        
        
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
