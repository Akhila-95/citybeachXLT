package com.xceptance.loadtest.posters.actions.checkout;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.configuration.EnumConfigList;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.data.CreditCard;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.checkout.OrderReviewPage;
import com.xceptance.loadtest.posters.models.pages.checkout.PaymentPage;

/**
 * Handles the payment page.
 * 
 * @author Xceptance Software Technologies
 */
public class CheckoutPayment extends PageAction<CheckoutPayment>
{
	private Account account;
	
	private CreditCard primcreditCard;
	private EnumConfigList<CreditCard> creditCards;
	
	public CheckoutPayment()
	{
		this(Context.get().data.getAccount().get(), Context.get().data.getAccount().get().creditCards);
	}
	
	public CheckoutPayment(Account account, EnumConfigList<CreditCard> creditCard)
	{
		this.account = account;
		
		this.creditCards = creditCard;
	}
	
	@Override
	public void precheck()
	{
		super.precheck();
		primcreditCard=creditCards.get(1);
			
	}
	
	@Override
	protected void doExecute() throws Exception
	{
		Assert.assertEquals("Card Number",primcreditCard.number);
//		// Fill payment form
//		PaymentPage.paymentForm.fillForm(account, creditCard);
//		
//		// Click continue button
//		loadPageByClick(PaymentPage.paymentForm.getContinueButton());
	}

	@Override
	protected void postValidate() throws Exception
	{
        Validator.validatePageSource();

        // Validate that it is the order review (place order) page
        OrderReviewPage.instance.validate();
	}
}