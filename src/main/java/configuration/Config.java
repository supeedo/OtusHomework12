package configuration;


@org.aeonbits.owner.Config.Sources("classpath:config.properties")
public interface Config extends org.aeonbits.owner.Config {

    @Key("url")
    String URL();
}
