package graphEditor.model;

import java.awt.*;
import java.util.Observable;

/**
 * A simple vertex class.
 */
public class GraphVertex extends Observable {
    static final String DEFAULT_NAME = "Node";
    private static final Rectangle DEFAULT_RECTANGLE = new Rectangle(30, 30, 100, 100);
    private Rectangle rectangle;
    private String name;

    /**
     * Creates a new vertex with the default values.
     */
    GraphVertex() {
        rectangle = new Rectangle(DEFAULT_RECTANGLE);
        name = DEFAULT_NAME + " 0";
    }

    /**
     * Creates a new vertex with the introduced parameters.
     *
     * @throws RuntimeException if the name is an empty string.
     */
    public GraphVertex(int x, int y, int width, int height, String name) throws RuntimeException {
        if (name.isEmpty())
            throw new RuntimeException("Name cannot be left empty");

        this.rectangle = new Rectangle(x, y, width, height);
        this.name = name;
    }

    /**
     * Returns the X coordinate of the bounding rectangle.
     */
    public int getX() {
        return (int) rectangle.getX();
    }

    /**
     * Returns the Y coordinate of the bounding rectangle.
     */
    public int getY() {
        return (int) rectangle.getY();
    }

    /**
     * Returns the location of the rectangle.
     */
    public Point getLocation() {
        return rectangle.getLocation();
    }

    /**
     * Returns the width of the rectangle.
     */
    public int getWidth() {
        return (int) rectangle.getWidth();
    }

    /**
     * Returns the height of the rectangle.
     */
    public int getHeight() {
        return (int) rectangle.getHeight();
    }

    /**
     * Returns the rectangle that represents the vertex.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Returns the name of the vertex.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vertex.
     *
     * @throws RuntimeException if the name is an empty string.
     */
    public void setName(String name) throws RuntimeException {
        if (name.isEmpty())
            throw new RuntimeException("Name cannot be left empty");

        this.name = name;

        setChanged();
        notifyObservers();
    }

    /**
     * Moves the rectangle to the specified location.
     */
    public void setLocation(int x, int y) {
        rectangle.setLocation(x, y);

        setChanged();
        notifyObservers();
    }

    /**
     * Sets the size of the rectangle to the specified width and height.
     */
    public void setSize(int width, int height) {
        rectangle.setSize(width, height);

        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the vertex intersects with the specified click coordinates.
     */
    public boolean intersects(Point click) {
        return rectangle.contains(click);
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight() + " " + name;
    }
}
