import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        LinkedList<Integer> q = new LinkedList<>();
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++){
                if(i+j % 2 == 0){
                    s.add(i+j);
                    q.add(s.peek());
                }
                if(i*2+j % 3 == 0){
                    int t= q.peek();
                    q.add(s.pop());
                    s.add(t);
                    q.poll();
                }
            }
        }
        int counts = 0;
        int countq = 0;

        while(!s.empty()){
            s.pop();
            counts++;
        }
        while(q.isEmpty()){
            s.pop();
            countq++;
        }
        System.out.println(counts);
        System.out.println(counts);

    }
}
