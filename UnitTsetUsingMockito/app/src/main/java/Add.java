/**
 * Created by adpr270759 on 11-04-2018.
 */

public class Add {

    Calculator calculator;

    public Add(Calculator calculator)
    {
        this.calculator=calculator;
    }

    public int Perform(int i,int j)
    {
        return  calculator.addition(i,j)*i;
    }


}
