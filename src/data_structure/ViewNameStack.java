package data_structure;

import java.util.ArrayList;

public class ViewNameStack implements Stack{
    ArrayList<String> stack;
    public ViewNameStack() {
        this.stack = new ArrayList<String>();
    }
    @Override
    public void push(Object item) {
        stack.add((String) item);
    }

    @Override
    public Object pop() {
        if (!stack.isEmpty()) {
            String prevView = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return prevView;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
