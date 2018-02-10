
import java.util.ArrayList;
import java.util.LinkedList;

abstract class Strategy {
    abstract Node chooseFringeNode(LinkedList<Node> fringe);
    abstract void expand(Problem problem, LinkedList<Node> fringe, ArrayList<Action> visited, Node node);
}
