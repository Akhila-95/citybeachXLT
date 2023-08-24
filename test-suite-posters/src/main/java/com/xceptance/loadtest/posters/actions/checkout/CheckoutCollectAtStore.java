package com.xceptance.loadtest.posters.actions.checkout;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.checkout.PaymentPage;
import com.xceptance.loadtest.posters.models.pages.checkout.ShippingAddressPage;

public class CheckoutCollectAtStore extends PageAction<CheckoutShippingAddress>
{
	private Account account;
	
	public CheckoutCollectAtStore()
	{
		this(Context.get().data.getAccount().get());
	}
	
	public CheckoutCollectAtStore(Account account)
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
		         //Assert.assertEquals("Responce expected",new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("shipmentUUID"));
		         String shipmetUId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("shipmentUUID");
		         String UId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("UUID");
		         

				 final List<NameValuePair> parm = new ArrayList<NameValuePair>();
				 parm.add(new NameValuePair("shipmentUUID", shipmetUId));
				 parm.add(new NameValuePair("firstName", account.firstname));
				 parm.add(new NameValuePair("lastName", account.lastname));
				 parm.add(new NameValuePair("address1", "25 DEVOCEAN PL"));
				 parm.add(new NameValuePair("city", "CAMERON PARK"));
				 parm.add(new NameValuePair("postalCode", "2285"));
				 parm.add(new NameValuePair("stateCode", "NSW"));
				 parm.add(new NameValuePair("countryCode","AU"));
				 parm.add(new NameValuePair("phone","+61 2 9192 0995"));
		         HttpRequest req1 = new HttpRequest()
							.XHR()
							.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutShippingServices-UpdateShippingMethodsList")
		                    .POST()             
						    .postParams(parm);
		         WebResponse response=req1.fire();
		         //Assert.assertEquals("Responce expected",new JSONObject(response.getContentAsString()));
					 final List<NameValuePair> parms = new ArrayList<NameValuePair>();
					 parms.add(new NameValuePair("originalShipmentUUID", shipmetUId));
					 parms.add(new NameValuePair("shipmentUUID", shipmetUId));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_firstName", account.firstname));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_lastName", account.lastname));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_phone", account.mobile));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_title", account.addressTitile));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_address1", "25 DEVOCEAN PL"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_city", "CAMERON PARK"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_states_stateCode", "NSW"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_postalCode", "2285"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_country", "AU"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_addressFields_addressType", "shipment"));
					 parms.add(new NameValuePair("dwfrm_shipping_shippingAddress_shippingMethodID", "Standard"));
					 parms.add(new NameValuePair("csrf_token",Csrf));
			         HttpRequest req2 = new HttpRequest()
								.XHR()
								.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutShippingServices-SubmitShipping")
			                    .POST()             
							    .postParams(parms);
			         WebResponse response2=req2.fire();
			         final List<NameValuePair> Paymentparms = new ArrayList<NameValuePair>();
			         Paymentparms.add(new NameValuePair("billing", "on"));
			         Paymentparms.add(new NameValuePair("shipmentUUID", shipmetUId));
			         Paymentparms.add(new NameValuePair("UUID", UId));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_email", "psumanth254@gmail.com"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_firstName", account.firstname));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_lastName", account.lastname));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_phone", account.mobile));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_address1", "25 DEVOCEAN PL"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_city", "CAMERON PARK"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_states_stateCode", "NSW"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_postalCode", "2285"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_country", "AU"));
			         Paymentparms.add(new NameValuePair("localizedNewAddressTitle", "New Address"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_phone", account.mobile));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_paymentMethod", "AdyenComponent"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_phone", account.mobile));
			         Paymentparms.add(new NameValuePair("csrf_token",Csrf));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardNumber", "4988  4388  4388 4305"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardType", "visa"));
			         Paymentparms.add(new NameValuePair("brandCode", "scheme"));
			         HttpRequest req3 = new HttpRequest()
								.XHR()
								.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutServices-SubmitPayment")
			                    .POST()             
							    .postParams(Paymentparms);
			         WebResponse response3=req3.fire();
			         Assert.assertEquals("Responce expected",new JSONObject(response3.getContentAsString()));
			        // String UId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("shipmentUUID");
			     
		     	JSONObject addToCartJson = AjaxUtils.convertToJson(response1.getContentAsString());
				// Click continue button
				loadPageByClick(ShippingAddressPage.shippingAddressForm.GetShipItToMe());
		// Fill shipping address form
		ShippingAddressPage.shippingAddressForm.fillForm(account);
		
		// Click continue button
		loadPageByClick(ShippingAddressPage.shippingAddressForm.getContinueButton());
	}

	@Override
	protected void postValidate() throws Exception
	{
        Validator.validatePageSource();

        // Validate that it is the payment page
        PaymentPage.instance.validate();
	}
}
