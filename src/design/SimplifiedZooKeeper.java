/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - Simplified ZooKeeper ==
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
 * == Notes == 
 * Airbnb Interview Problem
 * 
 */

package design;

import java.util.HashMap;
import java.util.Map;

public class SimplifiedZooKeeper {
	
    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;
    
    public SimplifiedZooKeeper() {
        this.pathMap = new HashMap<>();
        pathMap.put("", 0);
        
        this.callbackMap = new HashMap<>();
    }
    
    public boolean create(String path, int value) {
        if (pathMap.containsKey(path)) {
            return false;
        }
        String prefix = path.substring(0, path.lastIndexOf("/"));
        if (!pathMap.containsKey(prefix)) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }
    
    public boolean set(String path, int value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
        
        // When the value of node get modified, trigger callback for every parent nodes along the path
        // eg: if path = '/A/B/C', then the callback functions of '/A/B/C', '/A/B', '/A' needs to be triggered
        while (path.length() > 0) {
            if (callbackMap.containsKey(path)) {
                callbackMap.get(path).run();
            }
            path = path.substring(0, path.lastIndexOf("/"));
        }
        return true;
    }
     
    public Integer get(String path) {
        if (!pathMap.containsKey(path)) {
            return null; // or ``` throw new java.util.NoSuchElementException(); ```
        }
        return pathMap.get(path);
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
   
  Map<String, Integer> pathMap;
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
		
		SimplifiedZooKeeper zooKeeper = new SimplifiedZooKeeper();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 - Test creat(), get() >---");
		
	    System.out.println(zooKeeper.create("/app1", 0)); // expected: true
	    System.out.println(zooKeeper.create("/app1", 0)); // expected: false
	    
	    System.out.println(zooKeeper.create("/app2/p1", 1)); // expected: false
	    System.out.println(zooKeeper.create("/app1/p1", 1)); // expected: true
	    
	    System.out.println(zooKeeper.get("/app1")); // expected: 0
	    System.out.println(zooKeeper.get("/app2")); // expected: null
	    System.out.println(zooKeeper.get("/app1/p1")); // expected: 1
	    
		/* Test Case 2 */
		System.out.println("---< Test Case 2 - Test set() >---");
		
	    System.out.println(zooKeeper.set("/app1", 3)); // expected: true
	    System.out.println(zooKeeper.get("/app1")); // expected: 3
	    
	    System.out.println(zooKeeper.set("/app2", 3)); // expected: false
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 - Test watch() >---");
	    zooKeeper.watch("/app1", "callback from app1");
	    System.out.println(zooKeeper.set("/app1", 4)); // expected: "callback from app1"
	    
	    zooKeeper.watch("/app1/p1", "callback from p1");
	    System.out.println(zooKeeper.set("/app1/p1", 5)); // expected: "callback from p1", "callback from app1" 
		
	}
}
