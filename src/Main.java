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
        Node currentNode;
        LinkedList<Node> fringe = new LinkedList<>();
        ArrayList<Action> visited = new ArrayList<>();
        fringe.add(new Node(problem.getInitialState()));

        while(!fringe.isEmpty()) {
            currentNode = strategy.chooseFringeNode(fringe);
            if(problem.goalTest(currentNode)) {
                return currentNode;
            } else {
                strategy.expand(problem, fringe, visited, currentNode);
            }
        }
        return new Node("failure");
    }
}