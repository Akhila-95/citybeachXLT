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

public class RegisterShippingAdress extends PageAction<RegisterShippingAdress>
{
	private Account account;
	private CreditCard creditCard;
	private int Cardindex;
	
	public RegisterShippingAdress()
	{
		this(Context.get().data.getAccount().get(),Context.get().data.getAccount().get().creditCards);
	}
	
	public RegisterShippingAdress(Account account,EnumConfigList<CreditCard> cards)
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
			          //Assert.assertEquals("Responce expected",new JSONObject(response2.getContentAsString()));
			        
			         //Assert.assertEquals("Responce expected",new JSONObject(shipparmsresponse3.getContentAsString()));
			         
			         final List<NameValuePair> Paymentparms = new ArrayList<NameValuePair>();
			         
			         Paymentparms.add(new NameValuePair("billing", "on"));
			         //Paymentparms.add(new NameValuePair("shipmentUUID", shipmetUId));
			         //Paymentparms.add(new NameValuePair("UUID", UId));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_email", account.email));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_firstName", account.firstname));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_lastName", account.lastname));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_address1", account.shippingAddress.addressLine1));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_city", account.shippingAddress.city));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_states_stateCode", account.shippingAddress.stateCode));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_postalCode", account.shippingAddress.zip));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_addressFields_country", account.shippingAddress.countryCode));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_contactInfoFields_phone", "+"+account.mobile));
			         Paymentparms.add(new NameValuePair("localizedNewAddressTitle", "New Address"));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_paymentMethod", "AdyenComponent"));
//			         Paymentparms.add(new NameValuePair("dwfrm_billing_adyenPaymentFields_adyenStateData", account.PaymentStatedata));
//			         Paymentparms.add(new NameValuePair("csrf_token",Csrf));
//			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardNumber", "************4305"));
//			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardType", "visa"));
			         
			         Paymentparms.add(new NameValuePair("dwfrm_billing_adyenPaymentFields_adyenStateData", creditCard.PaymentStatedata));
			         Paymentparms.add(new NameValuePair("csrf_token",Csrf));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardNumber", creditCard.encryptedcardnumber));
			         Paymentparms.add(new NameValuePair("dwfrm_billing_creditCardFields_cardType", creditCard.type));
			         Paymentparms.add(new NameValuePair("adyenPaymentMethod", "Credit Card"));
			         Paymentparms.add(new NameValuePair("brandCode", "scheme"));
			         HttpRequest req3 = new HttpRequest()
								.XHR().header("sec-fetch-dest", "empty").header("sec-fetch-mode", "cors").header("sec-fetch-site", "same-origin")
								.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutServices-SubmitPayment")
			                    .POST()             
							    .postParams(Paymentparms);
			         WebResponse response3=req3.fire();
			       // Assert.assertEquals("Responce expected",new JSONObject(response3.getContentAsString()));
//			         HttpRequest req4 = new HttpRequest()
//								.XHR()
//								.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/OpttyCheckout-IsOptty")
//			                    .POST();
//			         WebResponse response4=req4.fire();
			         HttpRequest req5 = new HttpRequest()
								.XHR()
								.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/CheckoutServices-PlaceOrder")
			                    .POST();
			         WebResponse response5=req5.fire();
			         boolean error=false;
                     if(response5.getStatusCode()==200)
                     {
                         // Assert.assertEquals("Response expected",new JSONObject(response5.getContentAsString()));
                             error=  (boolean) new JSONObject(response5.getContentAsString()).get("error");
                             if(error)
                                {
                                    Assert.assertEquals("Response Error Message: ", new JSONObject(response5.getContentAsString()).get("errorMessage"));
                                }
                     }
                     else
                     {
                         Assert.fail(response5.getStatusMessage());
                     }
                     
                     String OrderId=new JSONObject(response5.getContentAsString()).getString("orderID");
 			        String orderToken=new JSONObject(response5.getContentAsString()).getString("orderToken");
 	
 			        final List<NameValuePair> confirmparam = new ArrayList<NameValuePair>();
 			         
 			        confirmparam.add(new NameValuePair("orderID", OrderId));
 			        confirmparam.add(new NameValuePair("orderToken", orderToken));
 			         HttpRequest req6 = new HttpRequest()
 								.url(new JSONObject(response5.getContentAsString()).getString("continueUrl")).param("ID", OrderId).postParams(confirmparam)
 			                    .POST();
 			         WebResponse response6=req6.fire();
//				        String OrderId=new JSONObject(response5.getContentAsString()).getString("orderID");
//				         HttpRequest req6 = new HttpRequest()
//									.url(new JSONObject(response5.getContentAsString()).getString("continueUrl")).param("ID", OrderId)
//				                    .POST();
//				         WebResponse response6=req6.fire();
				         Assert.assertEquals(true,    response6.getContentAsString().contains(OrderId));
		  		
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