package fr.ippon.portlet.model;

import java.io.Serializable;

/**
 * Simple Spring form bean
 *
 * @author afillatre@ippon.fr
 * @since 14/10/14
 */
public class MessageForm implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
