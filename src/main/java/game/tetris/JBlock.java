package game.tetris;

import java.util.ArrayList;


public class JBlock extends AbstractBlock implements InterfaceBlock {

    public JBlock(int cubeX, int cubeY, int cubeSize, int cubeXMax, int cubeYMax) {

        ArrayList<Integer> buffer = new ArrayList<>();
        buffer.add(cubeXMax / 2 - 1);
        buffer.add(0);
        arr.add(buffer);

        buffer = new ArrayList<>();
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

        this.stuck = false;
    }
}
