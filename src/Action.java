
class Action {
    private String startState;
    private String endState;
    private int cost;

    Action(String startState, String endState, int cost) {
        this.startState = startState;
        this.endState = endState;
        this.cost = cost;
    }

    String getStartState() {
        return startState;
    }

    String getEndState() {
        return endState;
    }

    int getCost() {
        return cost;
    }
}
