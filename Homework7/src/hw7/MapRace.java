package hw7;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// import java.util.HashMap;
// import java.util.TreeMap;
// import java.util.Map;
// We don't need these! We made our own!

public class MapRace {

	public static Integer[] keys = new Integer[1];
	public static Integer[] vals = new Integer[1];
	
	public static Integer[] initArray(Integer length){
		Integer[] arr = new Integer[length];
		for(int i = 0; i < length; i += 1){
			//Fisher-Yates "inside-out"
			int target = (int)(Math.random() * (i + 1));
			arr[i] = arr[target];
			arr[target] = i;
		}
		//System.out.println("Arrays Initiallized!");
		return arr;
	}
	
    /* Tests the put action the specified number of times. */
    private static long timePuts61B(Map<Integer, Integer> map, 
                int num_puts, int key_range, int val_range) {
    	
    	//System.out.println(num_puts);
    	
    	//check to make sure vals is initiallized to the right range
    	if(vals == null || vals.length != num_puts){
    		keys = initArray(num_puts);
    		vals = initArray(num_puts);
    	}		
    	
		Stopwatch sw = new Stopwatch();
		for(int i = 0; i < num_puts; i += 1){
			map.put(keys[i], vals[i]);
		}
		return (long)(sw.elapsedTime() * 1000);

    }

    /* Tests the get action the specified number of times. */
    private static long timeGets61B(Map<Integer, Integer> map, 
                int num_gets, int key_range) {
    	//check to make sure vals is initiallized to the right range
    	if(vals == null || vals.length != num_gets){
    		keys = initArray(num_gets);
    		vals = initArray(num_gets);
    	}
    	
		Stopwatch sw = new Stopwatch();
		for(int i = 0; i < num_gets; i += 1){
			map.get(keys[i]);
		}
		return (long)(sw.elapsedTime() * 1000);
    }

    /* Tests the get action the specified number of times. */
    private static long timeRemove61B(Map<Integer, Integer> map, 
                int num_removes, int key_range) {
    	//check to make sure vals is initiallized to the right range
    	if(vals == null || vals.length != num_removes){
    		keys = initArray(num_removes);
    		vals = initArray(num_removes);
    	}
    	
		Stopwatch sw = new Stopwatch();
		for(int i = 0; i < num_removes; i += 1){
			map.remove(keys[i]);
		}
		return (long)(sw.elapsedTime() * 1000);
    }

    /* Warms up Java to get the cache hot and ready. If you don't warm up, 
     * you'll see that the first test has an unfair handicap. */
    private static void warmup() {
        //Map61B<Integer, Integer> trashMap1 = new MyHashMap<Integer, Integer>();
        //Map61B<Integer, Integer> trashMap2 = new BSTMap<Integer, Integer>();
        Map<Integer, Integer> trashMap1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> trashMap2 = new TreeMap<Integer, Integer>();
        timePuts61B(trashMap1, MIL, MIL, MIL);
        timePuts61B(trashMap2, MIL, MIL, MIL);
        timeGets61B(trashMap1, MIL, MIL);
        timeGets61B(trashMap2, MIL, MIL);
    }

    private static final int MIL = 1000000;

    private static void run61BTimedTests(int num, int key_range, 
                int val_range) {
        //Map61B<Integer, Integer> hMap = new MyHashMap<Integer, Integer>();
        //Map61B<Integer, Integer> tMap = new BSTMap<Integer, Integer>();
        Map<Integer, Integer> hMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> tMap = new TreeMap<Integer, Integer>();

        // TreeMap puts
        long tPuts = timePuts61B(tMap, num, key_range, val_range);
        String tm = "TreeMap " + num + " puts: " + tPuts + " ms.";
        System.out.println(tm);

        // HashMap puts
        long hPuts = timePuts61B(hMap, num, key_range, val_range);
        String hm = "HashMap " + num + " puts: " + hPuts + " ms.";
        System.out.println(hm);

        // TreeMap gets
        long tGets = timeGets61B(tMap, num, key_range);
        String tg = "TreeMap " + num + " gets: " + tGets + " ms.";
        System.out.println(tg);

        // HashMap gets
        long hGets = timeGets61B(hMap, num, key_range);
        String hg = "HashMap " + num + " gets: " + hGets + " ms.";
        System.out.println(hg);

        // TreeMap removes
        long tRemove = timeRemove61B(tMap, num, key_range);
        String tr = "TreeMap " + num + " removes: " + tRemove + " ms.";
        System.out.println(tr);
        
        // HashMap removes
        long hRemove = timeRemove61B(hMap, num, key_range);
        String hr = "HashMap " + num + " removes: " + hRemove + " ms.";
        System.out.println(hr);
    }

    public static final String followUp() {
        // YOUR ANSWER HERE
        String answer = "yes, because I done it ";
        answer += "yuh huh I did";
        return answer;
    }

    public static void main(String[] args) {
        warmup();
        System.out.println("######## 1 Million ########");
        run61BTimedTests(MIL, MIL, MIL);

        System.out.println();
        System.out.println("######## 5 Million ########");
        run61BTimedTests(5 * MIL, 5 * MIL, 5 * MIL);

        System.out.println();
        System.out.println("######## 10 Million ########");
        run61BTimedTests(10 * MIL, 10 * MIL, 10 * MIL);

        //50 million was overkill so I changed to 20 million...
        System.out.println();
        System.out.println("######## 20 Million ########");
        run61BTimedTests(20 * MIL, 20 * MIL, 20 * MIL);
    }
}