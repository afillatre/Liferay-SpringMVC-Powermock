package fr.ippon.portlet.controller;

import fr.ippon.portlet.test.annotation.PortletMVCTest;
import fr.ippon.springmvc.test.unit.web.junit.AbstractSpringRunnerControllerTest;
import fr.ippon.springmvc.test.util.MVCUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;

import javax.portlet.RenderResponse;

/**
 *
 * @author afillatr@ippon.fr
 * @since 13/10/2014
 */
@PortletMVCTest
public class HelloWorldViewControllerTest extends AbstractSpringRunnerControllerTest {

    @Test
    public void testDefaultRenderViewName() throws Exception {
        RenderResponse renderResponse = new MockRenderResponse();
        dispatcherPortlet.render(new MockRenderRequest(), renderResponse);
        Assert.assertEquals(HelloWorldViewController.DEFAULT_VIEW, viewResolver.getViewName());
    }

    @Test
    public void testDefaultRenderModel() throws Exception {
        RenderResponse renderResponse = new MockRenderResponse();
        MockRenderRequest request = new MockRenderRequest();
        final String prefValue = "Testing";
        request.getPreferences().setValue(HelloWorldViewController.PREF_MESSAGE, prefValue);

        dispatcherPortlet.render(request, renderResponse);
        Assert.assertEquals(prefValue, MVCUtil.getModel(request).get(HelloWorldViewController.PARAM_MESSAGE));
    }
}