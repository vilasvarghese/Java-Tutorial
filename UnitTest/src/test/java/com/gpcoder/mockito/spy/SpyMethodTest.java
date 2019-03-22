package com.gpcoder.mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SpyMethodTest {

	@Test
	public void test() {
		List<String> spyList = Mockito.spy(new ArrayList<>());

		// Using mock object
		spyList.add("gpcoder.com");

		// Method add() is really called,
		// it run on real object, so the size is 1
		Assert.assertEquals(1, spyList.size());
		Assert.assertEquals("gpcoder.com", spyList.get(0));

		// We can assign the size of spy object
		Mockito.when(spyList.size()).thenReturn(5);
		Assert.assertEquals(5, spyList.size());
	}
}
