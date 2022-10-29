package me.savag3.dotjava;

import lombok.Getter;
import me.savag3.dotjava.structure.SourceClass;
import me.savag3.dotjava.tree.FileNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Savag3life
 * @since 1.0
 */
public class JavaProject {

    @Getter private final String projectName;
    @Getter private final String projectPackage;

    @Getter private final HashMap<String, SourceClass> classPathMap = new HashMap<>();
    @Getter private final FileNode root = FileNode.of("src/main/java");

    public JavaProject(String projectName, String projectPackage) {
        this.projectName = projectName;
        this.projectPackage = projectPackage;

        // Setup FileTree
        root.addChild(FileNode.of(projectPackage.replace(".", "/"), root));
    }

    public SourceClass createClass(String name) {

        if (name.contains("/")) {
            String[] split = name.split("/");
            FileNode node = root;
            for (int i = 0; i < split.length; i++) {
                node = FileNode.of(split[i], node);
            }
        }

        String fileName = this.projectPackage + "." + name;
        SourceClass sourceClass = new SourceClass(name, this.projectPackage);
        classPathMap.put(fileName, sourceClass);

        return sourceClass;
    }

    public void write() {
        File projectFile = initialize();

        for (Map.Entry<String, SourceClass> entry : classPathMap.entrySet()) {
            File f = new File(projectFile, entry.getKey().replace(".", "/") + ".java");
            if (!f.exists()) {
                try {
                    System.out.println("Creating file: " + f.getAbsolutePath());
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writeFile(entry.getValue().asSource().toString().getBytes(), f);
        }

    }

    private File initialize() {
        File projectsFile = new File("projects");
        if (!projectsFile.exists()) projectsFile.mkdirs();


        File projectFile = new File("projects/" + projectName);
        if (!projectFile.exists()) projectFile.mkdirs();


        root.writeTree(projectFile);

        return projectFile;
    }

    private void writeFile(byte[] bytes, File f) {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
