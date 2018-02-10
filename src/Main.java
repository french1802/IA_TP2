import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();
        Strategy strategy = new DepthFirstSearch();

        Node result = treeSearch(problem, strategy);
        System.out.println(result.result());
        System.out.println("cost : " + result.solutionCost());
    }

    private static Node treeSearch(Problem problem, Strategy strategy) {


        Node node;
        LinkedList<Node> fringe = new LinkedList<>();
        ArrayList<Action> visited = new ArrayList<>();
        fringe.add(new Node(problem.getInitialState()));

        while (!fringe.isEmpty()) {
            node = strategy.chooseFringeNode(fringe);
            if (problem.goalTest(node)) {
                return node;
            } else {
                fringe = strategy.insertAll(strategy.expand(problem, node), fringe);
            }
        }
        return new Node("failure");
    }



}