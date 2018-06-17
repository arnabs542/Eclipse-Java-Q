/*
 * Created Date: June 16, 2018
 * 
 * Question - Bipartite:  
 *   Determine if an undirected graph is bipartite. 
 *   A bipartite graph is one in which the nodes can be divided into two groups 
 *     such that no nodes have direct edges to other nodes in the same group.
 *      
 *   Example: 
 *   
 *   Assumptions:
 *     The graph is represented by a list of nodes, and the list of nodes is not null.
 *   
 * Need to Review in June 17!!!
 * 
 * Updated:
 * 
 */

package bfsRelated;

import java.util.ArrayList;
import java.util.List;

class GraphNode {
	public int key;
    public List<GraphNode> neighbors;
    
    public GraphNode(int key) {
    	    this.key = key;
     	this.neighbors = new ArrayList<GraphNode>();
    }
}


public class Bipartite {
	
	public boolean isBipartite(List<GraphNode> graph) {
		
		
	}
	

}
