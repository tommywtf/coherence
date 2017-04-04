import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by Tommy on 4/3/2017.
 */
public class CoherenceClient1 {

    private static final String CACHE_NAME = "ITD_REPORT_CACHE";

    public static void main(String[] args) throws IOException {

        CacheFactory.ensureCluster();
        NamedCache cache = CacheFactory.getCache(CACHE_NAME);

        System.out.println(cache.size());

        ValueExtractor extractor = new ReflectionExtractor("getUserId");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        System.out.println("I'm ready. Please enter.");
        long time = 0;
        while ((input = br.readLine()) != null){
            try {
                switch (input.split(" ")[0].trim()) {
                    case "size":
                        System.out.println("Size :" + cache.size());
                        break;
                    case "loadkey":
                        time = System.currentTimeMillis();
                        EqualsFilter filter = new EqualsFilter(extractor, input.split(" ")[1].trim());
                        Set keySet = cache.keySet(filter);
                        System.out.println("Load key :" + keySet.size() + " " + (keySet.iterator().hasNext()? keySet.iterator().next() : ""));
                        System.out.println("Time used:" + (System.currentTimeMillis() - time));
                        break;
                    case "loadall":
                        time = System.currentTimeMillis();
                        EqualsFilter filter2 = new EqualsFilter(extractor, input.split(" ")[1].trim());
                        Set entrySet = cache.entrySet(filter2);
                        System.out.println("Load entrySet :" + entrySet.size() + " " + (entrySet.iterator().hasNext() ? entrySet.iterator().next() : ""));
                        System.out.println("Time used:" + (System.currentTimeMillis() - time));
                        break;
                    case "mem":
                        System.out.println("MB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
                        break;
                    case "addindex":
                        cache.addIndex(extractor, false, null);
                        System.out.println("added index");
                        break;
                    case "bye":
                        break;
                    default: {
                        System.out.println("Unknown command.....");
                        break;
                    }

                }
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
            }

            if (input.equals("bye")){
                break;
            }
        }

        CacheFactory.shutdown();
    }
}
