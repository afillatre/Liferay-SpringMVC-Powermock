package fr.ippon.portlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderRequest;

/**
 *
 * @author afillatre@ippon.fr
 * @since 13/10/2014
 */
@Controller
@RequestMapping("VIEW")
public class HelloWorldViewController {

    public static final String PORTLET_PATH = "portlet-with-tests/";
    public static final String DEFAULT_VIEW = PORTLET_PATH + "view";

    public static final String PREF_MESSAGE = "display-message";

    public static final String PARAM_MESSAGE = "message";

    @RenderMapping
    public String render(RenderRequest request, Model model) {
        model.addAttribute(PARAM_MESSAGE, request.getPreferences().getValue(PREF_MESSAGE, "World"));
        return DEFAULT_VIEW;
    }
}
