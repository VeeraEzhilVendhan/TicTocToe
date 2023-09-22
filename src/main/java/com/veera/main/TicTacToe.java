package com.veera.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe
{
    public static void main(String[] args)
    {

        Scanner scan=new Scanner(System.in);
        String[][] gameBox = new String[][] {{".",".","."},{".",".","."},{".",".","."}};
        Map<String,String> playerDetails=new HashMap<String,String>();
        System.out.println("enter player 1 symbol");
        String player1Symbol=scan.next();
        if(!player1Symbol.equalsIgnoreCase("O") && !player1Symbol.equalsIgnoreCase("X"))
        {
           System.err.println("Invalid Symbol choose only X or O, restart manually");
           System.exit(1);
        }
        System.out.println("enter player 2  symbol");
        String player2Symbol=scan.next();
        if(player2Symbol.equalsIgnoreCase(player1Symbol) || (!player2Symbol.equalsIgnoreCase("O") && !player2Symbol.equalsIgnoreCase("X")))
        {
            System.err.println("Invalid Symbol choose only X or O and Player 2 Symbol can't be Player 1 Symbol, restart manually");
            System.exit(1);
        }
        playerDetails.put("player1", player1Symbol);
        playerDetails.put("player2", player2Symbol);

        boolean playing=true;
        boolean playing1Player=true;
        int gameCount=1;

        while(playing)
        {
            if(playing1Player)
            {
                System.out.println("Player 1 playing.... Enter "+playerDetails.get("player1")+" in any of the place");
            }
            else
            {
                System.out.println("Player 2 playing....Enter "+playerDetails.get("player2")+" in any of the place");
            }
            System.out.println("give i and j value to put your symbol in game box");
            int i=scan.nextInt();
            int j=scan.nextInt();
            if(i>2 || j>2)
            {
                System.out.println("invalid place");
                continue;
            }
            if(!gameBox[i][j].equals("."))
            {
                System.out.println("this place already has a symbol");
                continue;
            }
            System.out.println("give symbol");
            String x_o=scan.next();
            if((playing1Player && playerDetails.get("player2").equalsIgnoreCase(x_o)) || (!playing1Player && playerDetails.get("player1").equalsIgnoreCase(x_o)) || (!x_o.equalsIgnoreCase("o") && (!x_o.equalsIgnoreCase("x"))))
            {
                System.out.println("Wrong symbol");
                continue;
            }
            gameBox[i][j]=x_o;
            printGameBox(gameBox);
            if(gameCount>=5)
            {
                if(checkWin(gameBox, x_o))
                {
                    String playerName=playing1Player?"player 1":"player 2";
                    System.out.println("Game won by "+playerName);
                    playing=false;
                }

            }
            ++gameCount;
            playing1Player=!playing1Player;
        }

        scan.close();

    }


    public static void printGameBox(String[][] gameBox)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(gameBox[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean checkWin(String[][] gameBox, String x_o)
    {
        boolean win=false;
        String wonCheck3="";
        String wonCheck4="";

        for(int i=0;i<3;i++)
        {
            String wonCheck1="";
            String wonCheck2="";

            for(int j=0;j<3;j++)
            {
                wonCheck1=wonCheck1+gameBox[i][j];
                wonCheck2=wonCheck2+gameBox[j][i];
                if(i==j)
                {
                    wonCheck3=wonCheck3+gameBox[i][j];
                }
                if((i==1 && j==1) || (i==2 && j==0) || (i==0 && j==2))
                {
                    wonCheck4=wonCheck4+gameBox[i][j];
                }
            }
            //System.out.print(wonCheck1+" "+wonCheck2+" "+wonCheck3+" "+wonCheck4+" ");
            if(wonCheck1.equalsIgnoreCase("XXX") || wonCheck1.equalsIgnoreCase("OOO") || wonCheck2.equalsIgnoreCase("XXX") || wonCheck2.equalsIgnoreCase("OOO")
                    || wonCheck3.equalsIgnoreCase("XXX") || wonCheck3.equalsIgnoreCase("OOO") || wonCheck4.equalsIgnoreCase("XXX") || wonCheck4.equalsIgnoreCase("OOO"))
            {
                return true;
            }
        }
        //System.out.println(wonCheck4);
        return win;
    }
}
