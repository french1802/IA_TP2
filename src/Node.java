
class Node implements Comparable{
    private String state;
    private Node parent;
    private int depth;
    private int cost;
    private int heurCost;

    Node(String state, Node parent, int depth, int cost) {
        this.state = state;
        this.parent = parent;
        this.depth = depth;
        this.cost = cost;
        this.heurCost =0;
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

    void setHeurCost(int set) { this.heurCost = set;}

    int getHeurCost(){return heurCost;}

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
    public boolean hasParent(String target)
    {
        if(this.getParent()!=null)
        {
            if(target.equals(this.getParent().getState()))
            {
                return true;
            }
            else
            {
                return this.getParent().hasParent(target);
            }
        }
        else
            return false;
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

    @Override
    public int compareTo(Object o)
    {
        Node n = (Node) o;
        int res = this.getHeurCost() - n.getHeurCost();
        return res;

    }

}
