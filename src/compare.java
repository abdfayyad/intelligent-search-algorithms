import java.util.Comparator;

public class compare implements Comparator<structure>{
    @Override
    public int compare(structure t1, structure t2) {
        if(t1.coast>t2.coast){
            return  1;
        }else if(t1.coast<t2.coast){
            return -1;

        }else return 0;
    }

}
