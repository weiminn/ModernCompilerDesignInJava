import java.util.*;

public class LexicalMain {

    static HashMap<Integer, HashMap<Character, HashSet<Integer>>> nfa = new HashMap();
//    static HashMap<HashSet<Integer>, HashMap<Character, HashSet<Integer>>> dfa = new HashMap<>();

    static ArrayList<HashSet<Integer>> dfaStates = new ArrayList<>();
    static ArrayList<HashMap<Character, Integer>> dfaTrans = new ArrayList<>();

    static char[] alphabet = {};
    public static void main(String[] args) {
        /* RegEx Notations
            if := IF
            [a-z][a-z0-9]* := ID
            [0-9]+ := NUM
            ([0-9]+"."[0-9]*)|([0-9]*"."[0-9]+) := REAL
            ("--"[a-z]*"\n")|(" "|"\n"|"\t")+ : no token, just white space
            . := error
        */

        /* Rules
            Longest match
            Rule Priority
         */

        //1. Derive NFA from Regex of the the language specifications


        //2. Generate DFA from the NFA using edge, closure, edgeDFA algorithms
        dfaStates.add(new HashSet<Integer>()); // for null state

        HashSet<Integer> nfaFirstState = new HashSet<>(); // for first state of nfa
        nfaFirstState.add(1);

        dfaStates.add(closure(nfaFirstState));

        int p = 1; // total states
        int j = 0; // current state

        while (j <= p) {
            for (char c : alphabet) {
                HashSet<Integer> dfaState = dfaEdge(dfaStates.get(j), c);
                if (dfaStates.contains(dfaState)){
                    dfaTrans.get(j).put(c, p);
                } else {
                    p += 1;
                    dfaTrans.add(new HashMap<Character, Integer>());
                    dfaTrans.get(j).put(c, p);

                    dfaStates.add(new HashSet<Integer>());
                }
            }
            j += 1;
        }
    }

    //set of all NFA states reachable by following a single edge with label c from state s
    static HashSet<Integer> edge(Integer s, char c){
        return nfa.get(s) //returns transition map for the state
                .get(c); //return states reached after reading c;
    }

    //set of states that can be reached from a state S without consuming any of the input
    static HashSet<Integer> closure(HashSet<Integer> S){
        HashSet<Integer> T = (HashSet<Integer>) S.clone();
        HashSet<Integer> _T;

        do {
            _T = (HashSet<Integer>) T.clone();
            Iterator<Integer> iter = _T.iterator();
            while(iter.hasNext()){
                HashSet<Integer> _s = edge(iter.next(), ' ');
                Iterator<Integer> iter2 = _s.iterator();
                while (iter2.hasNext()){
                    T.add(iter2.next());
                }
            }
        }
        while (!T.equals(_T));

        return T;
    }

    //set of states reachable from the set of states after eating an input symbol c
    static HashSet<Integer> dfaEdge(HashSet<Integer> ss, char c){
        HashSet<Integer> D = new HashSet<>();

        Iterator<Integer> iter = ss.iterator();
        while (iter.hasNext()){
            Integer s = iter.next();
            HashSet<Integer> _ss = edge(s, c);
            D.addAll(_ss);
        }

        return closure(D);
    }
}