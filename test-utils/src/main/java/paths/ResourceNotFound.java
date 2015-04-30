package paths;

/**
 * Created by brudnick on 2015-04-30.
 */
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(final String message) {
        super(message);
    }

}
