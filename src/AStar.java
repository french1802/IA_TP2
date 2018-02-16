

import java.util.Collections;
import java.util.LinkedList;



public class AStar implements Strategy {




    private String nom;

    AStar()
    {
        this.setNom("A*");
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Node chooseFringeNode(LinkedList<Node> fringe)
    {
        return fringe.remove();
    }

    public LinkedList<Node> expand(Problem problem, Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        for(Action action: problem.successors(node.getState()))
        {

            String endState = action.getEndState();
            if(!node.hasParent(endState))
            {

                int cost = problem.getCost(node.getState(), endState);
                Node s = new Node(endState, node, node.getDepth() + 1, cost);
                //s.setHeurCost(problem.getStrait(endstate) + s.solutionCost());
                successors.add(s);
            }

        }

        return successors;
    }

    public LinkedList<Node> insertAll(LinkedList<Node> expand, LinkedList<Node> fringe) // depth first search
    {

        expand.addAll(fringe);
        Collections.sort(expand);

        return expand;
    }


}
