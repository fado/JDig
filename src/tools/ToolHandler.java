package tools;

import java.util.ArrayList;
import java.util.List;

public class ToolHandler {

    private Tool selectedTool;
    private final List<ToolListener> listeners;

    public ToolHandler() {
        selectedTool = new DefaultPointer();
        listeners = new ArrayList<>();
    }

    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        fireToolChanged();
    }

    public void addToolListener(ToolListener obj) {
        listeners.add(obj);
    }

    protected void fireToolChanged() {
        ToolEvent toolEvent = new ToolEvent(this, getSelectedTool());
        for (ToolListener eachListener : listeners) {
            eachListener.toolChanged(toolEvent);
        }
    }
}
