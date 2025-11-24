package restaurant.menu;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * Simple immutable implementation for basic menu items.
 */
public final class SimpleMenuItem implements MenuItem {
    private final String name;
    private final String description;
    private final double basePrice;
    private final MenuCategory category;
    private final Set<MenuTag> tags;

    private SimpleMenuItem(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.basePrice = builder.basePrice;
        this.category = builder.category;
        this.tags = Collections.unmodifiableSet(EnumSet.copyOf(builder.tags));
    }

    public static Builder builder(String name, double basePrice, MenuCategory category) {
        return new Builder(name, basePrice, category);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MenuCategory getCategory() {
        return category;
    }

    @Override
    public Set<MenuTag> getTags() {
        return tags;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double calculatePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + basePrice;
    }

    public static final class Builder {
        private final String name;
        private final double basePrice;
        private final MenuCategory category;
        private String description = "";
        private final Set<MenuTag> tags = EnumSet.noneOf(MenuTag.class);

        private Builder(String name, double basePrice, MenuCategory category) {
            this.name = Objects.requireNonNull(name);
            this.basePrice = basePrice;
            this.category = Objects.requireNonNull(category);
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder addTag(MenuTag tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder addTags(MenuTag... tags) {
            Collections.addAll(this.tags, tags);
            return this;
        }

        public MenuItem build() {
            if (tags.isEmpty()) {
                tags.add(MenuTag.VEGETARIAN);
            }
            return new SimpleMenuItem(this);
        }
    }
}

