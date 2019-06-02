package graphEditor.model;

import java.awt.*;

/**
 * A simple vertex class.
 */
public class GraphVertex {
    private static final Rectangle DEFAULT_RECTANGLE = new Rectangle(0, 0, 40, 30);
    private static final String DEFAULT_NAME = "Unnamed 0";
    private Rectangle rectangle;
    private String name;

    public GraphVertex() {
        this.rectangle = DEFAULT_RECTANGLE;
        this.name = DEFAULT_NAME;
    }

    public GraphVertex(int x, int y, int width, int height, String name) {
        rectangle = new Rectangle(x, y, width, height);
        this.name = name;
    }

    public int getX() {
        return (int) rectangle.getX();
    }

    public int getY() {
        return (int) rectangle.getY();
    }

    public int getWidth() {
        return (int) rectangle.getWidth();
    }

    public int getHeight() {
        return (int) rectangle.getHeight();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeight() + " " + this.name;
    }
}
