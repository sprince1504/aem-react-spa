package com.adobe.aem.guides.aemspa.react.core.services;/*
package com.mysite.core.models.service;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.settings.SlingSettingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class CrumbServiceImplTest {
    @InjectMocks
    BreadcrumbServiceImpl breadcrumbService;
    @Mock
    Page currentPage,page;
    String structureStart = "1";
    @Mock
    Resource resource;
    @Mock
    ResourceResolver resolver;
    @Mock
    SlingSettingsService slingSettingService;
    Set<String> mockRunModes = new TreeSet<String>();

    @Test
    public void testgetItems(){
        when(currentPage.getPath()).thenReturn("/content/sites");
        when(currentPage.getDepth()).thenReturn(2);
        when(currentPage.getAbsoluteParent(1)).thenReturn(page);
        when(page.getContentResource()).thenReturn(resource);
        when(page.getPath()).thenReturn("/content/sites/us");
        when(page.getNavigationTitle()).thenReturn("us");
        when(slingSettingService.getRunModes()).thenReturn(mockRunModes);
        mockRunModes.add("publish");
        breadcrumbService.getItems(currentPage,structureStart,resolver);
    }
    @Test
    public void testgetItems1(){
    	when(currentPage.getPath()).thenReturn("/content/sites");
        when(currentPage.getDepth()).thenReturn(2);
        when(currentPage.getAbsoluteParent(1)).thenReturn(page);
        when(page.getContentResource()).thenReturn(resource);
        when(page.getPath()).thenReturn("/content/sites/us");
        when(page.getNavigationTitle()).thenReturn("us");
        when(slingSettingService.getRunModes()).thenReturn(mockRunModes);     
        mockRunModes.add("author");
        breadcrumbService.getItems(currentPage,structureStart,resolver);
    }
    
    @Test
    public void testgetItems2(){
    	when(currentPage.getPath()).thenReturn("/content/sites");
        when(currentPage.getDepth()).thenReturn(2);
        when(currentPage.getAbsoluteParent(1)).thenReturn(page);
        when(page.getContentResource()).thenReturn(resource);
        when(page.getPath()).thenReturn("/content/sites/us");
        when(page.getNavigationTitle()).thenReturn("us");
        
        breadcrumbService.getItems(currentPage,structureStart,resolver);
    }
}
*/
