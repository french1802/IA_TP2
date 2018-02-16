Challet Antoine - CHAA30069502
Duport Fran�ois - DUPF18029406

Intelligence artificielle



README :

La lecture du probl�me se fait via un fichier XML pour faciliter l'interchangeabilit� du probleme

La strategie est diff�renci� de l'algorithme via une fonction insertAll dont le nom vient du pseudo
code de tree search.

La fonction hasParent() sert � certifier qu'un chemin pour aller d'un point A � un point B ne peut pas
par exemple passer deux fois par le point A. (Le probl�me ici pr�sent montrant des boucles).
L'id�e de faire une liste d'actions parcourues a �t� utilis�e mais �limin� car elle empeche deux
chemins diff�rents d'utiliser le m�me segment.

Avant de r�pondre de mani�re pertinnente aux consignes une version de l'algorithme Iterative Deepening a �t� 
�crite. N'�tant pas relevante sur notre m�thode utilis�e elle a �t� retir�. Nous l'avons mise a la fin de ce document.









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