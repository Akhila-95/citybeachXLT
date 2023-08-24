package com.xceptance.loadtest.posters.actions.checkout;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.configuration.EnumConfigList;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.data.CreditCard;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.checkout.ShippingAddressPage;
import com.xceptance.xlt.api.util.XltRandom;

public class registerOrder  extends PageAction<registerOrder>
{
	private Account account;
	private CreditCard creditCard;
	private int Cardindex;
	
	public registerOrder()
	{
		this(Context.get().data.getAccount().get(),Context.get().data.getAccount().get().creditCards);
	}
	
	public registerOrder(Account account,EnumConfigList<CreditCard> cards)
	{
		this.account = account;
		Cardindex = XltRandom.nextInt(0, cards.size() - 1);
		this.creditCard=cards.get(Cardindex);
		
	}
	
	@Override
	public void precheck()
	{
		//this.getWebClient().getOptions().setJavaScriptEnabled(true);
		super.precheck();

		// Validate that we have the shipping address form
		Assert.assertTrue("Expected shipping address form", ShippingAddressPage.shippingAddressForm.exists());
	}
	
	@Override
	protected void doExecute() throws Exception
	{
		// Fill shipping address form
				String ShipmentUuid=ShippingAddressPage.shippingAddressForm.GetShipmentUUid();
				 String Csrf=ShippingAddressPage.shippingAddressForm.GetCsrf();
                     // Assert.assertEquals("Responce expected",new JSONObject(response.getContentAsString()));
					 final List<NameValuePair> parms = new ArrayList<NameValuePair>();
					 parms.add(new NameValuePair("originalShipmentUUID", ShipmentUuid));
					 parms.add(new NameValuePair("shipmentUUID", ShipmentUuid));
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
		ShippingAddressPage.instance.validate();
		//this.getWebClient().getOptions().setJavaScriptEnabled(false);
        Validator.validatePageSource();
        // Validate that it is the payment page
        //PaymentPage.instance.validate();
	}
}