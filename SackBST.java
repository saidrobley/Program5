public class SackBST
{
  private BSTNode root;
  private int n;

  private BSTInfo info; // infor.parent

  public SackBST()
  {
    root = null;
    n = 0;
  }

  public void add( String s )
  {
    if( root == null )
    {
      root = new BSTNode( s );// creates root node.
    }
    else
    {
      root.add( s );
    }
    n++;
  }
  
//Reserved for Exercise 5:
 public void remove()
 {
	 if (info.found) {
		 if (info.parent == null) {
			 if (root.left == null && root.right == null) {
				 root = null;
			 } else {
				 root.remove(root.data);
			 }
		 } else {
		 
			 if (info.leftOrRight == 'l') {
				 root.remove(info.parent.left.data);
			 } else {
				 root.remove(info.parent.right.data);
			 }
		 }
	 }
 }

  public boolean find( String s )
  {
    if( root == null )
    {
      info = new BSTInfo( false, null, ' ' );
      return false;
    }
    else
    {
      info = root.find( s );
      return info.found;
    }
  }

  // return the found data
  // (assumes find succeeded)
  public String get()
  {
    if( info.leftOrRight == 'l'  )
      return info.parent.left.data;
    else if( info.parent == null )
      return root.data;
    else  // info.leftOrRight == 'r'
      return info.parent.right.data;
  }

  

  public int size()
  {
    return n;
  }

  public void show()
  {
    if( root == null )
      System.out.println("this tree is empty");
    else
      root.show( "" );
  }

  public void draw( Camera cam, double x, double y, double hs, double vs )
  {
    if( root != null )
      root.draw( cam, x, y, hs, vs );
  }

  public static void main(String[] args)
  {
    SackBST sack = new SackBST();

    sack.add( "cow" );
    sack.add( "moose" );
    sack.add( "koala" );
    sack.add( "quail" );
    sack.add( "hippo" );
    sack.add( "dog" ); 
    sack.show();

    System.out.println("find on \"hyena\" produced " + sack.find( "hyena" ) );
    boolean result = sack.find("quail");
    System.out.println("find on \"quail\" produced " + sack.info + " result: " +
       result );
    
  }

}