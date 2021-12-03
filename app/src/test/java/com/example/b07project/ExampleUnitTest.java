package com.example.b07project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;


    @Test
    public void testLoginPresenter(){
        LoginPresenter presenter = new LoginPresenter(view,model);

        /*** stubbing ***/
        /*
        when(view.getUsername()).thenReturn("testing");
        when(view.getPassword()).thenReturn("L12345");
        doCallRealMethod().when(model).correctCredentials("testing", "L12345", false);

        doAnswer(invocation -> {
            String username = invocation.getArgumentAt(0,String.class);
            String password = invocation.getArgumentAt(1,String.class);
            boolean isCustomer = invocation.getArgumentAt(2, boolean.class);

            assertEquals(username,"testing");
            assertEquals(password,"L12345");
            assertEquals(isCustomer, false);

            return  null;
        }).when(model).correctCredentials();


        presenter.checkValidLogin(false);
        verify(view).result("Login Successful");
        */
    }

}