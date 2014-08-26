package gui.infopanel;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import gui.infopanel.infosetters.SetterCommand;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoPanelDocListener implements DocumentListener {

    private final InfoPanel infoPanel;
    private final SetterCommand command;
    static final Logger logger = LoggerFactory.getLogger(InfoPanelDocListener.class);
    
    public InfoPanelDocListener(InfoPanel infoPanel, SetterCommand command) {
        this.infoPanel = infoPanel;
        this.command = command;
    }
    
    @Override
    public void insertUpdate(DocumentEvent event) {
        update(event);
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        update(event);
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        update(event);
    }
    
    private void update(DocumentEvent event) {
        try {
            String update = event.getDocument().getText(0, event.getDocument()
                    .getLength());
            if(infoPanel.getCurrentRoom() != null) {
                command.set(infoPanel.getCurrentRoom(), update);
            }
        } catch (BadLocationException ex) {
            logger.error(ex.toString());
        }
    }
    
}