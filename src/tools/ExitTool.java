package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import main.Cell;
import main.Entity;
import main.Map;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(CellPanel cellPanel, MouseEvent event) {
        Cell cellObject = cellPanel.getCellObject();
        Map parentMap = cellObject.getParentMap();
        Entity suggestedEntity = parentMap.getPotentialEntity(cellObject);
        System.out.println(suggestedEntity);
        if(!cellObject.isFilled() && !suggestedEntity.equals(Entity.NO_ENTITY)) {
            cellPanel.addImage(suggestedEntity.getPath());
        }
        if (suggestedEntity.equals(Entity.VERTICAL_EXIT)) {
            cellPanel.setVerticalExitBorder();
        }
        if (suggestedEntity.equals(Entity.HORIZONTAL_EXIT)) {
            cellPanel.setHorizontalExitBorder();
        }
    }
    
    @Override
    public void mouseExited(CellPanel cellPanel, MouseEvent event) {
        Cell cellObject = cellPanel.getCellObject();
        if(!cellObject.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellPanel cellPanel, MouseEvent event) {
 
    }
}
