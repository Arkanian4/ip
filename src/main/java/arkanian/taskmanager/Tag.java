package arkanian.taskmanager;

/**
 * Represents a single tag associated with a task.
 * A tag is a short label that can be used for categorization or filtering.
 */
public class Tag {
    private final String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return this.tag.isEmpty()
                ? ""
                : "[#" + this.tag + "] ";
    }
}
