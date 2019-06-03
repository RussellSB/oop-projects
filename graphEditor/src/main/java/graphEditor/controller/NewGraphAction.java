package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewGraphAction extends AbstractAction {

    NewGraphAction(){
        super("New graph");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
