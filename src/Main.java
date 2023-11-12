import java.util.*;
interface SortBehaviour{
    boolean sort(int i, int j);
}

class sortAscending implements SortBehaviour{
    public boolean sort(int i, int j){
        return i<j;
    }
}
class sortDescending implements SortBehaviour{
    public boolean sort(int i, int j) {
        return i > j;
    }
}
class Concrete{
    private int[] _a1;

    public Concrete(){
        _a1 = new int[3];
        _a1[0] = 5;
        _a1[1] = 2;
        _a1[2] = 6;
    }
    public Concrete(int size){
        _a1 = new int[size];
    }

    public void sort(SortBehaviour sb){
        for(int i=0;i<_a1.length-1;i++){
            for(int j=i+1;j<_a1.length;j++){
                if(sb.sort(_a1[i],_a1[j])){
                    int t = _a1[i];
                    _a1[i] = _a1[j];
                    _a1[j] = t;
                }
            }
        }
    }
    public void ascentSort(){
        sort(new sortAscending());
    }
    public void descentSort(){
        sort(new sortDescending());
    }
    public int size(){
        return _a1.length;
    }
    public int get(int idx){
        return _a1[idx];
    }
}


public class Main {
    public static void main(String[] args) {
        Concrete cron = new Concrete();

        for(int i=0;i<cron.size();i++){
            System.out.println(cron.get(i));
        }
        System.out.println();
        //sort ascending
        cron.ascentSort();
        for(int i=0;i<cron.size();i++){
            System.out.println(cron.get(i));
        }
        System.out.println();
        //sort descending
        cron.descentSort();
        for(int i=0;i<cron.size();i++){
            System.out.println(cron.get(i));
        }
    }
}
