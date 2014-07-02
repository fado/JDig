package tools;

import java.util.EventObject;

public class ToolEvent extends EventObject {
    private final Tool tool;
    
    public ToolEvent(Object source, Tool tool) {
        super(source);
        this.tool = tool;
    }
    
    public Tool getTool() {
        return tool;
    }
}