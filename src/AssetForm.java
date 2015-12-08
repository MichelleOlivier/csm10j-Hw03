//package hw03;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Michelle
 */
public class AssetForm extends JFrame implements ActionListener
{
    Container pane;
    private JTextField nameTextField = new JTextField();
    private JTextField valueTextField = new JTextField();
    private JTextField debtTextField = new JTextField();
    private JTextField tickerTextField = new JTextField();
    private JTextField quantityTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField balanceTextField = new JTextField();
    
    private JButton cancelButton = new JButton("Cancel");
    private JButton saveButton = new JButton("Save");
    private JComboBox houseCarComboBox = new JComboBox();

    private JLabel nameLabel = new JLabel("Name");
    private JLabel tickerLabel = new JLabel("Ticker");
    private JLabel balanceLabel = new JLabel("Balance");
    private JLabel quantityLabel = new JLabel("Quantity");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel valueLabel = new JLabel("Value");
    private JLabel debtLabel = new JLabel("Debt");
    private JLabel typeLabel = new JLabel("Type");
    private GridLayout theGrid;
    private Font theFont = new Font("Ariel", Font.PLAIN, 28);   
    private Object theObject;
    private String _AddUpdate;
    //private ArrayList<Asset> _theAssets;
    private Double tempDouble = 0.0;
    private Integer tempInteger = 0;
    private WealthManagerForm _mainForm;

    public AssetForm(Object o, String AddUpdate, WealthManagerForm mainForm)
    {
        theObject = o;
        _AddUpdate = AddUpdate;
        //_theAssets = theAssets;
        _mainForm = mainForm;
        nameTextField.setFont(theFont);
        valueTextField.setFont(theFont);
        debtTextField.setFont(theFont);
        tickerTextField.setFont(theFont);
        quantityTextField.setFont(theFont);
        priceTextField.setFont(theFont);
        balanceTextField.setFont(theFont);        
        nameLabel.setFont(theFont); 
        tickerLabel.setFont(theFont); 
        balanceLabel.setFont(theFont); 
        quantityLabel.setFont(theFont); 
        priceLabel.setFont(theFont); 
        valueLabel.setFont(theFont); 
        debtLabel.setFont(theFont); 
        typeLabel.setFont(theFont); 
        cancelButton.setFont(theFont);
        saveButton.setFont(theFont);
        
        if (o instanceof BankAccount)
        {
           this.setTitle("Bank Account");
           setUpForm(3,2);
           pane.add(nameLabel);        
           pane.add(nameTextField);
           pane.add(balanceLabel);
           pane.add(balanceTextField);
           pane.add(cancelButton);
           pane.add(saveButton);
           
           if (AddUpdate.equals("Update"))
           {
               nameTextField.setText(((BankAccount)o).name);          
               balanceTextField.setText(((BankAccount)o).getAssetValue().toString());
           }
        }
        else if (o instanceof Stock)
        {
            this.setTitle("Stock");
            setUpForm(4,2);
            pane.add(tickerLabel);
            pane.add(tickerTextField);
            pane.add(quantityLabel);
            pane.add(quantityTextField);
            pane.add(priceLabel);
            pane.add(priceTextField);
            pane.add(cancelButton);
            pane.add(saveButton);
            
            if (AddUpdate.equals("Update"))
            {
                tickerTextField.setText(((Stock)o).name);
                quantityTextField.setText(Integer.toString(((Stock)o).quantity));
                priceTextField.setText(((Stock)o).price.toString());
            }
        }
        else if (o instanceof Car)
        {
            this.setTitle("Car");
            setUpForm(5,2);
            pane.add(typeLabel);
            pane.add(houseCarComboBox);
            pane.add(nameLabel);
            pane.add(nameTextField);
            pane.add(valueLabel);
            pane.add(valueTextField);
            pane.add(debtLabel);
            pane.add(debtTextField);
            pane.add(cancelButton);
            pane.add(saveButton);
            
            if (AddUpdate.equals("Update"))
            {
                nameTextField.setText(((Car)o).name); 
                valueTextField.setText(((Car)o).getAssetValue().toString());
                debtTextField.setText(((Car)o).getDebtAmount().toString());
            }
        }
        else if(o instanceof House)
        {
            this.setTitle("House");
            setUpForm(5,2);
            pane.add(typeLabel);
            pane.add(houseCarComboBox);
            pane.add(nameLabel);
            pane.add(nameTextField);
            pane.add(valueLabel);
            pane.add(valueTextField);
            pane.add(debtLabel);
            pane.add(debtTextField);
            pane.add(cancelButton);
            pane.add(saveButton);
            
            if (AddUpdate.equals("Update"))
            {
                nameTextField.setText(((Car)o).name); 
                valueTextField.setText(((Car)o).getAssetValue().toString());
                debtTextField.setText(((Car)o).getDebtAmount().toString());
            }
        }
        this.setVisible(true);
    }
    
    private void setUpForm(int row, int column)
    {
        pane = getContentPane();
        theGrid = new GridLayout(row,column);
        pane.setLayout(theGrid); 
        this.setSize(800, 800);
        nameTextField.setFont(theFont);
        valueTextField.setFont(theFont);
        debtTextField.setFont(theFont);
        tickerTextField.setFont(theFont);
        quantityTextField.setFont(theFont);
        priceTextField.setFont(theFont);
        balanceTextField.setFont(theFont);        
        nameLabel.setFont(theFont); 
        tickerLabel.setFont(theFont); 
        balanceLabel.setFont(theFont); 
        quantityLabel.setFont(theFont); 
        priceLabel.setFont(theFont); 
        valueLabel.setFont(theFont); 
        debtLabel.setFont(theFont); 
        typeLabel.setFont(theFont); 
        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);
        houseCarComboBox.addItem("House");
        houseCarComboBox.addItem("Stock");
        houseCarComboBox.addItem("Car");
        houseCarComboBox.addItem("Bank Account");
    }
    
    public void actionPerformed(ActionEvent ape)
    {
        if(ape.getActionCommand().equals("Cancel"))
        {
            setVisible(false);
            this.dispose();
        }
        else
        {
            if (theObject instanceof BankAccount)
            {
                if (TestIfThereIsInput("Name", nameTextField.getText()))
                {
                    if (TestIfInputIsDouble("Balance", balanceTextField.getText()))
                    {
                        if (_AddUpdate.equals("Add"))
                        {
                            _mainForm.theAssets.add(new BankAccount(nameTextField.getText(), tempDouble));
                            AfterAddActions("Bank Account", nameTextField.getText());
                        }
                        else
                            DoUpdate(new BankAccount(nameTextField.getText(), tempDouble));
                    }
                }
            }
            else if (theObject instanceof House)
            {
                if (TestIfThereIsInput("Name", nameTextField.getText()))
                {
                    if (TestIfInputIsDouble("Value", valueTextField.getText()))
                    {
                        Double otherTemp = tempDouble;
                        if (TestIfInputIsDouble("Debt", debtTextField.getText()))
                        {
                            if (_AddUpdate.equals("Add"))
                            {
                                _mainForm.theAssets.add(new House(nameTextField.getText(), otherTemp, tempDouble));
                                AfterAddActions("House", nameTextField.getText());
                            }
                            else
                                DoUpdate(new House(nameTextField.getText(), otherTemp, tempDouble));
                        }
                    }
                }
            }
            else if (theObject instanceof Car)
            {
                if (TestIfThereIsInput("Name", nameTextField.getText()))
                {
                    if (TestIfInputIsDouble("Value", valueTextField.getText()))
                    {
                        Double otherTemp = tempDouble;
                        if (TestIfInputIsDouble("Debt", debtTextField.getText()))
                        {
                            if (_AddUpdate.equals("Add"))
                            {
                                _mainForm.theAssets.add(new Car(nameTextField.getText(), otherTemp, tempDouble));
                                AfterAddActions("Car", nameTextField.getText());
                            }
                            else 
                                DoUpdate(new Car(nameTextField.getText(), otherTemp, tempDouble));
                        }
                    }
                }
            }
            else if (theObject instanceof Stock)
            {
                if (TestIfThereIsInput("Name", tickerTextField.getText()))
                {
                    if (TestIfInputIsInteger("Quantity", quantityTextField.getText()))
                    {
                        if (TestIfInputIsDouble("Price", priceTextField.getText()))
                        {
                           if (_AddUpdate.equals("Add")) 
                           {
                                _mainForm.theAssets.add(new Stock(tickerTextField.getText(), tempInteger, tempDouble));                          
                                AfterAddActions("Stock", tickerTextField.getText());
                           }
                           else
                               DoUpdate(new Stock(nameTextField.getText(), tempInteger, tempDouble));
                        }
                    }
                }
            }
        }
    }
    
    private void AfterAddActions(String objectType, String objectName)
    {
         JOptionPane.showMessageDialog(this, 
                        objectType + " " + objectName + " has been added.", 
                        "Asset Added", 
                        JOptionPane.INFORMATION_MESSAGE);
        _mainForm.ReloadListOfAssets();
        setVisible(false);
        this.dispose();
    }
    
    private void AfterUpdateActions(String objectType, String objectName)
    {
         JOptionPane.showMessageDialog(this, 
                        objectType + " " + objectName + " has been updated.", 
                        "Asset Updated", 
                        JOptionPane.INFORMATION_MESSAGE);
        _mainForm.ReloadListOfAssets();
        setVisible(false);
        this.dispose();
    }
    
    private Boolean DoUpdate(Object o)
    {
        for (Asset asset : _mainForm.theAssets)
        {
            if (asset instanceof BankAccount && o instanceof BankAccount)
            {
                ((BankAccount)asset)._balance = ((BankAccount)o)._balance;
            }
            else if (theObject instanceof House)
            {
                ((House)asset)._loan = ((House)o)._loan;
                ((House)asset)._value = ((House)o)._value;
            }
            else if (theObject instanceof Car)
            {
                ((Car)asset)._loan = ((Car)o)._loan;
                ((Car)asset)._value = ((Car)o)._value;
            }
            else if (theObject instanceof Stock)
            {
                ((Stock)asset).price = ((Stock)o).price;
                ((Stock)asset).quantity = ((Stock)o).quantity;
            }
            return true;
        }
        return false;
    }
    
    private boolean TestIfThereIsInput(String NameOfField, String FieldValue)
    {
        if(FieldValue.trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, 
                        "Field: " + NameOfField + " must be filled in.", 
                        "Empty Field", 
                        JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private Boolean TestIfInputIsDouble(String NameOfField, String FieldValue)
    {
        if (TestIfThereIsInput(NameOfField, FieldValue))
        {
            try
            {
                tempDouble = Double.parseDouble(FieldValue);
                return true;
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, 
                        "Field: " + NameOfField + " has invalid input data (decimal format).", 
                        "Invalid Input Data", 
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
    private Boolean TestIfInputIsInteger(String NameOfField, String FieldValue)
    {
        if (TestIfThereIsInput(NameOfField, FieldValue))
        {
            try
            {
                tempInteger = Integer.parseInt(FieldValue);
                return true;
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, 
                        "Field: " + NameOfField + " has invalid input data (integer format).", 
                        "Invalid Input Data", 
                        JOptionPane.ERROR_MESSAGE);
            return false;
            }
        }
        return false;
    }
}
