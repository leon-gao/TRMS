package com.TRMS.home.service;

import java.util.List;

import com.TRMS.home.dto.LinkDataDto;
import com.TRMS.home.dto.RuleDto;

/**
 *
 */
public interface IExtractService {

	public List<LinkDataDto> extract(RuleDto ruleDto);
}
