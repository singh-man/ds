package dp;

import java.util.Random;
import java.util.function.Consumer;

public class Template {
    public static void withResource(Consumer<Resource> consumer) {
        Resource resource = new Resource();
        try {
            consumer.accept(resource);
        } finally {
            resource.dispose();
        }
    }

    public static void main(String[] args) {
        withResource(resource -> resource.useResource());
        withResource(resource -> resource.employResource());
    }

    class Resource {
        public Resource() {
            System.out.println("Resource created");
        }

        public void useResource() {
            riskyOperation();
            System.out.println("Resource used");
        }

        public void employResource() {
            riskyOperation();
            System.out.println("Resource employed");
        }

        public void dispose() {
            System.out.println("Resource disposed");
        }

        private void riskyOperation() {
            if (new Random().nextInt(10) == 0) {
                throw new RuntimeException();
            }
        }

        public static void main(String[] args) {
            Resource resource = new Resource();
            resource.useResource();
            resource.employResource();
            resource.dispose();
        }
    }
}