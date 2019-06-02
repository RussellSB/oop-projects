package graphEditor.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GraphPanel extends JPanel {

    public GraphPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(10, 38, 70), 50));
        setBackground(new Color(10, 37, 87));
        setVisible(true);
        setOpaque(true);
    }
}
