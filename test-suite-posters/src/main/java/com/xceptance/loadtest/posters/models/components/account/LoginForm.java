package com.xceptance.loadtest.posters.models.components.account;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.FormUtils;

/**
 * LoginForm component.
 * 
 * @author Xceptance Software Technologies
 */
public class LoginForm implements Component
{
	public static final LoginForm instance = new LoginForm();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byCss(".login-form-block[name='login-form']");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public HtmlElement getSignInButton()
    {
    	return locate().byXPath("//button[contains(text(),'Login')]").asserted().single();
    }

    public HtmlForm fillLoginForm(final Account account)
    {
        final HtmlForm form = locate().asserted().single();

        FormUtils.setInputValue(HPU.find().in(form).byId("login-form-email"), account.email);
        FormUtils.setInputValue(HPU.find().in(form).byId("login-form-password"), account.password);

        return form;
    }
}