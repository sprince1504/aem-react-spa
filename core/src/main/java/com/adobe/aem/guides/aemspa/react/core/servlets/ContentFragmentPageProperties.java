package com.adobe.aem.guides.aemspa.react.core.servlets;
import com.adobe.aem.guides.aemspa.react.core.models.ContentFragmentPojo;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=prom app aem Search",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/global/cfPageProperties"})
public class ContentFragmentPageProperties extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            String contentFragmentPath = request.getParameter("contentFragmentPath");

            ResourceResolver resolver = request.getResourceResolver();

            ContentFragment cf = resolver.resolve(contentFragmentPath).adaptTo(ContentFragment.class);

            String title = cf.getTitle();

            ContentFragmentPojo contentFragmentPojo = new ContentFragmentPojo(title);

            Gson gson = new Gson();
            String jsonString = gson.toJson(contentFragmentPojo);

            response.getWriter().write(jsonString);
            response.getWriter().close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}