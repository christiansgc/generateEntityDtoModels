package cm.aites.dev.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class EntityDtoGenerator {
    private final String patternEntityDtoInTemplate = "entityDtoSpec";
    private final String pathTemplateFile_entity = "javaEntityClassModel.ftl";
    private final String pathTemplateFile_dto = "javaDtoClassModel.ftl";
    private final String pathTemplateFile_dtoCreate = "javaDtoCreateClassModel.ftl";
    private final String pathTemplateFile_dtoEdit = "javaDtoEditClassModel.ftl";
    private final String pathTemplateFile_dtoImport = "javaDtoImportClassModel.ftl";
    private final String pathTemplateFile_dtoExport = "javaDtoExportClassModel.ftl";
    private Configuration configuration;

    private String pathYmlFile_entityDtoDesc;
    //private String pathJavaFileDirectory;

    public EntityDtoGenerator() {
        this.configuration = this.getCustomFreemarkerTemplateConfig();
    }

    public EntityDtoGenerator(String pathYmlFile_entityDtoDesc) {
        this();
        this.pathYmlFile_entityDtoDesc = pathYmlFile_entityDtoDesc;
    }

    private EntityDtoDescription getEntityDtoDescription() throws IOException {
        //System.out.println(this.pathYmlFile_entityDtoDesc);
        //InputStream inputStream = new FileInputStream(ResourceUtils.getFile(this.pathYmlFile_entityDtoDesc));
        InputStream inputStream = new FileInputStream(this.pathYmlFile_entityDtoDesc);
        //System.out.println(inputStream);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        EntityDtoWrapper entitydtoWrapper = objectMapper.readValue(inputStream, EntityDtoWrapper.class);
        System.out.println(entitydtoWrapper);
        return entitydtoWrapper.getEntityDtoDesc();
    }

    /**
     * @throws IOException
     */
    private Configuration getCustomFreemarkerTemplateConfig() {
        Configuration myTemplateConfig = new Configuration(Configuration.VERSION_2_3_32);

        // Set the root of the class path ("") as the location
        // to find templates
        myTemplateConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "");

        myTemplateConfig.setDefaultEncoding("UTF-8");
        myTemplateConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        myTemplateConfig.setLogTemplateExceptions(false);
        myTemplateConfig.setWrapUncheckedExceptions(true);

        /*try {
            FieldUtils.writeField(myTemplateConfig, "trueStringValue", "true", true);
            FieldUtils.writeField(myTemplateConfig, "falseStringValue", "false", true);
        } catch (IllegalAccessException ignored) {
        }*/
        return myTemplateConfig;
    }

    private void generateJavaSourceFile(String pathTemplate, String pathJavaFileDirectory, String nomJavaFile, EntityDtoDescription classEntityDtoSpec) throws Exception {
        Map<String, Object> freemarkerDataModel = new HashMap<>();

        // Get the template to generate Java source files
        Template template = configuration.getTemplate(pathTemplate);

        // Put the EntityDtoDescription into the data model.
        // It can  be accessed in the template
        // through ${entityDtoSpec}
        freemarkerDataModel.put(this.patternEntityDtoInTemplate, classEntityDtoSpec);

        // The Java source file will be generated in the same directory as the YAML file
        File javaSourceFile = new File(pathJavaFileDirectory, nomJavaFile + ".java");
        System.out.println(javaSourceFile.getCanonicalPath());
        System.out.println(javaSourceFile.getName());

        Writer javaSourceFileWriter = new FileWriter(javaSourceFile);

        // Generate the Java source file
        template.process(freemarkerDataModel, javaSourceFileWriter);

    }

    private void generateJavaSourceFiles_Entity(EntityDtoDescription classEntityDtoSpec) throws Exception {
        //EntityDtoDescription classEntityDtoSpec = this.getEntityDtoDescription();
        this.generateJavaSourceFile(this.pathTemplateFile_entity, classEntityDtoSpec.getPathDirEntityClass(), classEntityDtoSpec.getEntityName(), classEntityDtoSpec);
    }

    private void generateJavaSourceFiles_Dto(EntityDtoDescription classEntityDtoSpec) throws Exception {
        //EntityDtoDescription classEntityDtoSpec = this.getEntityDtoDescription();
        File parentDirDto = new File(classEntityDtoSpec.getPathDirDtoClass(), classEntityDtoSpec.getEntityName());
        if (!parentDirDto.exists()) {
            parentDirDto.mkdir();
        }
        if (parentDirDto.exists()) {
            this.generateJavaSourceFile(this.pathTemplateFile_dto, parentDirDto.getCanonicalPath(), classEntityDtoSpec.getEntityName() + "DTO", classEntityDtoSpec);
            this.generateJavaSourceFile(this.pathTemplateFile_dtoCreate, parentDirDto.getCanonicalPath(), classEntityDtoSpec.getEntityName() + "DTOCreate", classEntityDtoSpec);
            this.generateJavaSourceFile(this.pathTemplateFile_dtoEdit, parentDirDto.getCanonicalPath(), classEntityDtoSpec.getEntityName() + "DTOEdit", classEntityDtoSpec);
            this.generateJavaSourceFile(this.pathTemplateFile_dtoImport, parentDirDto.getCanonicalPath(), classEntityDtoSpec.getEntityName() + "DTOImport", classEntityDtoSpec);
            this.generateJavaSourceFile(this.pathTemplateFile_dtoExport, parentDirDto.getCanonicalPath(), classEntityDtoSpec.getEntityName() + "DTOExport", classEntityDtoSpec);
        }
    }

    public void generateAllJavaSourceFiles_EntityDto() throws Exception {
        EntityDtoDescription classEntityDtoSpec = this.getEntityDtoDescription();
        this.generateJavaSourceFiles_Entity(classEntityDtoSpec);
        this.generateJavaSourceFiles_Dto(classEntityDtoSpec);
    }

    /**
     *
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                System.out.println("FileName: " + args[i]);
                EntityDtoGenerator myEntityGenerator = new EntityDtoGenerator(args[i]);
                myEntityGenerator.generateAllJavaSourceFiles_EntityDto();
            } catch (Exception e) {
                System.err.println("ENTITYDTOGENERATOR -----> Erreur !!!!");
                System.err.println(e);
            }
        }
    }
}
