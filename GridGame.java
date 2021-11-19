import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
public class GridGame  extends JFrame implements ActionListener
{
    int x,y;
    JButton jb[][];
    JPanel jp;
    int pos=15;
    GridGame()// To initialize variable and create a random board to play on
    {
        x=3;
        y=3;
        setName("GridGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(400,400));
        
        JPanel jp=new JPanel();
        jp.setLayout(new GridLayout(4,4,0,0));
        
        ArrayList<Integer> ar=new ArrayList();
        for(int i=1;i<16;i++)
        {
            ar.add(i);
        }
       Collections.shuffle(ar);
        
        jb=new JButton[4][4];
        
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                int num=i*4+j;
                
                if(num!=pos)
                {
                    int x=ar.get(num);
                    jb[i][j]=button(x);
                    jp.add(jb[i][j]);
                }
                else
                {
                    jb[i][j]=buttonEmpty();
                    jp.add(jb[i][j]);
                }
            }
        }
        
        add(jp);
        setVisible(true);
    }
    public JButton button(int num)// code for clikable button
    {
        JButton jb=new JButton(Integer.toString(num));
        jb.setSize(new Dimension(100,100));
        jb.setBackground(new Color(21,52,80));
        jb.setForeground(new Color(248,228,204));
        jb.setFont(new Font("Ariel",Font.BOLD,40));
        jb.addActionListener(this);
        return jb;
    }
    public JButton buttonEmpty()//code for empty button
    {
        JButton jb=new JButton( );
        jb.setSize(new Dimension(100,100));
        jb.setBackground(Color.WHITE);
        jb.addActionListener(this);
        return jb;
    }
    public void actionPerformed(ActionEvent ae)//to determine the action performed on pressing the buttons
    {
        JButton jt=(JButton)ae.getSource();
        String num=jt.getLabel();
        Point p=jt.getLocation();
        
        JButton empty=jb[x][y];
        String num2=empty.getLabel();
        Point emptyp=empty.getLocation();
        //need to make cases for all 4 sides
        //this is for left
        if(emptyp.x-p.x<100 && emptyp.x>p.x && p.y==emptyp.y)//p=210,empty=300
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            --this.y;
        }
        //for right
        if(p.x-emptyp.x<100 && p.x>emptyp.x && p.y==emptyp.y)
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            ++this.y;
        }
        //for up
        if(emptyp.y-p.y<100 && emptyp.y>p.y && p.x==emptyp.x)//p=210,empty=300
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            --this.x;
        }
        //for down
         if(p.y-emptyp.y<100 && p.y>emptyp.y && p.x==emptyp.x)
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            ++this.x;
        }
        //to check if the game is finished or not
        if(isSolved())
        {
            JOptionPane.showMessageDialog(null, "You Win The Game.");
            setVisible(false);
            new GridGame();
        }
    }
    public boolean isSolved()
    {
        String str;
        int num2;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(i==3 && j==3)
                {
                    return true;
                }
                int num=i*4+j+1;
                try
                {
                     str=jb[i][j].getText();
                     num2=Integer.parseInt(str);
                }
                catch(Exception e)
                {
                    return false;
                }
                if(num!=num2)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                GridGame gg=new GridGame();
            }
        });
    }
}
