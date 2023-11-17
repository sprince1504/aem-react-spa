package com.adobe.aem.guides.aemspa.react.core.servlets;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=prom app aem Search",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/global/contentFragmentReference"})
public class ContentFragmentReference extends SlingAllMethodsServlet {

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String searchKeyword = request.getParameter("searchKeyword");
        try {
            //List<String> searchContentFragmentReferenceList = searchContentFragment(request, searchKeyword);
            List<String> searchPageReferenceList = FullTextAEMPage(request, searchKeyword);

            List<String> combinedList = new ArrayList<>();
            //combinedList.addAll(searchContentFragmentReferenceList);
            combinedList.addAll(searchPageReferenceList);

            String jsonResult = convertListToJson(combinedList);

            response.getWriter().write(jsonResult);
            response.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> searchContentFragment(SlingHttpServletRequest request, String searchKeyword) {
        try {
            SearchResult result = null;
            List<String> searchFragmentInPageList2 = new ArrayList<>();
            ResourceResolver resolver = request.getResourceResolver();
            Session session = resolver.adaptTo(Session.class);
            final Map<String, String> predicateMap = new HashMap<String, String>();
            predicateMap.put("type", "dam:Asset");
            predicateMap.put("path", "/content/dam");
            predicateMap.put("boolproperty", "jcr:content/contentFragment");
            predicateMap.put("fulltext", searchKeyword);
            PredicateGroup predicateGroup = PredicateGroup.create(predicateMap);
            Query query = queryBuilder.createQuery(predicateGroup, session);
            result = query.getResult();
            List<Hit> hits = result.getHits();
            for (Hit hit : hits) {
                String path = hit.getPath();
                searchFragmentInPageList2 = searchFragmentInPage(request, path);
            }
            return searchFragmentInPageList2;
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> searchFragmentInPage(SlingHttpServletRequest request, String path){
        try {
            List<String> searchFragmentInPageList = new ArrayList<>();
            SearchResult result = null;
            ResourceResolver resolver = request.getResourceResolver();
            Session session = resolver.adaptTo(Session.class);
            final Map<String, String> predicateMap = new HashMap<String, String>();
            predicateMap.put("property", "fragmentPath");
            predicateMap.put("property.value", path);
            predicateMap.put("path", "/content/dd");
            PredicateGroup predicateGroup = PredicateGroup.create(predicateMap);
            Query query = queryBuilder.createQuery(predicateGroup, session);
            result = query.getResult();
            List<Hit> hits = result.getHits();
            for (Hit hit : hits) {
                String referencePath = hit.getPath();
                searchFragmentInPageList.add(referencePath);
            }
            return searchFragmentInPageList;
        }catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> FullTextAEMPage(SlingHttpServletRequest request, String searchKeyword) {
        try {
            SearchResult result = null;
            List<String> searchPageReferenceList = new ArrayList<>();
            ResourceResolver resolver = request.getResourceResolver();
            Session session = resolver.adaptTo(Session.class);
            final Map<String, String> predicateMap = new HashMap();
            predicateMap.put("fulltext", searchKeyword);
            predicateMap.put("type", "cq:Page");
            predicateMap.put("path", "/content/dd");
            predicateMap.put("p.limit", "-1");
            PredicateGroup predicateGroup = PredicateGroup.create(predicateMap);
            Query query = queryBuilder.createQuery(predicateGroup, session);
            result = query.getResult();
            List<Hit> hits = result.getHits();
            for (Hit hit : hits) {
                String pagePath = hit.getPath();
                searchPageReferenceList.add(pagePath);
            }
            return searchPageReferenceList;
        }catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertListToJson(List<String> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}