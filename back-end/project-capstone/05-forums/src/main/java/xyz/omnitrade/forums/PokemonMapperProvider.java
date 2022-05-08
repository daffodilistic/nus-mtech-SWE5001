
package xyz.omnitrade.forums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Priority;

import io.helidon.dbclient.DbColumn;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;
import io.helidon.dbclient.spi.DbMapperProvider;

/**
 * Provider for Pokemon mappers.
 *
 * Pokémon, and Pokémon character names are trademarks of Nintendo.
 */
@Priority(1000)
public class PokemonMapperProvider implements DbMapperProvider {
    private static final PokemonMapper MAPPER = new PokemonMapper();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        return type.equals(Pokemon.class) ? Optional.of((DbMapper<T>) MAPPER) : Optional.empty();
    }

    /**
     * Maps database types to and from Pokemon class.
     */
    static class PokemonMapper implements DbMapper<Pokemon> {

        @Override
        public Pokemon read(DbRow row) {
            DbColumn id = row.column("id");
            DbColumn name = row.column("name");
            DbColumn type = row.column("id_type");
            return new Pokemon(id.as(Integer.class), name.as(String.class), type.as(Integer.class));
        }

        @Override
        public Map<String, Object> toNamedParameters(Pokemon value) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("id", value.getId());
            map.put("name", value.getName());
            map.put("idType", value.getIdType());
            return map;
        }

        @Override
        public List<Object> toIndexedParameters(Pokemon value) {
            List<Object> list = new ArrayList<>(3);
            list.add(value.getId());
            list.add(value.getName());
            list.add(value.getIdType());
            return list;
        }
    }
}
