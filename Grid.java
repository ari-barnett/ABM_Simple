package ABM;

import HAL.GridsAndAgents.AgentGrid2D;
import HAL.GridsAndAgents.AgentSQ2Dunstackable;
import HAL.Gui.GridWindow;
import HAL.Rand;
import HAL.Util;

import java.util.Random;

class Cell extends AgentSQ2Dunstackable<ABM.Grid> {

    public void StepCell(double divProb) {
        // Each step consists of an ability to reproduce at a 50% chance (divProb) == Dependent on availabilty
        // Else Cell dies

        if (G.rng.Double() < divProb) {
            int options = MapEmptyHood(G.divHood);
            if (options > 0) {
                G.NewAgentSQ(G.divHood[G.rng.Int(options)]);
            }
        } else{
            Dispose();
        }
    }
}
//Grid in which the cells interact on...
//Contains random number generator for use by cells
public class Grid extends AgentGrid2D<ABM.Cell> {
    Rand rng=new Rand();
    int[]divHood= Util.MooreHood(false); //Moore Neighborhood ---> not including origin index

    public Grid(int x, int y) {
        super(x, y, ABM.Cell.class);
    }
    public void StepCells(double divProb){
        for(ABM.Cell cell:this){
            cell.StepCell(divProb);
        }
    }
    public void DrawModel(GridWindow window){ //Returns Cells in a Blue color depiction... Empty space == White
        for (int i = 0; i < length; i++){
            int color=Util.WHITE;
            if(GetAgent(i) != null){
                color=Util.BLUE;
            }
            window.SetPix(i, color);
        }
    }
    public static void main(String[] args){
        int x=250; // X dimension of board
        int y=250; // Y dimension of board
        int timesteps=1000; //Duration of simulation in "Days"
        double divProb=0.50; //Probability 50%

        GridWindow window=new GridWindow(x,y,3);
        ABM.Grid model=new ABM.Grid(x,y);

        for (int a = 0; a < 1000; a++){ //Can be used to generate multiple random cells ***DOES NOT ACCOUNT FOR REPEATS***
            Random rand= new Random();
            int upperbound = 250;
            int j = rand.nextInt(upperbound);
            int k = rand.nextInt(upperbound);

            if (model.GetAgent(j,k) == null){
                model.NewAgentSQ(j,k);
            }
        }
        for (int i = 0; i < timesteps; i++){
            window.TickPause(100);
            model.StepCells(divProb);
            model.DrawModel(window);
        }
    }
}
