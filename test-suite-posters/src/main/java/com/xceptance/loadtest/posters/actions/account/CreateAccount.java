package com.xceptance.loadtest.posters.actions.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.junit.Assert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.account.CreateAccountPage;
import com.xceptance.loadtest.posters.models.pages.account.LoginPage;

/**
 * Creates a new account.
 * 
 * @author Xceptance Software Technologies
 */
public class CreateAccount extends PageAction<CreateAccount>
{
    private final Account account;

    public CreateAccount(final Account account)
    {
        this.account = account;
    }

    @Override
    protected void doExecute() throws Exception
    {
        CreateAccountPage.instance.createAccountForm.fillCreateAccountForm(account);
        String csrf=CreateAccountPage.instance.createAccountForm.GetCsrf();
       // loadPageByClick(CreateAccountPage.instance.createAccountForm.getCreateAccountButton());
      

    		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    		Date date1 = new Date();
    		String timestamp = dateFormat.format(date1);
    		Random randomGenerator = new Random();  
    		int randomInt = randomGenerator.nextInt(10000); 
     String Emailadress=account.firstname+account.lastname+"+"+timestamp+randomInt+"@gmail.com";
     final List<NameValuePair> parm = new ArrayList<NameValuePair>();
  	 parm.add(new NameValuePair("dwfrm_profile_customer_email", Emailadress));
  	 parm.add(new NameValuePair("dwfrm_profile_customer_firstname", account.firstname));
  	 parm.add(new NameValuePair("dwfrm_profile_customer_lastname", account.lastname));
  	 parm.add(new NameValuePair("dwfrm_profile_customer_phone", "+"+account.mobile));
  	 parm.add(new NameValuePair("dwfrm_profile_customer_birthday", account.Dob));
     parm.add(new NameValuePair("dwfrm_profile_customer_gender", "3")); 
  	 parm.add(new NameValuePair("dwfrm_profile_login_password", account.password));
  	 parm.add(new NameValuePair("dwfrm_profile_customer_addtoemaillist", "true"));
  	 parm.add(new NameValuePair("csrf_token", csrf));
       HttpRequest req1 = new HttpRequest()
  				.XHR()
  				.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Account-SubmitRegistration?rurl=1")
                  .POST()             
  			    .postParams(parm);
       WebResponse response=req1.fire();
       String url = "";
       if(response.getStatusCode()==200)
   	 url=new JSONObject(response.getContentAsString()).getString("redirectUrl");
       else
    	 Assert.fail(response.getStatusMessage());
    loadPageByUrlClick(url);
    }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();
        account.isRegistered = true;
    }
}