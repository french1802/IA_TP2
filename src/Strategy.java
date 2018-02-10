
import java.util.ArrayList;
import java.util.LinkedList;

abstract class Strategy {


    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;


    public String getNom() {
        return nom;
    }

    abstract LinkedList<Node> expand(Problem problem,Node node);
    abstract Node chooseFringeNode(LinkedList<Node> fringe);
}
