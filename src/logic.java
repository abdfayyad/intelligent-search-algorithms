/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

import static java.lang.StrictMath.abs;

/**
 * @author Raneea
 */
public class logic {
    Scanner in = new Scanner(System.in);
    //structure ob = new structure() ;
    boolean visited[][] = new boolean[6][6];


    logic() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                visited[i][j] = false;
    }

    void userPlay(structure ob) {
        int move;
        ob.print();
        while (true) {
            if (ob.checkLost()) {
                System.out.println("YOU LOST ");
                break;
            }
            if (ob.isFinal()) {
                System.out.println("YOU WIN ");
                break;
            }
            System.out.println("8->up-------2->down-------4->left-------6->right   ");
            move = in.nextInt();
            if (move == 8 && ob.canMove(new position(ob.px - 1, ob.py)))
                ob.move(new position(ob.px - 1, ob.py));
            else if (move == 2 && ob.canMove(new position(ob.px + 1, ob.py)))
                ob.move(new position(ob.px + 1, ob.py));
            else if (move == 4 && ob.canMove(new position(ob.px, ob.py - 1)))
                ob.move(new position(ob.px, ob.py - 1));
            else if (move == 6 && ob.canMove(new position(ob.px, ob.py + 1)))
                ob.move(new position(ob.px, ob.py + 1));
            else System.out.println("CONT MOVE HERE");
            ob.print();
        }
    }

    int c = 0;

    List<structure> path = new LinkedList();

    boolean IsEqual(structure s, List<structure> path) {
        for (structure a : path) {
            if (s.equal(a))
                return true;
        }
        return false;
    }

    boolean dfsFinish = false;


    void DFS(structure s) {
        if (s.isFinal()) {
            dfsFinish = true;
            s.printPath();
            return;
        }
        if (!dfsFinish) {
            List<structure> child = s.get_next_states();
            for (structure a : child) {
                if (!IsEqual(a, path))
                    path.add(s);
                DFS(a);
                c++;
                path.remove(s);
            }
        }
    }

    void BFS(structure s) {
        Queue<structure> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            structure node = q.remove();
            c++;
            if (!IsEqual(node, path)) {
                path.add(node);
                if (node.isFinal()) {
//                         node.print();
                    node.printPath();
                    System.out.println(c);
                    break;
                } else {
                    List<structure> child = node.get_next_states();
                    for (structure k : child) {
                        q.add(k);
                    }

                }
                path.remove(node);

            }
        }

    }

    void UCS(structure s) {
        PriorityQueue<structure> q = new PriorityQueue<>(100, new compare());
        q.add(s);
        while (!q.isEmpty()) {
            c++;
            structure node = q.remove();
            if (!IsEqual(node, path)) {
                path.add(node);
                if (node.isFinal()) {
//                         node.print();
                    node.printPath();
                    System.out.println(c);
                    break;
                } else {
                    List<structure> child = node.get_next_states();
                    for (structure k : child) {
                        q.add(k);
                    }
                }
                path.remove(node);

            }
        }

    }
    int  H_Function(structure s){
        int h=0;
        h+=abs(0-s.px);
        h+=abs(4-s.py);
        return  h;
    }
    void A_STAR(structure s) {
        PriorityQueue<structure> q = new PriorityQueue<>(60000000, new compare2());
        q.add(s);
        while (!q.isEmpty()) {

            structure node = q.remove();
            if (!IsEqual(node, path)) {
                path.add(node);
                c++;
                if (node.isFinal()) {
//                         node.print();
                    node.printPath();
                    System.out.println(c);
                    break;
                } else {
                    List<structure> child = node.get_next_states();
                    for (structure k : child) {
                        k.coast_H=k.coast-H_Function(k);
                        q.add(k);
                    }
                }
                path.remove(node);
            }
        }

    }
}
