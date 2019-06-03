package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoadAction extends AbstractAction {

    LoadAction(){
        super("Load");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
