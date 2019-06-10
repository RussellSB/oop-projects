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
        // TODO: Implement with imaginary circle surrounding the mouse click and intersecting it with the line.


        /*int RADIUS = 33;
        Circle circle = new Circle(click.getX(), click.getY(), RADIUS);
        Line edgeLine = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());


        double baX = edgeLine.getEndX() - edgeLine.getStartX();
        double baY = edgeLine.getEndY() - edgeLine.getStartY();
        double caX = circle.getCenterX() - edgeLine.getStartX();
        double caY = circle.getCenterY() - edgeLine.getStartY();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - circle.getRadius() * circle.getRadius();

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;

        return !(disc < 0);*/

        // TODO: Implement


        int RADIUS = 10;
        Line2D edgeLine = new Line2D.Float(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        Rectangle clickRectangle = new Rectangle(
                (int) click.getX() - RADIUS * 2,
                (int) click.getY() - RADIUS * 2,
                RADIUS,
                RADIUS);


        System.out.println(click);
        System.out.println(clickRectangle);
        System.out.println(edgeLine);
        System.out.println();

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
