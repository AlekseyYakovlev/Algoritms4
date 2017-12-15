// Aleksey Yakovlev
//3.	*Требуется обойти конём шахматную доску размером NxM, пройдя через все поля доски по одному разу.
// Здесь алгоритм решения такой же, как и в задаче о 8 ферзях. Разница только в проверке положения коня.


package Algorithms4;

public class Algorithms4_3 {
    private static final int N =8, M=8;
    private static final int NM = N*M;
    private static int [][] globalMap = new int [N][M];
    private static int counter=0;

    public static void main(String[] args) {
        int [] position={0,0};
        checkNextTurn(position, globalMap,1);
        System.out.println("Ходы коня:");
        drawMap();
    }

    private static boolean checkNextTurn( int[] position, int[][] map, int turn ){
        if (turn ==NM) {
            globalMap[position[0]][position[1]]=turn;
            return true;}

        int localMap[][]= new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <M; j++) {
                localMap[i][j]=map[i][j];
            }
        }

        localMap[position[0]][position[1]]=turn;

        int moves[][]=getMoves(position,localMap);

        int [] nextPosition;

        do{
            nextPosition=getNextMove(moves);
        }while (nextPosition[0]!=-10 && !checkNextTurn(nextPosition,localMap,turn+1));

        if (nextPosition[0]==-10) return false; //if x==0
        else {
            globalMap[position[0]][position[1]]=turn;
            return true;
        }
    }

    private static int [] getNextMove(int moves[][]){
        int nextPosition[]=new int[2];
        int i = 0;
        while(i <8 && moves[i][0]==0) i++; // looking for valid moves

        if(i==8) nextPosition= new int[]{-10, -10}; // none found
        else {
            moves[i][0]=0;
            nextPosition[0]=moves[i][1]; //x
            nextPosition[1]=moves[i][2]; //y
            counter++;
        }
        return nextPosition;
    }

    private static int [][] getMoves( int position[], int map[][] ){
        int[][]moves=new int[8][3];
        moves[0]=new int[]{0,position[0]-2,position[1]+1};
        moves[1]=new int[]{0,position[0]-1,position[1]+2};
        moves[2]=new int[]{0,position[0]+1,position[1]+2};
        moves[3]=new int[]{0,position[0]+2,position[1]+1};
        moves[4]=new int[]{0,position[0]+2,position[1]-1};
        moves[5]=new int[]{0,position[0]+1,position[1]-2};
        moves[6]=new int[]{0,position[0]-1,position[1]-2};
        moves[7]=new int[]{0,position[0]-2,position[1]-1};

        for(int i=0;i<moves.length;i++) {
            moves[i][0]=isMoveValid(moves[i][1],moves[i][2],map);
        }
        return moves;
    }
    private static int isMoveValid( int x, int y, int map[][] ){
        return (x<0 || y<0 || x>N-1 || y>M-1 || map[x][y]!=0)?0:1;// 1-valid move, 0-invalid move
    }

    private static void drawMap() {
        for (int i = 0; i <globalMap.length ; i++) {
            for (int j = 0; j <globalMap[i].length ; j++) {
                System.out.print("\t"+globalMap[i][j]);
            }
            System.out.println();
        }
        System.out.println("Всего итераций: "+counter);
    }
}
//    Ходы коня:
//        1	    38	55	34	3	36	19	22
//        54	47	2	37	20	23	4	17
//        39	56	33	46	35	18	21	10
//        48	53	40	57	24	11	16	5
//        59	32	45	52	41	26	9	12
//        44	49	58	25	62	15	6	27
//        31	60	51	42	29	8	13	64
//        50	43	30	61	14	63	28	7
//        Всего итераций: 3242064
