package com.adobe.aem.guides.aemspa.react.core.services;/*
package com.mysite.core.models.service;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class ValidationServiceTest {

    @Mock
    Resource currentResource,container,resource,layoutResource,layoutContainer,innerLayoutResource,innerLayoutContainer;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    Form formContainerResource;
    @Mock
    FormContainerSDM formContainerSDM;
    @Mock
    Iterable<Resource> children,children2,children3;
    @Mock
    Spliterator<Resource> spliteratorChildren,spliteratorChildren2,spliteratorChildren3;
    @Mock
    Node node,layoutNodes,innerLayoutnode,innerLayouts;
    @Mock
    NodeIterator layout,innerLayout;
    @Test
    public void testgetValidationJSON() throws RepositoryException {
        when(currentResource.adaptTo(Form.class)).thenReturn(formContainerResource);
        when(formContainerResource.getFormContainer()).thenReturn(formContainerSDM);
        when(formContainerSDM.getFormContainerId()).thenReturn("formId");
        when(currentResource.getPath()).thenReturn("/content/us");
        when(resourceResolver.getResource("/content/us/container")).thenReturn(container);
        when(container.getChildren()).thenReturn(children);
        when(children.spliterator()).thenReturn(spliteratorChildren);
        when(resource.getResourceType()).thenReturn("/components/form");
        when(container.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("layout*")).thenReturn(layout);
        when(layout.hasNext()).thenReturn(true,false);
        when(layout.nextNode()).thenReturn(layoutNodes);
        when(layoutNodes.getPath()).thenReturn("/content/site/us");
        when(resourceResolver.getResource("/content/site/us")).thenReturn(layoutResource);
        when(layoutResource.getChild("container")).thenReturn(layoutContainer);
        when(layoutContainer.getChildren()).thenReturn(children2);
        when(children2.spliterator()).thenReturn(spliteratorChildren2);
        when(innerLayoutContainer.adaptTo(Node.class)).thenReturn(innerLayoutnode);
        when(innerLayoutnode.getNodes("layout*")).thenReturn(innerLayout);
        when(innerLayout.hasNext()).thenReturn(true,false);
        when(innerLayout.nextNode()).thenReturn(innerLayouts);
        when(innerLayouts.getPath()).thenReturn("/content/site/us");
        when(resourceResolver.getResource("/content/site/us")).thenReturn(innerLayoutResource);
        when(innerLayoutResource.getChild("container")).thenReturn(innerLayoutContainer);
        when(innerLayoutContainer.getChildren()).thenReturn(children3);
        when(children3.spliterator()).thenReturn(spliteratorChildren3);
        validationService.getValidationJSON(currentResource,resourceResolver);
    }
}
*/
