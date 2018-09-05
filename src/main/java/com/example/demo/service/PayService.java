package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.PayManInfo;
import com.example.demo.repository.PayRepository;

@Service
public class PayService {
	
	@Autowired
	PayRepository payRepository;

	public boolean savePay(PayManInfo payManInfo) {
		if(payRepository.save(payManInfo) != null) {
			return true;
		}else
			return false;
	}
	
	public boolean savePay1(String PayNo, double TradeAmt,double tActualAmt,
							String TradeStatus,Date PayTime,
						    String TradeType, String access_pay_no) {
		if(payRepository.saveadd(PayNo,TradeAmt,tActualAmt,TradeStatus,PayTime,TradeType,access_pay_no)) {
			return true;
		}else
			return false;
	}
	
	public List<PayManInfo> showPayList(){
		return payRepository.findMyAll();
	}


	public Map<String ,Object> isPay(String accessPayNo){
			Map<String,Object> map = new HashMap<>();
			PayManInfo p = payRepository.findByAccessPayNo(accessPayNo);
			if (p.getTradeStatus() == null || "".equals(p.getAccessPayNo()))
				map.put("state",false);
			else
				map.put("state",true);
			return map;
	}
}
