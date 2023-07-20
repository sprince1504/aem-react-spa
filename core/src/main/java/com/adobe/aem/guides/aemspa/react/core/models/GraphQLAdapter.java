package com.adobe.aem.guides.aemspa.react.core.models;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GraphQLAdapter extends AEMGraphQL {
    @Self
    SlingHttpServletRequest request;

    @SlingObject
    Resource resource;

    public static String PARAM_RESOURCE_PATH_VAR="resourcePath";


    @PostConstruct
    protected void init() throws Exception {
        // we will have to check which request is coming in as
        if(request.getMethod().equalsIgnoreCase(HttpConstants.METHOD_GET)) {
            // this mean we are using a get request we will try to grab the resourcePath from query
            resource = request.getResourceResolver().getResource(request.getParameter(PARAM_RESOURCE_PATH_VAR));
        }
        initQuery(request,resource);
    }


}
