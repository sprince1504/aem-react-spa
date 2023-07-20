package com.adobe.aem.guides.aemspa.react.core.servlets;

import com.adobe.aem.guides.aemspa.react.core.models.GraphQLAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.json.Json;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=SaveRecipe",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.selectors=graphql",
        "sling.servlet.extensions=json",
        "sling.servlet.resourceTypes=sling/servlet/default"
})
public class GraphQLJavaServlet extends SlingAllMethodsServlet {


    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        resp.addHeader("Content-Type","application/json");
        try {
            String query = req.getParameter("query");
            GraphQLAdapter graphQLAdapter = req.adaptTo(GraphQLAdapter.class);
            ExecutionResult executionResult = graphQLAdapter.execute(query);
            String response = new ObjectMapper().writeValueAsString(executionResult);
            resp.getWriter().print(response);
            resp.getWriter().flush();
        }catch (Exception e ){
            e.getMessage();
        }
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req,
                          final SlingHttpServletResponse resp) {
        resp.addHeader("Content-Type", "application/json");
        try {
            GraphQLAdapter graphQLAdapter = req.adaptTo(GraphQLAdapter.class);
            String query = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            if (StringUtils.isNotEmpty(query)) {
                ExecutionResult executionResult = graphQLAdapter.execute(query);
                String response = new ObjectMapper().writeValueAsString(executionResult);
                resp.getWriter().print(response);
                resp.getWriter().flush();
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
