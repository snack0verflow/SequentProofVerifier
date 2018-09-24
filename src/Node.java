
class  Node
{
	char data;
	boolean not;
	Node left;
	Node right;
	public Node()
	{
	}
	public Node(char ch, boolean b)
	{
		data=ch;
		not=b;
		right=null;
		left=null;
	}
	public boolean equals(Node root)
	{
		if (root == this)
		{
			return true;
		}
		if (root == null || this == null)
		{
			return false;
		}
		boolean b=root.data==this.data;
		if(this.left!=null && root.left!=null)
			b&=this.left.equals(root.left);
		if(this.right!=null && root.right!=null)
			b&=this.right.equals(root.right);
		return b;
	}
}
