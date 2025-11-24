package restaurant.menu;

import java.util.Arrays;
import java.util.List;

abstract class BaseMenuFactory implements MenuFactory {
    protected List<MenuItem> items(MenuItem... items) {
        return Arrays.asList(items);
    }
}
