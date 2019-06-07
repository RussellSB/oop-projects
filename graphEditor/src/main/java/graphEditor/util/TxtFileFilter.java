package graphEditor.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Simple file filter for a file chooser. It will only accept directories and .txt files.
 */
public class TxtFileFilter extends FileFilter {
    /**
     * Checks if the file is accepted or not.
     */
    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        else
            return f.getName().toLowerCase().endsWith(".txt");
    }

    /**
     * Returns the description of the filter that will be shown in the file chooser.
     */
    @Override
    public String getDescription() {
        return ".txt files";
    }
}