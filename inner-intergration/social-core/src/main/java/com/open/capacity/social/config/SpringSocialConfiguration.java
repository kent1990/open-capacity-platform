package com.open.capacity.social.config;


import com.open.capacity.social.processor.SocialAuthenticationFilterPostProcessor;
import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Data
public class SpringSocialConfiguration extends SpringSocialConfigurer {
    private  String filterProcessesUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public SpringSocialConfiguration(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }



    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        socialAuthenticationFilterPostProcessor.process(filter);
        return (T) filter;
    }

}
