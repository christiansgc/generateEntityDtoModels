package cm.aites.dev.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            //EntityDtoDescription myEntityDtoDesc = getEntitiesDtoDescription("classpath:create-entities-dto.yml");
            EntityDtoGenerator myEntityGenerator = new EntityDtoGenerator("classpath:create-entities-dto.yml");
            myEntityGenerator.generateAllJavaSourceFiles_EntityDto();
            //System.out.println(myEntityDtoDesc);

            //JavaDataClassGenerator javaDataClassGenerator = new JavaDataClassGenerator();
            //javaDataClassGenerator.generateJavaSourceFiles(classSpecifications, outputDirectory);
        } catch (Exception e) {
            System.err.println("Erreur!!!!");
            System.err.println(e);
            //throw new RuntimeException(e);
        }
    }
}