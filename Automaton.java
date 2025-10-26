import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Integer;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    private ArrayList<Integer> nextState;
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        state = new int[numberOfCells];
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            if(cellValue == 1) {
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }   
    
    /**
     * Update the automaton to its next state.
     */
    public void update()
    {
        // Build the new state in a separate array.
        nextState = new ArrayList(numberOfCells);
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        for(int i = 0; i < numberOfCells; i++) {
            nextState.add(i,null);
            Integer stateGiver = calculateNextState(i);
            nextState.add(i,stateGiver);
        }
        state = new int[numberOfCells];
        for(int i = 0; i < numberOfCells; i++) {
            state[i] = 0;
            if(nextState.get(i)!=null){
                Integer stateGiver = nextState.get(i);
                int stateGiver2 = stateGiver.intValue();
                state[i] = stateGiver2;
            }
        }
    }
    public Integer calculateNextState(int i){
        int left = i-1 > 0 ? state[i-1] : 0;
        int center = state[i];
        int right = i + 1 < state.length ? state[i+1] : 0;
        Integer stateGiver = (left + center + right) % 2;
        return stateGiver;
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell.
        state[numberOfCells / 2] = 1;
        state[numberOfCells / 4] = 1;
        state[numberOfCells / 4 * 3] = 1;
    }
}
