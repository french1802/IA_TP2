
class Node {
    private String state;
    private Node parent;
    private int depth;
    private int cost;

    Node(String state, Node parent, int depth, int cost) {
        this.state = state;
        this.parent = parent;
        this.depth = depth;
        this.cost = cost;
    }

    Node(String state) {
        this.state = state;
        this.depth = 0;
        this.cost = 0;
    }

    String getState() {
        return state;
    }

    int getDepth() {
        return depth;
    }

    int getCost() { return cost; }

    Node getParent() {
        if(depth > 0) {
            return parent;
        } else {
            return null;
        }
    }

    String result() {
        String node = "End state : " + this.getState() ;
        Node parent = this.getParent();
        if (parent != null) {
            node += this.getParent().addInResult();
        }
        return node;
    }

    private String addInResult() {
        String s = " <-- " + this.getState();
        Node parent = this.getParent();
        if (parent != null) {
            s += this.getParent().addInResult();
        }
        return s;
    }

    int solutionCost() {
        int cost = this.getCost();
        Node parent = this.getParent();
        while (parent != null) {
            cost += parent.getCost();
            parent = parent.getParent();
        }
        return cost;
    }
}
