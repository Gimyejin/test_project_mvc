package com.care.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration // 설정하는 값이구나 /안쪽에 설정된 값을 bean으로 등록해줌 ==> xml에서 만드던걸 자바코드로 만드는 구나
public class FileConfig {
	@Bean // 리턴으로 돌려주는 것을 빈으로 만들어준다.//bean을 만들어야 파일 업무처리가 가능함
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxInMemorySize(52428800);// 50MB
		mr.setDefaultEncoding("utf-8");
		return mr;
	}
}
