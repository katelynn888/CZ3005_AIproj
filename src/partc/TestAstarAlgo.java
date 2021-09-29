package partc;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class TestAstarAlgo{


	public static void main(String[] args) {
	    Node head = new Node(-23842315, 25364012);
	    head.g = 0;

	    Node n1 = new Node(-23846125, 25863012);
	    Node n2 = new Node(-35819023, 23475206);
	    Node n3 = new Node(-46346457, 13525346);

	    head.addBranch(1, n1);
	    head.addBranch(5, n2);
	    head.addBranch(2, n3);
	    n3.addBranch(1, n2);

	    Node n4 = new Node(-25436246,2462463);
	    Node n5 = new Node(-23523646,3645637);
	    Node target = new Node(-73643471, 41026897);

	    n1.addBranch(7, target);
	    n2.addBranch(4, n5);
	    n3.addBranch(6, n4);

	    n4.addBranch(3, target);
	    n5.addBranch(1, n4);
	    n5.addBranch(3, n3);

	    Node res = AstarAlgo.aStar(head, target);
	    AstarAlgo.printPath(res);
	    AstarAlgo.getTotalCost(res);
	}
        
}


