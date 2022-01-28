package game.tetris;

import java.util.ArrayList;

//   O shape
//   0
//   0
//   0
public class LBlock implements InterfaceBlock {
    //initial coordinates
    //int[][] arr = new int[4][2];
    ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
    boolean stuck;

    public LBlock(int cubeX, int cubeY, int cubeSize, int cubeXMax, int cubeYMax) {

        ArrayList<Integer> buffer = new ArrayList<>();
        buffer.add(cubeXMax / 2 - 1);
        buffer.add(1);
        arr.add(buffer);

        buffer = new ArrayList<>();
        buffer.add(cubeXMax / 2);
        buffer.add(1);
        arr.add(buffer);

        buffer = new ArrayList<>();
        buffer.add(cubeXMax / 2 + 1);
        buffer.add(1);
        arr.add(buffer);

        buffer = new ArrayList<>();
        buffer.add(cubeXMax / 2 + 1);
        buffer.add(0);
        arr.add(buffer);

        this.stuck = false;
    }

    public void getData() {
        for (var a : arr) {
            System.out.println(a + "\n");
        }
    }

    public boolean moveBlock(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain) {
        if (stuck) {
            return stuck;
        }
        if (direction.equals("NONE"))
            stuck = moveNONE(direction, cubeSize, cubeXMax, cubeYMax, terrain);
        if (direction.equals("RIGHT"))
            stuck = moveRIGHT(direction, cubeSize, cubeXMax, cubeYMax, terrain);
        if (direction.equals("LEFT"))
            stuck = moveLEFT(direction, cubeSize, cubeXMax, cubeYMax, terrain);

        return stuck;
    }

    public ArrayList<ArrayList<Integer>> getArr() {
        return arr;
    }

    public boolean moveNONE(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain) {
        int temp;
        if (arr.get(0).get(1) + 1 == cubeYMax || arr.get(1).get(1) + 1 == cubeYMax || arr.get(2).get(1) + 1 == cubeYMax || arr.get(3).get(1) + 1 == cubeYMax) {
            stuck = true;
            return stuck;
        }
        if (terrain.blockAndTerrainTouches(arr, direction)) {
            stuck = true;
            return stuck;
        }

        for (var a: arr) {
            temp = a.get(1) + 1;
            a.set(1, temp);
        }

        return stuck;
    }

    public boolean moveRIGHT(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain) {
        int temp;
        if (arr.get(0).get(0) + 1 == cubeXMax || arr.get(1).get(0) + 1 == cubeXMax || arr.get(2).get(0) + 1 == cubeXMax || arr.get(3).get(0) + 1 == cubeXMax) {
            //stuck = true;
            return stuck;
        }
        if (terrain.blockAndTerrainTouches(arr, direction)) {
            //stuck = true;
            return stuck;
        }

        for (var a: arr) {
            temp = a.get(0) + 1;
            a.set(0, temp);
        }
//        for (var a: arr) {
//            temp = a.get(1) + 1;
//            a.set(1, temp);
//        }

        return stuck;
    }

    public boolean moveLEFT(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain) {
        int temp;
        if (arr.get(0).get(0) - 1 < 0 || arr.get(1).get(0) - 1 < 0  || arr.get(2).get(0) - 1 < 0 || arr.get(3).get(0) - 1 < 0) {
            //stuck = true;
            return stuck;
        }
        if (terrain.blockAndTerrainTouches(arr, direction)) {
            //stuck = true;
            return stuck;
        }

        for (var a: arr) {
            temp = a.get(0) - 1;
            a.set(0, temp);
        }
//        for (var a: arr) {
//            temp = a.get(1) + 1;
//            a.set(1, temp);
//        }

        return stuck;
    }
}
