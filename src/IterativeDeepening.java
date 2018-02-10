

import java.util.LinkedList;


public class IterativeDeepening extends Strategy {

    Node chooseFringeNode(LinkedList<Node> fringe) {
        return fringe.remove();
    }


    IterativeDeepening()
    {
        this.setNom("IterativeDeepening");
    }

    public LinkedList<Node> expand(Problem problem,  Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        for(Action action: problem.successors(node.getState()))
        {

            String endState = action.getEndState();
            int cost = problem.getCost(node.getState(), endState);
            Node s = new Node(endState, node, node.getDepth()+1, cost);
            successors.add(s);
        }

        return successors;
    }



    public Node depthLimitedSearch(Problem problem, int limitDepth)
    {
        return recursiveDLS(new Node(problem.getInitialState()),problem,limitDepth);
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
}
