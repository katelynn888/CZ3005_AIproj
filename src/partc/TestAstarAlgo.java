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
	    Node n3 = new Node(-25436246, 13525346);

	    head.addBranch(1, n1, 124);
	    head.addBranch(5, n2, 34);
	    head.addBranch(2, n3, 235);
	    n3.addBranch(1, n2, 23);

	    Node n4 = new Node(-25436246,2462463);
	    Node n5 = new Node(-23523646,3645637);
	    Node target = new Node(-23523646, 41026897);

	    n1.addBranch(7, n4, 345);
	    n2.addBranch(4, n5, 412);
	    n3.addBranch(6, n4, 235);

	    n4.addBranch(3, target, 235);
	    n5.addBranch(1, n4, 347);
	    n5.addBranch(3, target, 233);

	    Node res = AstarAlgo.aStar(head, target);
	    AstarAlgo.printPath(res);
	    AstarAlgo.getTotalCost(res);
	    AstarAlgo.getTotalEnergy(res);
	}
        
}




