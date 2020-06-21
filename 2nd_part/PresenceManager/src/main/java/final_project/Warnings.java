package final_project;
import java.util.*;

public class Warnings {

    private List<Integer> warned_50;
    private List<Integer> warned_25;

    public Warnings()
    {
        warned_50 = new ArrayList<Integer>();
        warned_25 = new ArrayList<Integer>();
    }

    public List<Integer> get_L()
    {
        return warned_50;
    }

    public List<Integer> get_XXV()
    {
        return warned_25;
    }

    


}