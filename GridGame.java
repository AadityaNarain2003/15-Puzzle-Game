package practice;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class GridGame  extends JFrame implements ActionListener
{
    int x,y;
    JButton jb[][];
    JMenuBar jmb;
    JPanel jp=new JPanel();;
    int pos=15;
    Timer t;
    JLabel jl;
    ArrayList<Integer> ar;
    LabelTimer lt;
    GridGame()
    {
        x=3;
        y=3;
        setName("GridGame");//setting the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(415,528));
        setLayout(null);
        
        jmb=new JMenuBar();//setting the menubar
        jmb.setBounds(0, 0, 415, 41);
        jmb.setBackground(new Color(41,64,82));
        jmb.setLayout(null);
        
        JButton restart=new JButton("RESTART");
        restart.setForeground(new Color(248,228,204));
        restart.setBackground(new Color(68,114,148));
        restart.addActionListener((ActionEvent ae) -> {setNum(jb,ar,jp);
        });
        restart.setBounds(10,5,120,25);
        jmb.add(restart);
        
        t=new Timer(true);//to set the timer
        new Thread(t).start();
        
        jl=new JLabel(t.min+" : "+t.sec);//to display the timer
        jl.setForeground(new Color(244,214,188));
        jl.setFont(new Font("Ariel",Font.BOLD,20));
        //jl.setBorder(BorderFactory.createLineBorder(Color.black));
        jl.setBounds(330, 5,80,30);
        jmb.add(jl);
        
        lt=new LabelTimer(jl,t);
        new Thread(lt).start();
             
        //setting the panel
        jp.setLayout(new GridLayout(4,4,0,0));
        jp.setBounds(0,40,400,450);
        jp.setBackground(new Color(244,214,188));
        
        ar=new ArrayList();//creating a random list of numbers
        for(int i=1;i<16;i++)
        {
            ar.add(i);
        }
        jb=new JButton[4][4];
        setNum(jb,ar,jp);
        add(jmb);
        add(jp);
        setVisible(true);
    }
    public JButton button(int num)
    {
        JButton jb=new JButton(Integer.toString(num));
        jb.setSize(new Dimension(100,100));
        jb.setBackground(new Color(21,52,80));
        jb.setForeground(new Color(248,228,204));
        jb.setFont(new Font("Ariel",Font.BOLD,40));
        jb.addActionListener(this);
        jb.setBorder(BorderFactory.createLineBorder(Color.black));
        return jb;
    }
    public JButton buttonEmpty()
    {
        JButton jb=new JButton( );
        jb.setSize(new Dimension(100,100));
        jb.setBackground(Color.WHITE);
        jb.addActionListener(this);
        return jb;
    }
    public void setNum(JButton[][] jb, ArrayList<Integer> ar,JPanel jp)
    {
        Collections.shuffle(ar);
        jp.removeAll();
        t.min=0;
        t.sec=0;
        x=3;
        y=3;
        for(int i=0;i<4;i++)//creating buttons
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
    }
    public void actionPerformed(ActionEvent ae)
    {
        JButton jt=(JButton)ae.getSource();
        String num=jt.getLabel();
        Point p=jt.getLocation();
        
        JButton empty=jb[x][y];
        String num2=empty.getLabel();
        Point emptyp=empty.getLocation();
        //need to make cases for all 4 sides
        //this is for left
        System.out.println("SOURCE:"+p.x+":"+p.y);
        System.out.println("EMPTY:"+emptyp.x+":"+emptyp.y);
        if(emptyp.x-p.x<=100 && emptyp.x>p.x && p.y==emptyp.y)//p=210,empty=300
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.setBorder(BorderFactory.createLineBorder(Color.black));
            empty.addActionListener(this);
            --this.y;
        }
        //for right
        if(p.x-emptyp.x<=100 && p.x>emptyp.x && p.y==emptyp.y)
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            empty.setBorder(BorderFactory.createLineBorder(Color.black));
            ++this.y;
        }
        //for up
        if(emptyp.y-p.y<=112 && emptyp.y>p.y && p.x==emptyp.x)//p=210,empty=300
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            empty.setBorder(BorderFactory.createLineBorder(Color.black));
            --this.x;
        }
        //for down
         if(p.y-emptyp.y<=112 && p.y>emptyp.y && p.x==emptyp.x)
        {
            jt.setText(num2);
            jt.setBackground(Color.WHITE);
            
            empty.setText(num);
            empty.setBackground(new Color(21,52,80));
            empty.setForeground(new Color(248,228,204));
            empty.setFont(new Font("Ariel",Font.BOLD,40));
            empty.addActionListener(this);
            empty.setBorder(BorderFactory.createLineBorder(Color.black));
            ++this.x;
        }
        //to check if the game is finished or not
        if(isSolved())
        {
            JOptionPane.showMessageDialog(null, "You Win The Game.");
            setNum(jb,ar,jp);
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
                    t.min=0;
                    t.sec=0;
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
        t.min=0;
        t.sec=0;
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
class Timer implements Runnable
{
    int min,sec;
    boolean t;
    Timer(boolean t)
    {
        this.t=t;
        min=0;
        sec=0;
    }
    public void run()
    {   
        while(t==true)
        {
           try 
           {
                Thread.sleep(1000);
           }
           catch (InterruptedException ex) 
            {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
            sec++;
            System.out.println(sec);
            if(sec>=60)
            {
                sec=0;
                min++;
            }
        }   
    }
}
class LabelTimer implements Runnable
{
    JLabel jl;
    Timer t;
    LabelTimer(JLabel jl,Timer t)
    {
        this.jl=jl;
        this.t=t;
    }
    public void run()
    {
        while(true)
        {
            jl.setText(t.min+" : "+t.sec);
        }
    }
}
