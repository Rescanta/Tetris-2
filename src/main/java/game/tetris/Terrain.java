package game.tetris;

import java.util.ArrayList;

public class Terrain {
    ArrayList<ArrayList<Boolean>> terr;
    ArrayList<Boolean> temp;

    public Terrain(int cubeX, int cubeY, int cubeSize, int cubeXMax, int cubeYMax) {
        this.terr = new ArrayList<ArrayList<Boolean>>();
        for(int i = 0; i < cubeYMax; i++) {
            temp = new ArrayList<Boolean>();
            for (int j = 0; j < cubeXMax; j++) {
                temp.add(false);

            }
            terr.add(temp);
        }
    }

    public ArrayList<ArrayList<Boolean>> getArr() {
        return terr;
    }

    public void getData() {
        for (var a : terr) {
            System.out.println(a.toString() + "\n");
        }
    }

    public void setNewCube(int y, int x) {
        terr.get(y).set(x, true);
    }

    public boolean blockAndTerrainTouches(ArrayList<ArrayList<Integer>> arr, String direction) {
        if (direction.equals("NONE")) {
            for (var a : arr) {
                int x = a.get(0);
                int y = a.get(1);
                if(terr.get(y + 1).get(x)) {
                    return true;
                }
            }
        }
        if (direction.equals("RIGHT")) {
            for (var a : arr) {
                int x = a.get(0);
                int y = a.get(1);
                if(terr.get(y).get(x + 1)) {
                    return true;
                }
            }
        }
        if (direction.equals("LEFT")) {
            for (var a : arr) {
                int x = a.get(0);
                int y = a.get(1);
                if(terr.get(y).get(x - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void checkIfThereAreFullLines(int cubeX, int cubeY, int cubeSize, int cubeXMax, int cubeYMax) {
        temp = terr.get(cubeYMax - 1);
        for (var t: temp) {
            if (!t)
                return;
        }
        terr.remove(terr.size() - 1);
        temp.clear();
        for (int i = 0; i < cubeXMax; i++) {
            temp.add(false);
        }
        terr.add(0, temp);
    }
}
