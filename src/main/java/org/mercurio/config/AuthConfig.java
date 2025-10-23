package org.mercurio.config;

public class AuthConfig {
    public static final String CLIENT_ID = "51149666-2bff-4a6e-8d7b-01c45772ba58";
    public static final String SCOPE = "XboxLive.signin offline_access openid profile";
    public static final String GRANT_TYPE = "urn:ietf:params:oauth:grant-type:device_code";

    public static final String MS_VERI_URL    = "https://www.microsoft.com/link";
    public static final String MS_AUTH_URL    = "https://login.microsoftonline.com/consumers/oauth2/v2.0/devicecode";
    public static final String MS_TOKEN_URL   = "https://login.microsoftonline.com/consumers/oauth2/v2.0/token";
    public static final String XBL_AUTH_URL   = "https://user.auth.xboxlive.com/user/authenticate";
    public static final String XSTS_AUTH_URL  = "https://xsts.auth.xboxlive.com/xsts/authorize";
    public static final String MC_LOGIN_URL   = "https://api.minecraftservices.com/authentication/login_with_xbox";
    public static final String MC_PROFILE_URL = "https://api.minecraftservices.com/minecraft/profile";
}
