package com.adobe.aem.guides.aemspa.react.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {CustomText.class, ComponentExporter.class},
        resourceType = CustomText.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION,
        options = {
                @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
                @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "false")
        }
)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class CustomText implements ComponentExporter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomText.class);

    /**
     * Component Path
     */
    public static final String RESOURCE_TYPE = "aem-spa-react/components/customText";

    public static final String RESOURCE_NAME = "Custom Text";

    @Inject
    private SlingHttpServletRequest request;

    @Getter
    @ValueMapValue
    private String text;

    @Override
    public String getExportedType() {
        return request != null ? request.getResource().getResourceType() : "";
    }

    public String getShowPlaceholder() {
        return RESOURCE_NAME;
    }
}
