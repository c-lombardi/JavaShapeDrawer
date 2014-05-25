import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * Created by Christopher on 2/23/14.
 */
public class Assign3 extends JFrame{
    public void Assign3 ()
    {

    }

    private JPanel panel;
    private JLabel fcx, fcy, ocx, ocy, td, esls;
    private JComboBox<String> styleList;
    private JTextField fcxtf, fcytf, ocxtf, ocytf, eslstf;
    private JRadioButton line, oval, rectangle;
    private ButtonGroup shapeGroup;
    private JButton draw;
    private String styles[] = {"Solid", "Outline"};
    private int styleChoice = 0;
    private int DrawChoice = 1;
    private int fcxVal;
    private int fcyVal;
    private int ocxVal;
    private int ocyVal;
    private int eslsVal;
    private int lsVal;
    private Color colorChoice;
    public Assign3()
    {
        super("Drawing Program");
        super.setLayout(new BorderLayout());
        setSize(500, 500);
        //panel
        panel = new JPanel(new GridLayout(4,4));
        panel.setSize(500,100);
        //drawPanel = new JPanel(new GridLayout());
        //Color
        colorChoice = Color.BLACK;
        //JLabels
        fcx = new JLabel("First Corner X");
        //fcx.setPreferredSize(new Dimension(110, 25));
        fcy = new JLabel("FIrst Corner Y");
        //fcy.setPreferredSize(new Dimension(110, 25));
        ocx = new JLabel("Opposite Corner X");
        //ocx.setPreferredSize(new Dimension(110, 25));
        ocy = new JLabel("Opposite Corner Y");
        //ocy.setPreferredSize(new Dimension(110, 25));
        td = new JLabel("To Draw...");
        //td.setPreferredSize(new Dimension(110, 25));
        esls = new JLabel("Enter Style/Line Size");
        //esls.setPreferredSize(new Dimension(110, 25));
        //JTextFields
        fcxtf = new JTextField(10);
        fcytf = new JTextField(10);
        ocxtf = new JTextField(10);
        ocytf = new JTextField(10);
        eslstf = new JTextField(10);
        //JRadioButtons
        line = new JRadioButton("line");
        //line.setPreferredSize(new Dimension(110, 25));
        line.setSelected(true);
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawChoice = 1;
            }
        });
        oval = new JRadioButton("oval");
        //oval.setPreferredSize(new Dimension(110, 25));
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawChoice = 2;
            }
        });
        rectangle = new JRadioButton("rectangle");
        //rectangle.setPreferredSize(new Dimension(110, 25));
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawChoice = 3;
            }
        });
        shapeGroup = new ButtonGroup();
        shapeGroup.add(line);
        shapeGroup.add(oval);
        shapeGroup.add(rectangle);
        //JButtons
        draw = new JButton("Draw");
        //draw.setPreferredSize(new Dimension(110, 25));
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(fcxtf.getText().isEmpty() || fcytf.getText().isEmpty() || ocxtf.getText().isEmpty() || ocytf.getText().isEmpty() || eslstf.getText().isEmpty()))
                {
                    //draw stuff
                    colorChoice = JColorChooser.showDialog(null, "Drawing Color", Color.BLACK);
                    try {
                        eslsVal = Integer.parseInt(eslstf.getText());
                        fcxVal = Integer.parseInt(fcxtf.getText())+10;
                        fcyVal = Integer.parseInt(fcytf.getText())+45;
                        ocxVal = Integer.parseInt(ocxtf.getText())+10;
                        ocyVal = Integer.parseInt(ocytf.getText())+45;
                        if (fcxVal > ocxVal || fcyVal > ocyVal)
                        {
                            int temp1 = fcxVal;
                            int temp2 = fcyVal;
                            fcxVal = ocxVal;
                            fcyVal = ocyVal;
                            ocxVal = temp1;
                            ocyVal = temp2;
                        }
                        if ((ocyVal <= 380) && (ocxVal <= 485) && (fcxVal > 10) && (fcyVal > 45) && (eslsVal <= 50))
                        {
                            repaint();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Your coordinates go off of the screen");
                        }
                    }
                    catch (NumberFormatException n)
                    {
                        JOptionPane.showMessageDialog(null, "Your coordinates need to be integers");
                    }
                }
                else
                {
                    //check failed
                    JOptionPane.showMessageDialog(null, "One of the text boxes was not set. Cannot draw shape.");
                }
            }
        });
        //JComboBoxes
        styleList = new JComboBox<String>(styles);
        //styleList.setPreferredSize(new Dimension(110, 25));
        styleList.setSelectedIndex(0);
        styleList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                styleChoice = styleList.getSelectedIndex();
            }
        });
        //Panel Adds
        //First Row
        panel.add(fcx);
        panel.add(fcy);
        panel.add(ocx);
        panel.add(ocy);
        //Second Row
        panel.add(fcxtf);
        panel.add(fcytf);
        panel.add(ocxtf);
        panel.add(ocytf);
        //Third Row
        panel.add(td);
        panel.add(line);
        panel.add(oval);
        panel.add(rectangle);
        //Fourth Row
        panel.add(esls);
        panel.add(styleList);
        panel.add(eslstf);
        panel.add(draw);
        //Frame Adds
        add(panel, BorderLayout.SOUTH);
        //Frame Modifiers
        setVisible(true);
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(eslsVal));
        g2d.setColor(colorChoice);
        switch (styleChoice)
        {
            case 0:
                switch (DrawChoice) {
                    case 1:
                        //line
                        g2d.drawLine(fcxVal, fcyVal, ocxVal, ocyVal);
                        break;
                    case 2:
                        //oval
                        g2d.fillOval(fcxVal, fcyVal, ocxVal-fcxVal, ocyVal-fcyVal);
                        break;
                    case 3:
                        //rectangle
                        g2d.fillRect(fcxVal, fcyVal, ocxVal-fcxVal, ocyVal-fcyVal);
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (DrawChoice) {
                    case 1:
                        //line
                        g2d.drawLine(fcxVal, fcyVal, ocxVal, ocyVal);
                        break;
                    case 2:
                        //oval
                        g2d.drawOval(fcxVal, fcyVal, ocxVal-fcxVal, ocyVal-fcyVal);
                        break;
                    case 3:
                        //rectangle
                        g2d.drawRect(fcxVal, fcyVal, ocxVal-fcxVal, ocyVal-fcyVal);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
    public static void main(String [] args) {
        Assign3 applicaiton = new Assign3();
        applicaiton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
