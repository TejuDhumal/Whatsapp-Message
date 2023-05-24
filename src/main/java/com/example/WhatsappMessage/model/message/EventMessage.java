package com.example.WhatsappMessage.model.message;

import com.example.WhatsappMessage.model.Data;

import java.util.ArrayList;
import java.util.List;

public class EventMessage {

        public String messaging_product;
        public String recipient_type;

        public String to;
        public String type;
        public Interactive interactive;

    public String getRecipient_type() {
        return recipient_type;
    }

    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Interactive getInteractive() {
        return interactive;
    }

    public void setInteractive(Interactive interactive) {
        this.interactive = interactive;
    }

    public EventMessage() {
    }

    public EventMessage(String messaging_product, String recipient_type, String to, String type, Interactive interactive) {
        this.messaging_product = messaging_product;
        this.recipient_type = recipient_type;
        this.to = to;
        this.type = type;
        this.interactive = interactive;
    }
}
