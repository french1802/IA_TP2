import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();

        Strategy strategy = new AStarNonInformee();

        Node result = treeSearch(problem, strategy);
        System.out.println(result.result());
        if(strategy.getNom() == "A*")
            System.out.println("cost : " + result.getCost());
        else
            System.out.println("cost : " + result.solutionCost());
    }

    private static Node treeSearch(Problem problem, Strategy strategy) {


        Node node;
        LinkedList<Node> fringe = new LinkedList<>();
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