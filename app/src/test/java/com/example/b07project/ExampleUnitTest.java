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
    public void testCheckValidLogin(){

        /*** stubbing ***/
        when(view.getUsername()).thenReturn("testing");
        when(view.getPassword()).thenReturn("L12345");
        /****************/

        LoginPresenter presenter = new LoginPresenter(view,model);
        presenter.checkValidLogin(false);
        verify(model).correctCredentials("testing","L12345", false);

    }

    @Test
    public void testLogin1(){
        /*** stubbing ***/
        doNothing().when(view).result("Login Successful");
        /****************/

        LoginPresenter presenter = new LoginPresenter(view,model);

        StoreOwner storeOwner = new StoreOwner("testing","L12345");
        presenter.login(storeOwner);
        verify(view).startOwnerLanding(storeOwner);
    }

    @Test
    public void testLogin2(){
        /*** stubbing ***/
        doNothing().when(view).result("Login Successful");
        /****************/

        LoginPresenter presenter = new LoginPresenter(view,model);

        Customer customer = new Customer("That_Guy", "Bdjf");
        presenter.login(customer);
        verify(view).startCustomerLanding(customer);
    }

    @Test
    public void testDoNotLogin(){
        LoginPresenter presenter = new LoginPresenter(view,model);
        presenter.doNotLogin();
        verify(view).result("Incorrect username, password, or both");
    }


}