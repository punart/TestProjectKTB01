package com.example.ktb.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ktb.model.ReqData;
import com.example.ktb.model.ResponseHeader;
import com.example.ktb.model.ReturnDataWrapper;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class CalNumberController {
	public static DecimalFormat df = new DecimalFormat("#.##");

	
	
	@Autowired
	public CalNumberController() {
		
	}


//	@PostMapping("/calNumberService")
    @PostMapping(path= "/calNumberService", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ReturnDataWrapper calNumberService(@RequestBody ReqData reqData) {
	
		ReturnDataWrapper responseData = new ReturnDataWrapper();
		ResponseHeader header = new ResponseHeader();
		boolean action = true;
		
		if(reqData.getTotal() == null || reqData.getTotal() == 0) {
			action = false;
			header.setResponseCode("007");
			header.setResponseMessage("Error  Total Target not null And 0");
			header.setResponseError("Error");
			responseData.setResponseHeader(header);
			responseData.setData(null);
		}
		
		if(reqData.getListInt() == null || reqData.getListInt().size() == 0) {
			action = false;
			header.setResponseCode("008");
			header.setResponseMessage("Error  List number set  not null");
			header.setResponseError("Error");
			responseData.setResponseHeader(header);
			responseData.setData(null);
		}
		
		if(action == true) {
			CalNumberController service = new CalNumberController();
			List<List<Integer>> listData =	service.calProcess(reqData.getListInt(),reqData.getTotal());
			
			header.setResponseCode("000");
			header.setResponseMessage("success");
			header.setResponseError("");
			responseData.setResponseHeader(header);
			responseData.setData(listData);
		}
		
		return responseData;
		
	}
	
	
	

	public List<List<Integer>> calProcess(List<Integer> listInt, Integer total){
		List<List<Integer>> listData = new ArrayList<List<Integer>>();
		
		Map<Integer, List<Integer>>  map = new HashMap<Integer,List<Integer>>();
		int index = 0;
		for(int obj : listInt){
			List<Integer> listResult = new ArrayList<>();
			int x = total % obj ; 
			if(listInt.contains(x)){
				int a = total / obj ;
				for(int row = 0 ; row < a ; row ++){
					listResult.add(obj);
				}
				listResult.add(x);
				map.put(index, listResult);
			}
			if(x == 0){
				if((total / obj) == 1){
					listResult.add(obj);
					map.put(index, listResult);
				}else{
					int a = total / obj ;
					for(int row = 0 ; row < a ; row ++){
						listResult.add(obj);
					}
					map.put(index, listResult);
				}
			}
			index ++;
		}
		
		for(Entry<Integer, List<Integer>> objxx : map.entrySet()){
			List<Integer> list = objxx.getValue();
			
			listData.add(list);
		}
	
		
		return listData;
	}
	
	
	public static void main(String[] args) {
		Integer total = 0;
		List<Integer> listInt = new ArrayList<Integer>();
//		listInt.add(2);
//		listInt.add(3);
//		listInt.add(5);
//		Integer total = 5;
//		
//		calProcess(listInt,total);
//		System.out.println("---------------");
		
		listInt = new ArrayList<>();
		listInt.add(2);
		listInt.add(3);
		listInt.add(6);
		listInt.add(7);
		total = 8;
		CalNumberController service = new CalNumberController();
		List<List<Integer>> listData =	service.calProcess(listInt,total);
		System.out.println(listData);
		
		System.out.println("---------------");
		
		
//		listInt = new ArrayList<>();
//		listInt.add(2);
//		listInt.add(3);
//		listInt.add(4);
//		listInt.add(7);
//		total = 14;
//		
//		calProcess(listInt,total);

	}

}




