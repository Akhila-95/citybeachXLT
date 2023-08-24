package com.xceptance.loadtest.posters.actions.account;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;
import com.xceptance.loadtest.posters.models.pages.general.HomePage;

import junit.framework.Assert;

/**
 * Logs out.
 * 
 * @author Xceptance Software Technologies
 */
public class Logout extends PageAction<Logout>
{
    @Override
    protected void doExecute() throws Exception
    {
    
           HttpRequest req1 = new HttpRequest()
      				.XHR()
      				.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Login-Logout")
                      .GET() ;      
           WebResponse response=req1.fire();
           String baseurl=Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage;
       loadPage(baseurl);
    }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();

        HomePage.instance.validate();
        HomePage.instance.miniCart.isEmpty();
        HomePage.instance.user.isNotLoggedIn();
    }
}
