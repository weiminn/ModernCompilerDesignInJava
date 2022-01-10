public class OpExp extends Exp{
    public Exp left, right; public int oper;
    final public static int Plus = 1, Minus = 2, Times = 3, Div = 4;

    public OpExp(Exp left, int oper, Exp right) {
        this.left = left;
        this.right = right;
        this.oper = oper;
    }
}
