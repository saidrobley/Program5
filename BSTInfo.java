public class BSTInfo
{
  public boolean found;  // whether the target was found
  public BSTNode parent; // parent of the node that has the target
                         // null if the root data is the target
  public char leftOrRight;  // 'l' means left child of parent,
                           // 'r' means right child

  public BSTInfo( boolean foundIn, BSTNode nodeIn, char lr )
  {
    found = foundIn;
    parent = nodeIn;
    leftOrRight = lr;
  }

  public String toString()
  {
    return "<" + found + " " + parent.id + " " + leftOrRight + ">";
  }

}