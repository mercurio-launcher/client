package org.mercurio.managers;

import org.mercurio.config.AuthConfig;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthManager {

    public void login() throws IOException, InterruptedException, URISyntaxException {
        HttpManager.AuthResult authRes = HttpManager.getMicrosoftAuthorizationToken();
        openDesktop();

    }

    public void refreshSession() {

    }

    public void logout() {

    }

    public void getCurrentProfile() {

    }

    public void openDesktop() throws IOException, URISyntaxException{
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(AuthConfig.MS_VERI_URL));
        } else {
            throw new IOException("Could not open browser");
        }
    }
}
