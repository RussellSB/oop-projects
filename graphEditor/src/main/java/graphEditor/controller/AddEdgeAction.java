package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddEdgeAction extends AbstractAction {

    AddEdgeAction(){
        super("Add edge");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
