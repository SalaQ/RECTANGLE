package com.seu.wsn.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.wsn.Core.Pojo.UwData;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.UwDataService;
import com.seu.wsn.Service.NodeService;

@Controller
public class DrawChart {

	@Autowired
	private UwDataService uwDataService;
	@Autowired
	private NodeService nodeService;
	
	public void setUwDataService(UwDataService uwDataService) {
		this.uwDataService = uwDataService;
	}
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	@RequestMapping("/getUwData")
	@ResponseBody
	public ModelMap getUwData(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		List<UwData> uwDataList = uwDataService.uwDataList();
		for (int i = 0; i < uwDataList.size(); i++) {
			if (uwDataList.get(i).getDataType().equals("CRASH")) {
				uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
						Double.parseDouble(uwDataList.get(i).getSourceId()),
						Double.parseDouble(uwDataList.get(i).getNote())});
			} else if (uwDataList.get(i).getDataType().equals("B") || uwDataList.get(i).getDataType().equals("Q") || uwDataList.get(i).getDataType().equals("P")) {
				uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
						Double.parseDouble(uwDataList.get(i).getSourceId())});
			} else {
				if (uwDataList.get(i).getSendOrReceive().equals("R")) {
					uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getTargetId()),
							Double.parseDouble(uwDataList.get(i).getSourceId())});
				} else if (uwDataList.get(i).getSendOrReceive().equals("S")) {
					uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getTargetId())});
				}
			}
//			uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
//					Double.parseDouble(uwDataList.get(i).getSourceId()),
//					Double.parseDouble(uwDataList.get(i).getTargetId()),
//					Double.parseDouble(uwDataList.get(i).getSourceId())});
		}
		model.put(WebConst.UWDATA_LIST, uwDataList);
		int max = 60;
		int startValue = 0;
		int endValue = 60;
		if ((int)Order.now / 10 >= 5) {
			max = (int)Order.now / 10 * 10 + 20;
			startValue = max - 60;
			endValue = max;
		}
		model.put("max", max);
		model.put("startValue", startValue);
		model.put("endValue", endValue);
		return model;
	}
	
	@RequestMapping("/getNetUwData")
	@ResponseBody
	public ModelMap getNetUwData(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		List<UwData> uwDataList = uwDataService.uwDataList();
		for (int i = 0; i < uwDataList.size(); i++) {
				if (uwDataList.get(i).getSendOrReceive().equals("R")) {
					uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getTargetId()),
							Double.parseDouble(uwDataList.get(i).getSourceId())});
				} else if (uwDataList.get(i).getSendOrReceive().equals("S")) {
					uwDataList.get(i).setPoint(new double[] {Double.parseDouble(uwDataList.get(i).getTime()), 
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getSourceId()),
							Double.parseDouble(uwDataList.get(i).getTargetId())});
				}
		}
		model.put(WebConst.UWDATA_LIST, uwDataList);
		int max = 60;
		int startValue = 0;
		int endValue = 60;
		if ((int)Order.now / 10 >= 5) {
			max = (int)Order.now / 10 * 10 + 20;
			startValue = max - 60;
			endValue = max;
		}
		model.put("max", max);
		model.put("startValue", startValue);
		model.put("endValue", endValue);
		System.out.println("get net data");
		return model;
	}
	
	@RequestMapping("/getUwTopology")
	@ResponseBody
	public ModelMap getUwTopology(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		String testId = (String)req.getSession().getAttribute(WebConst.TESTID);
//		List<Node> buoyNodeList = nodeService.getNodeListByNodeType(testId, "buoyNode");
//		List<Node> gatewayNodeList = nodeService.getNodeListByNodeType(testId, "gatewayNode");
//		List<Node> commonNodeList = nodeService.getNodeListByNodeType(testId, "commonNode");
//		for (int i = 0; i < buoyNodeList.size(); i++) {
//			if (buoyNodeList.get(i).getNodeId().equals("null")) {
//				buoyNodeList.get(i).setPoint(new double[] {2000, 2000});
//			} else {
//				buoyNodeList.get(i).setPoint(new double[] {500, 100});
//			}
//		}
//		model.put("buoyNodeList", buoyNodeList);
//		model.put("gatewayNodeList", gatewayNodeList);
//		model.put("commonNodeList", commonNodeList);
		List<Node> nodeList = nodeService.getNodeListByTestId(testId);
		model.put("nodeList", nodeList);
		return model;
	}
}
