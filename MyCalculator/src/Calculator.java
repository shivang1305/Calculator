import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Calculator extends WindowAdapter implements ActionListener,MouseMotionListener,MouseListener,KeyListener
{
	JFrame f;
	JPanel p;
	JLabel l1,l2;
	JButton b0,b1;
	JButton b[];
	Font ft,ft1;
	String op,op1,op2,ct;
	int k,dot;
    Calculator()
    {
    	try
    	{
    		 f=new JFrame("Calculator");
    	     p=new JPanel();
             f.setLayout(null);
    	     p.setLayout(new GridLayout(4,4,5,5));
    	     f.addWindowListener(this);

             l1=new JLabel("0");
             l1.addKeyListener(this);
    	     l2=new JLabel();
    	     l2.addKeyListener(this);

    	     b0=new JButton("CE");
    	     b1=new JButton("C");
    	     b0.addActionListener(this);
    	     b0.addMouseListener(this);
    	     b0.addKeyListener(this);
    	     b1.addActionListener(this);
    	     b1.addMouseListener(this);
    	     b1.addKeyListener(this);
    	     
    	     b=new JButton[16];

    	     ft=new Font("Elephant",1,16);
    	     ft1=new Font("Arial",1,30);
    	        
             for(int i=0;i<=9;i++)
    	     {	 
    	       	 b[i]=new JButton(""+i);
    	         b[i].addActionListener(this);
    	         b[i].addMouseListener(this);
    	         b[i].addKeyListener(this);
    	         b[i].setFont(ft1);
    	         b[i].setBackground(Color.white);
    	     }  
    	     b[10]=new JButton("+");
    	     b[11]=new JButton("-");
    	     b[12]=new JButton("*");
    	     b[13]=new JButton("/");
    	     b[14]=new JButton("=");
    	     b[15]=new JButton(".");
    	        
            for(int i=10;i<=15;i++)
    	    {
    	        b[i].addActionListener(this);
    	       	b[i].addMouseListener(this);
    	       	b[i].addKeyListener(this);
    	       	b[i].setFont(ft1);
    	       	b[i].setBackground(Color.cyan);
            }    
    	        
    	    p.setBounds(0,150,390,414);
            l1.setBounds(5,100,385,50);
            l2.setBounds(5,56,385,44);
            b0.setBounds(320,3,68,50);
            b1.setBounds(0,0,48,50);
    	    
            b1.setFont(ft);
    	    b0.setFont(ft);
    	    l1.setFont(ft1);
    	    l1.setBackground(Color.lightGray);
    	    l2.setBackground(Color.lightGray);
    	    
    	    b0.setBackground(Color.cyan);
    	    b1.setBackground(Color.cyan);
    	    
    	    p.add(b[7]);
    	    p.add(b[8]);
    	    p.add(b[9]);
    	    p.add(b[13]);
    	    p.add(b[4]);
    	    p.add(b[5]);
    	    p.add(b[6]);
    	    p.add(b[12]);
    	    p.add(b[1]);
    	    p.add(b[2]);
    	    p.add(b[3]);
    	    p.add(b[11]);
    	    p.add(b[14]);
    	    p.add(b[0]);
    	    p.add(b[15]);
    	    p.add(b[10]);
    	    
    	    f.add(p);
    	    f.add(l1);
    	    f.add(l2);
    	    f.add(b0);
    	    f.add(b1);

    	    f.setResizable(false);
    	    f.setVisible(false);
    	    f.setSize(405,600);

    	}
    	catch(Exception e)
    	{
    		JOptionPane.showMessageDialog(null,e.getMessage());
    	}
    }
    public void Actions(JButton bt)
    {
    	if(bt==b[0]||bt==b[1]||bt==b[2]||bt==b[3]||bt==b[4]||bt==b[5]||bt==b[6]||bt==b[7]||bt==b[8]||bt==b[9])
    	{
    		if(k==0)
    		{
    			l1.setText(bt.getText());
    			k=1;
    		}
    		else
    			l1.setText(l1.getText()+bt.getText());
    	}
    	else if(bt==b[15] && dot==0)
    	{
    		if(k==0)
    		{
    			l1.setText(bt.getText());
    			k=1;
    			dot=1;
    		}
    		else
    		{
    			l1.setText(l1.getText()+bt.getText());
    			dot=1;
    		}	
    	}
    	else if(bt==b[10]||bt==b[11]||bt==b[12]||bt==b[13])
    	{
    		if(op!=null && op2==null)
    		{
    			op2=l1.getText();
    			calculate(op1,op,op2);
    			op=bt.getText();
    			op1=l1.getText();
    			l2.setText(l2.getText()+op2+op);
    			op2=null;
	    	}
    		else 
    		{
    			op=bt.getText();
    			op1=l1.getText();
    			l2.setText(l2.getText()+op1+op);
    			k=0;
    			dot=0;
    		}
    	}
    	else if(bt==b[14])
    	{
    		l2.setText("");
    		if(op==null && op2==null)
    			op1=l1.getText();
    		if(op==null && op2==null && op1!=null)
    		{
    			op2=""+0;
    			op="+";
    		}
    		else
    		    op2=l1.getText();
    		calculate(op1,op,op2);
    		k=0;
    		dot=0;
    		op=null;
    		op1=null;
    		op2=null;
    	}
    	else if(bt==b0)
    	{
    		k=0;
    		dot=0;
    		l1.setText("0");
    		l2.setText("");
    		op=null;
    		op1=null;
    		op2=null;
    	}
    	if(bt==b1)
    	{
    		StringBuilder str=new StringBuilder(l1.getText());
    		int l=str.length();
    		if(l<=1)
    		{
    			l1.setText("0");
    			k=0;
    			return;
    		}
    		else
    		{
    			str.deleteCharAt(l-1);
        		l1.setText(str.toString());
    		
    		}      		       		
    			
      	}
    }
    public void calculate(String op1,String op,String op2)
    {
    	try
    	{
    		float x,y,z;
        	x=Float.parseFloat(op1);
        	y=Float.parseFloat(op2);
        	if(op.equals("+"))
        		z=x+y;
        	else if(op.contentEquals("-"))
        		z=x-y;
        	else if(op.contentEquals("*"))
        		z=x*y;
        	else 
        		z=x/y;
        	int f=Math.round(z);
        	if(f==z)
        		l1.setText(""+f);
        	else
        		l1.setText(""+z);
        	k=0;
        	dot=0;
    	}
    	catch(Exception e)
    	{
    		JOptionPane.showMessageDialog(null,e.getMessage());
    	}
    }
    public void keyPressed(KeyEvent e)
    {
    	JButton bt=null;
    	if(e.getKeyChar()=='\b')
    		bt=b1;
    	if(e.getKeyChar()=='\n')
    		bt=b[14];
    	String strg="";
    	strg+=e.getKeyChar();
    	for(int i=0;i<=15;i++)
    	{
    		if(strg.equals(b[i].getText()))
    		{
    			bt=b[i];
    		}
    	}
    	if(bt==b[0]||bt==b[1]||bt==b[2]||bt==b[3]||bt==b[4]||bt==b[5]||bt==b[6]||bt==b[7]||bt==b[8]||bt==b[9])
    		bt.setBackground(Color.cyan);
    	else 
    		bt.setBackground(Color.blue);
    }
    public void keyReleased(KeyEvent e)
    {
    	JButton bt=null;
    	if(e.getKeyChar()=='\b')
    		bt=b1;
    	if(e.getKeyChar()=='\n')
    		bt=b[14];
    	String strg="";
    	strg+=e.getKeyChar();
    	for(int i=0;i<=15;i++)
    	{
    		if(strg.equals(b[i].getText()))
    		{
    			bt=b[i];
    		}
    	}
    	if(bt==b[0]||bt==b[1]||bt==b[2]||bt==b[3]||bt==b[4]||bt==b[5]||bt==b[6]||bt==b[7]||bt==b[8]||bt==b[9])
    		bt.setBackground(Color.white);
    	else 
    		bt.setBackground(Color.cyan);
    }
    public void keyTyped(KeyEvent e)
    {
    	JButton bt=null;
    	if(e.getKeyChar()=='\b')
    		bt=b1;
    	if(e.getKeyChar()=='\n')
    		bt=b[14];
    	String strg="";
    	strg+=e.getKeyChar();
    	for(int i=0;i<=15;i++)
    	{
    		if(strg.equals(b[i].getText()))
    		{
    			bt=b[i];
    		}
    	}
    	Actions(bt);
    }
    public void actionPerformed(ActionEvent e)
    {	
    	try
    	{
    		JButton bt=(JButton)e.getSource();
        	Actions(bt);
    	}	
    	catch(Exception e1)
    	{
    		JOptionPane.showMessageDialog(null,e1.getMessage());
    	}
    }
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)
    {
    	JButton bt=(JButton)e.getSource();
    	if(bt==b[0]||bt==b[1]||bt==b[2]||bt==b[3]||bt==b[4]||bt==b[5]||bt==b[6]||bt==b[7]||bt==b[8]||bt==b[9])
    		bt.setBackground(Color.cyan);
    	else 
    		bt.setBackground(Color.blue);
    }
    public void mouseExited(MouseEvent e)
    {
    	JButton bt=(JButton)e.getSource();
    	if(bt==b[0]||bt==b[1]||bt==b[2]||bt==b[3]||bt==b[4]||bt==b[5]||bt==b[6]||bt==b[7]||bt==b[8]||bt==b[9])
    		bt.setBackground(Color.white);
    	else 
    		bt.setBackground(Color.cyan);
    }
    public void windowClosing(WindowEvent e)
    {
    	try
    	{
    		int a=JOptionPane.showConfirmDialog(f,"Do you want to Exit?");
        	if(a==0)
        		f.dispose();
    	}
    	catch(Exception e1)
    	{
    		JOptionPane.showMessageDialog(null,e1.getMessage());
    	}
    }
    public void windowClosed(WindowEvent e)
    {
    	System.out.println("its working");
    	JOptionPane.showMessageDialog(null,"Your Calculations are saved");
    }
}