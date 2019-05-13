package nl.rug.oop.helloWorld;

import java.util.HashSet;

public class Memory {

    private HashSet<String> memory;

    Memory() {
        memory = new HashSet<>();
    }

    void remember(String said) {
        if(!memory.contains(said))
            memory.add(said);
    }

    boolean canRemember(String said) {
        return memory.contains(said);
    }

}