import java.util.ArrayList;
import java.util.Arrays;

public class Practice1 {
    private static int[][] arr = {{1, 2, 3}, {1, 4, 7}, {1, 5, 9}, {2, 5, 8}, {3, 6, 9}, {3, 5, 7}, {4, 5, 6}, {7, 8, 9}};
    private static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    private static int count = 0;
    private static int cross1 = 0, cross2 = 0, cross3 = 0, cross4 = 0;
    private static int circle1 = 0, circle2 = 0, circle3 = 0;

    private static void createList() {
        list.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 4, 7)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 5, 9)));
        list.add(new ArrayList<Integer>(Arrays.asList(2, 5, 8)));
        list.add(new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
        list.add(new ArrayList<Integer>(Arrays.asList(3, 5, 7)));
        list.add(new ArrayList<Integer>(Arrays.asList(4, 5, 6)));
        list.add(new ArrayList<Integer>(Arrays.asList(7, 8, 9)));
    }

    public Practice1() {
        createList();
    }

    private static void checkWin(boolean isCross, int x) {
        if (isCross) {
            if (cross1 == 0) cross1 = x;
            else if (cross2 == 0) cross2 = x;
            else if (cross3 == 0) cross3 = x;
            else if (cross4 == 0) cross4 = x;

            for (int i = 0 ; i < list.size() ; i++) {
                if ((list.get(i).contains(cross1) && list.get(i).contains(cross2) && list.get(i).contains(cross3)) ||
                        (list.get(i).contains(cross2) && list.get(i).contains(cross3) && list.get(i).contains(cross4)) ||
                        (list.get(i).contains(cross1) && list.get(i).contains(cross3) && list.get(i).contains(cross4)) ||
                        (list.get(i).contains(cross1) && list.get(i).contains(cross2) && list.get(i).contains(cross4))) {
                    System.out.println("Cross Won!");
                }
            }
        } else {
            if (circle1 == 0) circle1 = x;
            else if (circle2 == 0) circle2 = x;
            else if (circle3 == 0) circle3 = x;

            for (int i = 0 ; i < list.size() ; i++) {
                if ((list.get(i).contains(circle1) && list.get(i).contains(circle2) && list.get(i).contains(circle3))) {
                    System.out.println("O Won!");
                }
            }
        }


    }

    public static void main(String[] args) {
        Practice1 p = new Practice1();
        p.checkWin(true,1);
        p.checkWin(true,2);
        p.checkWin(true,4);
        p.checkWin(true,7);
    }
}
