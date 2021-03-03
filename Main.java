import com.cricket.Cricket;
import com.cricket.MatchMethod;
import java.util.Scanner;

import static java.text.MessageFormat.*;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
    Cricket c= Cricket.getCricket();
    MatchMethod m=MatchMethod.getMethod();
    c.setTotalOvers(1);
    c.setTotalWickets(3);
    System.out.println("Enter the team to Bat First 1 or 2 : ");
    int choice = sc.nextInt();
    System.out.println(format("Team {0} batting First", choice));
    System.out.println();
    c.setTeamBattingFirst(choice);
    m.battingFirst(c);
    System.out.println("Team "+c.getTeamBattingFirst()+" Score:"+c.getBattingFirstScore()+"/"+c.getTotalWicketsBattingFirst());
    System.out.println("First Innings Over");
    System.out.println();
    if(choice==1){
        m.battingSecond(2,c);
    }
    else{
        m.battingSecond(1,c);
    }
    }
}
