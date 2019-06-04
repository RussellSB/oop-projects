package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddVertexAction extends AbstractAction {

    AddVertexAction(){
        super("Add vertex");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
