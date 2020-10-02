import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test_Swing {
    public static void main(String[] args){
        SwingTest obj=new SwingTest();
    }

}

class SwingTest extends JFrame {

    SwingTest(){
        JLabel jLabel1=new JLabel();
        jLabel1.setText("First No");
        JLabel jLabel2= new JLabel();
        jLabel2.setText("Second No");
        JTextField jTextField1 = new JTextField(20);
        JTextField jTextField2= new JTextField(20);
        JTextField jTextField3 = new JTextField(20);
        JButton jButton = new JButton("ADD");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=Integer.parseInt(jTextField1.getText());
                int b=Integer.parseInt(jTextField2.getText());
//                if(e.getSource()==jButton)
                jTextField3.setText((a+b)+" ");

            }
        });

        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jTextField2);
        add(jButton);
        add(jTextField3);




//    flow border grid
        //default layout is Card layout
        FlowLayout flowLayout = new FlowLayout();
//        BorderLayout gridLayout = new GridLayout(2,3);
        setLayout(flowLayout);
        setSize(300,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}