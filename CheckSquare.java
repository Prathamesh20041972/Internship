public class CheckSquare {

    
    static int distance(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) +
               (y2 - y1) * (y2 - y1);
    }

    static boolean isSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int d1 = distance(p1[0], p1[1], p2[0], p2[1]);
        int d2 = distance(p1[0], p1[1], p3[0], p3[1]);
        int d3 = distance(p1[0], p1[1], p4[0], p4[1]);

        
        if (d1 == 0 || d2 == 0 || d3 == 0)
            return false;

        if (d1 == d2 && 2 * d1 == d3 &&
            distance(p2[0], p2[1], p4[0], p4[1]) == d1)
            return true;

        if (d1 == d3 && 2 * d1 == d2 &&
            distance(p2[0], p2[1], p3[0], p3[1]) == d1)
            return true;

        if (d2 == d3 && 2 * d2 == d1 &&
            distance(p2[0], p2[1], p3[0], p3[1]) == d2)
            return true;

        return false;
    }

    public static void main(String[] args) {

        int[] p1 = {20, 10};
        int[] p2 = {10, 20};
        int[] p3 = {20, 20};
        int[] p4 = {10, 10};

        if (isSquare(p1, p2, p3, p4))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
