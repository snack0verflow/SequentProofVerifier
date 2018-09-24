import java.util.*;
class code
{
	public static Node treefy(String s)
	{
		Stack<Node> st=new Stack<>();
		Node root=null;
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			if(c=='~')
			{
				c=s.charAt(++i);
				Node n=new Node(c,true);
				st.push(n);
			}
			else if(c==')')
			{
				Node p=st.pop();
				Node q=st.pop();
				Node r=st.pop();
				q.right=p;
				q.left=r;
				if(st.pop().not)
					q.not=true;
				else
					q.not=false;
				st.push(q);
			}
			else
			{
				Node n=new Node(c,false);
				st.push(n);
			}
		}
		root=st.pop();
		//inorder(root);
		return root;
	}
	public static void inorder(Node n)
	{
		if(n.left!=null)
			inorder(n.left);
		System.out.print(n.data);
		if(n.right!=null)
			inorder(n.right);
	}
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		// String s=in.nextLine();
		// Node tree=null;
		// tree=treefy(s);
		System.out.println("Enter number of statements");
		int n=in.nextInt(),x,y;
		String pro[]=new String[n+1];
		int rule[][]=new int[n+1][3];
		System.out.println("Enter "+n+" statements");
		System.out.println("Rules:\n0. &i\n1. &e1\n2. &e2\n3. |i1\n4. |i2\n5. p followed by line number(s)");
		for(int i=1;i<=n;i++)
		{
			
			pro[i]=in.next();
			rule[i][0]=in.nextInt();
			switch(rule[i][0])
			{
				case 0:
				rule[i][1]=in.nextInt();
				rule[i][2]=in.nextInt();
				break;
				case 1:
				case 2:
				case 3:
				case 4:
				rule[i][1]=in.nextInt();
				break;
				case 5:
			}
		}
		Node proof[]=new Node[n+1];
		for(int i=1;i<=n;i++)
		{
			proof[i]=treefy(pro[i]);
		}
		boolean flag=true;
		for(int i=1;i<=n;i++)
		{
			switch(rule[i][0])
			{
				case 0:
				x=rule[i][1];
				y=rule[i][2];
				Node nn=new Node('&',false);
				nn.left=proof[x];
				nn.right=proof[y];
				if(!nn.equals(proof[i]))
				{
					flag=false;
				}
				break;
				case 1:
				x=rule[i][1];
				if(!proof[x].left.equals(proof[i]))
				{
					flag=false;
				}
				break;
				case 2:
				x=rule[i][1];
				if(!proof[x].right.equals(proof[i]))
				{
					flag=false;
				}
				break;
				case 3:
				x=rule[i][1];
				if(!proof[i].left.equals(proof[x]) || !(proof[i].data=='|'))
				{
					flag=false;
				}
				break;
				case 4:
				x=rule[i][1];
				if(!proof[i].right.equals(proof[x]) || !(proof[i].data=='|'))
				{
					flag=false;
				}
				break;
				case 5:
			}
		}
		if(flag)
			System.out.println("Valid proof");
		else
			System.out.println("Invalid proof");
	}
} 
