package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteVertexAction extends AbstractAction {

    DeleteVertexAction(){
        super("Delete vertex");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
