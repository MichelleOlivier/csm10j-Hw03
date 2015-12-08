package hw03;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Michelle
 */
public class WealthManagerForm extends JFrame implements ActionListener 
{
    private JTextArea detailedView = new JTextArea();
    private JList assetList;
    private JPanel assetsAndDebts = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JLabel assetLabel = new JLabel("Assets");
    private JLabel assetDetailLabel = new JLabel("Asset Detail");
    private JMenuBar theMenuBar = new JMenuBar();
    private JMenu newM = new JMenu("New");
    private JMenuItem bankAccountM = new JMenuItem("Bank Account");
    private JMenuItem stockM = new JMenuItem("Stock");
    private JMenuItem carM = new JMenuItem("Car");
    private JMenuItem houseM = new JMenuItem("House");
    private Font theFont = new Font("Ariel", Font.PLAIN, 28);
    public ArrayList<Asset> theAssets = new ArrayList<Asset>();
    DefaultListModel listModel = new DefaultListModel();
    private GridLayout mainGrid = new GridLayout(2,2);
    private GridLayout detailGrid = new GridLayout(3,1);
    private JTextField assetValue = new JTextField(40);
    private JTextField debtValue = new JTextField(40);
    private JTextField netWorthValue = new JTextField(40);
    private BorderLayout borderLayout;
    private WealthManagerForm self;
    
    public WealthManagerForm()
    {
        self = this;
        setTitle("Wealth Manager");
        this.setFont(theFont);
        assetList = new JList(listModel);
        assetList.setFont(theFont);
        assetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        assetList.addListSelectionListener(new SharedListSelectionHandler()); 
        assetList.addKeyListener(new SharedKeyListener());
        assetList.addMouseListener(new SharedMouseListener());
        assetValue.setFont(theFont);
        debtValue.setFont(theFont);
        netWorthValue.setFont(theFont);
        Container pane = getContentPane();
        borderLayout = new BorderLayout(10,10);      
        pane.setLayout(mainGrid);
        pane.add(assetLabel);
        pane.add(assetDetailLabel);
        pane.add(assetList);
        pane.add(detailedView);
        pane.add(assetValue);
        pane.add(debtValue); 
        pane.add(netWorthValue);
        detailedView.setFont(theFont);
        newM.setFont(theFont);
        bankAccountM.setFont(theFont);
        stockM.setFont(theFont);
        carM.setFont(theFont);
        houseM.setFont(theFont);
        assetLabel.setFont(theFont); 
        assetDetailLabel.setFont(theFont);
        setJMenuBar(theMenuBar);
        theMenuBar.add(newM);
        newM.add(bankAccountM);
        bankAccountM.addActionListener(this);
        newM.add(stockM);
        stockM.addActionListener(this);
        newM.add(carM);
        carM.addActionListener(this);
        newM.add(houseM);
        houseM.addActionListener(this);
        this.setSize(900, 900);
               
        ReloadListOfAssets();
    }
     
    public void ReloadListOfAssets()
    {
        listModel.clear();
        for (Asset asset : theAssets)
        {
            listModel.addElement(((GetName)asset).getName());
        } 
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem mItem = (JMenuItem) e.getSource();
        AssetForm theAssetForm;
        
        if (mItem == bankAccountM)
        {
            theAssetForm = new AssetForm(new BankAccount(), "Add", this);
        }
        else if (mItem == stockM)
        {
            theAssetForm = new AssetForm(new Stock(), "Add", this);
        }
        else if (mItem == carM)
        {
            theAssetForm = new AssetForm(new Car(), "Add", this);
        }
        else if (mItem == houseM)
        {
            theAssetForm = new AssetForm(new House(), "Add", this);
        }
    }
    
    class SharedMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent me) 
        {
            if (me.getClickCount() > 1)
            {  
                AssetForm theAssetForm = new 
                    AssetForm(theAssets.get(assetList.getSelectedIndex()), "Update", self);
            }       
        }

        @Override
        public void mousePressed(MouseEvent me) 
        {
            
        }

        @Override
        public void mouseReleased(MouseEvent me) 
        {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) 
        {
            
        }

        @Override
        public void mouseExited(MouseEvent me) 
        {
            
        }
        
    }
    
    class SharedKeyListener implements KeyListener
    {
        @Override 
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyChar() == 'd' ||
                e.getKeyChar() == 'D')
            {
                int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this Asset?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
                if (response == JOptionPane.YES_OPTION) 
                    assetList.remove(assetList.getSelectedIndex());
            }
        }
        
        @Override 
        public void keyReleased(KeyEvent e)
        {
            
        }
        
        @Override 
        public void keyTyped(KeyEvent e)
        {
            
        }
    }
    
    class SharedListSelectionHandler implements ListSelectionListener 
    { 
        @Override
        public void valueChanged(ListSelectionEvent lse) 
        {          
            detailedView.setText(theAssets.get(assetList.getSelectedIndex()).toString());
            
            assetValue.setText("Value: " + theAssets.get(assetList.getSelectedIndex()).getAssetValue().toString());
            debtValue.setText("Debt: " + theAssets.get(assetList.getSelectedIndex()).getDebtAmount().toString());
            netWorthValue.setText("Net Worth: " + theAssets.get(assetList.getSelectedIndex()).getNetValue().toString());
        }
    }
    
}
