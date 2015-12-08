//package hw03;
import java.text.DecimalFormat;

/**
 *
 * @author Michelle
 */
public class House extends Property implements GetName
{
    double _value;
    double _loan; 
        
    public House()
    {
        
    }
        
    public House(String _name, double value, double loan)
    {
        _value = value;
        _loan = loan;
        name = _name;
    }
       
    public String getName()
    {
        return name;
    }
    
    public Double getNetValue()
    {
        return _value - _loan;
    }
    
    public Double getAssetValue()
    {
        return _value;
    }
    
    public Double getDebtAmount()
    {
        return _loan;
    }
    
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("Address: %1s, House, Value: $%2s, Debt: $%3s", name, df.format(_value), df.format(_loan));
    }
}
