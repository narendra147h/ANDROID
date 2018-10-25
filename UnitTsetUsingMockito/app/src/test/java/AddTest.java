import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by adpr270759 on 11-04-2018.
 */

@RunWith(JUnit4.class)
public class AddTest {

    Add add;


    /**
     * I need Dependent class Object Calculator to Perfrom Test
     * So that We need Mockito Frame Work which mock the that refrences
     */

    //We have two types to get dummy object

     //First
    //Calculator calculator = Mockito.mock(Calculator.class);


    //Second

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    Calculator calculator;


    @Before
    public void setUp() throws Exception {

        add=new Add(calculator);
    }


    @Test
    public void test_PerformMethod()
    {
        when(calculator.addition(3,2)).thenReturn(5);
        assertEquals("(3*2)*3=15",15,add.Perform(3,2));

    }

    @After
    public void tearDown() throws Exception {
        add=null;
        calculator=null;
    }

}