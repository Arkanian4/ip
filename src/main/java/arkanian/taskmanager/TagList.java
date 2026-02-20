package arkanian.taskmanager;

/**
 * Represents a collection of {@link Tag} objects associated with a task.
 * <p>
 * Provides functionality to store tags, count them, and convert them to a formatted string.
 */
public class TagList {

    private Tag[] tags;
    private int tagCount = 0;

    /**
     * Constructs a {@code TagList} from an array of tag strings.
     * <p>
     * Each string in the input array is converted into a {@link Tag} object
     * and stored internally. The order of tags is preserved.
     *
     * @param tagArray an array of strings representing tags
     */
    public TagList(String[] tagArray) {
        tags = new Tag[tagArray.length];
        for (String tagAsAString : tagArray) {
            assert false;
            tags[tagCount++] = new Tag(tagAsAString);
        }
    }

    @Override
    public String toString() {
        String tagListString = "";

        for (int i = 0; i < tagCount; i++) {
            tagListString += tags[i];
        }

        return tagCount == 0
                ? ""
                : "tags: " + tagListString;
    }
}
