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
 * Create account form component.
 * 
 * @author Xceptance Software Technologies
 */
public class CreateAccountForm implements Component
{
	public static final CreateAccountForm instance = new CreateAccountForm();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byCss(".registration");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public HtmlElement getCreateAccountButton()
    {
    	return locate().byCss("button.submit-registration").asserted().single();
    }

    public void fillCreateAccountForm(final Account account)
    {
        final HtmlForm form = locate().asserted().single();
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-lname"), account.lastname);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-fname"), account.firstname);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-email"), account.email);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-phone"), account.mobile);
        FormUtils.setInputValue(HPU.find().in(form).byId("birthday-date"), account.Dob);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-password"), account.password);
    }
    public String GetCsrf()
    {
    	 final HtmlElement  element = HPU.find().in(locate().single()).byXPath("//input[@type='hidden'][@name='csrf_token']").first();
    	 return element.getAttribute("value");
    }
}