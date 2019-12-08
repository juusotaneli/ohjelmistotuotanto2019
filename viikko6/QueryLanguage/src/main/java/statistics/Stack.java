package statistics;

import java.util.ArrayDeque;

import statistics.matcher.Matcher;

public class Stack {

    private ArrayDeque <Matcher> elements;

    public Stack() {
        elements = new ArrayDeque<Matcher>();
    }

    public void push(Matcher element){
        elements.addFirst(element);
    }

    public Matcher pop(){
        return elements.pollFirst();
    }

    public boolean empty(){
        return elements.isEmpty();
    }
    public int size(){
        return elements.size();
    }
    public Matcher [] getAll() {
        int x = 0;
        Matcher [] a = new Matcher [this.size()];
        while(elements.peekFirst() != null) {
            a[x] = elements.pollFirst();
            x++;
            
        }
        return a;
    }
}