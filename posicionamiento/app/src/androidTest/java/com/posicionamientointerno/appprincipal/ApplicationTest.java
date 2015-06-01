package com.posicionamientointerno.appprincipal;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public final static String apiURL = "http://localhost:64641/api/Login/SignIn";
    public ApplicationTest() {
        super(Application.class);
    }


    public void test() throws Exception{


    }

}