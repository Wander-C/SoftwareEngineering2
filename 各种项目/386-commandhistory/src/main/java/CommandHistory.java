import java.util.Stack;

// The global command history is just a stack.
public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    // Last in...
    public void push(Command c) {
        //todo:add code here
        history.push(c);
    }

    // ...first out
    public Command pop() {
        //todo:add code here
        if(history.isEmpty()) {
            return null;
        }else {
            return history.pop();
        }
    }
}