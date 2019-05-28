package graphEditor.model;

import java.awt.*;

/**
 * A simple vertex class.
 */
public class GraphVertex {
    private Rectangle rectangle;
    private String name;

    public GraphVertex(int x, int y, int width, int height, String name) {
        rectangle = new Rectangle(x, y, width, height);
        this.name = name;
    }

    public double getX() {
        return rectangle.getX();
    }

    public double getY() {
        return rectangle.getY();
    }

    public double getWidth() {
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeight() + " " + this.name;
    }
}
