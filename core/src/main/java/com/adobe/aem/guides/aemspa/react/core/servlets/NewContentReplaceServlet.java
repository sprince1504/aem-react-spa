package com.adobe.aem.guides.aemspa.react.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(service = { Servlet.class },
        property = { SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
                SLING_SERVLET_PATHS + "=/bin/NewContentReplaceServlet" })
public class NewContentReplaceServlet extends SlingAllMethodsServlet {
    private static Logger LOG = LoggerFactory.getLogger(NewContentReplaceServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String oldPagePath = "";
        String tagResourceType = "";
        String propertyName = "value";
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            PageManager pm = resourceResolver.adaptTo(PageManager.class);
            Page page = pm.getContainingPage(oldPagePath);
            Iterator<Page> it = page.listChildren();
            while(it.hasNext()) {
                Page childPage = it.next();
                Resource childPageJcrContent = childPage.getContentResource();
                ModifiableValueMap jcrProps = childPageJcrContent.adaptTo(ModifiableValueMap.class);
                List<Resource> tagResourceList = getAllChildBasedonResourceType(childPageJcrContent,tagResourceType);
                for(Resource tagResource: tagResourceList) {
                    if (tagResource.getName().equalsIgnoreCase("tags")) {
                        ValueMap mProperties = tagResource.adaptTo(ValueMap.class);
                        String[] nodeProp = mProperties.get(propertyName, String[].class);
                        String updatedTags = "";
                        List<String> conditionList = new ArrayList<>();
                        List<String> specialityList = new ArrayList<>();
                        for (String newTagValue : nodeProp) {
                            if(!newTagValue.contains("service")){
                                if (newTagValue.contains("condition")) {
                                    updatedTags = newTagValue.replace(":au/condition", ":condition");
                                    String conditionTags = checkDuplicate(updatedTags);
                                    conditionList.add(conditionTags);
                                }
                            } else if (newTagValue.contains("speciality")) {
                                updatedTags = newTagValue.replace(":au/speciality", ":speciality");
                                String specialitytags = checkDuplicate(updatedTags);
                                specialityList.add(specialitytags);
                            }
                        }
                        jcrProps.put("specialitytag", specialityList.stream().toArray(String[]::new));
                        jcrProps.put("conditiontag", conditionList.stream().toArray(String[]::new));
                        childPageJcrContent.getResourceResolver().refresh();
                        childPageJcrContent.getResourceResolver().commit();
                    }
                }

            }
        }catch (Exception e){

        }
    }

    public List<Resource> getAllChildBasedonResourceType(Resource parentRes, String resType) {
        List<Resource> filteredResources = new ArrayList<>();
        try {
            Iterator<Resource> childrenItr = parentRes.listChildren();
            while (childrenItr.hasNext()) {
                Resource childRes = childrenItr.next();
                if(childRes.getResourceType().contains(resType)){
                    filteredResources.add(childRes);
                }
                if(childRes.hasChildren())
                    filteredResources.addAll(getAllChildBasedonResourceType(childRes, resType));
            }

        } catch (RuntimeException e) {
            LOG.error("RuntimeException {}", e.getMessage());
        }
        return filteredResources;
    }

    public String checkDuplicate(String duplicateString){
        String[] duplicate = duplicateString.split("/");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<duplicate.length;i++){
            String newTag = duplicate[i]+"/";
            if(i==0){
                sb.append(newTag);
            } else if(sb.indexOf(newTag) == -1) {
                sb.append(newTag);
            }
        }
        return StringUtils.chop(sb.toString());
    }
}
