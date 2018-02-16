Challet Antoine - CHAA30069502
Duport François - DUPF18029406

Intelligence artificielle



README :

La lecture du problème se fait via un fichier XML pour faciliter l'interchangeabilité du probleme

La strategie est différencié de l'algorithme via une fonction insertAll dont le nom vient du pseudo
code de tree search.

La fonction hasParent() sert à certifier qu'un chemin pour aller d'un point A à un point B ne peut pas
par exemple passer deux fois par le point A. (Le problème ici présent montrant des boucles).
L'idée de faire une liste d'actions parcourues a été utilisée mais éliminé car elle empeche deux
chemins différents d'utiliser le même segment.

Avant de répondre de manière pertinnente aux consignes une version de l'algorithme Iterative Deepening a été 
écrite. N'étant pas relevante sur notre méthode utilisée elle a été retiré. Nous l'avons mise a la fin de ce document.









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