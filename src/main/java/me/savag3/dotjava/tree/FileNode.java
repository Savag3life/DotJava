package me.savag3.dotjava.tree;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author Savag3life
 * @since 1.0
 */
public class FileNode {

    @Getter @Setter private FileNode[] children;
    @Getter @Setter private FileNode parent;

    @Getter @Setter private String name;

    private FileNode(String name, FileNode parent) {
        this.name = name;
        this.parent = parent;
        this.children = new FileNode[0];
    }

    public static FileNode of(String name) {
        if (name.contains("/")) {
            String[] split = name.split("/");
            FileNode node = new FileNode(split[0], null);
            for (int i = 1; i < split.length; i++) {
                node = new FileNode(split[i], node);
            }
            return node;
        }
        return new FileNode(name, null);
    }

    public static FileNode of(String name, FileNode parent) {
        return new FileNode(name, parent);
    }

    public void addChild(FileNode child) {
        FileNode[] children = this.children;
        FileNode[] newChildren = new FileNode[children.length + 1];
        System.arraycopy(children, 0, newChildren, 0, children.length);
        newChildren[children.length] = child;
        this.children = newChildren;
    }

    public void writeTree(File node) {
        for (FileNode child : children) {
            File f = new File(node, child.name);
            if (!f.exists()) f.mkdirs();
            if (child.children.length > 0) {
                child.writeTree(f);
            }
        }
    }

    public void walk() {
        walk(0);
    }

    public void walk(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(name);
        for (FileNode child : children) {
            child.walk(depth + 1);
        }
    }
}
