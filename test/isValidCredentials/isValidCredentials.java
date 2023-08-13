import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import domain.validity.InputValidity;
class isValidCredentials {
	InputValidity i;
	@BeforeEach
	void setUp() {
		 i = new InputValidity();
		
		
		
	}

	@Test
	void test() {


	assertTrue(i.isValidCredential("username","Password"));
	assertFalse(i.isValidCredential("username","password"));
	assertFalse(i.isValidCredential(".sername","password"));
	assertFalse(i.isValidCredential("username","p#ssword"));
	assertFalse(i.isValidCredential("username","pas:word"));
	assertThrows(NullPointerException.class, () ->  { i.isValidCredential("","pasSword");});



	}

}
