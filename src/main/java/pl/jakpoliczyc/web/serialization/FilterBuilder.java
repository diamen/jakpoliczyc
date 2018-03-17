package pl.jakpoliczyc.web.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

public class FilterBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterBuilder.class);
    private FilterPojo filter;

    public FilterBuilder() {
        this.filter = new FilterPojo();
    }

    private class FilterPojo {
        private String filterName;
        private Object objectToSerialize;
        private Set<String> excludedProperties;

        private String getFilterName() {
            return filterName;
        }

        private void setFilterName(String filterName) {
            this.filterName = filterName;
        }

        private Object getObjectToSerialize() {
            return objectToSerialize;
        }

        private void setObjectToSerialize(Object objectToSerialize) {
            this.objectToSerialize = objectToSerialize;
        }

        private Set<String> getExcludedProperties() {
            return excludedProperties;
        }

        private void setExcludedProperties(Set<String> excludedProperties) {
            this.excludedProperties = excludedProperties;
        }
    }

    public FilterBuilder filterName(final String filterName) {
        filter.setFilterName(filterName);
        return this;
    }

    public FilterBuilder objectToSerialize(final Object objectToSerialize) {
        filter.setObjectToSerialize(objectToSerialize);
        return this;
    }

    public FilterBuilder excludedProperties(final String... excludedProperties) {
        filter.setExcludedProperties(Sets.newHashSet(excludedProperties));
        return this;
    }

    public Optional<String> buildJson() {
        Preconditions.checkNotNull(filter.getFilterName(), "Fitler name has to be passed!");
        Preconditions.checkNotNull(filter.getObjectToSerialize(), "Object to serialize has to be passed!");

        final SimpleBeanPropertyFilter simpleBeanPropertyFilter = CollectionUtils.isEmpty(filter.getExcludedProperties()) ?
                SimpleBeanPropertyFilter.serializeAll() : SimpleBeanPropertyFilter.serializeAllExcept(filter.getExcludedProperties());

        final ObjectMapper mapper = new ObjectMapper();
        final FilterProvider filters = new SimpleFilterProvider()
                .addFilter(filter.getFilterName(), simpleBeanPropertyFilter);
        try {
            final String json = mapper.writer(filters).writeValueAsString(filter.getObjectToSerialize());
            return Optional.of(json);
        } catch (final JsonProcessingException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

}
