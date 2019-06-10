package graphEditor.model;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * A simple edge class which connects two vertices.
 */
public class GraphEdge {
    private GraphVertex v1;
    private GraphVertex v2;

    /**
     * Creates a new edge that connects vertices v1 and v2.
     *
     * @throws IllegalArgumentException if v1 and v2 are the same.
     */
    GraphEdge(GraphVertex v1, GraphVertex v2) throws IllegalArgumentException {
        // Self-loops are not allowed
        if (v1 == v2)
            throw new IllegalArgumentException("Self-loops are not allowed: v1 and v2 must be different");

        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Gets the first vertex.
     */
    public GraphVertex getV1() {
        return v1;
    }

    /**
     * Gets the second vertex.
     */
    public GraphVertex getV2() {
        return v2;
    }

    /**
     * Checks if the edge contains v.
     */
    boolean hasVertex(GraphVertex v) {
        return this.v1 == v || this.v2 == v;
    }

    /**
     * Checks if the edge intersects with the specified click coordinates.
     */
    public boolean intersects(Point click) {
        // We create an imaginary rectangle around the click coordinates to make it easier to click the edge. This rectangle is 'RADIUS' pixels around the click coordinates. Then we just intersect this rectangle with the edge line.
        int RADIUS = 5;
        Rectangle clickRectangle = new Rectangle(
                (int) click.getX() - RADIUS,
                (int) click.getY() - RADIUS,
                RADIUS * 2,
                RADIUS * 2);

        int v1CenterX = v1.getX() + v1.getWidth() / 2;
        int v1CenterY = v1.getY() + v1.getHeight() / 2;
        int v2CenterX = v2.getX() + v2.getWidth() / 2;
        int v2CenterY = v2.getY() + v2.getHeight() / 2;
        Line2D edgeLine = new Line2D.Float(v1CenterX, v1CenterY, v2CenterX, v2CenterY);

        return edgeLine.intersects(clickRectangle);
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        return v1.getName() + " " + v2.getName();
    }
}
