
package com.xspeeder.common.util.property;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ResourceProperty {


	@Autowired
	private Properties resource_ko_KR;
	
	
	@Autowired
	private Properties resource_en_US;
	
	@Autowired
	private Properties resource_zh_CN;
	
	@Autowired
	private Properties resource_zh_TW;
}
