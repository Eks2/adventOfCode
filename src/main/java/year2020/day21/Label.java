package year2020.day21;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Set;

@Value
@AllArgsConstructor
public class Label {
    private Set<String> allergens;
    private Set<String> ingredients;

}
