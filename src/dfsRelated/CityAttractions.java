package dfsRelated;

import java.util.*;

public class CityAttractions {

    /*
     * Complete the 'findBestPath' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n - the number of sights
     *  2. INTEGER m - the number of connecting roads
     *  3. INTEGER max_t - the amount of time for sightseeing
     *  4. INTEGER_ARRAY beauty - the beauty value assigned to each sight
     *  5. INTEGER_ARRAY u - the starting sight for each bidirectional road
     *  6. INTEGER_ARRAY v - the ending sight for each bidirectional road
     *  7. INTEGER_ARRAY t - the travel time for each bidirectional road
     */

    static class Square {
        private int num;
        private int beautyValue;
        private Map<Integer, Integer> travelTimeMap = null; // key: neighbor; val: travel time
        private List<Integer> neighbors = null; // a list of neighbors
        private boolean visited = false;
        Square(int n, int b) {
            this.num = n;
            this.beautyValue = b;
            travelTimeMap = new HashMap<Integer, Integer>(); 
            neighbors = new ArrayList<>();
        }
    }
    private static int res = 0;

    public static int findBestPath(int n, int m, int max_t, List<Integer> beauty, List<Integer> u, List<Integer> v, List<Integer> t) {
    // Write your code here

        List<Square> sightsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Square sight = new Square(i, beauty.get(i));
            sightsList.add(sight);
        }

        for (int i = 0; i < m; i++) {
            Square cur = sightsList.get(u.get(i));
            cur.neighbors.add(v.get(i));
            cur.travelTimeMap.put(v.get(i),t.get(i));

            cur = sightsList.get(v.get(i));
            cur.neighbors.add(u.get(i));
            cur.travelTimeMap.put(u.get(i),t.get(i));
        }    

        Square hotel = sightsList.get(0);
        hotel.visited = true;
        for (int nei : hotel.neighbors) {
            int travelTime = hotel.travelTimeMap.get(nei);
            traverse(sightsList, sightsList.get(nei), max_t - travelTime, hotel.beautyValue);
        }
        return res;
    }

    private static void traverse(List<Square> sightsList, Square sight, int timeLeft, int beautySum) {
        if (timeLeft < 0) {
            return;
        }
        if (sight.num == 0 && timeLeft >= 0) {
            res = Math.max(res, beautySum);
        }

        for (int nei : sight.neighbors) {
            int travelTime = sight.travelTimeMap.get(nei);
            if (sight.visited) {
            		traverse(sightsList, sightsList.get(nei), timeLeft - travelTime, beautySum);
            } else {
            		sight.visited = true;
            		traverse(sightsList, sightsList.get(nei), timeLeft - travelTime, beautySum + sight.beautyValue);
            		sight.visited = false;
            }   
        }
    }
    
    /*
    n = 4
    m = 3
    max_t = 49
    beauty = {0, 32, 10, 43}
    u = {0, 2, 0}
    v = {1, 0, 3}
    t = {10, 13, 17}

    sightsList: {0, 1, 2, 3}
    num: 0, beauty: 0, timeMap:{ [1, 10], [2,13], [3,17] } nei: [1,2,3]
    num: 1, beauty: 32, timeMap:{ [0, 10] } nei: [0]
    num: 2, beauty: 10, timeMap:{ [0, 13] } nei: [0]
    num: 3, beauty: 43, timeMap:{ [0, 17] } nei: [0]

    */
    
    /* ------------------------------------------< test stub >-------------------------------------------------*/
    
    public static void main(String[] args) {
    		int n = 4; 
    		int m = 3; 
    		int max_t = 49;
    		List<Integer> beauty = new ArrayList<>();
    		beauty.add(0);
    		beauty.add(32);
    		beauty.add(10);
    		beauty.add(43);
    		
    		List<Integer> u = new ArrayList<>();
    		u.add(0);
    		u.add(2);
    		u.add(0);
    		
    		List<Integer> v = new ArrayList<>();
    		v.add(1);
    		v.add(0);
    		v.add(3);
    		
    		List<Integer> t = new ArrayList<>();
    		t.add(10);
    		t.add(13);
    		t.add(17);
    		
    		System.out.print(findBestPath(n, m, max_t, beauty, u, v, t));
    }

}