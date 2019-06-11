package graphEditor.model;

import java.awt.*;

/**
 * A temporary edge class which connects one vertex to a point location (where the cursor will be, in this case)
 */
public class GraphTempEdge {
    private GraphVertex v1;
    private Point endPoint;

    /**
     * Creates a new edge that connects vertex v1 with end point
     */
    GraphTempEdge(GraphVertex v1, Point endPoint) {
        this.v1 = v1;
        this.endPoint = endPoint;
    }

    /**
     * Gets the first vertex.
     */
    public GraphVertex getV1() {
        return v1;
    }

    /**
     * Gets the end point.
     */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the end point.
     */
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        return v1.getName() + " " + endPoint.getX() + "," + endPoint.getY();
    }
}
