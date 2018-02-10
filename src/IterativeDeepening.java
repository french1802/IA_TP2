

import java.util.LinkedList;
import java.util.ArrayList;


public class IterativeDeepening extends Strategy {

    Node chooseFringeNode(LinkedList<Node> fringe) {
        return fringe.remove();
    }

    void LinkedList<Node> expand(Problem problem,  Node node) {
        int newDepth = node.getDepth() + 1;
        for(Action action: problem.successors(node.getState())) {
            if (!visited(action, visited)) {
                visited.add(action);
                String endState = action.getEndState();
                int cost = problem.getCost(node.getState(), endState);
                Node s = new Node(endState, node, newDepth, cost);
                fringe.add(s);
            }
        }
    }

    private boolean visited(Action action, ArrayList<Action> visited) {
        int i = 0;
        int size = visited.size();
        String startState = action.getStartState();
        String endState = action.getEndState();
        while (i < size) {
            Action a = visited.get(i);
            if ((a.getStartState().equals(startState) && a.getEndState().equals(endState)) || (a.getStartState().equals(endState) && a.getEndState().equals(startState))) {
                return true;
            }
            i++;
        }
        return false;
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
            LinkedList<Node> successors = new LinkedList<>();
            this.expand(problem, successors, visited, node);
            for(Node successor: problem.successors(node.getState()))
            {
               Node result = recursiveDLS(successor,problem,limitDepth);
            }
        }
    }

    public Node DLS(Node current, int depth) {
        if (depth == 0 && current == goal) {
            return current;
        }
        if (depth > 0) {
            for (Node child : current.findNeighbours()) {
                Node found = DLS(child, depth - 1);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }



    public Node IDS(Node root) {
        // loops through until a goal node is found
        for (int depth = 0; depth < Integer.MAX_VALUE; depth++) {
            Node found = DLS(root, depth);
            if (found != null) {
                return found;
            }
        }
        // this will never be reached as it
        // loops forever until goal is found
        return null;
    }
}
