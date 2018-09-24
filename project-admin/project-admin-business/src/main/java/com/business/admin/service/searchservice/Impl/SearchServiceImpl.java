package com.business.admin.service.searchservice.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.admin.dao.searchdao.diz.SearchDao;
import com.business.admin.service.searchservice.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public Map<String, Object> findTestAllCustomer() {
		List<Map<String, Object>> list = searchDao.findTestAllCustomer();
		Map<String, Object> map = new HashMap<String, Object>();
		//将取出的信息放到每一个solr文档对象当中
		for (int i = 0; i < list.size(); i++) {
			 try {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", list.get(i).get("id"));
				document.addField("lefang_refer_price", list.get(i).get("refer_price")); //商品价格
				document.addField("lefang_special_price", list.get(i).get("special_price"));//商品特价
				document.addField("lefang_inspection_report_url", list.get(i).get("inspection_report_url"));//商品图片的url
				document.addField("lefang_str_valuess", list.get(i).get("str_valuess"));//商品的详情
				document.addField("lefang_weisha", list.get(i).get("weisha"));//纬纱，排序用的
				document.addField("lefang_jinsha", list.get(i).get("jinsha"));//经纱排序用的
				solrServer.add(document);
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
				map.put("resultList", null);
				map.put("error", "呵呵哒，出错了");
				map.put("flag", false);
				return map;
			}
		 }
		try {
			solrServer.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("resultList", list);
		map.put("error", null);
		map.put("flag", true);
		return map;
	}

}
