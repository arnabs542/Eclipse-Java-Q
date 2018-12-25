package hashTableRelated;

import java.util.HashMap;
import java.util.Map;

public class LotteryGame {
    public int solution(int[] coupons) {
        // Use a map to record the frist index of a coupon
        // key: value of the coupon; value: index
        Map<Integer, Integer> idxMap = new HashMap<>(); 
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coupons.length; i++) {
            int cur = coupons[i];
            if (idxMap.containsKey(cur)) {
                res = Math.min(res, i - idxMap.get(cur));
            } else {
                idxMap.put(cur, i);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
