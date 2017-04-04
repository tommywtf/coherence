import bean.Key;
import bean.Order;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tommy on 4/3/2017.
 */
public class CoherenceInsert {

    private static final String CACHE_NAME = "ITD_REPORT_CACHE";

    public static void main(String[] args) {

        CacheFactory.ensureCluster();
        NamedCache<Key, Order> cache = CacheFactory.getCache(CACHE_NAME);

        String[] userId = {"chengtom", "julien", "kirk", "anurag", "stanley"};
        String[] symbol = {"0005.HK", "0001.HK", "0939.HK"};
        int[] qty = {14800, 10000, 23000};
        double[] price = {63.5, 142, 6.21};


        for (int i=0; i< 1000000; i++){
            int userIndex = (int) Math.floor(Math.random()*5);
            int idx = (int) Math.floor(Math.random()*3);

            Order order = new Order(UUID.randomUUID().toString(), symbol[idx], price[idx], qty[idx], price[idx] * qty[idx], userId[userIndex], "XHKG", new Date(), new Date());

            cache.put(order.getKey(), order);
        }

        System.out.println(cache.size());
        CacheFactory.shutdown();
    }
}
