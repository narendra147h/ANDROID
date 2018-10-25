package broadcast.second.com.broadcastreciever;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adpr270759 on 16-04-2018.
 */
public class MyRecieverTest {

    @Rule
    public ActivityTestRule<MyBroadCast> testRule = new ActivityTestRule<MyBroadCast>(MyBroadCast.class);

    @Test
    public void test_onReceive() throws Exception {

        Activity activity = testRule.getActivity();

        Intent i=new Intent();
        i.setAction("com.myreciver.mybroadcast");
        i.putExtra("tag","hello World");
        activity.sendBroadcast(i);



    }

}