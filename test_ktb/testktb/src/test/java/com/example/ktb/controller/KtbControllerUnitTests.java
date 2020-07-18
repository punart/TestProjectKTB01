package com.example.ktb.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ktb.model.ReqData;
import com.example.ktb.model.ReturnDataWrapper;

@ExtendWith(MockitoExtension.class)
public class KtbControllerUnitTests {
	@InjectMocks
	CalNumberController calNumberController;
	@Test
	public void testProcess() 
	{	
		 ReqData reqData =new ReqData();
		 reqData.setTotal(8);
		 List<Integer> list = new ArrayList<Integer>();
		 list.add(2);
		 list.add(3);
		 list.add(4);
		 list.add(5);
		 list.add(7);
		 list.add(8);
		 reqData.setListInt(list);
		ReturnDataWrapper data =	calNumberController.calNumberService(reqData);
		System.out.println(data.getResponseHeader().getResponseCode());
		System.out.println(data.getResponseHeader().getResponseError());
		System.out.println(data.getResponseHeader().getResponseMessage());
		System.out.println(data.getData());
		List<Integer> lists = (List<Integer>) data.getData();
		assertThat(lists.size()).isNotZero();
		assertThat(data.getResponseHeader().getResponseCode()).isEqualTo("000");
		
	
	}
}
