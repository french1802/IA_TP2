

import java.util.LinkedList;


public class DepthFirstSearch extends Strategy {

    Node chooseFringeNode(LinkedList<Node> fringe) {
        return fringe.remove();
    }



    int depth;

    DepthFirstSearch()
    {
        this.setNom("DepthFirstSearch");
        depth =15;
    }

    public LinkedList<Node> expand(Problem problem,  Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        for(Action action: problem.successors(node.getState()))
        {

            String endState = action.getEndState();
            if(!(endState == node.getParent().getState()))
            {
                int cost = problem.getCost(node.getState(), endState);
                Node s = new Node(endState, node, node.getDepth() + 1, cost);
                successors.add(s);
            }
        }

        return successors;
    }

    public Node algorithmExecution(Problem problem)
    {
        return depthLimitedSearch(problem);
    }

    public Node depthLimitedSearch(Problem problem)
    {
        return recursiveDLS(new Node(problem.getInitialState()),problem,depth);
    }

    public Node recursiveDLS(Node node, Problem problem, int limitDepth)
    {
        boolean cutoff = false;
        if(problem.goalTest(node))
            return node;
        else if (node.getDepth() == limitDepth)
            return new Node("cutoff");
        else
        {
            LinkedList<Node> successors = this.expand(problem, node);
            for(Node successor: successors)
            {
                Node result = recursiveDLS(successor,problem,limitDepth);
                if (result.getState() == "cutoff")
                    cutoff = true;
                else if(result.getState() != "failure")
                    return result;
            }

        }
        if(cutoff)
            return new Node("cutoff");
        else
            return new Node("failure");
    }


    /*
    //
    //
    //
    //     KIKOU JE SUIS EN DESSOUS
    /
    /
    //
    //
     */
    public LinkedList<Node> insertAll(LinkedList<Node> expand, LinkedList<Node> fringe) // depth first search
    {
        expand.addAll(fringe);
        return expand;
    }
    /*
    public Node iterativeDeepeningSearch(Problem problem)
    {
        for(int i = 0; )
    }*/
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

}
