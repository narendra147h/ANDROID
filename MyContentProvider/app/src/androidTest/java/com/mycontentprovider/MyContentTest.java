package com.mycontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.test.rule.provider.ProviderTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by adpr270759 on 13-04-2018.
 */
public class MyContentTest {


    @Rule
    public ProviderTestRule mproviderTestRule = new ProviderTestRule.Builder(MyContent.class, MyContent.PROVIDER_NAME).build();




    @Test
    public void test_InsertValueTestForAnyGivenData()
    {
        ContentResolver mContentResolver = mproviderTestRule.getResolver();

        ContentValues values = new ContentValues();
        values.put(MyContent.EMP_ID, 569);
        values.put(MyContent.EMP_DESIG, "asd");

        Uri uri = mContentResolver.insert(MyContent.CONTENT_URI, values);

       assertNotNull(uri);
    }



}