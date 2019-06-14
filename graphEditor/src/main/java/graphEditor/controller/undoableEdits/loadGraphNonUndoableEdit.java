package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import java.io.IOException;

/**
 * A not undoable edit: It won't be possible to undo the edits done before loading the new graph.
 */
public class loadGraphNonUndoableEdit extends AbstractUndoableEdit {
    /**
     * Loads a graph from the specified file.
     *
     * @throws IOException if there's a problem while opening the file.
     */
    public loadGraphNonUndoableEdit(GraphModel graph, String filePath) throws IOException {
        graph.load(filePath);
    }

    /**
     * This edit cannot be undone.
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
