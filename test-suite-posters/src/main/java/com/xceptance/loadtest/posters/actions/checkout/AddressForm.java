package com.xceptance.loadtest.posters.actions.checkout;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.checkout.ShippingAddressPage;

public class AddressForm extends PageAction<AddressForm>
{
	private Account account;
	
	public AddressForm()
	{
		this(Context.get().data.getAccount().get());
	}
	
	public AddressForm(Account account)
	{
		this.account = account;
	}
	
	@Override
	public void precheck()
	{
		super.precheck();
		// Validate that we have the shipping address form
		Assert.assertTrue("Expected shipping address form", ShippingAddressPage.shippingAddressForm.exists());
	}
	
	@Override
	protected void doExecute() throws Exception
	{
		// Fill shipping address form
		ShippingAddressPage.shippingAddressForm.EmailfillForm(account);
		
		 String Csrf=ShippingAddressPage.shippingAddressForm.GetCsrf();
		 final List<NameValuePair> EmailParams = new ArrayList<NameValuePair>();
	 EmailParams.add(new NameValuePair("csrf_token", Csrf));
		 EmailParams.add(new NameValuePair("dwfrm_coCustomer_email", account.email));
         HttpRequest req = new HttpRequest()
					.XHR()
					.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutServices-SubmitCustomer")
                    .POST()             
				    .postParams(EmailParams);
         WebResponse response1=req.fire();
         //Assert.assertEquals("Responce expected",response1.getContentAsString());
         String shipmetUId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("shipmentUUID");
         String UId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("UUID");
       
     ShippingAddressPage.shippingAddressForm.fillForm(account);
    // WebResponse res=ShippingAddressPage.shippingAddressForm.getConfirmAdressButton().click().getWebResponse();
    // Assert.assertEquals("Responce expected",new JSONObject(res.getContentAsString()));
   
    
		 final List<NameValuePair> parm = new ArrayList<NameValuePair>();
		 parm.add(new NameValuePair("firstName", account.firstname));
		 parm.add(new NameValuePair("lastName", account.lastname));
		 parm.add(new NameValuePair("address1", account.shippingAddress.addressLine1));
			 parm.add(new NameValuePair("city", account.shippingAddress.city));
		 parm.add(new NameValuePair("postalCode", account.shippingAddress.zip));
		 parm.add(new NameValuePair("stateCode", account.shippingAddress.stateCode));
		 parm.add(new NameValuePair("countryCode",account.shippingAddress.countryCode));
		 parm.add(new NameValuePair("phone", "+"+account.mobile));
		 parm.add(new NameValuePair("shipmentUUID", shipmetUId));
         HttpRequest req1 = new HttpRequest()
					.XHR()
					.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutShippingServices-UpdateShippingMethodsList")
                    .POST()             
				    .postParams(parm);
         WebResponse response=req1.fire();
         
             // Assert.assertEquals("Responce expected",new JSONObject(response.getContentAsString()));
			 final List<NameValuePair> parms = new ArrayList<NameValuePair>();
			 parms.add(new NameValuePair("originalShipmentUUID", shipmetUId));
			 parms.add(new NameValuePair("shipmentUUID", shipmetUId));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_firstName", account.firstname));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_lastName", account.lastname));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_phone", "+"+account.mobile));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_title", account.addressTitile));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_address1", account.shippingAddress.addressLine1));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_city", account.shippingAddress.city));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_states_stateCode", account.shippingAddress.stateCode));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_postalCode", account.shippingAddress.zip));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_country", account.shippingAddress.countryCode));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_phone", "+"+account.mobile));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_addressType", "shipment"));
			 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_shippingMethodID", "Standard"));
			 parms.add(new NameValuePair("csrf_token",Csrf));
	         HttpRequest req2 = new HttpRequest()
						.XHR()
						.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutShippingServices-SubmitShipping")
	                    .POST()             
					    .postParams(parms);
	         WebResponse response2=req2.fire();
	}
	@Override
	protected void postValidate() throws Exception
	{
        Validator.validatePageSource();

        // Validate that it is the shipping address page
        ShippingAddressPage.instance.validate();
	}
	
}
