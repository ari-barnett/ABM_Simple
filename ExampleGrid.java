package OnLatticeExample;

import HAL.GridsAndAgents.AgentGrid2D;
import HAL.GridsAndAgents.AgentSQ2Dunstackable;
import HAL.Gui.GridWindow;
import HAL.Rand;
import HAL.Util;

import java.util.Random;

class ExampleCell extends AgentSQ2Dunstackable<ExampleGrid>{

    public void StepCell(double divProb){
        // Each step consists of an ability to reproduce at a 50% chance (divProb)
        // If the random Double is less than 0.50 ---> The cell dies
        // Else the cell has an opportunity to reproduce if spaces are available
        if (G.rng.Double()<divProb){
            int options=MapEmptyHood(G.divHood);
            if(options>0){
                G.NewAgentSQ(G.divHood[G.rng.Int(options)]);
        }
        else{
                Dispose();
                //return;
            }
        }
    }
}
//Grid in which the cells interact on...
//Contains random number generator for use by cells
public class ExampleGrid extends AgentGrid2D<ExampleCell> {
    Rand rng=new Rand();
    int[]divHood= Util.MooreHood(false); //Moore Neighborhood ---> not including origin index

    public ExampleGrid(int x, int y) {
        super(x, y, ExampleCell.class);
    }
    public void StepCells(double divProb){
        for(ExampleCell cell:this){
            cell.StepCell(divProb);
        }
    }
    public void DrawModel(GridWindow window){ //Returns Cells in a Black color depication... Empty space == White
        for (int i = 0; i < length; i++){
            int color=Util.WHITE;
            if(GetAgent(i) != null){
                color=Util.BLACK;
            }
            window.SetPix(i, color);
        }
    }

    public static void main(String[] args){
        int x=150; // X dimension of board
        int y=150; // Y dimension of board
        int timesteps=1000; //Duration of simulation in "Days"
        double divProb=.50; //Probability 50%

        GridWindow window=new GridWindow(x,y,5);
        ExampleGrid model=new ExampleGrid(x,y);

        for (int a = 0; a < 10; a++){ //Can be used to generate multiple random cells ***DOES NOT ACCOUNT FOR REPEATS***
            Random rand= new Random();
            int upperbound = 100;
            int j = rand.nextInt(upperbound);
            int k = rand.nextInt(upperbound);
            model.NewAgentSQ(j,k);
        }

        //model.NewAgentSQ(model.xDim/2, model.yDim/2); //CENTER CELL POSITION

        for (int i = 0; i < timesteps; i++){
            window.TickPause(100);
            model.StepCells(divProb);
            model.DrawModel(window);
        }
    }
}
