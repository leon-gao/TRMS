package com.TRMS.home.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TRMS.base.RuleException;
import com.TRMS.common.CommonConstants;
import com.TRMS.home.dto.LinkDataDto;
import com.TRMS.home.dto.RuleDto;
import com.TRMS.home.service.IExtractService;

/**
 *
 */
@Service
@Transactional(rollbackFor = RuleException.class)
public class ExtractServiceImpl implements IExtractService {

	@Override
	public List<LinkDataDto> extract(RuleDto ruleDto) {

		validateRule(ruleDto);

		List<LinkDataDto> datas = new ArrayList<LinkDataDto>();
		LinkDataDto data = null;

		try {
			String url = ruleDto.getUrl();
			String[] params = ruleDto.getParams();
			String[] values = ruleDto.getValues();
			String resultTagName = ruleDto.getResultTagName();
			int type = ruleDto.getType();
			int requestType = ruleDto.getRequestMethod();

			Connection conn = Jsoup.connect(url);
			
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}

			Document doc = null;
			switch (requestType) {
				case CommonConstants.GET:
					doc = conn.timeout(100000).get();
					break;
				case CommonConstants.POST:
					doc = conn.timeout(100000).post();
					break;
			}

			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
				case CommonConstants.CLASS:
					results = doc.getElementsByClass(resultTagName);
					break;
				case CommonConstants.ID:
					Element result = doc.getElementById(resultTagName);
					results.add(result);
					break;
				case CommonConstants.SELECTION:
					results = doc.select(resultTagName);
					break;
				default:
					// 当resultTagName为空时默认去body标签
					if (StringUtils.isEmpty(resultTagName)) {
						results = doc.getElementsByTag("body");
					}
			}

			for (Element result : results) {
				Elements links = result.getElementsByTag("a");

				for (Element link : links) {
					// 必要的筛选
					String linkHref = link.attr("href");
					String linkText = link.text();

					data = new LinkDataDto();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);
					datas.add(data);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datas;
	}

	private static void validateRule(RuleDto ruleDto) {
		
		String url = ruleDto.getUrl();
		
		if (StringUtils.isEmpty(url)) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (ruleDto.getParams() != null && ruleDto.getValues() != null) {
			if (ruleDto.getParams().length != ruleDto.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}
}
