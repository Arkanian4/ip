package arkanian.taskmanager;

public class TagList {

    private Tag[] tags;
    private int tagCount = 0;

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
