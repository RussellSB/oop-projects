package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import java.io.IOException;

/**
 * A not undoable edit: It won't be possible to undo the edits done before saving the graph to a file.
 */
public class SaveGraphNonUndoableEdit extends AbstractUndoableEdit {
    /**
     * Saves a graph to a file with the given name.
     *
     * @throws IOException if there's a problem while creating/opening the file.
     */
    public SaveGraphNonUndoableEdit(GraphModel graph, String filePath) throws IOException {
        graph.save(filePath);
    }

    /**
     * This edit cannot be undone.
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
