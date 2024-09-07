import java.util.LinkedList;
import java.util.List;

public class structure {
    int coast = 0;
    int n ,m ;
    int px , py ;
    int coast_H;
    structure father;
    List<structure>myPath =new LinkedList<>();
    boolean R  , k ;
    char board[][] = new char[6][6] ;
    structure(){
        R =false ;
        k = false ;
        //my position
        px =  5;
        py = 3 ;

        n = 6 ;
        m = 6 ;
        for(int i=0 ; i<6 ; i++)
            for(int j=0 ; j<6 ; j++){
                board[i][j] = '.' ;
            }
        selectR(4,1) ;
        selectR(1,4) ;
        selectHole(5,4) ;
        selectHole(5,0) ;
        selectHole(5,5) ;
        selectHole(0,5) ;
        selectHole(3,2) ;
        board[px][py] = 'M';
        board[0][4]='K';
    }

    //add ruk to board
    void selectR(int x , int y ){
        board[x][y] = 'R' ;
    }
    ///add hole to board
    void selectHole(int x , int y ){
        board[x][y] = ' ' ;
    }
    //
    void move(position p) {
        father = deep_copy() ;
        myPath.add(father);
        coast++;
        if (R) {
            if (board[p.x][p.y] == 'K') k = true;
            board[px][py] = 'R';
            board[p.x][p.y] = 'M';
            R = false;
            coast++;
        } else if (k) {
            if (board[p.x][p.y] == 'R') R = true;
            board[px][py] = 'K' ;
            board[p.x][p.y] = 'M';
            coast++;
        } else {
            board[px][py] = ' ';
            if (board[p.x][p.y] == 'R') R = true;
            if (board[p.x][p.y] == 'K') k = true;
            board[p.x][p.y] = 'M';
            coast++;
        }
        ///change location
        px = p.x ;
        py = p.y ;
    }
    List <position> check_move(){
        List<position> moves = new LinkedList() ;
        if (canMove(new position(px-1 , py ))) moves.add(new position(px-1 , py)) ;
        if (canMove(new position(px , py+1 ))) moves.add(new position(px , py+1)) ;
        if (canMove(new position(px+1 , py ))) moves.add(new position(px+1 , py)) ;
        if (canMove(new position(px , py-1 ))) moves.add(new position(px , py-1)) ;
        return moves ;
    }
    //get list of structure after move
    List<structure> get_next_states(){
        List<structure> next_states = new LinkedList() ;
        List<position> moves = check_move() ;
        for(position k : moves) {
            structure s = deep_copy();
            s.move(k);
            next_states.add(s);
        }
        return next_states ;
    }

    structure deep_copy(){
        structure s = new structure();
        s.father=this.father;
        s.coast=this.coast;
        s.R = this.R ;
        s.m = this.m ;
        s.n = this.n ;
        s.px = this.px ;
        s.py = this.py ;
        for (int i=0 ; i<6 ; i++)
            for(int j=0 ; j<6 ; j++)
                s.board[i][j] = this.board[i][j] ;
        return s ;
    }


    boolean equal(structure s){
        if (s.px != this.px || s.py != this.py ) return false ;
        for(int i=0 ; i<6 ; i++)
            for (int j=0 ; j<6 ; j++)
                if (s.board[i][j] != this.board[i][j])
                    return false;
        return true ;
    }
    ///testing
//    void printmoves(){
//        List<position > a  = check_move() ;
//        for( position k : a){
//            System.out.println(k.x + " " + k.y );
//        }
//    }
    void printNext(){
        List<structure> a = get_next_states() ;
        for (structure k : a ){
            k.print();
        }
    }
    boolean canMove(position p){
        if (p.x < 0 || p.x > 5 || p.y < 0 || p.y > 5 ) return false ;
       else if (board[p.x][p.y] == ' ') return false ;
       else return true ;
    }
    boolean checkLost(){
        boolean up = px==0 || board[px-1][py] == ' ' ;
        boolean down = px==5 || board[px+1][py] == ' ' ;
        boolean left = py==0 || board[px][py-1] == ' ' ;
        boolean right = py==5 || board[px][py+1] == ' ' ;
        if(up && down && left && right)
            return true ;
        return false ;
    }
    boolean isFinal(){
        if(px!= 0 || py != 4)
            return false ;
        else {
            for(int i=0 ; i<6 ; i++)
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == '.')
                        return false ;
                }
            return true ;
        }
    }
    void print(){
        System.out.println("   ------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print( "      "+ board[i][j]  );
                if (j==5 ) System.out.print("      ");
            }
            System.out.println("");
            System.out.println("  ------------------------------------------------");
//
        }
    }
    void printPath(){
        int i=0;
        structure s=this;
        while (s!=null){
            s.print();
            s=s.father;
            System.out.println("NODE "+i++);
        }
    }
    boolean isFinal_A_Stare(){
        if(px!= 0 || py != 4)
            return false ;
        else
            return true ;
    }

}
