package paths;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by brudnick on 2015-04-30.
 */
public final class ResourcesUtils {

    private ResourcesUtils(){}

    public static String getResourcePath(final String resourceName) {
        URL resource = ResourcesUtils.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new ResourceNotFound(String.format("Resource %s was not found", resourceName));
        }

        return resource.getPath();
    }
    
    public static InputStream getResourceStream(final String resourceName) {
        final InputStream resourceAsStream = ResourcesUtils.class.getClassLoader().getResourceAsStream(resourceName);

        if (resourceAsStream == null) {
            throw new ResourceNotFound(String.format("Resource %s was not found", resourceName));
        }

        return resourceAsStream;
    }

}
