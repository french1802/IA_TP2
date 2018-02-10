import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();
        Strategy strategy = new IterativeDeepening();

        Node result = treeSearch(problem, strategy);
        System.out.println(result.result());
        System.out.println("cost : " + result.solutionCost());
    }

    private static Node treeSearch(Problem problem, Strategy strategy) {

        if (strategy.getNom() == "Iterative Deepening")
           // return strategy.depthLimitedSearch();

        else {
            Node node;
            LinkedList<Node> fringe = new LinkedList<>();
            ArrayList<Action> visited = new ArrayList<>();
            fringe.add(new Node(problem.getInitialState()));

            while (!fringe.isEmpty()) {
                node = strategy.chooseFringeNode(fringe);
                if (problem.goalTest(node)) {
                    return node;
                } else {
                    fringe.addAll(strategy.expand(problem, node));
                }
            }
            return new Node("failure");
        }
    }


}