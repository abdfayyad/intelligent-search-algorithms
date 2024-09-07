import java.util.Comparator;

public  class compare2 implements Comparator<structure> {
    @Override
    public int compare(structure t1, structure t2) {
        if(t1.coast_H>t2.coast_H){
            return  -1;
        }else if(t1.coast_H<t2.coast_H){
            return 1;

        }else return 0;
    }
}
