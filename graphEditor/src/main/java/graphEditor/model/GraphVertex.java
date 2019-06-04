package graphEditor.model;

import java.awt.*;

/**
 * A simple vertex class.
 */
public class GraphVertex {
    static final String DEFAULT_NAME = "Unnamed";
    private static final Rectangle DEFAULT_RECTANGLE = new Rectangle(0, 0, 40, 30);
    private Rectangle rectangle;
    private String name;

    public GraphVertex() {
        this.rectangle = new Rectangle(DEFAULT_RECTANGLE);
        this.name = DEFAULT_NAME + " 0";
    }

    public GraphVertex(int x, int y, int width, int height, String name) throws RuntimeException {
        if (name.equals(""))
            throw new RuntimeException("Name cannot be left empty");

        this.rectangle = new Rectangle(x, y, width, height);
        this.name = name;
    }

    public int getX() {
        return (int) rectangle.getX();
    }

    public int getY() {
        return (int) rectangle.getY();
    }

    public Point getLocation() {
        return rectangle.getLocation();
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

    void setName(String name) throws RuntimeException {
        if (name.equals(""))
            throw new RuntimeException("Name cannot be left empty");

        this.name = name;
    }

    public void setLocation(int x, int y) {
        rectangle.setLocation(x, y);
    }

    public void setSize(int width, int height) {
        rectangle.setSize(width, height);
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeight() + " " + this.name;
    }
}
