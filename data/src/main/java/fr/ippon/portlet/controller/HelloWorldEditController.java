package fr.ippon.portlet.controller;

import com.liferay.portal.kernel.servlet.SessionMessages;
import fr.ippon.portlet.model.MessageForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;

/**
 * Shows a form to edit and save the string that will be display in the "view" mode
 *
 * @author afillatre@ippon.fr
 * @since 13/10/2014
 */
@Controller
@RequestMapping("EDIT")
public class HelloWorldEditController {

    public static final String PORTLET_PATH = "portlet-with-tests/";
    public static final String DEFAULT_VIEW = PORTLET_PATH + "edit";
    public static final String VIEW_SAVED = PORTLET_PATH + "edit_saved";

    public static final String PREF_MESSAGE = "display-message";

    public static final String PARAM_MESSAGE = "message";
    public static final String PARAM_MESSAGE_FORM = "messageForm";
    public static final String PARAM_VIEW = "view";
    public static final String PARAM_VIEW_SAVED = "saved";

    @ModelAttribute(PARAM_MESSAGE_FORM)
    public MessageForm getMessageForm() {
        return new MessageForm();
    }

    @RenderMapping
    public String render() {
        return DEFAULT_VIEW;
    }

    /**
     * Dummy method for test purpose only (in real life there are better ways to do that)
     *
     * @param model
     * @param message
     * @return
     */
    @RenderMapping(params = PARAM_VIEW + "=" + PARAM_VIEW_SAVED)
    public String renderAfterSave(Model model,
                         @RequestParam String message) {
        model.addAttribute(PARAM_MESSAGE, message);
        return VIEW_SAVED;
    }

    @ActionMapping
    public void saveConfiguration(ActionRequest request, ActionResponse response, Model model,
                                  @ModelAttribute(PARAM_MESSAGE_FORM) MessageForm messageForm) throws ReadOnlyException, IOException, ValidatorException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(PREF_MESSAGE, messageForm.getMessage());
        preferences.store();

        model.addAttribute(PARAM_MESSAGE, messageForm.getMessage()); // For test purpose only
        response.setRenderParameter(PARAM_MESSAGE, messageForm.getMessage()); // For test purpose only
        SessionMessages.add(request, "request_processed");
    }
}
