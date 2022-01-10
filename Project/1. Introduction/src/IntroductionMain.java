import java.util.ArrayList;

public class IntroductionMain {

    .field;

    public static void main(String[] args) {
	// write your code here
        Stm prog = new CompoundStm(new AssignStm("a",
                new OpExp(new NumExp(5),
                        OpExp.Plus, new NumExp(3))),
                new CompoundStm(new AssignStm("b",
                        new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
                                new LastExpList(new OpExp(new IdExp("a"),
                                        OpExp.Minus, new NumExp(1))))),

                        new OpExp(new NumExp(10), OpExp.Times,
                                new IdExp("a")))),
                new PrintStm(new LastExpList(new IdExp("b")))));
        System.out.println(prog);
//        maxargs(prog);
    }

    static int maxargs(Stm s){
        ArrayList printStmts = ArrayList<Stm>();
        traverse(max, s);
        return max;
    }

    static void traverse(Integer refInt, Stm stm){
        //Compound: check all attr

        //AssignStm: check right side

        //PairExpList: check all attr

        //PrintStm: compare to ref and continue check the expression

    }

    static void traverse(Integer refInt, Exp exp){
        //ExeqExp: check all attr

        //OpExp: check left and right

        //IdExp, NumExp, LastExpList: stop check
    }

    //measure the depth of individual Print statements recursively
    static int checkPrint(Integer refInt, ExpList expList){


        return 0;

    }
}
