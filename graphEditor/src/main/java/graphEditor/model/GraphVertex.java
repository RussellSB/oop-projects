package graphEditor.model;

/**
 * A simple vertex class.
 */
public class GraphVertex {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;

    public GraphVertex(int x, int y, int width, int height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.x + " " + this.y + " " + this.width + " " + this.height + " " + this.name;
    }
}
