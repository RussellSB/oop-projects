package graphEditor.util;

/**
 * Represents types that can be selected
 */
public interface Selectable {
    /**
     * Marks the object as selected.
     */
    void select();

    /**
     * Marks the object as not selected.
     */
    void deselect();

    /**
     * Checks if the object is selected or not.
     */
    boolean isSelected();
}
