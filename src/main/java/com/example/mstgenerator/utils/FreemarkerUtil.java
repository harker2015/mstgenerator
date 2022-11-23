package com.example.mstgenerator.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import java.util.Map;

@Component
public class FreemarkerUtil {
    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);

    /**
     * 传入需要转义的字符串进行转义
     */
    public static String escapeString(String originStr) {
        return originStr.replaceAll("井", "\\#").replaceAll("￥", "\\$");
    }

    /**
     * freemarker config
     */
    private static Configuration freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    static {
        try {
            freemarkerConfig.setClassForTemplateLoading(FreemarkerUtil.class, "/project/template");
            freemarkerConfig.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "/project/template"));
            freemarkerConfig.setNumberFormat("#");
            freemarkerConfig.setClassicCompatible(true);
            freemarkerConfig.setDefaultEncoding("UTF-8");
            freemarkerConfig.setLocale(Locale.CHINA);
            freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * process Template Into String
     *
     * @param template
     * @param model
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String processTemplateIntoString(Template template, Object model)
            throws IOException, TemplateException {

        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    /**
     * process String
     *
     * @param templateName
     * @param params
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String processString(String templateName, Map<String, Object> params)
            throws IOException, TemplateException {

        Template template = freemarkerConfig.getTemplate(templateName);
        String htmlText = escapeString(processTemplateIntoString(template, params));
        return htmlText;
    }

}