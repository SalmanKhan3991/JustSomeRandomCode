public class roundRobinMatrix {
    public static void main(String[] args) {

        int teamsCount = 7;

        int teamsToConsider = teamsCount;
        if (teamsCount % 2 == 0) {
            teamsToConsider--;
        }

        int[][] matches = new int[teamsToConsider][teamsToConsider];

        for (int i = 0; i < teamsToConsider; i++) {
            int count = (i + 1) % teamsToConsider;
            for (int j = 0; j < teamsToConsider; j++) {
                int value = count;
                if (value == 0) value = teamsToConsider;
                matches[i][j] = value;
                count = (count + 1) % teamsToConsider;
            }
        }

        for (int i = 0; i < teamsToConsider; i++) {
            for (int j = 0; j < teamsToConsider; j++) {
                System.out.print(matches[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
