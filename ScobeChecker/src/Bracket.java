public class Bracket {
    String left;
    String right;
    public Bracket(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public char getLeft() {
        return left.charAt(0);
    }

    public char getRight() {
        return right.charAt(0);
    }
}
