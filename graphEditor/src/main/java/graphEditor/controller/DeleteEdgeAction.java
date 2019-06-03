package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteEdgeAction extends AbstractAction {

    DeleteEdgeAction(){
        super("Delete edge");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
