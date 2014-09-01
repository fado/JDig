package gui.infopanel.streeteditor;

import data.Level;
import gui.infopanel.InfoPanel;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;
import javax.swing.JButton;
import static org.junit.Assert.assertFalse;

public class StreetEditorTest {

    StreetEditor streetEditor;
    TestingUtils testingUtils;

    @Before
    public void setUp() {
        Level level = new Level(1, 1);
        InfoPanel infoPanel = new InfoPanel(level);
        streetEditor = new StreetEditor(infoPanel);
        testingUtils = new TestingUtils();
    }

    @Test
    public void testAddButtonNotEnabled() {
        streetEditor.run();
        JButton deleteButton = testingUtils.getDeleteButton(streetEditor);
        assertFalse(deleteButton.isEnabled());
    }
}
