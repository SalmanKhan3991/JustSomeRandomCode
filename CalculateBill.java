import java.util.*;

/**
 * @author Salman
 */
public class CalculateBill {

    private static final int ONE_HOUR_IN_SECONDS = 3600;
    private static final int ONE_MIN_IN_SECONDS = 60;

    public static void main(String[] args) {
        String str = "00:01:07,400-234-090\n" +
                "00:05:01,701-080-080\n" +
                "00:05:00,400-234-090\n";

        String[] records = str.split("\\r?\\n");
        HashMap<Long, List<Integer>> map = new HashMap<>();

        int cost = 0;

        for (int i = 0; i < records.length; i++) {
            String[] details = records[i].split(",");
            Long phone = Long.parseLong(details[1].replaceAll("-", ""));
            int secs = getSeconds(details[0]);
            if (secs < 300) {
                cost += secs * 3;
            } else if (secs >= 300) {
                int mins = secs / 60;
                if (secs % 60 > 0) {
                    mins++;
                }
                cost += mins * 150;
            }
            if (map.containsKey(phone)) {
                List<Integer> val = map.get(phone);
                val.add(secs);
                map.put(phone, val);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(secs);
                map.put(phone, list);
            }
        }

        Iterator itr = map.entrySet().iterator();
        int maxsec = 0;
        Long maxKey = 0L;

        // Find the highest caller
        while (itr.hasNext()) {
            Map.Entry<Long, List<Integer>> entry = (Map.Entry) itr.next();
            Iterator listItr = entry.getValue().iterator();
            int sum = 0;
            while (listItr.hasNext()) {
                sum += (int) listItr.next();
            }
            if (sum > maxsec) {
                maxsec = sum;
                maxKey = entry.getKey();
            }
        }

        List<Integer> maxValues = map.get(maxKey);

        Iterator maxItr = maxValues.iterator();

        // Remove the cost for free promotion for highest caller
        while (maxItr.hasNext()) {
            int val = (int) maxItr.next();
            if (val < 300) {
                cost -= val * 3;
            } else if (val >= 300) {
                int mins = val / 60;
                if (val % 60 > 0) {
                    mins++;
                }
                cost -= mins * 150;
            }
        }
        System.out.println(cost);
    }

    /**
     * Get seconds for the time string passed
     * @param item
     * @return
     */
    public static int getSeconds(String item) {
        int seconds = 0;

        String[] strParts = item.split(":");

        int[] parts = new int[strParts.length];
        for (int j = 0; j < parts.length; j++) {
            parts[j] = Integer.parseInt(strParts[j]);
        }
        seconds += parts[0] * ONE_HOUR_IN_SECONDS;
        seconds += parts[1] * ONE_MIN_IN_SECONDS;
        seconds += parts[2];

        return seconds;
    }
}
