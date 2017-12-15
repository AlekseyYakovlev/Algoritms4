// Aleksey Yakovlev
//1.	Количество маршрутов с препятствиями.
// Реализовать чтение массива с препятствием и нахождение количество маршрутов.



package Algorithms4;

public class Algorithms4_1 {
    private static int map[][] = {  {0,  0,  0,-1,0},
                                    {0, 0,  0, 0,0},
                                    {-1, 0,  0,-1,0},
                                    {0,  0, -1, 0,0},
                                    {0,  0,  0, 0,0}};

    public static void main( String[] args ) {
        int n = map.length;
        map[0][0] =1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(map[i][j]>-1)map[i][j] = getNumberOfRouts(i - 1, j) + getNumberOfRouts(i, j - 1);
                if(map[j][i]>-1 && i!=j)map[j][i] = getNumberOfRouts(j - 1, i) + getNumberOfRouts(j, i - 1);
                if(i==0 && j==0)map[i][j] =1;
            }
        }
    printMap();
    }

    private static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }


    private static int getNumberOfRouts( int x, int y ) {
        return (x < 0 || y < 0 || map[x][y] == -1) ? 0 : map[x][y];
    }
}
// Вывод:
//          1	1	1	-1	0
//          1	2	3	3	3
//          -1	2	5	-1	3
//          0	2	-1	0	3
//          0	2	2	2	5