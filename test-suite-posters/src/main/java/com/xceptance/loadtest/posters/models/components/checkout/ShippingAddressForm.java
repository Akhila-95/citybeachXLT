package com.xceptance.loadtest.posters.models.components.checkout;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.FormUtils;

/**
 * Shipping address form component.
 *  
 * @author Xceptance Software Technologies
 */
public class ShippingAddressForm implements Component
{
    public final static ShippingAddressForm instance = new ShippingAddressForm();
    
    @Override
    public LookUpResult locate()
    {
        return Page.find().byId("checkout-main");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public void EmailfillForm(final Account account)
    {
        final HtmlForm form = HPU.find().in(locate().asserted("Expected single Main").single()).byId("guest-customer").single();
        FormUtils.setInputValue(HPU.find().in(form).byId("email-guest"), account.email);
       // FormUtils.checkRadioButton(HPU.find().in(form).byCss("#billEqualShipp-Yes"));
    }
    public String GetShipmentUUid()
    {
    	final HtmlElement  element = HPU.find().in(locate().asserted("Expected single Main").single()).byXPath("//input[@type='hidden'][@name='shipmentUUID']").first();
   	 return element.getAttribute("value");
    }
    public String GetCsrf()
    {
    	 final HtmlElement  element = HPU.find().in(locate().asserted("Expected single Main").single()).byXPath("//input[@type='hidden'][@name='csrf_token']").first();
    	 return element.getAttribute("value");
    }
    public void fillForm(final Account account)
    {
        final HtmlForm form =  HPU.find().in(locate().asserted("Expected single Main").single()).byId("dwfrm_shipping").asserted("Expected single Form").single();
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingFirstNamedefault"), account.firstname);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingLastNamedefault"), account.lastname);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingPhoneNumberdefault"), account.mobile);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingAddressTitledefault"), account.addressTitile);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingAddressOnedefault"), account.shippingAddress.addressLine1);
        //FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingAddressTwodefault"), account.shippingAddress.addressLine2);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingAddressCitydefault"), account.shippingAddress.city);
        FormUtils.select(HPU.find().in(form).byId("shippingCountrydefault"), account.shippingAddress.countryCode);
        //FormUtils.select(HPU.find().in(form).byId("shippingStatedefault"), account.shippingAddress.stateCode);
        FormUtils.setInputValue(HPU.find().in(form).byCss("#shippingZipCodedefault"), account.shippingAddress.zip);
       // FormUtils.checkRadioButton(HPU.find().in(form).byCss("#billEqualShipp-Yes"));
        
    }
    
    public HtmlElement getContinueButton()
    {
    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byXPath("//button[text()='Confirm Address']").asserted("Expected single continue button").single();
    }

    public HtmlElement GetShipItToMe()
    {
    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byXPath("//button[text()='Ship it to me']").asserted("Expected single continue button").single();
    }
    public HtmlElement getConfirmAdressButton()
    {
    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byXPath("//button[contains(text(),'Confirm Address')]").asserted("Expected single continue button").single();
    }

    public HtmlElement GetPaypalpaymentmethod()
    {
    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byXPath("//li[@data-method-id='PayPal']/a").asserted("Expected single continue button").single();
    }
    public LookUpResult GetPaypalpaymentbutton()
    {
    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byXPath("//div[@role='button']/div").asserted("Expected single continue button");
    }

}