package com.adobe.aem.guides.aemspa.react.core.services;/*
package com.mysite.core.models.service;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.settings.SlingSettingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceImplTest {
    @InjectMocks
    ArticleServiceImpl articleService;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    Page articlePage;
    @Mock
    Resource resource,parentLayout,titlecontainer,imgcontainer,jcrContentResource,textContainer;
    @Mock
    Node node,contentChildNode,node2,node1,node3;
    @Mock
    Iterator<Resource> layoutIterator;
    @Mock
    NodeIterator titleContentIterator,imageContentIterator,textContentIterator;
    @Mock
    ArticleItem articleData;
    @Mock
    SlingSettingsService slingSettingService;
    
    private ArticleItem articleItem;
    
    private final static String LINK = "/content";
	private final static String ALT_TEXT = "altText";
	private final static String DATE_LABEL = "datelabel";
	private final static String ADDRESS = "address";
	private final static String PHONE = "phone";
	private final static String PHONE_HELP_TEXT = "phoneHelpText";
	private final static String TEXT = "text";
	private final static String DIRECTION_LINK = "directionLink";
	private final static String CENTER_LOCATION = "centerLocation";
	private final static String QUALIFICATION = "qualification";
	private final static String CENTERS = "centers";
	private final static Integer CENTER_COUNT = 2;
	private final static String JOB_TITLE = "job_title";
	
	

    String pagePath = "/content/sites/us";

    ValueMap valueMap;

    String tags[] = {"content","site"};

    @Mock
    PageManager pageManager;

    @Mock
    ElasticSearchMetadataServiceImpl elasticSearchMetadataService;

    @Mock
    Property property;

    String date = "2022-02-24T16:21:05.505+10:00";

    @Test
    public void testgetArticleDataPatient() throws RepositoryException {
        String contentSkin ="teaserRelatedPatientStory";
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String query ="SELECT * FROM [nt:unstructured]\n" +
                "where isdescendantnode('/content/sites/us')\n" +
                "and [sling:resourceType] = 'test/components/layout'\n" +
                "and [skin] = 'patientStoryContent'";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node3);
        when(node3.hasProperty("text/title")).thenReturn(true);
        when(node3.getProperty("text/title")).thenReturn(property);
        when(property.getString()).thenReturn("content*");
        articleService.getArticleData(resourceResolver,pagePath,contentSkin);
    }
    @Test
    public void testgetArticleDataNewsPrimary() throws RepositoryException {
        String contentSkin ="teaserFeaturedNewsPrimary";
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String query = "SELECT * FROM [nt:unstructured]\n" +
                "where isdescendantnode('/content/sites/us')\n" +
                "and [sling:resourceType] = 'test/components/layout'\n" +
                "and [skin] = 'articleContent'";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);

        when(articlePage.getPath()).thenReturn("/content/us");
        when(resourceResolver.getResource("/content/us/jcr:content")).thenReturn(jcrContentResource);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cq:lastReplicated", date);
        valueMap = new ValueMapDecorator(map);
        when(jcrContentResource.getValueMap()).thenReturn(valueMap);
        when(parentLayout.getChild("container")).thenReturn(textContainer);
        when(textContainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(textContentIterator);
        when(textContentIterator.hasNext()).thenReturn(true,false);
        when(textContentIterator.nextNode()).thenReturn(contentChildNode);
        articleService.getArticleData(resourceResolver,pagePath,contentSkin);
    }

    @Test
    public void testgetPatientStoryCards() throws RepositoryException {
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String query = "SELECT layout.* FROM [nt:unstructured] AS layout INNER JOIN [cq:Page] AS page ON ISDESCENDANTNODE(layout, page) WHERE ISCHILDNODE(page,'/content/sites/us') AND page.[/jcr:content/sling:resourceType]='test/components/structure/articlePage' AND layout.[sling:resourceType] = 'test/components/layout' AND layout.[skin] = 'patientStoryContent'";
        String contentSkin ="teaserFeaturedNewsPrimary";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(parentLayout)).thenReturn(articlePage);
        when(articlePage.getPath()).thenReturn("/content/site/us");
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node1);
        articleService.getPatientStoryCards(resourceResolver,pagePath,contentSkin);
    }

    @Test
    public void testgetNewsArticleCards() throws RepositoryException {
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String contentSkin ="teaserFeaturedNewsPrimary";
        String query = "SELECT layout.* FROM [nt:unstructured] AS layout INNER JOIN [cq:Page] AS page ON ISDESCENDANTNODE(layout, page) WHERE ISCHILDNODE(page,'/content/sites/us') AND page.[/jcr:content/sling:resourceType]='test/components/structure/articlePage' AND layout.[sling:resourceType] = 'test/components/layout' AND layout.[skin] = 'articleContent'";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(parentLayout)).thenReturn(articlePage);
        when(articlePage.getPath()).thenReturn("/content/site/us");
        when(resourceResolver.getResource("/content/site/us")).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(elasticSearchMetadataService.getStringTags(resourceResolver,articlePage)).thenReturn(tags);
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node1);
        when(node1.hasProperty("text/title")).thenReturn(true);
        when(node1.getProperty("text/title")).thenReturn(property);
        when(property.getString()).thenReturn("content");

        when(node1.hasProperty("date/publisheddate")).thenReturn(true);
        when(node1.getProperty("date/publisheddate")).thenReturn(property);
        when(property.getString()).thenReturn(date);
        when(parentLayout.getChild("container")).thenReturn(textContainer);

        articleService.getNewsArticleCards(resourceResolver,pagePath,contentSkin,tags);
    }
    
    @Test
    public void testgetNewsArticleCardsEs() throws RepositoryException {
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String contentSkin ="teaserFeaturedNewsPrimary";
        String query = "SELECT layout.* FROM [nt:unstructured] AS layout INNER JOIN [cq:Page] AS page ON ISDESCENDANTNODE(layout, page) WHERE ISCHILDNODE(page,'/content/sites/us') AND page.[/jcr:content/sling:resourceType]='test/components/structure/articlePage' AND layout.[sling:resourceType] = 'test/components/layout' AND layout.[skin] = 'articleContent'";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(parentLayout)).thenReturn(articlePage);
        when(articlePage.getPath()).thenReturn("/content/site/us");
        when(resourceResolver.getResource("/content/site/us")).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(elasticSearchMetadataService.getStringTags(resourceResolver,articlePage)).thenReturn(tags);
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node1);
        when(node1.hasProperty("text/title")).thenReturn(true);
        when(node1.getProperty("text/title")).thenReturn(property);
        when(property.getString()).thenReturn("content");

        when(node1.hasProperty("date/publisheddate")).thenReturn(true);
        when(node1.getProperty("date/publisheddate")).thenReturn(property);
        when(property.getString()).thenReturn(date);
        when(parentLayout.getChild("container")).thenReturn(textContainer);

        articleService.getNewsArticleCards(resourceResolver,pagePath,contentSkin,tags);
    }

    @Test
    public void testgetClinicalTrialCards() throws RepositoryException {
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String query = "SELECT layout.* FROM [nt:unstructured] AS layout INNER JOIN [cq:Page] AS page ON ISDESCENDANTNODE(layout, page) WHERE ISCHILDNODE(page,'/content/sites/us') AND page.[/jcr:content/sling:resourceType]='test/components/structure/articlePage' AND layout.[sling:resourceType] = 'test/components/layout' AND layout.[skin] = 'patientStoryContent'";
        String contentSkin ="teaserClinicalTrialCards";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(parentLayout)).thenReturn(articlePage);
        when(articlePage.getPath()).thenReturn("/content/site/us");
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node1);
        articleService.getClinicalTrialCards(resourceResolver,pagePath,contentSkin,tags);
            }
    
    @Test
    public void testgetWellbeingBlogCards() throws RepositoryException {
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(resource.adaptTo(Page.class)).thenReturn(articlePage);
        when(articlePage.getContentResource()).thenReturn(resource);
        when(articlePage.isValid()).thenReturn(true);
        when(resource.getResourceType()).thenReturn("test/components/structure/articlePage");
        when(layoutIterator.hasNext()).thenReturn(true,false);
        when(layoutIterator.next()).thenReturn(parentLayout);
        when(parentLayout.getChild("titlecontainer")).thenReturn(titlecontainer);
        when(titlecontainer.adaptTo(Node.class)).thenReturn(node);
        when(node.getNodes("content*")).thenReturn(titleContentIterator);
        when(parentLayout.getChild("imgcontainer")).thenReturn(imgcontainer);
        when(imgcontainer.adaptTo(Node.class)).thenReturn(node2);
        when(node2.getNodes("content*")).thenReturn(imageContentIterator);
        when(imageContentIterator.hasNext()).thenReturn(true,false);
        when(imageContentIterator.nextNode()).thenReturn(contentChildNode);
        String query = "SELECT layout.* FROM [nt:unstructured] AS layout INNER JOIN [cq:Page] AS page ON ISDESCENDANTNODE(layout, page) WHERE ISCHILDNODE(page,'/content/sites/us') AND page.[/jcr:content/sling:resourceType]='test/components/structure/articlePage' AND layout.[sling:resourceType] = 'test/components/layout' AND layout.[skin] = 'patientStoryContent'";
        String contentSkin ="teaserWellbeingBlogCards";
        when(resourceResolver.findResources(query, Query.JCR_SQL2)).thenReturn(layoutIterator);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(parentLayout)).thenReturn(articlePage);
        when(articlePage.getPath()).thenReturn("/content/site/us");
        when(titleContentIterator.hasNext()).thenReturn(true,false);
        when(titleContentIterator.nextNode()).thenReturn(node1);
        articleService.getWellbeingBlogCards(resourceResolver,pagePath,contentSkin,tags);
                    }
    @Test
	public void testArticleData() {
    	
    	articleItem = new ArticleItem();
		articleItem.setLink(LINK);
		articleItem.setAltText(ALT_TEXT);
		articleItem.setDateLabel(DATE_LABEL);
		articleItem.setAddress(ADDRESS);
		articleItem.setPhoneNo(PHONE);
		articleItem.setPhoneNoHelpText(PHONE_HELP_TEXT);
		articleItem.isMultipleContacts();
		articleItem.setMultipleContacts(true);
		
		assertEquals(LINK, articleItem.getLink(),"Link");
		assertEquals(ALT_TEXT, articleItem.getAltText(),"altText");
		assertEquals(DATE_LABEL, articleItem.getDateLabel(),"datelabel");
		assertEquals(ADDRESS, articleItem.getAddress(),"address");
		assertEquals(PHONE, articleItem.getPhoneNo(),"phone");
		assertEquals(PHONE_HELP_TEXT, articleItem.getPhoneNoHelpText(),"phoneHelpText");
		
    	
    }
}
*/
