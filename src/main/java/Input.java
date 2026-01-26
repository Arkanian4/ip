public class Input {
    final private String[] split_input;

    public Input(String input) {
        this.split_input = input.split(" ");
    }

    public int len() {
        return split_input.length;
    }

    public String getInstr() {
        return split_input[0];
    }

    public int getIdx() {
        return Integer.valueOf(split_input[1]);
    }

}
