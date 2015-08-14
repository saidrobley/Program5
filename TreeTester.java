/*  let the user select a
file containing a sequence of
integers, add those numbers one
at a time into a sack
(implemented by a binary search tree),
display the tree, and let the
user interactively add and remove
numbers
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class TreeTester extends Basic
{
  public static void main(String[] args)
  {
    // example: hard-coded location and size of window:
    TreeTester a = new TreeTester("Simple Text Editor", 0, 0, 900, 600);

  }
 
  private SackBST sack;
  private String inputString;
  private BSTNode parent;
  private BSTNode victim;
  private double horizSpread = 10, vertSpread = 2;
  private String state;

  public TreeTester( String title, int ulx, int uly, int pw, int ph )
  {
    super(title,ulx,uly,pw,ph);

    setBackgroundColor( new Color( 200, 255, 200 ) );

    // camera 0 shows the tree
    cameras.add( new Camera( 10, 30, 
                             880, 520, 
                             -20, 20, 0, 25,
                             Color.white ) );
 
    // camera 1 shows input string
    cameras.add( new Camera( 10, 560,
                             880, 30,
                             0, 100, 0, 5,
                             new Color( 255, 200, 200 ) ) );
 
    sack = new SackBST();
    inputString = "";
    state = "regular";
    String fileName = FileBrowser.chooseFile( true );
    
    try{
      Scanner input = new Scanner( new File(fileName ) );
      int numLines = input.nextInt();  
      input.nextLine();  // toss the rest of line
      System.out.println("input " + numLines + " lines");
      
      for( int k=0; k<numLines; k++ )
      {
        String line = input.nextLine(); 
        sack.add( line );
        System.out.println("added [" + line + "] from data file" );
      }
    }
    catch(Exception e)
    {
      System.out.println("proceeding with no input from file \"data\"");
    }


    super.start();
  }

  public void step()
  {
    Camera cam;

    // draw the tree in camera 0
    cam = cameras.get(0);
    cam.activate();

    cam.setColor( Color.black );

    sack.draw( cam, 0, 23, horizSpread, vertSpread );

    // stub drawing
/*
    cam.drawText("50", 0, 24 );
    cam.drawText("25", -10, 23 );
    cam.drawText("75", 10, 23 );
    cam.drawText("12", -20, 22 );
    cam.drawText("87", 19, 22 );
    cam.drawText("100", 0, 0 );
*/

    // --------------------------

    // draw the input string in camera 1
    cam = cameras.get(1);
    cam.activate();

    cam.setColor( Color.black );
    cam.drawText( inputString, 1, 1 );
    // --------------------------

  }// step method

  public void keyTyped( KeyEvent e )
  {
    char key = e.getKeyChar();

    if( !state.equals( "regular" ) && 'a'<=key && key<='z' )
    {// append digit to inputString
      inputString += key;
    }

    else if( state.equals( "regular" ) && key == 'f' )
    {// start find mode
      state = "find";
      inputString = "";
    }
    else if( state.equals( "regular" ) && key == 'a' )
    {// start add mode
      state = "add";
      inputString = "";
    }
    else if( state.equals( "regular" ) && key == 'r' )
    {// remove a node
      state = "remove";
      inputString = "";
    }

    else if( key == '=' )
    {// increase horizontal spread
      horizSpread *= 1.2;
    }
    else if( key == '-' )
    {// decrease horizontal spread
      horizSpread /= 1.2;
    }

    

  }

  public void keyPressed( KeyEvent e )
  {
    int code = e.getKeyCode();
 
    if( code == KeyEvent.VK_BACK_SPACE && inputString.length() > 0 )
    {
      inputString = inputString.substring( 0, inputString.length()-1 );
    }
    else if( state.equals( "add" ) && code == KeyEvent.VK_ENTER )
    {
      state = "regular";
      sack.add( inputString );
      inputString = "";
    }
    else if( state.equals( "remove" ) && code == KeyEvent.VK_ENTER )
    {
      state = "regular";
      sack.find(inputString);
      sack.remove();
      inputString = "";
    }

    else if( state.equals( "find" ) && code == KeyEvent.VK_ENTER )
    {
      state = "regular";
      System.out.println("find resulted in: " + sack.find( inputString ) );
      inputString = "";
    }
    else if( code == KeyEvent.VK_LEFT )
    {
      cameras.get(0).shiftRegion( -0.25, 0 );
    }
    else if( code == KeyEvent.VK_RIGHT )
    {
      cameras.get(0).shiftRegion( 0.25, 0 );
    }

  }

}