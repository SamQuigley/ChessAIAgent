import java.util.*;

public class AIAgent
{
  Random rand;
  public AIAgent()
  {
    rand = new Random();
  }
  /*
    The method randomMove takes as input a stack of potential moves that the AI agent
    can make. The agent uses a rondom number generator to randomly select a move from
    the inputted Stack and returns this to the calling agent.
  */
  public Move randomMove(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("RANDOM AI : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
    }
  /*
  NEXT BEST MOVE
  */
  public Move nextBestMove(Stack movesWhite, Stack movesBlack)
  {
    /*
    initilising needed variables / data structures
    */
    int blackWeight = 0;
    int piece = 0;
    Move whiteMove, move, captureMove;
    captureMove = null;
    Square blackPiece;
    Stack white = (Stack) movesWhite.clone();
    Stack black = (Stack) movesBlack.clone();
    /*
    The next best approach I have taken is a greedy best, in the sense the strategy is to capture black pieces .
    I havent tried to implement different strategies.
    this function looks for black pieces that can be captured
    each piece has been assigned a vlaue / weight
    if a white piece can capture two pieces it will choose the one with more value --> Queen over Pawn etc.....
    */
    while (!movesWhite.isEmpty())
    {
      whiteMove = (Move) movesWhite.pop();
      move = whiteMove;
        while (!black.isEmpty())
        {
          blackWeight = 0;
          blackPiece = (Square) black.pop();
          if ((move.getLanding().getXC() == blackPiece.getXC()) && (move.getLanding().getYC() == blackPiece.getYC()))
          {
            if (blackPiece.getName().contains("Pawn"))
            {
              blackWeight=1;
            }
            else if (blackPiece.getName().contains("Bishop")||blackPiece.getName().contains("Knight"))
            {
              blackWeight=3;
            }
            else if (blackPiece.getName().contains("Rook"))
            {
              blackWeight=5;
            }
            else if (blackPiece.getName().contains("Queen"))
             {
              blackWeight=9;
            }
            else if (blackPiece.getName().contains("King"))
             {
              blackWeight=Integer.MAX_VALUE; //used so that AI knows this is = game end, biggest 32 bit number is the blackWeight og the king
            }
          }
          if (piece < blackWeight)
           {
            piece = blackWeight;
            captureMove = move;
          }
        }
      }
      if (piece > 0)
      {
        System.out.println("NEXT BEST AI :" + piece);
        return captureMove;
      }
      else
      {
        return randomMove(white);
      }
    }
  }
/*
did not have time to do part three -> I have to turn my attention to the Software Project Mid Point presentation
*/
