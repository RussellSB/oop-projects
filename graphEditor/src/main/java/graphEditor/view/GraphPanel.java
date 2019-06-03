package graphEditor.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class GraphPanel extends JPanel {

    public GraphPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(70, 60, 110), 50));
        setBackground(new Color(83, 70, 126));
        setVisible(true);
        setOpaque(true);
    }
}
