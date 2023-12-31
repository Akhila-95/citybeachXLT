package com.xceptance.loadtest.posters.flows;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.posters.actions.account.GoToCreateAccount;
import com.xceptance.loadtest.posters.actions.account.Login;

public class LoginFlow extends Flow
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
        // Open create account page
        new GoToCreateAccount().run();
        new Login(Context.get().data.getAccount().get()).run();
        return true;
    }
}