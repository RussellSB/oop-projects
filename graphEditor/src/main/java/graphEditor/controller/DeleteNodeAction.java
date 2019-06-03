package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractAction {

    DeleteNodeAction(){
        super("Delete node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
