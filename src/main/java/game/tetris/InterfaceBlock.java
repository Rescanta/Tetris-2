package game.tetris;

import java.util.ArrayList;

public interface InterfaceBlock {
    public void getData();
    public boolean moveBlock(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain);
    public ArrayList<ArrayList<Integer>> getArr();
    public boolean moveNONE(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain);
    public boolean moveRIGHT(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain);
    public boolean moveLEFT(String direction, int cubeSize, int cubeXMax, int cubeYMax, Terrain terrain);

}
