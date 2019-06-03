package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractAction {

    AddNodeAction(){
        super("Add node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
