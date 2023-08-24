package com.xceptance.loadtest.posters.actions.account;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.account.CreateAccountPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

import junit.framework.Assert;

/**
 * Opens the account creation page.
 * 
 * @author Xceptance Software Technologies
 */
public class GoToCreateAccount extends PageAction<GoToCreateAccount>
{
    @Override
    protected void doExecute() throws Exception
    {
    	String baseurl=Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage;
    	loadPage(baseurl+"on/demandware.store/Sites-CityBeachAustralia-Site/default/Login-Show");
        //loadPageByUrlClick(GeneralPages.instance.user.getCreateAccountLink().getAttribute("href"));
    }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();

        CreateAccountPage.instance.validate();
    }
}