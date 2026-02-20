package arkanian.taskmanager;

public class Tag {
    private final String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return this.tag == ""
                ? ""
                : "[#" + this.tag + "] ";
    }
}
