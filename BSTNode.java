public class BSTNode
{
  public static int currentId = 0;

  public int id;
  public BSTNode left;
  public String data;
  public BSTNode right;

  public BSTNode( String s )
  {
    currentId++;
    id = currentId;

    left = null;
    data = s;
    right = null;
  }
  //
  public void remove(String s)
  {
	BSTInfo fnd = find(s);
	
	
	if (fnd.parent == null) 
	{
		// the node being removed has no parent
		// which means it's the root.
		if (left == null) 
		{// root has right child
			data = right.data;
			left = right.left;
			right = right.right;
			
		} 
		else if (right == null)
		{// root has left child
			data = left.data;
			right = left.right;
			left = left.left;
			
		} 
		else 
		{
			BSTNode temp = right;
			
			if(temp.left == null)
			{
				data = temp.data;
				right = temp.right;
			}
			else if(temp.left != null)
			{
				if(temp.left.left == null && temp.left.right == null)
				{
					data = temp.left.data;
					temp.left = null;
				}
				else if(temp.left.left == null & temp.left.right !=null)
				{
					BSTNode temp2 = temp.left.right;
					data = temp.left.data;
					temp.left = temp2;
				}
				else
				{
					while(temp.left.left != null)
					{
						temp = temp.left;
					}
					if(temp.right == null)
					{
						data = temp.left.data;
						temp.left = null;
					}
					else
					{
						data = temp.left.data;
						BSTNode temp2 = temp.left.right;
						temp.left = null;
						temp.left = temp2;
						//temp.left = temp.left.right;
						//temp.left.right = null;
					}
					
				}
			}
			
				
		}		
			
		
		return;
	}// end of case1: root node is being removed.
    //=====================================================	
	boolean left = fnd.leftOrRight == 'l';
	BSTNode victim;
	
	if(left){// the node has only left child
		victim = fnd.parent.left;
	}
	else{// the node has only right.
		victim = fnd.parent.right;
	}
	
	if(victim.left == null && victim.right == null){
		// the node being removed has no children
		// the node being remove is either right or left 
		// of its parent.
		
		if(left){
			// if it's left go up to the parent and set its left
			// to null.
			fnd.parent.left = null; 
		}
		else{
			// if it's right go up to the parent and set 
			// its right to null
			fnd.parent.right = null;
		}
		
	} // end of case 2: it has no children.
    //======================================================
	else if (victim.left == null) {
		// the node being removed has only right child
		if (left) {
			fnd.parent.left = victim.right;
		} else {
			fnd.parent.right = victim.right;
		}
		
	} else if (victim.right == null) {
		// the node being removed has only left child
		if(left){
			fnd.parent.left = victim.left;	
		}
		else{
			fnd.parent.right = victim.left;
		}
		
	} // end of case 3: it has only one child either right or left.
    // ============================================================
	else 
	{
		BSTNode temp = victim.right;
		if(temp.right == null && temp.left == null)
		{
			victim.data = temp.data;
			victim.right = null;
			
		}
		else if(temp.left != null && temp.right != null)
		{
			if(temp.left.left == null && temp.left.right != null)
			{	
				//victim.data = temp.left.data;
				//temp.left= null;
				BSTNode temp2 = temp.left.right;
				victim.data = temp.left.data;
				temp.left = temp2;
			}
			else
			{
				while(temp.left.left != null)
				{
					temp = temp.left;
				}
				if(temp.right == null)
				{
					victim.data = temp.data;
				}
				else
				{
					BSTNode temp2 = temp.left.right;
					victim.data = temp.left.data;
					temp.left = temp2;
				}
				
			}
		
			
		}
		else if(temp.left == null && temp.right != null)
		{
			victim.right = temp.right;
			victim.data = temp.data;
			
		}
		else if(temp.left != null && temp.right == null)
		{
			if(temp.left.left == null)
			{
				
				victim.data = temp.left.data;
				temp.left = null;
			}
			else
			{
				while(temp.left.left != null)
				{
					temp = temp.left;
				}
				if(temp.right == null)
				{
					victim.data = temp.data;
				}
				else
				{
					victim.data = temp.left.data;
					temp.left = temp.right;
					//BSTNode temp2 = temp.left.right;
					//victim.data = temp.left.data;
					//temp.left = temp2;
					
				}
				
			}
		
	
		}
		
		
			
	}		
		
		
	 // end of case 4: both children are present.
    // =========================================================
	
		
  }
  
  public BSTInfo find( String s )
  {// returns BSTInfo type.
    if( s.compareTo( data ) == 0 )
    {// can only happen if this node is the root and has the target
      return new BSTInfo( true, null, ' ' );
    }
    else if( s.compareTo( data ) < 0 )
    {// s belongs in left sub-tree
      if( left == null )
      {// have no left child
        return new BSTInfo( false, null, ' ' );
      }
      else
      {// have a left child
        if( left.data.equals( s ) )
          return new BSTInfo( true, this, 'l' ); 
        else
          return left.find( s );
      }
    }
    else 
    {// s belongs in right sub-tree
      if( right == null )
      {// have no right child
        return new BSTInfo( false, null, ' ' );
      }
      else
      {// have a right child
        if( right.data.equals( s ) )
          return new BSTInfo( true, this, 'r' );
        else
          return right.find( s );
      }
    }
  }
  

  public void add( String s )
  {
    if( data.compareTo( s ) > 0 )
    {// add s to my left subtree
      if( left == null )
        left = new BSTNode( s );
      else
        left.add( s );
    }
    else
    {// add s to my right subtree
      if( right == null )
        right = new BSTNode( s );
      else
        right.add( s );
    }
 
  }
  

  private static String niceId( BSTNode node )
  {
    if( node == null )
      return "null";
    else
      return ""+node.id;
  }

  public void show( String indent )
  {
    System.out.println( indent + 
    "Node #" + id + " has data: [" + data + "], has left: " + niceId(left) + 
                                         " and right: " + niceId(right) );
    if( left != null )
      left.show( indent + "    " );

    if( right != null )
      right.show( indent + "    " );
  }

  public void draw( Camera cam, double x, double y, double hs, double vs )
  {
    cam.drawText( data, x, y );
    if( left != null )
    {
      left.draw( cam, x-hs, y-vs, hs/2, vs );
      cam.drawLine( x, y, x-hs, y-vs+0.3*vs );
    }
    if( right != null )
    {
      right.draw( cam, x+hs, y-vs, hs/2, vs );
      cam.drawLine( x, y, x+hs, y-vs+0.3*vs );
    }
  }

}