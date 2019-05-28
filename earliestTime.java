import java.util.stream.IntStream;

public class earliestTime {

    public static void main(String[] args) {
        System.out.println(solution(1, 8, 3, 2, 6, 4));
    }

    public static String solution(int A, int B, int C, int D, int E, int F) {

        int[] d = IntStream.of(A, B, C, D, E, F).filter(n -> n >= 0).sorted().toArray();

        if (d[4] < 6) { // 2nd largest digit is smaller 6, we can just fill up
            if (10 * d[0] + d[1] < 24)
                return "" + d[0] + d[1] + ":" + d[2] + d[3] + ":" + d[4] + d[5];
        } else if (d[3] < 6) { // 3rd largest digit is smaller 6, put 2nd largest in 4th position
            if (10 * d[0] + d[1] < 24)
                return "" + d[0] + d[1] + ":" + d[2] + d[4] + ":" + d[3] + d[5];
        } else if (d[2] < 6) { // 4th largest digit is smaller 6, put 3rd largest in 2nd position
            if (10 * d[0] + d[3] < 24)
                return "" + d[0] + d[3] + ":" + d[1] + d[4] + ":" + d[2] + d[5];
        }
        return "NOT POSSIBLE";

    }
}
