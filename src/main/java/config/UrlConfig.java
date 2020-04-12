package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:config.properties")
public interface UrlConfig extends Config  {

    @Key("url")
    String url();
}
