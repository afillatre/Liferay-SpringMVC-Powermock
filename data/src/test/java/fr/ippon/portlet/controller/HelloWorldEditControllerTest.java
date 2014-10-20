package fr.ippon.portlet.controller;

import fr.ippon.portlet.test.unit.AbstractControllerUnitTest;
import fr.ippon.springmvc.test.util.MVCUtil;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.web.portlet.bind.MissingPortletRequestParameterException;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 *
 * @author afillatr@ippon.fr
 * @since 13/10/2014
 */
public class HelloWorldEditControllerTest extends AbstractControllerUnitTest {

    @Test
    public void testDefaultRenderView() throws Exception {
        RenderResponse renderResponse = new MockRenderResponse();
        dispatcherPortlet.render(getMockRenderRequest(), renderResponse);
        Assert.assertEquals(HelloWorldEditController.DEFAULT_VIEW, viewResolver.getViewName());
    }

    @Test
    public void testRenderAfterSaveView() throws Exception {
        MockRenderRequest request = getMockRenderRequest();
        RenderResponse renderResponse = new MockRenderResponse();
        request.setParameter(HelloWorldEditController.PARAM_VIEW, HelloWorldEditController.PARAM_VIEW_SAVED);
        request.setParameter(HelloWorldEditController.PARAM_MESSAGE, "");

        dispatcherPortlet.render(request, renderResponse);
        Assert.assertEquals(HelloWorldEditController.VIEW_SAVED, viewResolver.getViewName());
    }

    public static Object[] parametersForTestRenderAfterSaveModelWithActualParameters() {
        return new Object[][]{
                new Object[] {"Testing"},
                new Object[] {"Other"}
        };
    }

    @Test
    @Parameters
    public void testRenderAfterSaveModelWithActualParameters(String message) throws Exception {
        testRenderAfterSaveModel(message);
    }

    @Test(expected = MissingPortletRequestParameterException.class)
    public void testRenderAfterSaveModelWithoutParameters() throws Exception {
        testRenderAfterSaveModel(null);
    }

    private void testRenderAfterSaveModel(String message) throws PortletException, IOException {
        MockRenderRequest request = getMockRenderRequest();
        RenderResponse renderResponse = new MockRenderResponse();
        request.setParameter(HelloWorldEditController.PARAM_VIEW, HelloWorldEditController.PARAM_VIEW_SAVED);

        if (message != null) {
            request.setParameter(HelloWorldEditController.PARAM_MESSAGE, message);
        }

        dispatcherPortlet.render(request, renderResponse);
        Assert.assertEquals(message, MVCUtil.getModel(request).get(HelloWorldEditController.PARAM_MESSAGE));
    }



    public static Object[] parametersForTestSaveConfiguration() {
        return new Object[][]{
                new Object[] {"Testing"},
                new Object[] {"Other"}
        };
    }
    @Test
    @Parameters
    public void testSaveConfiguration(String message) throws Exception {
        MockActionRequest request = createAndExecuteSaveFormAction(message);
        Assert.assertEquals(message, request.getPreferences().getValue(HelloWorldEditController.PREF_MESSAGE, ""));
    }

    private MockActionRequest createAndExecuteSaveFormAction(String message) throws PortletException, IOException {
        ActionResponse response = new MockActionResponse();
        MockActionRequest request = new MockActionRequest();
        request.setPortletMode(PortletMode.EDIT);
        request.setParameter(HelloWorldEditController.PARAM_MESSAGE, message);

        dispatcherPortlet.processAction(request, response);
        return request;
    }

    @Test
    public void testSaveConfigurationModel() throws Exception {
        final String paramValue = "Testing";
        MockActionRequest request = createAndExecuteSaveFormAction(paramValue);

        Assert.assertEquals(paramValue, MVCUtil.getModel(request).get(HelloWorldEditController.PARAM_MESSAGE));
    }

    @Test
    public void testSaveConfigurationRedirect() throws Exception {
        final String paramValue = "Testing";
        MockActionRequest request = createAndExecuteSaveFormAction(paramValue);

        Assert.assertEquals(paramValue, MVCUtil.getModel(request).get(HelloWorldEditController.PARAM_MESSAGE));
    }

    private MockRenderRequest getMockRenderRequest() {
        MockRenderRequest request = new MockRenderRequest();
        request.setPortletMode(PortletMode.EDIT);
        return request;
    }
}