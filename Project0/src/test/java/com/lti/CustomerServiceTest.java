package com.lti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lti.daos.BidDao;
import com.lti.daos.ItemsDao;
import com.lti.daos.UserDao;
import com.lti.exceptions.UserInvalidException;
import com.lti.models.BidList;
import com.lti.models.Shoes;
import com.lti.models.User;
import com.lti.services.CustomerService;

public class CustomerServiceTest {
	@Mock
	private BidDao bd;
	
	@InjectMocks
	private CustomerService cs;
	@Test
	public void MakeOfferTest() throws UserInvalidException {
		User sampleCustomer = new User();
		sampleCustomer.setId(1);
		
		Shoes sampleShoe= new Shoes();
		sampleShoe.setId(3);
	
//		bd= Mockito.mock(BidDao.class);
		Mockito.when(bd.addItemBid(sampleCustomer.getId(),sampleShoe.getId(), 10.0,"Pending")).thenReturn(1);
	
		int expected = 1;
		assertEquals(expected,cs.makeOffer(1, 3, 10));
	}
	
	public void MakePaymentTest() {
		User customer = new User("Andy", 1);
		Shoes shoe = new Shoes(2);
		shoe.setBrand("test");
//		public BidList(int itemId, int buyerId, double offerPrice, double paymentTotal, String itemStatus
		BidList bid = new BidList(shoe.getId(),customer.getId(),10,0,"Pending");
		
		Mockito.when(bd.makePayment(2, 1, 10)).thenReturn(false);
		
	}

}
