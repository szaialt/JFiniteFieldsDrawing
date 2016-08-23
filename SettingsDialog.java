import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

//A dialog to set the order of the drawing paper. 
//It bases on a tutorial settings window of Oracle.
class SettingsDialog extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private String typedText = null;
    private JTextField textField;

    private JOptionPane optionPane;

    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";

    private int ordo;

    /** Creates the reusable dialog. */
    public SettingsDialog(Frame aFrame) {
        super(aFrame, true);

        setTitle("Beállítások");

        textField = new JTextField(10);
        setPreferredSize(new Dimension(300,200));
        //Create an array of the text and components to be displayed.
        String msgString1 = "Kérem az alaptest rendjét:";
        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION,
                                    null,
                                    options,
                                    options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        setModal(false);
        pack();
        setVisible(true);
    }

    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            else if (value == btnString1){
              //Reset the JOptionPane's value.
              //If you don't do this, then if the user
              //presses the same button next time, no
              //property change event will be fired.
              //optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
              typedText = textField.getText();
              ordo = Integer.parseInt(typedText);
              System.out.println(value);
              System.out.println(ordo);
              DrawingPaper paper = new DrawingPaper(ordo);
              MainWindow mw = new MainWindow(paper);
            }
                 
          } 
          typedText = null;
          clearAndHide();
        
    }

    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        textField.setText(null);
        setVisible(false);
    }
    
    public int getOrdo(){
      return this.ordo;
    }
}
