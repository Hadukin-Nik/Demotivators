package com.example.demotivators.helper_s;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

public class TemplatesLoader {
    private static Configuration configuration;
    static {
        configuration = new Configuration(Configuration.VERSION_2_3_32);
        ClassTemplateLoader loader = new ClassTemplateLoader(TemplatesLoader.class, "/templates");
        configuration.setTemplateLoader(loader);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
