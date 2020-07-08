//start - changes made by paxton
public class NumberFormatExceptionHandlingTest {
    public float floatParsingMethod(String temp) throws NumberFormatException{
        float checkedFloat = Float.parseFloat(temp);
        return checkedFloat;
    }
}
//stop - changes made by paxton