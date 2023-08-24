package com.xceptance.loadtest.posters.models.components.checkout;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.FormUtils;

public class ShippingAdressEmailForm implements Component{
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
	        final HtmlForm form = locate().asserted("Expected single shipping address form").single();
	        FormUtils.setInputValue(HPU.find().in(form).byCss("#email-guest"), account.email);
	       // FormUtils.checkRadioButton(HPU.find().in(form).byCss("#billEqualShipp-Yes"));
	        
	    }
	    
	    public HtmlElement getContinueButton()
	    {
	    	return HPU.find().in(locate().asserted("Expected single shipping address form").single()).byId("btnAddDelAddr").asserted("Expected single continue button").single();
	    }

}
