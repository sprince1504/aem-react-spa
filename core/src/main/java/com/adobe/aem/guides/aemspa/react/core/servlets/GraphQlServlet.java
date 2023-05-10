/*
package com.adobe.aem.guides.aemspa.react.core.servlets;

import com.adobe.aem.graphql.client.AEMHeadlessClient;
import com.adobe.aem.graphql.client.GraphQlResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=SaveRecipe",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/graphql"})
public class GraphQlServlet extends SlingAllMethodsServlet {

    private static final String OPEN_CURLY_BRACKET = "{";

    private static final String OPEN_BRACKET = "(";

    private static final String CLOSE_BRACKET = ")";
    private static final String CLOSE_CURLY_BRACKET = "}";
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQlServlet.class);
    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        String endpoint = "https://countries.trevorblades.com/graphql";
        String country_code = request.getParameter("country");
        try {
            AEMHeadlessClient aemHeadlessClient = AEMHeadlessClient.builder().endpoint(endpoint)
                    .basicAuth("admin", "admin")
                    .connectTimeout(10000)
                    .readTimeout(30000)
                    .build();

            GraphQlResponse graphQlResponse  = aemHeadlessClient.runQuery(buildQuery(country_code));
            JsonNode data = graphQlResponse.getData();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
        } catch (Exception e) {
            LOGGER.error("Error GraphqlServlet",e);
        }
    }

    public String buildQuery(String country_code) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPEN_CURLY_BRACKET);
        stringBuilder.append("country");
        stringBuilder.append(OPEN_BRACKET);
        stringBuilder.append("code");
        stringBuilder.append(":");
        stringBuilder.append("\""+country_code+"\"");
        stringBuilder.append(CLOSE_BRACKET);
        stringBuilder.append(OPEN_CURLY_BRACKET);
        stringBuilder.append("name");
        stringBuilder.append(" ");
        stringBuilder.append("capital");
        stringBuilder.append(CLOSE_CURLY_BRACKET);
        stringBuilder.append(CLOSE_CURLY_BRACKET);
        return  stringBuilder.toString();
    }
}
*/
