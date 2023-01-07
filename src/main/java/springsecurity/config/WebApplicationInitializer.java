package springsecurity.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		Class[] confArray = {MyAppConfig.class};
		return confArray;
	}

	@Override
	protected String[] getServletMappings() {
		String [] mapping = {"/"};
		return mapping;
	}

}
