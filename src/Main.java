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

interface Subject{
    void subscribe(Observer obs);
    void unsubscribe(Observer obs);
    void notifyObservers(int i);
}
interface Observer{
    void update(int i);
}
class GenNums implements Subject{
    private ArrayList<Integer> _arr;
    private ArrayList<Observer> _obs;

    public void subscribe(Observer obs){
        _obs.add(obs);
    }
    public void unsubscribe(Observer obs){
        _obs.remove(obs);
    }
    public void notifyObservers(int i){
        for(Observer obs : _obs)
            obs.update(i);
    }

    public GenNums(){
        _arr = new ArrayList<>();
        _obs = new ArrayList<>();
    }
    public GenNums(int size){
        _arr = new ArrayList<>(size);
        _obs = new ArrayList<>(size);
    }

    public void genNums(){
        _arr.add(5);
        _arr.add(25);
        _arr.add(14);
        _arr.add(336);
        _arr.add(41);
        for (int i=0;i<_arr.size();i++) {
            notifyObservers(_arr.get(i));
        }

    }

    public int getNum(int i){
        return _arr.get(i);
    }
    public int size(){
        return _arr.size();
    }
}

class Even implements Observer{
    private ArrayList<Integer> _evens;
    private Subject _subject;

    public Even(Subject s){
        _evens = new ArrayList<>();
        _subject = s;
        s.subscribe(this);
    }
    public void update(int i){
        if(i%2 == 0){
            _evens.add(i);
        }
    }

    public int getNum(int i){
        return _evens.get(i);
    }
    public int size(){
        return _evens.size();
    }
}
class Odd implements Observer{
    private ArrayList<Integer> _odds;
    private Subject _subject;

    public Odd(Subject s){
        _odds = new ArrayList<>();
        _subject = s;
        s.subscribe(this);
    }
    public void update(int i){
        if(i%2 != 0){
            _odds.add(i);
        }
    }

    public int getNum(int i){
        return _odds.get(i);
    }
    public int size(){
        return _odds.size();
    }
}
public class Main {
    public static void main(String[] args) {
//        Concrete cron = new Concrete();
//
//        for(int i=0;i<cron.size();i++){
//            System.out.println(cron.get(i));
//        }
//        System.out.println();
//        //sort ascending
//        cron.ascentSort();
//        for(int i=0;i<cron.size();i++){
//            System.out.println(cron.get(i));
//        }
//        System.out.println();
//        //sort descending
//        cron.descentSort();
//        for(int i=0;i<cron.size();i++){
//            System.out.println(cron.get(i));
//        }

        GenNums gen = new GenNums(5);
        Even evens = new Even(gen);
        Odd odds = new Odd(gen);

        gen.genNums();
        for(int i=0;i<gen.size();i++){
            System.out.println(gen.getNum(i));
        }
        System.out.println();
        for(int i=0;i<evens.size();i++){
            System.out.println(evens.getNum(i));
        }
        System.out.println();
        for(int i=0;i<odds.size();i++){
            System.out.println(odds.getNum(i));
        }
        System.out.println();
    }
}
