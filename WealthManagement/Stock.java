//package hw03;
import java.text.DecimalFormat;

/**
 *
 * @author Michelle
 */
public class Stock extends Security implements GetName
{
    int _quantity;
    double _price;
    String name;
        
    public Stock()
    {
        
    }
        
    public Stock(String _name, int quantity, double price)
    {
        name = _name;
        _quantity = quantity;
        _price = price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Double getDebtAmount()
    {
        return 0.00;
    }
    
    public Double getNetValue()
    {
        return _quantity * _price;
    }
    
    public Double getAssetValue()
    {
        return _quantity * _price;
    }
    
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("%1s, Stock, Shares Owned: %2s, Price: $%3s, Value: $%4s", name, _quantity, df.format(_price), df.format(getAssetValue()));
    }
}
