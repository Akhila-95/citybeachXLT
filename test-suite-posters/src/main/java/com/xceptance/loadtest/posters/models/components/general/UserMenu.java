package com.xceptance.loadtest.posters.models.components.general;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;

/**
 * User menu component.
 * 
 * @author Xceptance Software Technologies
 */
public class UserMenu implements Component
{
	public static final UserMenu instance = new UserMenu();

    @Override
    public LookUpResult locate()
    {
        return Header.instance.locate().byCss("li>div>a[aria-label]");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public LookUpResult getLoginLink()
    {
        return locate().byCss("a[role='button'][aria-label]");
    }
    
    public HtmlElement getCreateAccountLink()
    {
    	return locate().byCss("[role='button']").single();
    }

    public LookUpResult getMyAccountLink()
    {
        return locate().byXPath("//a[contains(text(),'My Account')]");
    }

    public LookUpResult getLogoutLink()
    {
        return locate().byCss("div>a.btn");
    }
    
    public boolean isLoggedIn()
    {
        return getLogoutLink().exists();
    }

    public boolean isNotLoggedIn()
    {
        return !isLoggedIn();
    }
}