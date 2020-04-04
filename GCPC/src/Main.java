import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int teams = fio.nextInt();
        int events = fio.nextInt();
        AVL avl = new AVL(teams);
        Team[] team_tracker = new Team[teams];
        for(int j = 1;j<=teams; j++) { //initialise all the teams in the competition
            Team to_add = new Team(j,0,0);
            avl.insert(to_add);
            team_tracker[j-1] = to_add;
        }
        for(int i = 0; i<events; i++) {
            int team_num = fio.nextInt();
            int task_penalty =  fio.nextInt();
            Team team =  team_tracker[team_num-1];
            avl.delete(team);
            team.updateTeam(task_penalty);
            team_tracker[team_num-1] = team;
            avl.insert(team);
            fio.println(avl.rankOfTeam(team_tracker[0]));
        }
        fio.flush();
    }
}

class Team implements Comparable<Team>{
    public int number, solved, penalty;

    public Team(int number, int solved, int penalty){
        this.number = number;
        this.solved =solved;
        this.penalty = penalty;
    }

    public void updateTeam(int penalty) {
        this.penalty += penalty;
        this.solved++;
    }

    @Override
    public int compareTo(Team ip) {
        if(ip.solved != this.solved) {
            return (this.solved - ip.solved); //order based on number of solved problems
        } else if (ip.solved == this.solved && ip.penalty != this.penalty) { //order based on penalty; smaller penalty -> higher ranking
            return (ip.penalty - this.penalty);
        } else {
            return (ip.number - this.number);
        }
    }
}

class AVLVertex {
    AVLVertex(Team values) {
        parent = left = right = null;
        height = -1;
        size =1;
        this.teamInfo = values;
    }
    public AVLVertex parent, left, right;
    public int height;
    public int size;
    public Team teamInfo;
}

class AVL {
    public AVLVertex root;
    final int teams;

    public AVL(int teams) {
        root = null;
        this.teams = teams;
    }

    // public method called to search for a Team t
    // Return Team if it is found in the AVL otherwise return null
    public Team search(Team t) {
        AVLVertex res = search(root, t);
        return res == null ? null : res.teamInfo;
    }

    // helper method to perform search
    public AVLVertex search(AVLVertex T, Team t) {
        if (T == null) {
            return null;                     // not found
        }
        else if (T.teamInfo.compareTo(t) == 0) {
            return T;                        // found
        }
        else if (T.teamInfo.compareTo(t) < 0) {
            return search(T.right, t);       // search to the right
        }
        else {
            return search(T.left, t);        // search to the left
        }
    }

    public Team findMin() {
        return findMin(root);
    }

    // helper method to perform findMin
    public Team findMin(AVLVertex T) {
        if (T.left == null) return T.teamInfo;                    // this is the min
        else return findMin(T.left);           // go to the left
    }

    public Team findMax() {
        return findMax(root);
    }

    // helper method to perform findMax
    public Team findMax(AVLVertex T) {
        if (T.right == null) return T.teamInfo;                   // this is the max
        else return findMax(T.right);        // go to the right
    }

    // public method to find successor to given Team t in AVL.
    public Team successor(Team t) {
        AVLVertex vPos = search(root, t);
        return vPos == null ? null : successor(vPos);
    }

    // helper recursive method to find successor to for a team t
    public Team successor(AVLVertex T) {
        if (T.right != null)                       // this subtree has right subtree
            return findMin(T.right);  // the successor is the minimum of right subtree
        else {
            AVLVertex par = T.parent;
            AVLVertex cur = T;
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur == par.right)) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? null : par.teamInfo;           // this is the successor of T
        }
    }

    // public method to find predecessor to given Team t in AVL
    public Team predecessor(Team t) {
        AVLVertex vPos = search(root, t);
        return vPos == null ? null : predecessor(vPos);
    }

    // helper recursive method to find predecessor to for a given vertex T in AVL
    public Team predecessor(AVLVertex T) {
        if (T.left != null)                         // this subtree has left subtree
            return findMax(T.left);  // the predecessor is the maximum of left subtree
        else {
            AVLVertex par = T.parent;
            AVLVertex cur = T;
            // if par(ent) is not root and cur(rent) is its left children
            while ((par != null) && (cur == par.left)) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? null : par.teamInfo;           // this is the successor of T
        }
    }

    // public method called to insert a new Team into AVL
    public void insert(Team t) {
        root = insert(root, t);
    }

    // helper recursive method to perform insertion of new Team into AVL
    public AVLVertex insert(AVLVertex T, Team t) {
        if (T == null) return new AVLVertex(t);          // insertion point is found

        if (T.teamInfo.compareTo(t) < 0) {                                      // search to the right
            T.right = insert(T.right, t);
            T.right.parent = T;
        } else {                                                 // search to the left
            T.left = insert(T.left, t);
            T.left.parent = T;
        }
        AVLVertex avt = updateHandS(T);
        AVLVertex newT = balanceTree(avt);
        return newT;
    }

    // public method to delete a Team t from AVL
    public void delete(Team t) {
        root = delete(root, t);
    }

    // helper recursive method to perform deletion
    public AVLVertex delete(AVLVertex T, Team t) {
        if (T == null) {return T;}              // cannot find the item to be deleted
        if (T.teamInfo.compareTo(t) < 0) {                        // search to the right
            T.right = delete(T.right, t);
        }else if (T.teamInfo.compareTo(t) >0) {                           // search to the left
            T.left = delete(T.left, t);
        } else {                                            // this is the node to be deleted
            if (T.left == null && T.right == null) {               // this is a leaf
                T = null;                                      // simply erase this node
            }else if (T.left == null && T.right != null) {   // only one child at right
                T.right.parent = T.parent;
                T = T.right;                                                 // bypass T
            } else if (T.left != null && T.right == null) {    // only one child at left
                T.left.parent = T.parent;
                T = T.left;                                                  // bypass T
            } else {                                 // has two children, find successor
                Team successorV = successor(t);
                T.teamInfo = successorV;         // replace this key with the successor's key
                T.right = delete(T.right, successorV);      // delete the old successorV
            }
        }
        AVLVertex avt = updateHandS(T);
        AVLVertex newT = balanceTree(avt);
        return newT;
    }

    public AVLVertex rotateLeft(AVLVertex T) {
        if (T == null) {
            return null;
        }
        AVLVertex w = T.right;
        if (T.right == null) {
            return null;
        }
        w.parent = T.parent;
        T.parent = w;
        T.right =  w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T;
        AVLVertex t1 = updateHandS(T);
        AVLVertex w_new = updateHandS(w);
        return w_new;
    }

    public AVLVertex rotateRight(AVLVertex T) {
        if (T == null) { //check if T is null
            return null;
        }
        AVLVertex w = T.left;
        if (w == null) { //check if w is null
            return null;
        }
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if(T.left != null) {
            w.right.parent = T;
        }
        w.right = T;
        AVLVertex t1 = updateHandS(T);
        AVLVertex w_new = updateHandS(w);
        return w_new;
    }

    public int rankOfTeam(Team t) {
        int r = rankOfTeam(root, t); //this gives us the ranking in ascending order; i.e. returns number of teams with score/etc lower than our team 1
        return this.teams -r + 1; // however, we want the number of teams team 1 is better than, so need to "inverse" this number
    }

    public int rankOfTeam(AVLVertex vt, Team t) { //need to modify using .compareTO later
        if(vt.teamInfo.compareTo(t) == 0) {
            if(vt.left!= null) {
                return vt.left.size + 1;
            } else {
                return 1;
            }
        } else if (vt.teamInfo.compareTo(t) > 0) {
            return rankOfTeam(vt.left,t);
        } else {
            if(vt.left != null) {
                return vt.left.size + 1 + rankOfTeam(vt.right, t);
            } else {
                return 1 + rankOfTeam(vt.right, t);
            }
        }
    }

    public int bf(AVLVertex avlt) {
        if (avlt == null) {return 0;}
        if(avlt.left != null && avlt.right != null) {
            return (avlt.left.height - avlt.right.height);
        } else if (avlt.left == null && avlt.right != null) {
            return (0-avlt.right.height);
        } else if (avlt.left != null && avlt.right == null) {
            return (avlt.left.height - 0);
        } else {
            return 0;
        }
    }

    public AVLVertex updateHandS(AVLVertex T) {
        if(T == null) {return T;}
        if(T.left != null && T.right != null) {
            T.height = Math.max(T.left.height, T.right.height) + 1;
            T.size = T.left.size + T.right.size + 1;
        } else if (T.left == null && T.right != null) {
            T.height = T.right.height + 1;
            T.size = T.right.size + 1;
        } else if (T.left != null && T.right == null) {
            T.height = T.left.height +1;
            T.size = T.left.size +1 ;
        } else {
            T.size = 1;
            T.height = 0;
        }
        return T;
    }

    public AVLVertex balanceTree(AVLVertex T) {
        if (T == null) {
            return null;
        }
        int bfactor_X = bf(T);
        int bfactor_R = bf(T.right);
        int bfactor_L = bf(T.left);

        if(bfactor_X ==2 && (0<= bfactor_L && bfactor_L <=1)){
            T = rotateRight(T);
        }
        if(bfactor_X ==2 && bfactor_L == -1) {
            T.left = rotateLeft(T.left);
            T = rotateRight(T);
        }
        if (bfactor_X == -2 && (-1 <= bfactor_R && bfactor_R <= 0)){
            T = rotateLeft(T);
        }
        if(bfactor_X == -2 && bfactor_R == 1) {
            T.right = rotateRight(T.right);
            T = rotateLeft(T);
        }
        return T;
    }
}

class FastIO extends PrintWriter
{
    BufferedReader br;
    StringTokenizer st;

    public FastIO()
    {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}