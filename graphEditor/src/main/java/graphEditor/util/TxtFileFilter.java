package graphEditor.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Simple file filter for a file chooser. It will only accept directories and .txt files.
 */
public class TxtFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        else
            return f.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return ".txt files";
    }
}