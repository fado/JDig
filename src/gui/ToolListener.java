package gui;

import java.util.EventListener;

public interface ToolListener extends EventListener {
    public void toolChanged(ToolEvent event);
}
