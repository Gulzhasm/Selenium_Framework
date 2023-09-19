package properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "file:${user.dir}/src/test/resources/config.properties",
        "file:${user.dir}/src/test/resources/environments/staging1/local-config.properties",
        "file:${user.dir}/src/test/resources/environments/staging2/dev-config.properties"
})
public interface ConfigProperties extends Config {

    String browser();

    @Key("environment")
    String environment();

    @Key("${environment}.base.url")
    String baseURL();

    @Key("${environment}.web.username")
    String username();

    @Key("${environment}.web.password")
    String password();
}
