import java.util.LinkedList;

public interface Strategy {

    void setNom(String nom);

    String getNom();

    LinkedList<Node> expand(Problem problem, Node node);

    LinkedList<Node> insertAll(LinkedList<Node> expand, LinkedList<Node> fringe);

    Node chooseFringeNode(LinkedList<Node> fringe);
//    Node algorithmExecution(Problem problem);
}
