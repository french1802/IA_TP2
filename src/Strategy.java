import java.util.ArrayList;
import java.util.LinkedList;

public interface Strategy {

    void setNom(String nom);

    String getNom();

    LinkedList<Node> expand(Problem problem, Node node, ArrayList<Action> tested);

    LinkedList<Node> insertAll(LinkedList<Node> expand, LinkedList<Node> fringe);

    Node chooseFringeNode(LinkedList<Node> fringe);
//    Node algorithmExecution(Problem problem);
}
